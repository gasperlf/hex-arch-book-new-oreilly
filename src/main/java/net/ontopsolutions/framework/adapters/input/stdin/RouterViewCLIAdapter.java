package net.ontopsolutions.framework.adapters.input.stdin;

import net.ontopsolutions.application.ports.input.RouterViewInputPort;
import net.ontopsolutions.application.usecases.RouterViewUseCase;
import net.ontopsolutions.domain.entity.Router;
import net.ontopsolutions.domain.vo.enumvo.RouterType;
import net.ontopsolutions.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {
    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(Router.filterRouterByType(RouterType.valueOf(type)));

    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}
