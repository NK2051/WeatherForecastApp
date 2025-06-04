import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherDisplay {

    public static void display(List<WeatherDataParser.WeatherInfo> weatherInfoList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (WeatherDataParser.WeatherInfo info : weatherInfoList) {
            String formattedDate = info.getDateTime().format(formatter);
            System.out.println(formattedDate + " " + info.getWeather());
        }
    }
}
