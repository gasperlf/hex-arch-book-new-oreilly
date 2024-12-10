package net.ontopsolutions.application.ports.input;

import net.ontopsolutions.application.ports.output.NotifyEventOutputPort;
import net.ontopsolutions.application.ports.output.RouterNetworkOutputPort;
import net.ontopsolutions.application.usecases.RouterNetworkUseCase;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.service.NetworkOperation;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.RouterId;

public class RouterNetworkInputPort  implements RouterNetworkUseCase {

    private RouterNetworkOutputPort routerNetworkOutputPort;
    private NotifyEventOutputPort notifyEventOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort, NotifyEventOutputPort notifyEventOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
        this.notifyEventOutputPort = notifyEventOutputPort;
    }

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        notifyEventOutputPort.sendEvent("Adding "+network.getName()+" network to router "+router.getId().getId());
        return createNetwork(router, network);
    }

    @Override
    public Router getRouter(RouterId routerId) {
        notifyEventOutputPort.sendEvent("Retrieving router ID "+routerId.getId());
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        try {
            var newRouter = NetworkOperation.createNewNetwork(router, network);
            return persistNetwork(router) ? newRouter : router;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
