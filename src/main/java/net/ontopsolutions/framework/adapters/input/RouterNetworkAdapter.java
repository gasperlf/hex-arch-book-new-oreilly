package net.ontopsolutions.framework.adapters.input;

import net.ontopsolutions.application.usecases.RouterNetworkUseCase;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.vo.IP;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {

    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    protected Router addNetworkToRouter(Map<String, String> params) {

        var routerId = RouterId. withId(params.get("routerId"));
        var network = new Network(
                IP.fromAddress( params.get("address")),
                params.get("name"),
                Integer.parseInt(params.get("cidr")));

        return routerNetworkUseCase.addNetworkToRouter(routerId, network);

    }

    public abstract Router processRequest(Object requestParams);

    public void setRouterNetworkUseCase(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }
}
