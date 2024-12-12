module application {
    exports net.ontopsolutions.topologyinventory.application.ports.output;
    exports net.ontopsolutions.topologyinventory.application.usecases;
    exports net.ontopsolutions.topologyinventory.application.ports.input;
    requires domain;
    requires static lombok;
}