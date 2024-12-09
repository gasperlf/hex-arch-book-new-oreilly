package net.ontopsolutions.domain.specification;

import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.specification.shared.AbstractSpecification;
import net.ontopsolutions.domain.vo.IP;
import net.ontopsolutions.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Router> {

    private IP address;
    private String name;
    private int cidr;

    public NetworkAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router!=null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Router router){
        var availability = true;
        for (Network network : router.retrieveNetworks()) {
            if(network.getAddress().equals(address) && network.getName().equals(name) && network.getCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }
}