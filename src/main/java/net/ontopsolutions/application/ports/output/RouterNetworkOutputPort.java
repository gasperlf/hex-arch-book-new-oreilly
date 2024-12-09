package net.ontopsolutions.application.ports.output;

import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.vo.id.RouterId;

public interface RouterNetworkOutputPort {

    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
