package net.ontopsolutions.topologyinventory.application;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.ontopsolutions.topologyinventory.application.usecases.NetworkManagementUseCase;
import net.ontopsolutions.topologyinventory.domain.service.NetworkService;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Network;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NetworkAdd extends ApplicationTestData {

    @Inject
    NetworkManagementUseCase networkManagementUseCase;

    public NetworkAdd(){
        loadData();
    }

    @Given("I have a network")
    public void i_have_a_network(){
        network = networkManagementUseCase.createNetwork(
                IP.fromAddress("10.0.0.1"),
                "Finance",
                8
        );
        assertNotNull(network);
    }

    @And("I have a switch to add a network")
    public void i_have_a_switch_to_add_a_network(){
        assertNotNull(networkSwitch);
    }

    @Then("I add the network to the switch")
    public void i_add_the_network_to_the_switch(){
        var predicate = Network.getNetworkNamePredicate("Finance");
        var networks = this.
                networkManagementUseCase.
                addNetworkToSwitch(network, networkSwitch).
                getSwitchNetworks();
        var network = NetworkService.findNetwork(networks, predicate).getNetworkName();

        assertEquals("Finance", network);
    }
}
