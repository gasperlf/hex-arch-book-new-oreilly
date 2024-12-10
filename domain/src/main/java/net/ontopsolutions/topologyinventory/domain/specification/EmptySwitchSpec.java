package net.ontopsolutions.topologyinventory.domain.specification;

import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.exception.GenericSpecificationException;
import net.ontopsolutions.topologyinventory.domain.specification.shared.AbstractSpecification;

public class EmptySwitchSpec extends AbstractSpecification<EdgeRouter> {

    @Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return edgeRouter.getSwitches()==null ||
                edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgeRouter edgeRouter) {
        if(!isSatisfiedBy(edgeRouter))
            throw new GenericSpecificationException("It isn't allowed to remove an edge router with a switch attached to it");
    }
}
