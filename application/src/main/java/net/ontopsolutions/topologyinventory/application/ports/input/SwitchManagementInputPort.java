package net.ontopsolutions.topologyinventory.application.ports.input;

import lombok.NoArgsConstructor;
import net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.SwitchType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SwitchManagementInputPort implements SwitchManagementUseCase {

    @Inject
    private SwitchManagementOutputPort switchManagementOutputPort;

    @Override
    public Switch createSwitch(Vendor vendor, Model model, IP ip, Location location, SwitchType switchType) {
        return Switch.builder()
                .switchId(Id.withoutId())
                .vendor(vendor)
                .model(model)
                .ip(ip)
                .location(location)
                .switchType(switchType)
                .build();
    }

    @Override
    public Switch retrieveSwitch(Id id) {
        return switchManagementOutputPort.retrieveSwitch(id);
    }

    @Override
    public EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        edgeRouter.addSwitch(networkSwitch);
        return edgeRouter;
    }

    @Override
    public EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        edgeRouter.removeSwitch(networkSwitch);
        return edgeRouter;
    }

}
