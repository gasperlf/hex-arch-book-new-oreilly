module application {
    requires domain;
    requires static lombok;

    exports net.ontopsolutions.topologyinventory.application.ports.input;
    exports net.ontopsolutions.topologyinventory.application.ports.output;
    exports net.ontopsolutions.topologyinventory.application.usecases;

    provides net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase
            with net.ontopsolutions.topologyinventory.application.ports.input.RouterManagementInputPort;
    provides net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase
            with net.ontopsolutions.topologyinventory.application.ports.input.SwitchManagementInputPort;
    provides net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase
            with net.ontopsolutions.topologyinventory.application.ports.input.NetworkManagementInputPort;
}