package basic;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    Instant utc = Instant.now(); // 2025-07-08T13:21:32.815193892Z

    ZonedDateTime currentSystem = ZonedDateTime.now(); // 2025-07-08T13:21:32.815193892Z[GMT]
    ZonedDateTime brasil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")); // 2025-07-08T10:21:32.883084292-03:00[America/Sao_Paulo]

    LocalDateTime localDateTime = LocalDateTime.now(); // 2025-07-08T13:21:32.883084292
    int year = localDateTime.getYear(); // 2025
    int month = localDateTime.getMonthValue(); // 7
    Month monthName = localDateTime.getMonth(); // JULY
    int day = localDateTime.getDayOfMonth(); // 8
    DayOfWeek dayName = localDateTime.getDayOfWeek(); // TUESDAY
    int hour = localDateTime.getHour(); // 13
    int minute = localDateTime.getMinute(); // 21
    int second = localDateTime.getSecond(); // 32
    ZonedDateTime localDateTimeToUtc = localDateTime.atZone(ZoneId.systemDefault()) // 2025-07-08T13:21:32.815193892Z
      .withZoneSameInstant(ZoneOffset.UTC);

    LocalDate localDate = LocalDate.now(); // 2025-07-08


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String brasilFormatted = brasil.format(formatter); // 08/07/2025 13:21:32
  }
}
