import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class GetDate {

    public LocalDate thisWeeksDates(int x) {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.UK).dayOfWeek();
        return now.with(fieldISO, x);
    }

    String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

}
