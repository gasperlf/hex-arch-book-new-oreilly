package net.ontopsolutions.topologyinventory.framework.adapters.output.h2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data.SwitchData;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;

public class SwitchManagementH2Adapter implements SwitchManagementOutputPort {

    private static SwitchManagementH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    public SwitchManagementH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Switch retrieveSwitch(Id id) {
        var switchData = em.getReference(SwitchData.class, id.getId());
        return RouterH2Mapper.switchDataToDomain(switchData);
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em =  entityManagerFactory.createEntityManager();
        this.em = em;
    }
}
