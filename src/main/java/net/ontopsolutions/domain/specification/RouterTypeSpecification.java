package net.ontopsolutions.domain.specification;

import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.specification.shared.AbstractSpecification;
import net.ontopsolutions.domain.vo.enumvo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
