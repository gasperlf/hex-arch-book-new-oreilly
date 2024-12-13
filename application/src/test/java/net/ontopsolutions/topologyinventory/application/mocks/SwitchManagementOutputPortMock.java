package net.ontopsolutions.topologyinventory.application.mocks;

import io.quarkus.test.Mock;
import net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.Id;

@Mock
public class SwitchManagementOutputPortMock implements SwitchManagementOutputPort {
    @Override
    public Switch retrieveSwitch(Id id) {
        return null;
    }
}
