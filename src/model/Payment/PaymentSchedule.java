package model.Payment;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.UUID;

public class PaymentSchedule implements Serializable {
    private UUID id;
    private int type;   //1 - monthly, 2 - weekly
    private int day;    //to monthly type, 0 = last month day
    private int frequency;  //to weekly type, weeks numbers
    private int weekDay;    //for weekly type

    public PaymentSchedule(int type, int day) {
        this.type = type;
        this.day = day;
        this.id = UUID.randomUUID();
    }

    public PaymentSchedule(int type, int frequency, int weekDay) {
        this.type = type;
        this.frequency = frequency;
        this.weekDay = weekDay;
        this.id = UUID.randomUUID();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        String typeString = "";
        if (type == 1) {
            if (day == 0) {
                typeString = "monthly, day= last month day";
            } else {
                typeString = "monthly, day=" + day;
            }
        } else if (type == 2) {
            typeString = "weekly, frequency payment=" +
                    frequency + "week(s)" +
            ", week day payment=" + DayOfWeek.of(weekDay);

        }
        return "PaymentSchedule{" +
                "type=" + typeString +
                '}';
    }
}
