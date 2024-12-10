package net.ontopsolutions.topologyinventory.domain.specification;

import net.ontopsolutions.topologyinventory.domain.entity.CoreRouter;
import net.ontopsolutions.topologyinventory.domain.exception.GenericSpecificationException;
import net.ontopsolutions.topologyinventory.domain.specification.shared.AbstractSpecification;

public class EmptyRouterSpec extends AbstractSpecification<CoreRouter> {

    @Override
    public boolean isSatisfiedBy(CoreRouter coreRouter) {
        return coreRouter.getRouters()==null||
                coreRouter.getRouters().isEmpty();
    }

    @Override
    public void check(CoreRouter coreRouter) {
        if(!isSatisfiedBy(coreRouter))
            throw new GenericSpecificationException("It isn't allowed to remove a core router with other routers attached to it");
    }

}
