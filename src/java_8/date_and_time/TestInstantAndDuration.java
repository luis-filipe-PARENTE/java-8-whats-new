package java_8.date_and_time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestInstantAndDuration {

  public static void main(String[] args) throws InterruptedException {
    // testInstan();
    // testConceptLocalDate();
    // testConceptPeriod();
    // testConceptDateAdjuster();
    // testPeriodUntilVsPeriodBetween();
    // testLocalTime();
    testConceptZonedTime();
  }


  public static void testInstan() throws InterruptedException {
    Instant start = Instant.now();
    // Thread.sleep(2000);
    Instant end = Instant.now();

    Duration duration = Duration.between(start, end);
    System.out.println(duration.toSeconds());
  }

  public static void testConceptLocalDate() {
    LocalDate now = LocalDate.now();
    System.out.println(now.getDayOfMonth());

    LocalDate dateOfBirth = LocalDate.of(1994, Month.JULY, 24);
    System.out.println(dateOfBirth.getMonthValue());
  }

  public static void testConceptPeriod() {
    LocalDate dateOfBirth = LocalDate.of(1994, Month.JULY, 24);

    System.out.println(
        "================================  VERSION 1 ===================================================");
    // Version 1
    Period p = dateOfBirth.until(LocalDate.now());

    System.out.println("# years = " + p.getYears());
    System.out.println(
        "# years = " + p.get(ChronoUnit.MONTHS) + " and " + p.get(ChronoUnit.YEARS) + " months");

    System.out.println("# months = " + dateOfBirth.until(LocalDate.now(), ChronoUnit.MONTHS));
    System.out.println("# months = " + dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS));

    System.out.println(
        "================================  VERSION 2 ===================================================");
    // version 2
    Period p1 = Period.between(dateOfBirth, LocalDate.now());

    System.out.println("# years = " + p1.getYears());
    System.out.println(
        "# years = " + p1.get(ChronoUnit.MONTHS) + " and " + p1.get(ChronoUnit.YEARS) + " months");

    System.out.println("# months = " + dateOfBirth.until(LocalDate.now(), ChronoUnit.MONTHS));
    System.out.println("# months = " + dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS));

  }

  /**
   * Note: see all methods of TemporalAdjusters....
   */
  public static void testConceptDateAdjuster() {
    LocalDate now = LocalDate.now();
    LocalDate nextSunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    System.out.println(now.until(nextSunday, ChronoUnit.DAYS));

    System.out
        .println("First day of next month " + now.with(TemporalAdjusters.firstDayOfNextMonth()));

    System.out.println("First day of next month " + now
        .with(TemporalAdjusters.firstDayOfNextMonth()).until(LocalDate.now(), ChronoUnit.DAYS));
  }

  public static void testPeriodUntilVsPeriodBetween() {
    LocalDate dateOfBirth = LocalDate.of(1981, Month.NOVEMBER, 13);

    Period p1 = Period.between(dateOfBirth, LocalDate.now());
    Period p2 = dateOfBirth.until(LocalDate.now());
    dateOfBirth.until(LocalDate.now());

    System.out.println("# years = " + p1.get(ChronoUnit.YEARS) + " and " + p1.get(ChronoUnit.MONTHS)
        + " months" + " and " + p1.get(ChronoUnit.DAYS) + " days");


    System.out.println("# years = " + p2.get(ChronoUnit.YEARS) + " and " + p2.get(ChronoUnit.MONTHS)
        + " months" + " and " + p2.get(ChronoUnit.DAYS) + " days");


    System.out.println(dateOfBirth.until(LocalDate.now(), ChronoUnit.DAYS));

  }


  public static void testLocalTime() {

    LocalTime now = LocalTime.now();
    LocalTime time = LocalTime.of(10, 20);

    System.out.println(now);
    System.out.println(time);

    LocalTime bedTime = LocalTime.of(10, 20);
    LocalTime wkaUpTime = bedTime.plusHours(9L);

    System.out.println(bedTime);
    System.out.println(wkaUpTime);

  }

  public static void testConceptZonedTime() {
    Set<String> allZonesIds = ZoneId.getAvailableZoneIds();
    allZonesIds.stream().limit(10).forEach(System.out::println);
    System.out.println(allZonesIds.stream().count());

    ZoneId ukTZ = ZoneId.of("Portugal");
    System.out.println(ukTZ.getId());



    // @formatter:off
        // Create a zoned time
    ZonedDateTime zdt = ZonedDateTime.of(
                    1564, Month.APRIL.getValue(), 23,
                    10, 0, 0, 0,
                    ZoneId.of("Europe/London")
                 );
    
    System.out.println(zdt);
    
    // @formatter:on

    // @formatter:off
    ZonedDateTime currentMeeting = ZonedDateTime.of(
        LocalDate.of(2020, Month.OCTOBER, 6),
        LocalTime.of(10, 20),
        ZoneId.of("Europe/London")
    );
        
    ZonedDateTime nextMeeting = currentMeeting.plus(Period.ofMonths(1));
    
    ZonedDateTime nextMeetingUS = nextMeeting.withZoneSameInstant( ZoneId.of("US/Central"));
    System.out.println("currentMeeting " + currentMeeting);
    System.out.println("nextMeeting "  +  nextMeeting) ;
    System.out.println("nextMeetingUS "  +  nextMeetingUS);
    
    // @formatter:on


    // Formating Date

    System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(nextMeetingUS));
    System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(nextMeetingUS));

  }



}
