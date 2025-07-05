package uz.pdp.weather_info_bot.payload.adaptor;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocaleDateTimeGsonAdapter extends TypeAdapter<LocalDateTime> {

    private DateTimeFormatter formatter = null;

    public LocaleDateTimeGsonAdapter(DateTimeFormatter formatter){
        this.formatter = formatter;
    }

    public LocaleDateTimeGsonAdapter() {
    }

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.toString());
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        String date = jsonReader.nextString();

        if (formatter != null){
            return LocalDateTime.parse(date,formatter);
        }

        return LocalDateTime.parse(date);
    }
}
