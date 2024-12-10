package net.ontopsolutions.application.ports.output;

public interface NotifyEventOutputPort {

    void sendEvent(String Event);

    String getEvent();
}
