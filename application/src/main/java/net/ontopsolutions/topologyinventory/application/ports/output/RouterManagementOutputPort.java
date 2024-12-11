package net.ontopsolutions.topologyinventory.application.ports.output;

import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.Id;

public interface RouterManagementOutputPort {

    Router retrieveRouter(Id id);

    Router persistRouter(Router router);
}
