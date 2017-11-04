package com.cotec.desafio.model.serializer;

import com.cotec.desafio.model.Organization;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

public class BidirectionalSerialazerGoalToOrganization extends JsonSerializer<Organization>{
    @Override
    public void serialize(Organization organization, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        Organization treatedObject;
        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            treatedObject = treatedObject(organization);
            objectMapper.writeValue(jsonGenerator, treatedObject);
        } catch (IOException | RuntimeException e) {
            new ObjectMapper().writeValue(jsonGenerator, "");
        }
    }

    private Organization treatedObject(Organization organization) {
        if(organization != null) {
            Organization organizationASerializar = new Organization();
            BeanUtils.copyProperties(organization, organizationASerializar, new String[]{"goals"});
            return organizationASerializar;
        }
        return organization;
    }
}
