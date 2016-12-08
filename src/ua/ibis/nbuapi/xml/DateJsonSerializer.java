package ua.ibis.nbuapi.xml;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ua.ibis.nbuapi.util.Formatter;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Created by conti on 06.12.2016.
 */
public class DateJsonSerializer implements JsonSerializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDate.format(Formatter.nbuFileDate));
    }
}
