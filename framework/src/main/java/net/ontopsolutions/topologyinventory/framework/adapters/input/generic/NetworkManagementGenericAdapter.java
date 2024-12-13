package net.ontopsolutions.topologyinventory.framework.adapters.input.generic;

import net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
import net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NetworkManagementGenericAdapter {

    @Inject
    private SwitchManagementUseCase switchManagementUseCase;

    @Inject
    private NetworkManagementUseCase networkManagementUseCase;

    /**
     * POST /network/add
     * */
    public Switch addNetworkToSwitch(Network network, Id switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.addNetworkToSwitch(network, networkSwitch);
    }

    /**
     * POST /network/remove
     * */
    public Switch removeNetworkFromSwitch(String networkName, Id switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch);
    }

}
