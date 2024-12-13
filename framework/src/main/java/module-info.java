import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.RouterManagementMySQLAdapter;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.SwitchManagementMySQLAdapter;

module framework {
    requires domain;
    requires application;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.persistence;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires java.ws.rs;
    requires io.smallrye.mutiny;
    requires java.xml.bind;
    requires smallrye.common.annotation;
    requires com.fasterxml.jackson.annotation;
    requires microprofile.openapi.api;
    requires quarkus.hibernate.reactive.panache;
    requires java.transaction;
    requires io.vertx.core;
    requires microprofile.context.propagation.api;

    exports net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data;
    opens net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data;

    provides net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort
            with RouterManagementMySQLAdapter;
    provides net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort
            with SwitchManagementMySQLAdapter;

    uses net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
    uses net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
}