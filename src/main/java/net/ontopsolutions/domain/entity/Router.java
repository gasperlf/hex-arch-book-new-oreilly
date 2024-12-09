package net.ontopsolutions.domain.entity;

import net.ontopsolutions.domain.vo.IP;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.RouterId;
import net.ontopsolutions.domain.vo.enumvo.RouterType;

import java.util.List;
import java.util.function.Predicate;

public class Router {

    private  RouterId id;
    private  RouterType type;
    private Switch networkSwitch;

    public Router(){

    }

    public Router(RouterType routerType, RouterId routerId){
        this.type = routerType;
        this.id = routerId;
    }

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.type = routerType;
        this.id = routerId;
        this.networkSwitch = networkSwitch;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType){
        return routerType.equals(RouterType.CORE) ? isCore() : isEdge();
    }

    private static Predicate<Router> isCore(){
        return p -> p.getRouterType() == RouterType.CORE;
    }

    private static Predicate<Router> isEdge(){
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    public void addNetworkToSwitch(Network network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr){
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks(){
        return networkSwitch.getNetworks();
    }

    public RouterId getId() {
        return id;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }

    public RouterType getRouterType() {
        return type;
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + type +
                ", id=" + id +
                ", networkSwitch=" + networkSwitch +
                '}';
    }

}
