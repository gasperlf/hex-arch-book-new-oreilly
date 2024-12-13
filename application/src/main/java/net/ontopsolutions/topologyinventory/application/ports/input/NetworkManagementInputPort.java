package net.ontopsolutions.topologyinventory.application.ports.input;

import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.service.NetworkService;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Predicate;

@ApplicationScoped
public class NetworkManagementInputPort implements NetworkManagementUseCase {

    @Inject
    private RouterManagementOutputPort routerManagementOutputPort;

    @Override
    public Network createNetwork(IP networkAddress, String networkName, int networkCidr) {
        return Network.builder()
                .networkAddress(networkAddress)
                .networkName(networkName)
                .networkCidr(networkCidr)
                .build();
    }

    @Override
    public Switch addNetworkToSwitch(Network network, Switch networkSwitch) {
        networkSwitch.addNetworkToSwitch(network);
        return networkSwitch;
    }

    @Override
    public Switch removeNetworkFromSwitch(Network network, Switch networkSwitch) {
        networkSwitch.removeNetworkFromSwitch(network);
        return networkSwitch;
    }

    @Override
    public Switch removeNetworkFromSwitch(String networkName, Switch networkSwitch) {
        Id routerId = networkSwitch.getRouterId();
        Id switchId = networkSwitch.getId();

        EdgeRouter edgeRouter = (EdgeRouter) routerManagementOutputPort.retrieveRouter(routerId);
        Switch switchToRemoveNetwork = edgeRouter.getSwitches().get(switchId);

        Predicate<Network> networkPredicate = Network.getNetworkNamePredicate(networkName);

        var network = NetworkService. findNetwork(switchToRemoveNetwork.getSwitchNetworks(), networkPredicate);

        switchToRemoveNetwork.removeNetworkFromSwitch(network);
        routerManagementOutputPort.persistRouter(edgeRouter);

        return switchToRemoveNetwork.removeNetworkFromSwitch(network) ? switchToRemoveNetwork : null;
    }

}
