package net.ontopsolutions.framework.adapters.output.file;

import net.ontopsolutions.application.ports.output.RouterNetworkOutputPort;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.entity.Switch;
import net.ontopsolutions.domain.vo.IP;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.enumvo.RouterType;
import net.ontopsolutions.domain.vo.enumvo.SwitchType;
import net.ontopsolutions.domain.vo.id.RouterId;
import net.ontopsolutions.domain.vo.id.SwitchId;

import java.util.ArrayList;
import java.util.List;

public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileAdapter instance;

    private List<Router> routers = new ArrayList<>();

    @Override
    public Router fetchRouterById(RouterId routerId) {
        Router retrievedRouter = null;
        for(Router router: routers){
            if(router.getId().getId().equals(routerId.getId())){
                retrievedRouter = router;
                break;
            }
        }
        return retrievedRouter;
    }

    private void createSampleRouter() {
        var routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("10.0.0.0"), "HR", 8);
        var networkSwitch = new Switch(SwitchType.LAYER3, SwitchId.withoutId(), List.of(network), new IP("9.0.0.9"));
        var router = new Router(RouterType.EDGE, routerId, networkSwitch);
        routers.add(router);
    }

    @Override
    public boolean persistRouter(Router router){
        return this.routers.add(router);
    }

    private RouterNetworkFileAdapter(){
        createSampleRouter();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }
}
