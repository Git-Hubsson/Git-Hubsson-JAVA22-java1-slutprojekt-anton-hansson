import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class GetDate {
    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    LocalDate now = LocalDate.now();

    public LocalDate thisWeeksDates(int x) {
        TemporalField fieldISO = WeekFields.of(Locale.UK).dayOfWeek();
        return now.with(fieldISO, x);
    }


}
