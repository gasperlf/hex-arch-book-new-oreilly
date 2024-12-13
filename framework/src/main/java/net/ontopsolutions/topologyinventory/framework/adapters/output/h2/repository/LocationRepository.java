package net.ontopsolutions.topologyinventory.framework.adapters.output.h2.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.data.LocationData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<LocationData> {

    public LocationData findByCity(String city){
        return (LocationData) find ("city", city).firstResult();

    }

    public LocationData findByState(String state){
        return (LocationData)find("state", state).firstResult();
    }

    public void deleteSomeCountry(){
        delete ("country", "SomeCountry");
    }

}
