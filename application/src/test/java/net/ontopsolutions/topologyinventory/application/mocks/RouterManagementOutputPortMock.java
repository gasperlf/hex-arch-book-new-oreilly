package net.ontopsolutions.topologyinventory.application.mocks;

import io.quarkus.test.Mock;
import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.Id;

@Mock
public class RouterManagementOutputPortMock implements RouterManagementOutputPort {
    @Override
    public Router retrieveRouter(Id id) {
        return null;
    }

    @Override
    public Router removeRouter(Id id) {
        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        return null;
    }
}
