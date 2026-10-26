import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/*
* overview of datetime api - some
*/




public class DateTimeAPI {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        System.out.println("current time : " + date);
        System.out.println("current year : " + date.getYear());
        System.out.println("current month : " + date.getMonth());
        System.out.println("current date : " + date.getDayOfMonth());
        System.out.println("current  day : " + date.getDayOfWeek());

        LocalDate customDate = LocalDate.of(2026, 12, 2);
        System.out.println("custom date : " + customDate);

        String DOB = "02-05-2003";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate formatDate = LocalDate.parse(DOB, dateTimeFormatter);
        System.out.println("formated date : " + formatDate);


        LocalDate start = LocalDate.of(2026, 1, 10);
        LocalDate end = LocalDate.of(2026, 1, 8);

        long days = ChronoUnit.DAYS.between(start, end);
        System.out.println("Days difference  : " + days);


        System.out.println(date.plusDays(20));
        System.out.println(date.plusMonths(1));
        System.out.println(date.minusDays(1));
        System.out.println(date.minusMonths(1));
    }
}