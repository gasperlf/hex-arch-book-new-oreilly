package net.ontopsolutions.topologyinventory.framework.adapters.input.rest;


import io.smallrye.mutiny.Uni;
import net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
import net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.EdgeRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.framework.adapters.input.rest.request.aswitch.CreateSwitch;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;



@ApplicationScoped
@Path("/switch")
@Tag(name = "Switch Operations", description = "Operations for switch management")
public class SwitchManagementGenericAdapter {

    @Inject
    private  SwitchManagementUseCase switchManagementUseCase;
    @Inject
    private  RouterManagementUseCase routerManagementUseCase;

    /**
     * GET /switch/retrieve/{id}
     * */
    @GET
    @Path("/{id}")
    @Operation(operationId = "retrieveSwitch", description = "Retrieve a switch from an edge router")
    public Uni<Response> retrieveSwitch(@PathParam("id") String switchId) {
        return Uni.createFrom()
                .item(switchManagementUseCase.retrieveSwitch(Id.withId(switchId)))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * POST /switch/create
     * */
    @POST
    @Path("/create/{edgeRouterId}")
    @Operation(operationId = "createAndAddSwitchToEdgeRouter", description = "Create switch and add to an edge router")
    public Uni<Response> createAndAddSwitchToEdgeRouter(CreateSwitch createSwitch, @PathParam("edgeRouterId") String edgeRouterId) {

        Switch newSwitch = switchManagementUseCase.
                createSwitch(
                        createSwitch.getVendor(),
                        createSwitch.getModel(),
                        IP.fromAddress(createSwitch.getIp()),
                        createSwitch.getLocation(),
                        createSwitch.getSwitchType());

        Router edgeRouter = routerManagementUseCase.retrieveRouter(Id.withId(edgeRouterId));
        if(!edgeRouter.getRouterType().equals(RouterType.EDGE))
            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
        Router router = switchManagementUseCase.addSwitchToEdgeRouter(newSwitch, (EdgeRouter) edgeRouter);

        return Uni.createFrom()
                .item((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * POST /switch/remove
     * */
    @DELETE
    @Path("/{switchId}/from/{edgeRouterId}")
    @Operation(operationId = "removeSwitch", description = "Retrieve a router from the network inventory")
    public Uni<Response> removeSwitchFromEdgeRouter(@PathParam("switchId") String switchId, @PathParam("edgeRouterId") String edgeRouterId) {

        EdgeRouter edgeRouter = (EdgeRouter) routerManagementUseCase
                .retrieveRouter(Id.withId(edgeRouterId));
        Switch networkSwitch = edgeRouter.getSwitches().get(Id.withId(switchId));
        Router router = switchManagementUseCase
                .removeSwitchFromEdgeRouter(networkSwitch, edgeRouter);

        return Uni.createFrom()
                .item((EdgeRouter) routerManagementUseCase.persistRouter(router))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
}
