import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class GetDate {
    public LocalDate thisWeeksDates(int x) {
        TemporalField fieldISO = WeekFields.of(Locale.UK).dayOfWeek();
        return LocalDate.now().with(fieldISO, x);
    }
}
