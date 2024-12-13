package net.ontopsolutions.topologyinventory.framework.adapters.input.rest.converter;

import net.ontopsolutions.topologyinventory.domain.vo.Id;

import javax.ws.rs.ext.ParamConverter;

public class IdParamConverter implements ParamConverter<Id> {

    @Override
    public Id fromString(String value){
       return Id.withId(value);
    }

    @Override
    public String toString(Id id) {
        return id.getId().toString();
    }
}
