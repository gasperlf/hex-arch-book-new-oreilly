package net.ontopsolutions.application.ports.input;

import net.ontopsolutions.application.ports.output.RouterNetworkOutputPort;
import net.ontopsolutions.application.usecases.RouterNetworkUseCase;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.service.NetworkOperation;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.RouterId;

public class RouterNetworkInputPort  implements RouterNetworkUseCase {

    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
