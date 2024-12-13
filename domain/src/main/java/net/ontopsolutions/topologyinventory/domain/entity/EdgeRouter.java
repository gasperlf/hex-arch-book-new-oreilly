package net.ontopsolutions.topologyinventory.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ontopsolutions.topologyinventory.domain.specification.EmptyNetworkSpec;
import net.ontopsolutions.topologyinventory.domain.specification.SameCountrySpec;
import net.ontopsolutions.topologyinventory.domain.specification.SameIpSpec;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

import java.util.Map;

@Getter
@ToString
public class EdgeRouter extends Router {

    @Setter
    private Map<Id, Switch> switches;

    @Builder
    public EdgeRouter(Id id, Id parentRouterId,  Vendor vendor, Model model, IP ip, Location location, RouterType routerType, Map<Id, Switch> switches) {
        super(id, parentRouterId,vendor, model, ip, location, routerType);
        this.switches = switches;
    }

    public void addSwitch(Switch anySwitch) {
        var sameCountryRouterSpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountryRouterSpec.check(anySwitch);
        sameIpSpec.check(anySwitch);

        this.switches.put(anySwitch.id,anySwitch);
    }

    public Switch removeSwitch(Switch anySwitch) {
        var emptyNetworkSpec = new EmptyNetworkSpec();
        emptyNetworkSpec.check(anySwitch);

        return this.switches.remove(anySwitch.id);
    }
}
