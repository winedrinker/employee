package com.employee.domain;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class NameDeserializer extends StdDeserializer<FullName> {

    public NameDeserializer() {
        super(FullName.class);
    }

    @Override
    public FullName deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String fullName = node.asText();

        final List<String> fullNameList = Stream.of(fullName.split(" "))
            .map(elem -> new String(elem))
            .collect(Collectors.toList());

        return new FullName(fullNameList.get(0), fullNameList.get(1));
    }
}
