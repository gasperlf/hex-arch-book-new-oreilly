module framework {
    requires domain;
    requires application;
    requires static lombok;
    requires org.eclipse.persistence.core;
    requires java.sql;
    requires jakarta.persistence;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;

    exports net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data;
    opens net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data;

    provides net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort
            with net.ontopsolutions.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
    provides net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort
            with net.ontopsolutions.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

    uses net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
    uses net.ontopsolutions.topologyinventory.application.ports.output.RouterManagementOutputPort;
    uses net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
}