package ad.ad_ud2_b.employee_crud.service.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public JsonElement serialize(final LocalDateTime date, final Type typeOfSrc,
                               final JsonSerializationContext context) {
    return new JsonPrimitive(date.format(formatter));
  }

  @Override
  public LocalDateTime deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    return LocalDateTime.parse(json.getAsString(), formatter);
  }
}