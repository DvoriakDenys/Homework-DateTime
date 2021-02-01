package test;

import HomeTask.DateTime;
import org.junit.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateTimeTest {

    DateTime dateTime = new DateTime();

    private static List<ZoneId> listOfZone () {
        List<ZoneId> zoneIds = new ArrayList<>();
        zoneIds.add(ZoneId.of("Europe/Warsaw"));
        zoneIds.add(ZoneId.of("America/Panama"));
        zoneIds.add(ZoneId.of("Europe/Copenhagen"));
        zoneIds.add(ZoneId.of("Africa/Gaborone"));
        zoneIds.add(ZoneId.of("Europe/Kiev"));
        return zoneIds;
    }

    @Test
    public void fridays13(){
        List<String> list = dateTime.fridays13();
        assertThat(list, hasItem("марта 2020"));
    }

    @Test
    public void endOnSundays(){
        List<YearMonth> listOfMonth = dateTime.endOnSundays();
        assertThat(listOfMonth, hasItem(YearMonth.of(2021, Month.JANUARY)));
    }

    @Test
    public void birthdaysOnSaturdays(){
        assertThat(dateTime.birthdaysOnSaturdays(LocalDate.of(2000, Month.FEBRUARY, 21)),
                hasItem(Year.of(2015)));
    }

    @Test
    public void daysNotWith24Hours (){
        assertThat(dateTime.daysNotWith24Hours(Year.of(2020)), hasItem(MonthDay.of(3, 29)));
    }

    @Test
    public void zonesAlwaysClockShift(){
        assertThat(dateTime.zonesAlwaysClockShift(listOfZone()), hasItem(ZoneId.of("Europe/Warsaw")));
    }

    @Test
    public void zonesNeverClockShift(){
        assertThat(dateTime.zonesNeverClockShift(listOfZone()), hasItem(ZoneId.of("Africa/Gaborone")));
    }

    @Test
    public void zonesChangedClockShiftRules(){
        assertThat(dateTime.zonesChangedClockShiftRules(listOfZone()), hasItem(ZoneId.of("Europe/Kiev")));
    }
}