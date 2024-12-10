package net.ontopsolutions.topologyinventory.domain.specification;


import net.ontopsolutions.topologyinventory.domain.entity.Equipment;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.exception.GenericSpecificationException;
import net.ontopsolutions.topologyinventory.domain.specification.shared.AbstractSpecification;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

public class NetworkAvailabilitySpec extends AbstractSpecification<Equipment> {

    private IP address;
    private String name;
    private int cidr;

    public NetworkAvailabilitySpec(Network network){
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }

    @Override
    public boolean isSatisfiedBy(Equipment switchNetworks){
        return switchNetworks!=null && isNetworkAvailable(switchNetworks);
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("This network already exists");
    }

    private boolean isNetworkAvailable(Equipment switchNetworks){
        var availability = true;
        for (Network network : ((Switch)switchNetworks).getSwitchNetworks()) {
            if(network.getNetworkAddress().equals(address) &&
                    network.getNetworkName().equals(name) &&
                    network.getNetworkCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }
}