package ua.ibis.nbuapi.xml;

import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import ua.ibis.nbuapi.util.Formatter;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Created by conti on 06.12.2016.
 */
public class DateJsonDeserializer implements JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsString(), Formatter.nbuFileDate);
    }
}
