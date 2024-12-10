package net.ontopsolutions.topologyinventory.domain.specification;

import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.exception.GenericSpecificationException;
import net.ontopsolutions.topologyinventory.domain.specification.shared.AbstractSpecification;

public class EmptyNetworkSpec extends AbstractSpecification<Switch> {

    @Override
    public boolean isSatisfiedBy(Switch switchNetwork) {
        return switchNetwork.getSwitchNetworks()==null||
                switchNetwork.getSwitchNetworks().isEmpty();
    }

    @Override
    public void check(Switch aSwitch) throws GenericSpecificationException {
        if(!isSatisfiedBy(aSwitch))
            throw new GenericSpecificationException("It's not possible to remove a switch with networks attached to it");
    }
}
