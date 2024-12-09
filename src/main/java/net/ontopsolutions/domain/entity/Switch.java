package net.ontopsolutions.domain.entity;

import net.ontopsolutions.domain.vo.IP;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.SwitchId;
import net.ontopsolutions.domain.vo.enumvo.SwitchType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Switch {

    private SwitchId switchId;
    private SwitchType switchType;
    private IP address;
    private List<Network> networks;

    public Switch (SwitchType switchType, SwitchId switchId, List<Network> networks, IP address){
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Router router) {
        List<Network> newNetworks = new ArrayList<>();
        router.retrieveNetworks().forEach(net ->{newNetworks.add(net);});
        newNetworks.add(network);
        return new Switch(this.switchType,this.switchId,  newNetworks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public SwitchId getSwitchId() {
        return this.switchId;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public IP getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "switchType=" + switchType +
                ", switchId=" + switchId +
                ", networks=" + networks +
                ", address=" + address +
                '}';
    }

}
