import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataParser {

    public static List<WeatherInfo> parse(String jsonData) {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();

        JSONArray rootArray = new JSONArray(jsonData);
        JSONObject timeStringObject = rootArray.getJSONObject(0)
                .getJSONArray("timeSeries").getJSONObject(0);

        JSONArray timeDefinesArray = timeStringObject.getJSONArray("timeDefines");
        JSONArray weathersArray = timeStringObject.getJSONArray("areas")
                .getJSONObject(0).getJSONArray("weathers");

        for (int i = 0; i < timeDefinesArray.length(); i++) {
            LocalDateTime dateTime = LocalDateTime.parse(
                    timeDefinesArray.getString(i),
                    DateTimeFormatter.ISO_DATE_TIME);
            String weather = weathersArray.getString(i);
            weatherInfoList.add(new WeatherInfo(dateTime, weather));
        }

        return weatherInfoList;
    }

    public static class WeatherInfo {
        private final LocalDateTime dateTime;
        private final String weather;

        public WeatherInfo(LocalDateTime dateTime, String weather) {
            this.dateTime = dateTime;
            this.weather = weather;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public String getWeather() {
            return weather;
        }
    }
}
