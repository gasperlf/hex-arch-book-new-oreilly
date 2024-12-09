package net.ontopsolutions.domain.policy;

import net.ontopsolutions.domain.entity.Event;
import net.ontopsolutions.domain.vo.Activity;
import net.ontopsolutions.domain.vo.id.EventId;
import net.ontopsolutions.domain.vo.enumvo.Protocol;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEventParser implements EventParser {

    @Override

    public Event parseEvent(String event) {

        final String regex = "(\\\"[^\\\"]+\\\")|\\S+";
        final Pattern pattern = Pattern.compile(regex,Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(event);

        var fields = new ArrayList<>();

        while (matcher.find()) {
            fields.add(matcher.group(0));
        }

        var timestamp = LocalDateTime.parse(matcher.group(0), formatter).atOffset(ZoneOffset.UTC);
        var id = EventId.of(matcher.group(1));
        var protocol = Protocol.valueOf(matcher.group(2));
        var activity = new Activity(matcher.group(3), matcher.group(5));

        return new Event(timestamp, id, protocol, activity);

    }

}
