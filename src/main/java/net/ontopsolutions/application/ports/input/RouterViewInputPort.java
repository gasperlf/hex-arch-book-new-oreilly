package net.ontopsolutions.application.ports.input;

import net.ontopsolutions.application.ports.output.RouterViewOutputPort;
import net.ontopsolutions.application.usecases.RouterViewUseCase;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.service.RouterSearch;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private final RouterViewOutputPort routerListOutputPort;
    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return RouterSearch.retrieveRouter(routers, filter);
    }

}
