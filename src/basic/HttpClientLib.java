package basic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings(value = "unused")
public class HttpClientLib {
  private static String BASE_API_URL = "https://api.cartola.globo.com";
  private static String ROUNDS_API_URL = "/rodadas";
  private static String MARKET_STATUS_API_URL = "/mercado/status";
  private static String TEAM_API_URL = "/time/id/%d/%d"; // /{time_id}/{rodada_id}
  private static String ATHLETES_API_URL = "/atletas/pontuados/%d"; // /{rodada_id}
  private static String LEAGUE_API_URL = "/auth/liga/%s"; // /{slug_name}
  private static String ATHLETE_SCORE_API_URL = "/auth/mercado/atleta/%d/pontuacao"; // /{rodada_id}
  private static String GLBID = "19ad1b80d012ce34c49f48af76019e7706c78756279414a707630706d535833316c2d685668432d4e615f70316f34484f61324767416c644f5632637544556b54514e7633745f33594c61694138587947504f4c70462d7531544d585246716b765230743075513d3d3a303a75313179717867756379616b7274657633683565";

  public static void main(String[] args) {
    HttpClient httpClient = HttpClient.newHttpClient();

    HttpRequest httpRequest = HttpRequest.newBuilder()
      .uri(URI.create(BASE_API_URL + ROUNDS_API_URL)) // Vegetti 99198
      .headers(
        "Accept", "application/json",
        "Accept-language", "pt-BR,pt;q=0.9",
        "User-agent", "Edg/138.0.0.0",
        "X-GLB-Token", GLBID
      )
      .GET()
      .build();

    try {
      HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      if (httpResponse.statusCode() == 200) {
        String content = httpResponse.body();
        System.out.println("Sim" + content);

        // Cambalacho, nem existe fazer isso... Foi preguiça de transformar em JSON
        for (String line : content.replace("[", "").replace("]", "").split("(?<=}\\s*),(?=\\s*\\{)")) {
          System.out.println(line);
        }
      } else {
        System.out.println(httpResponse.statusCode());
      }

    } catch (InterruptedException | IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
