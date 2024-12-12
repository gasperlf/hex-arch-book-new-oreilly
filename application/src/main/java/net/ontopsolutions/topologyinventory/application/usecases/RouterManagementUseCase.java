package net.ontopsolutions.topologyinventory.application.usecases;

import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.CoreRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

public interface RouterManagementUseCase {

    Router createRouter(Id id,Vendor vendor, Model model, IP ip, Location location, RouterType routerType);

    Router removeRouter(Id id);

    CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter);

    Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter);

    Router retrieveRouter(Id id);

    Router persistRouter(Router router);

    void setOutputPort(RouterManagementOutputPort routerManagementOutputPort);
}
