package uz.pdp.weather_info_bot.payload.adaptor;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.time.LocalDate;

public class LocaleDateGsonAdapter extends TypeAdapter<LocalDate> {

    private DateFormatter formatter = null;

    public LocaleDateGsonAdapter(DateFormatter formatter) {
        this.formatter = formatter;
    }

    public LocaleDateGsonAdapter() {
    }

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(localDate.toString());
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String date = jsonReader.nextString();
        return LocalDate.parse(date);

    }
}
