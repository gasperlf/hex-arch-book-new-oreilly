package net.ontopsolutions.topologyinventory.domain.entity;

import lombok.Getter;
import lombok.Setter;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

import java.util.function.Predicate;

@Getter
public abstract class Router extends Equipment {

    protected final RouterType routerType;
    @Setter
    public Id parentRouterId;

    public static Predicate<Equipment> getRouterTypePredicate(RouterType routerType){
        return r -> ((Router)r).getRouterType().equals(routerType);
    }

    public static Predicate<Equipment> getModelPredicate(Model model){
        return r -> r.getModel().equals(model);
    }

    public static Predicate<Equipment> getCountryPredicate(Location location){
        return p -> p.location.getCountry().equals(location.getCountry());
    }

    public Router(Id id, Id parentRouterId, Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        super(id, vendor, model, ip, location);
        this.routerType = routerType;
        this.parentRouterId = parentRouterId;
    }

}
