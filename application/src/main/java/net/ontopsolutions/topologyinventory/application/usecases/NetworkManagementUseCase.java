package net.ontopsolutions.topologyinventory.application.usecases;

import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

public interface NetworkManagementUseCase {

    Network createNetwork(IP networkAddress, String networkName, int networkCidr);

    Switch addNetworkToSwitch(Network network, Switch networkSwitch);

    Switch removeNetworkFromSwitch(Network network, Switch networkSwitch);

}