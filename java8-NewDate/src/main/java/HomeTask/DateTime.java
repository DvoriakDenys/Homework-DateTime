package HomeTask;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateTime {

    //Task 1
    public List<String> fridays13(){
        List<String> listOfMonth = new ArrayList<>();
        LocalDate localDate = LocalDate.of(2000, 1, 1);

        while (!localDate.equals(LocalDate.now())){

            if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY && localDate.getDayOfMonth() == 13){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
                String day = localDate.format(formatter);
                listOfMonth.add(day);
            }
            localDate = localDate.plusDays(1);
        }

        return listOfMonth;
    }

    //Task 2
    public List<YearMonth> endOnSundays(){
        List<YearMonth> listOfMonth = new ArrayList<>();
        LocalDate localDate = LocalDate.of(2000, 1, 1);

        while (!localDate.equals(LocalDate.now())){
            if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY &&
                    localDate.getMonth().maxLength() == localDate.getDayOfMonth()){
                listOfMonth.add(YearMonth.of(localDate.getYear(), localDate.getMonth()));
            }
            localDate = localDate.plusDays(1);
        }

        return listOfMonth;
    }

    //Task 3
    public List<Year> birthdaysOnSaturdays(LocalDate birthday){
        List<Year> listOfMonth = new ArrayList<>();
        LocalDate date = birthday;

        while (!birthday.equals(LocalDate.now())){

            if (birthday.getDayOfWeek() == DayOfWeek.SATURDAY && birthday.getMonth() == date.getMonth()
                    && birthday.getDayOfMonth() == date.getDayOfMonth()){

                listOfMonth.add(Year.of(birthday.getYear()));

            }
            birthday = birthday.plusDays(1);
        }

        return listOfMonth;
    }

    //Task 4
    public List<MonthDay> daysNotWith24Hours(Year year){
        List<MonthDay> listOfMonth = new ArrayList<>();
        int nextYear = year.getValue() + 1;
        ZoneId zoneId = ZoneId.of("Europe/Kiev");

        ZonedDateTime date1 = LocalDateTime.of(year.getValue(), Month.JANUARY,  1,   0,   0)
                .atZone(zoneId);

        while (date1.getYear() != nextYear){
            if (ChronoUnit.HOURS.between(date1, date1.plusDays(1)) != 24) {
                listOfMonth.add(MonthDay.of(date1.getMonth(), date1.getDayOfMonth()));
            }
            date1 = date1.plusDays(1);
        }

        return listOfMonth;
    }

    //Task 5 (1)
    public List<ZoneId> zonesAlwaysClockShift(List<ZoneId> zones){

        List<ZoneId> listOfZoneId = new ArrayList<>();

        for (ZoneId zoneId: zones){
            if (counter(zoneId) == 0){
                listOfZoneId.add(zoneId);
            }
        }
        return listOfZoneId;
    }

    //Task 5 (2)
    public List<ZoneId> zonesNeverClockShift(List<ZoneId> zones){

        List<ZoneId> listOfZoneId = new ArrayList<>();

        for (ZoneId zoneId: zones){
            ZonedDateTime date1 = LocalDateTime.of(1990, Month.JANUARY,  1,   0,   0)
                    .atZone(zoneId);
            int count = (LocalDate.now().getYear() - date1.getYear()) * 2;
            final int count2 = count;
            while (!date1.toLocalDate().equals(LocalDate.now())) {
                if (ChronoUnit.HOURS.between(date1, date1.plusDays(1)) != 24) {
                    count = count - 1;
                }
                date1 = date1.plusDays(1);
            }
            if (count == count2){
                listOfZoneId.add(zoneId);
            }
        }
        return listOfZoneId;
    }

    //Task 5 (3)
    public List<ZoneId> zonesChangedClockShiftRules(List<ZoneId> zones){

        List<ZoneId> listOfZoneId = new ArrayList<>();

        for (ZoneId zoneId: zones){
            if (counter(zoneId) != 0){
                listOfZoneId.add(zoneId);
            }
        }
        return listOfZoneId;
    }

    private static int counter (ZoneId zoneId){
        ZonedDateTime date1 = LocalDateTime.of(1990, Month.JANUARY,  1,   0,   0)
                .atZone(zoneId);
        int count = (LocalDate.now().getYear() - date1.getYear()) * 2;

        while (!date1.toLocalDate().equals(LocalDate.now())) {
            if (ChronoUnit.HOURS.between(date1, date1.plusDays(1)) != 24) {
                count = count - 1;
            }
            date1 = date1.plusDays(1);
        }
        return count;
    }
}
