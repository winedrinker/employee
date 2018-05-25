package com.employee.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;

@RunWith(MockitoJUnitRunner.class)
public class NameDeserializerTest {

    private static  final  String FIRSTNAME = "George";
    private static  final  String SURNAME = "Smith";
    private static final String FULL_NAME_STRING = FIRSTNAME + " " + SURNAME;
    private NameDeserializer underTest;

    @Mock
    private JsonParser jsonParserMock;
    @Mock
    private ObjectCodec objectCodecMock;
    @Mock
    private JsonNode jsonNodeMock;

    @Before
    public void setup() {
        underTest = new NameDeserializer();
    }

    @Test
    public void deserializeShouldReturnTheExpectedFullName() throws IOException {
        // GIVEN
        final FullName expected = new FullName(FIRSTNAME, SURNAME);
        given(jsonParserMock.getCodec()).willReturn(objectCodecMock);
        given(objectCodecMock.readTree(jsonParserMock)).willReturn(jsonNodeMock);
        given(jsonNodeMock.asText()).willReturn(FULL_NAME_STRING);
        // WHEN
        final FullName actual = underTest.deserialize(jsonParserMock, null);
        // THEN
        assertEquals(expected, actual);
    }
}
