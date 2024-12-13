package net.ontopsolutions.topologyinventory.framework.adapters.input.rest;

import io.smallrye.mutiny.Uni;
import net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
import net.ontopsolutions.topologyinventory.application.usecases.SwitchManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Network;
import net.ontopsolutions.topologyinventory.framework.adapters.input.rest.request.network.AddNetwork;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/network")
@Tag(name = "Network Operations", description = "Network management operations")
public class NetworkManagementGenericAdapter {

    @Inject
    private SwitchManagementUseCase switchManagementUseCase;

    @Inject
    private NetworkManagementUseCase networkManagementUseCase;

    /**
     * POST /network/add
     * */
    @POST
    @Path("/add/{switchId}")
    @Operation(operationId = "addNetworkToSwitch", description = "Add network to a switch")
    public Uni<Response> addNetworkToSwitch(AddNetwork addNetwork, @PathParam("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        Network network = Network.builder()
                .networkAddress(IP.fromAddress(addNetwork.getNetworkAddress()))
                .networkName(addNetwork.getNetworkName())
                .networkCidr(addNetwork.getNetworkCidr())
                .build();

        return Uni.createFrom()
                .item(networkManagementUseCase.addNetworkToSwitch(network, networkSwitch))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    /**
     * DELETE /network/remove
     * */
    @DELETE
    @Path("/{networkName}/from/{switchId}")
    @Operation(operationId = "removeNetworkFromSwitch", description = "Remove network from a switch")
    public Uni<Response> removeNetworkFromSwitch(@PathParam("networkName") String networkName, @PathParam("switchId") String switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(Id.withId(switchId));

        return Uni.createFrom()
                .item(networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}
