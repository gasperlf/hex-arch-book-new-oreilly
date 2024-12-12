package net.ontopsolutions.topologyinventory.framework;


import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.SwitchType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;
import net.ontopsolutions.topologyinventory.framework.adapters.input.generic.SwitchManagementGenericAdapter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwitchTest extends FrameworkTestData {

    public SwitchTest(){
        loadPortsAndUseCases();
        loadData();
    }

    @Test
    @Order(1)
    public void retrieveSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb47");
        Switch networkSwitch = switchManagementGenericAdapter.retrieveSwitch(switchId);
        assertNotNull(networkSwitch);
    }

    @Disabled
    @Test
    @Order(2)
    public void createAndAddSwitchToEdgeRouter(){
        var expectedSwitchIP = "15.0.0.1";
        var id = Id.withId("b07f5187-2d82-4975-a14b-bdbad9a8ad46");
        EdgeRouter edgeRouter = switchManagementGenericAdapter.createAndAddSwitchToEdgeRouter(
                Vendor.HP,
                Model.XYZ0004,
                IP.fromAddress(expectedSwitchIP),
                locationA,
                SwitchType.LAYER3,
                id);
        String actualSwitchIP = edgeRouter.getSwitches()
                .entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .map(aSwitch -> aSwitch.getIp().getIpAddress())
                .filter(ipAddress -> ipAddress.equals(expectedSwitchIP))
                .findFirst()
                .get();
        assertEquals(expectedSwitchIP,actualSwitchIP);
    }

    @Test
    @Order(3)
     void removeSwitchFromEdgeRouter(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb47");
        Id edgerRouterId = Id.withId("b07f5187-2d82-4975-a14b-bdbad9a8ad46");
        EdgeRouter edgeRouter = switchManagementGenericAdapter.removeSwitchFromEdgeRouter(switchId, edgerRouterId);
        assertNull(edgeRouter.getSwitches().get(switchId));
    }
}
