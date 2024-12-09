package net.ontopsolutions.framework.adapters.output.h2;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import net.ontopsolutions.application.ports.output.RouterNetworkOutputPort;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.vo.id.RouterId;
import net.ontopsolutions.framework.adapters.output.h2.data.RouterData;
import net.ontopsolutions.framework.adapters.output.h2.mappers.RouterH2Mapper;

public class RouterNetworkH2Adapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private RouterNetworkH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var routerData = em.getReference(RouterData.class, routerId.getId());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerData = RouterH2Mapper.toH2(router);
        em.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.em = em;
    }

    public static RouterNetworkH2Adapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2Adapter();
        }
        return instance;
    }
}
