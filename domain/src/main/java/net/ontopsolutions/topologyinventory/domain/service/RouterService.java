package net.ontopsolutions.topologyinventory.domain.service;

import net.ontopsolutions.topologyinventory.domain.entity.Equipment;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.Id;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouterService {

    public static List<Router> filterAndRetrieveRouter(List<Router> routers, Predicate<Equipment> routerPredicate){
        return routers
                .stream()
                .filter(routerPredicate)
                .collect(Collectors.<Router>toList());
    }

    public static Router findById(Map<Id,Router> routers, Id id){
        return routers.get(id);
    }
}
