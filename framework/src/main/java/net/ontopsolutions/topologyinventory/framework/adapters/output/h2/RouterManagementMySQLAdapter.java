package net.ontopsolutions.topologyinventory.framework.adapters.output.h2;

import io.quarkus.hibernate.reactive.panache.Panache;
import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.mappers.RouterMapper;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.repository.RouterManagementRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RouterManagementMySQLAdapter implements RouterManagementOutputPort {

    @Inject
    RouterManagementRepository routerManagementRepository;


    @Override
    public Router retrieveRouter(Id id) {
        var routerData = routerManagementRepository.findById(id.getId()).subscribe().asCompletionStage().join();
        return RouterMapper.routerDataToDomain(routerData);
    }

    @Override
    public Router removeRouter(Id id) {

        var removed = routerManagementRepository.deleteById(id.getId()).subscribe().asCompletionStage().join();
        if(!removed){
            throw new InternalError();
        }
        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        var routerData = RouterMapper.routerDomainToData(router);
        Panache.withTransaction(()->routerManagementRepository.persist(routerData));
        return router;
    }


}
