package net.ontopsolutions.topologyinventory.framework.adapters.output.h2.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data.RouterData;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class RouterManagementRepository implements PanacheRepositoryBase<RouterData, UUID> {

}
