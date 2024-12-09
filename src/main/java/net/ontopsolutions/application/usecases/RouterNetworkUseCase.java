package net.ontopsolutions.application.usecases;

import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.vo.Network;
import net.ontopsolutions.domain.vo.id.RouterId;

public interface RouterNetworkUseCase {
    Router addNetworkToRouter(RouterId routerId, Network network);
}
