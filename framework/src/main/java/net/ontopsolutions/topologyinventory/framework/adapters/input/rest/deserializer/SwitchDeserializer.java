package net.ontopsolutions.topologyinventory.framework.adapters.input.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.IP;
import net.ontopsolutions.topologyinventory.domain.vo.Id;
import net.ontopsolutions.topologyinventory.domain.vo.Model;
import net.ontopsolutions.topologyinventory.domain.vo.Network;
import net.ontopsolutions.topologyinventory.domain.vo.SwitchType;
import net.ontopsolutions.topologyinventory.domain.vo.Vendor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.ontopsolutions.topologyinventory.framework.adapters.input.rest.deserializer.LocationDeserializer.getLocation;
import static net.ontopsolutions.topologyinventory.framework.adapters.input.rest.deserializer.NetworkDeserializer.getNetworkDeserialized;

public class SwitchDeserializer extends StdDeserializer<Switch> {

    public SwitchDeserializer() {
        this(null);
    }

    public SwitchDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Switch deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var id = node.get("id").get("id").asText();
        var routerId = node.get("routerId").get("id").asText();
        var vendor = node.get("vendor").asText();
        var model = node.get("model").asText();
        var ip = node.get("ip").get("ipAddress").asText();
        var location = node.get("location");
        var switchType = node.get("switchType").asText();
        var switchNetworksNode = node.get("switchNetworks");

        var networkSwitch = Switch.builder()
                .switchId(Id.withId(id))
                .vendor(Vendor.valueOf(vendor))
                .model(Model.valueOf(model))
                .ip(IP.fromAddress(ip))
                .location(getLocation(location))
                .switchType(SwitchType.valueOf(switchType))
                .routerId(Id.withId(routerId))
                .build();

        fetchChildNetworks(switchNetworksNode, networkSwitch);

        return networkSwitch;
    }

    private void fetchChildNetworks(JsonNode switchNetworksNode, Switch networkSwitch) throws IOException {
        List<Network> networks = new ArrayList<>();
        if (switchNetworksNode != null) {
            var childNetworks = switchNetworksNode.iterator();
            while (childNetworks.hasNext()) {
                var childNetwork = childNetworks.next();
                var fetchedNetwork = getNetworkDeserialized(childNetwork.toString());
                networks.add(fetchedNetwork);
            }
            networkSwitch.setSwitchNetworks(networks);
        }
    }

    public static Switch getSwitchDeserialized(String jsonStr) throws IOException {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addDeserializer(Switch.class, new SwitchDeserializer());
        mapper.registerModule(module);
        var networkSwitch = mapper.readValue(jsonStr, Switch.class);
        return networkSwitch;
    }
}
