package model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Hourly extends Employee {
    private ArrayList<TimeCard> timeCards;

    public Hourly () {
    }
    public Hourly(UUID id, String name, Double salary, String address) {
        super(id, name, salary, address);
        this.timeCards = new ArrayList<TimeCard>();
    }

    public Hourly(String name, Double salary, String address) {
        super(name, salary, address);
        this.timeCards = new ArrayList<TimeCard>();
    }

    public ArrayList<TimeCard> getTimeCards() {
        return timeCards;
    }

    public void setTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    public Double getFullSalary(LocalDate date) {
        Double fullSalary = 0.0, hours, hourSalary = this.getSalary();

        for (TimeCard timeCard: this.timeCards) {
            if (timeCard.getDate().isAfter(date)) {
                hours = timeCard.getWorkedHours();

                if (hours > 8) {
                    fullSalary += 8 * hourSalary;
                    fullSalary += (hours - 8) * hourSalary * 1.5;
                } else {
                    fullSalary += hours * hourSalary;
                }
            }
        }

        return fullSalary;
    }

    public Double getFullSalary() {
        Double fullSalary = 0.0, hours, hourSalary = this.getSalary();

        for (TimeCard timeCard: this.timeCards) {
            hours = timeCard.getWorkedHours();

            if (hours > 8) {
                fullSalary += 8 * hourSalary;
                fullSalary += (hours - 8) * hourSalary * 1.5;
            } else {
                fullSalary += hours * hourSalary;
            }
        }

        return fullSalary;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t" + "type: Hourly" +
                "\n\t" + "Time Cards: " + this.timeCards;
    }
}
