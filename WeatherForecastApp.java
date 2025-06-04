import java.util.List;

/**
 * 天気予報アプリ-本体
 * このアプリケーションは、気象庁のWeb APIから大阪府の天気予報データを取得して表示します
 * 
 * @author n.nagamati
 * @version 1.0
 */
public class WeatherForecastApp {
    /**
     * 気象庁の天気予報APIのエンドポイントURL
     * 大阪府の天気予報データを提供します
     */
    private static final String TARGET_URL = "https://www.jma.go.jp/bosai/forecast/data/forecast/270000.json"; // 天気予報APIのURL(大阪府)

    /**
     * メイン処理: 天気予報の取得と表示を実行します
     * 
     * @param args コマンドライン引数(使用しません)
     */
    public static void main(String[] args) {
        try {
            // APIクライアントを使用してデータを取得
            WeatherApiClient apiClient = new WeatherApiClient(TARGET_URL);
            String jsonData = apiClient.fetchWeatherData();

            // データを解析
            List<WeatherDataParser.WeatherInfo> weatherInfoList = WeatherDataParser.parse(jsonData);

            // データを表示
            WeatherDisplay.display(weatherInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("エラーが発生しました: " + e.getMessage());
        }
    }
}