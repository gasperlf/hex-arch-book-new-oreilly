package net.ontopsolutions.topologyinventory.framework.adapters.input.rest.outputAdapters;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.SwitchType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.SwitchManagementMySQLAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class SwitchManagementMySQLAdapterTest {

    @InjectMock
    SwitchManagementMySQLAdapter switchManagementMySQLAdapter;

    @Test
    public void testRetrieveSwitch() {
        Switch aSwitch = getSwitch();
        Mockito.when(switchManagementMySQLAdapter.retrieveSwitch(aSwitch.getId())).thenReturn(aSwitch);
        Switch retrievedSwitch = switchManagementMySQLAdapter.retrieveSwitch(aSwitch.getId());
        Assertions.assertSame(aSwitch, retrievedSwitch);

    }

    Switch getSwitch(){
        return Switch.builder()
                .switchId(Id.withoutId())
                .switchType(SwitchType.LAYER3)
                .switchNetworks(null)
                .ip(IP.fromAddress("10.0.0.1"))
                .model(Model.XYZ0004)
                .vendor(Vendor.CISCO)
                .location(null)
                .build();
    }
}
