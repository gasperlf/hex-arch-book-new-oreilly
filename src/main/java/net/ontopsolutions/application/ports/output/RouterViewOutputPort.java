package net.ontopsolutions.application.ports.output;

import net.ontopsolutions.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
