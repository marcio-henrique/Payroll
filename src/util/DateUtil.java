package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return  day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public static boolean isLastWeekOfMonth(LocalDate date) {
        return date.getMonthValue() != date.plusWeeks(1).getMonthValue();
    }

    public static boolean isLastWorkDayOfMonth(LocalDate date) {
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            lastDayOfMonth = lastDayOfMonth.minusDays(1);
        } else if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            lastDayOfMonth = lastDayOfMonth.minusDays(2);
        }

        return date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth() == date.getDayOfMonth();
    }

    public static boolean isSameDay(LocalDate date, int day) {
        return date.getDayOfMonth() == day;
    }

    public static boolean isSameWeekDay(LocalDate date, int day) {
        return date.getDayOfWeek().getValue() == day;
    }


}
