package com.hc.sap.anywhere.api.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;


public class LanguageCodeDeserializer extends JsonDeserializer<LanguageCode> {

    @Override
    public LanguageCode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return LanguageCode.fromCode(jsonParser.getValueAsString());
    }
}
