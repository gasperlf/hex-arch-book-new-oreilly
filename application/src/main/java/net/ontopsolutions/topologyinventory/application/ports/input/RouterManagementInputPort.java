package net.ontopsolutions.topologyinventory.application.ports.input;

import lombok.NoArgsConstructor;
import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.CoreRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.entity.factory.RouterFactory;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

@NoArgsConstructor
public class RouterManagementInputPort implements RouterManagementUseCase {

    RouterManagementOutputPort routerManagementOutputPort;

    public RouterManagementInputPort(RouterManagementOutputPort routerManagementOutputPort) {
        this.routerManagementOutputPort = routerManagementOutputPort;
    }

    @Override
    public Router createRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        return RouterFactory.getRouter(id, vendor, model, ip, location, routerType);
    }

    @Override
    public Router removeRouter(Id id) {
        return routerManagementOutputPort.removeRouter(id);
    }

    @Override
    public CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter) {
        var addRouter = coreRouter.addRouter(router);
        return addRouter;
    }

    @Override
    public Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter) {
        var removeRouter = coreRouter.removeRouter(router);
        return removeRouter;
    }

    @Override
    public Router retrieveRouter(Id id) {
        return routerManagementOutputPort.retrieveRouter(id);
    }

    @Override
    public Router persistRouter(Router router) {
        return routerManagementOutputPort.persistRouter(router);
    }

    @Override
    public void setOutputPort(RouterManagementOutputPort routerManagementOutputPort) {
        this.routerManagementOutputPort = routerManagementOutputPort;
    }
}
