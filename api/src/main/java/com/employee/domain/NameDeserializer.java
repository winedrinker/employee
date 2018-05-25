package com.employee.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Custom deserializer to parse full name string node to the {@link FullName} class.
 */
public class NameDeserializer extends StdDeserializer<FullName> {

    public static final int FIRSTNAME_POS = 0;
    public static final int SURNAME_POS = 1;

    public NameDeserializer() {
        super(FullName.class);
    }

    @Override
    public FullName deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String fullName = node.asText();

        final String[] fullNameList = fullName.split(" ");
        return new FullName(fullNameList[FIRSTNAME_POS], fullNameList[SURNAME_POS]);
    }
}
