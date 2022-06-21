package com.ivscheianu.stockmanagement.user;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class UserDTOSerializer implements JsonbSerializer<UserDTO> {

    private static final String ID = "id";
    private static final String USER_NAME = "userName";

    @Override
    public void serialize(final UserDTO user, final JsonGenerator jsonGenerator, final SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        jsonGenerator.write(ID, user.getId());
        jsonGenerator.write(USER_NAME, user.getUserName());
        jsonGenerator.writeEnd();
    }
}
