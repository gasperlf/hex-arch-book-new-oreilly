package net.ontopsolutions.topologyinventory.framework.adapters.input.rest;

import io.smallrye.mutiny.Uni;
import net.ontopsolutions.topologyinventory.application.usecases.RouterManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.CoreRouter;
import net.ontopsolutions.topologyinventory.domain.entity.Router;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Location;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.RouterType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;
import net.ontopsolutions.topologyinventory.framework.adapters.input.rest.request.router.AddRouter;
import net.ontopsolutions.topologyinventory.framework.adapters.input.rest.request.router.CreateRouter;
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

@Path("/router")
@ApplicationScoped
@Tag(name = "Router Operations", description = "Router management operations")
public class RouterManagementGenericAdapter {

    @Inject
    private  RouterManagementUseCase routerManagementUseCase;

    /**
     * GET /router/retrieve/{id}
     * */
    @GET
    @Path("/{id}")
    @Operation(operationId = "retrieveRouter", description = "Retrieve a router from the network inventory")
    public Uni<Response> retrieveRouter(@PathParam("id") String id){
        return Uni.createFrom()
                .item(routerManagementUseCase.retrieveRouter(Id.withId(id)))
                .onItem()
                .transform(f -> f !=null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build)
                ;
    }

    /**
     * DELETE /router/{id}
     * */
    @DELETE
    @Path("/{id}")
    @Operation(operationId = "removeRouter", description = "Remove a router from the network inventory")
    public Uni<Response> removeRouter(@PathParam("id") String id){
        return Uni.createFrom()
                .item(routerManagementUseCase.removeRouter(Id.withId(id)))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * POST /router/create
     * */
    @POST
    @Path("/")
    @Operation(operationId = "createRouter", description = "Create and persist a new router on the network inventory")
    public Uni<Response> createRouter(CreateRouter createRouter) {
        var router = routerManagementUseCase.createRouter(null, createRouter.getVendor(),
                createRouter.getModel(),
                IP.fromAddress(createRouter.getIp()),
                createRouter.getLocation(),
                createRouter.getRouterType());

        return Uni.createFrom()
                .item(routerManagementUseCase.persistRouter(router))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * POST /router/add
     * */
    @POST
    @Path("/add")
    @Operation(operationId = "addRouterToCoreRouter", description = "Add a router into a core router")
    public Uni<Response> addRouterToCoreRouter(AddRouter addRouter) {
        Router router = routerManagementUseCase.retrieveRouter(Id.withId(addRouter.getRouterId()));

        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(Id.withId(addRouter.getCoreRouterId()));

        return Uni.createFrom()
                .item(routerManagementUseCase.
                        addRouterToCoreRouter(router, coreRouter))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * POST /router/remove
     * */
    @DELETE
    @Path("/{routerId}/from/{coreRouterId}")
    @Operation(operationId = "removeRouterFromCoreRouter", description = "Remove a router from a core router")
    public Uni<Response> removeRouterFromCoreRouter(@PathParam("routerId") String routerId, @PathParam("coreRouterId") String coreRouterId) {

        Router router = routerManagementUseCase.retrieveRouter(Id.withId(routerId));
        CoreRouter coreRouter = (CoreRouter) routerManagementUseCase.retrieveRouter(Id.withId(coreRouterId));

        return Uni.createFrom()
                .item(routerManagementUseCase.
                        removeRouterFromCoreRouter(router, coreRouter))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
}
