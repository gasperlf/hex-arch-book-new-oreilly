package net.ontopsolutions.topologyinventory.application.ports.input;

import lombok.NoArgsConstructor;
import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.service.NetworkService;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

import java.util.function.Predicate;

@NoArgsConstructor
public class NetworkManagementInputPort implements NetworkManagementUseCase {

    RouterManagementOutputPort routerManagementOutputPort;
    public NetworkManagementInputPort(RouterManagementOutputPort routerNetworkOutputPort){
        this.routerManagementOutputPort = routerNetworkOutputPort;
    }

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

    @Override
    public void setOutputPort(RouterManagementOutputPort routerNetworkOutputPort) {
        this.routerManagementOutputPort = routerNetworkOutputPort;
    }
}
