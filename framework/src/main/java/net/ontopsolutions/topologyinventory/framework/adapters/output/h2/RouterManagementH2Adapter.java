package net.ontopsolutions.topologyinventory.framework.adapters.output.h2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data.RouterData;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;

public class RouterManagementH2Adapter implements RouterManagementOutputPort {

    private static RouterManagementH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    public RouterManagementH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Router retrieveRouter(Id id) {
        var routerData = em.getReference(RouterData.class, id.getId());
        return RouterH2Mapper.routerDataToDomain(routerData);
    }

    @Override
    public Router removeRouter(Id id) {

        var routerData = em.getReference(RouterData.class, id.getId());
        em.remove(routerData);
        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        var routerData = RouterH2Mapper. routerDomainToData(router);
        em.persist(routerData);
        return router;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.em = em;
    }
}
