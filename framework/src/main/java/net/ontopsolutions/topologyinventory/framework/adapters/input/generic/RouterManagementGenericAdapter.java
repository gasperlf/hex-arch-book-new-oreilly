package net.ontopsolutions.topologyinventory.framework.adapters.input.generic;

import net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.CoreRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;


public class RouterManagementGenericAdapter {

    private  RouterManagementUseCase routerManagementUseCase;

    public RouterManagementGenericAdapter(RouterManagementUseCase routerManagementUseCase){
        this.routerManagementUseCase = routerManagementUseCase;
    }

    /**
     * GET /router/retrieve/{id}
     * */
    public Router retrieveRouter(Id id){
        return routerManagementUseCase.retrieveRouter(id);
    }

    /**
     * GET /router/remove/{id}
     * */
    public Router removeRouter(Id id){
        return routerManagementUseCase.removeRouter(id);
    }

    /**
     * POST /router/create
     * */
    public Router createRouter(Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        var router = routerManagementUseCase.createRouter(null, vendor, model,ip, location, routerType);
        return routerManagementUseCase.persistRouter(router);
    }

    /**
     * POST /router/add
     * */
    public Router addRouterToCoreRouter(Id routerId, Id coreRouterId){
        Router router = routerManagementUseCase.retrieveRouter(routerId);
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(coreRouterId);
        return routerManagementUseCase.
                addRouterToCoreRouter(router, coreRouter);
    }

    /**
     * POST /router/remove
     * */
    public Router removeRouterFromCoreRouter(Id routerId, Id coreRouterId){
        Router router = routerManagementUseCase.retrieveRouter(routerId);
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(coreRouterId);
        return routerManagementUseCase.
                removeRouterFromCoreRouter(router, coreRouter);
    }
}
