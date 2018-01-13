package com.hc.sap.anywhere.api.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;


public class LanguageCodeSerializer extends JsonSerializer<LanguageCode> {

    @Override
    public void serialize(LanguageCode languageCode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(languageCode.getCode());
    }
}
