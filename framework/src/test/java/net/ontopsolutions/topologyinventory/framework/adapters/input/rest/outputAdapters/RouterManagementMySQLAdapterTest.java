package net.ontopsolutions.topologyinventory.framework.adapters.input.rest.outputAdapters;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.entity.factory.RouterFactory;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;
import net.ontopsolutions.topologyinventory.framework.adapters.input.rest.RouterManagementAdapterTest;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.RouterManagementMySQLAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class RouterManagementMySQLAdapterTest {

    @InjectMock
    RouterManagementMySQLAdapter routerManagementMySQLAdapter;

    @Test
    public void testRetrieveRouter() {
        Router router = getRouter();
        Mockito.when(routerManagementMySQLAdapter.retrieveRouter(router.getId())).thenReturn(router);
        Router retrievedRouter = routerManagementMySQLAdapter.retrieveRouter(router.getId());
        Assertions.assertSame(router, retrievedRouter);
    }

    @Test
    public void testRemoveRouter() {
        Router router = getRouter();
        Mockito.when(routerManagementMySQLAdapter.removeRouter(router.getId())).thenReturn(router);
        Router removedRouter = routerManagementMySQLAdapter.removeRouter(router.getId());
        Assertions.assertSame(router, removedRouter);
    }

    @Test
    public void testPersistRouter() {
        Router router = getRouter();
        Mockito.when(routerManagementMySQLAdapter.persistRouter(router)).thenReturn(router);
        Router persistedRouter = routerManagementMySQLAdapter.persistRouter(router);
        Assertions.assertSame(router, persistedRouter);
    }

    private Router getRouter(){
        return RouterFactory.getRouter(
                Id.withoutId(),
                Vendor.CISCO,
                Model.XYZ0004,
                IP.fromAddress("10.0.0.1"),
                RouterManagementAdapterTest.createLocation("US"),
                RouterType.EDGE);
    }
}