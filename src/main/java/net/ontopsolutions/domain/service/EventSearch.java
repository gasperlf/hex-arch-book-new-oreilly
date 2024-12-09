package net.ontopsolutions.domain.service;

import net.ontopsolutions.domain.entity.Event;
import net.ontopsolutions.domain.vo.enumvo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType){

        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.forEach(event -> parsedEvents.add(Event.parsedEvent(event, policyType)));
        return parsedEvents;

    }

}
