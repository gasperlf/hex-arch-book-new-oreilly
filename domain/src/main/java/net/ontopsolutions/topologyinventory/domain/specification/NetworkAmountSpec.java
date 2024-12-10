package net.ontopsolutions.topologyinventory.domain.specification;


import net.ontopsolutions.topologyinventory.domain.entity.Equipment;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.exception.GenericSpecificationException;
import net.ontopsolutions.topologyinventory.domain.specification.shared.AbstractSpecification;

public class NetworkAmountSpec extends AbstractSpecification<Equipment> {

    public static final int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Equipment switchNetwork) {
        return ((Switch)switchNetwork).getSwitchNetworks().size()
                <=MAXIMUM_ALLOWED_NETWORKS;
    }
    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("The max number of networks is "+ NetworkAmountSpec.MAXIMUM_ALLOWED_NETWORKS);
    }
}
