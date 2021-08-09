package controller;

import model.Employee.Commissioned;
import model.Employee.Employee;
import model.Employee.Hourly;
import model.Employee.Salaried;
import model.Payment.PaymentEmployee;
import model.Payment.PaymentHistory;
import model.Payment.PaymentSchedule;
import model.Syndicate.EmployeeSyndicate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PaymentController {

    public void payRoll (Scanner in, ArrayList<PaymentEmployee> paymentEmployees, ArrayList<PaymentHistory> paymentHistories) {

        System.out.println("date (format YYYY-MM-d, ex.: 2021-08-24):");
        String dateString = in.next();

        LocalDate date = LocalDate.parse(dateString);

        boolean payRounded = false;

        for (PaymentEmployee paymentEmployee : paymentEmployees) {
            if (paymentEmployee.isPayTime(date)) {
                payRounded = true;

                Employee employee = paymentEmployee.getEmployee();
                PaymentHistory lastPayment = paymentEmployee.getLastPayment();

                Double salary = 0.0;
                if (employee.getClass().isAssignableFrom(Commissioned.class)) {
                    Commissioned commissioned = (Commissioned) employee;
                    salary += commissioned.getSalary() / paymentEmployee.getPaymentSchedule().getFrequency();
                    if (lastPayment == null) {
                        salary += commissioned.getCommissionsValues();
                    } else {
                        salary += commissioned.getCommissionsValues(lastPayment.getDate());
                    }

                } else if (employee.getClass().isAssignableFrom(Salaried.class)) {
                    salary = employee.getSalary();
                } else if (employee.getClass().isAssignableFrom(Hourly.class)) {
                    Hourly hourly = (Hourly) employee;
                    if (lastPayment == null) {
                        salary += hourly.getFullSalary();
                    } else {
                        salary += hourly.getFullSalary(lastPayment.getDate());
                    }
                }

                EmployeeSyndicate employeeSyndicate = employee.getEmployeeSyndicate();
                if (employeeSyndicate != null) {
                    if (lastPayment == null) {
                        salary -= employeeSyndicate.getAdditionalServiceTaxes();
                    } else {
                        salary -= employeeSyndicate.getAdditionalServiceTaxes(lastPayment.getDate());
                    }

                    if (paymentEmployee.getPaymentSchedule().getType() == 1) {
                        salary -= employeeSyndicate.getMonthlyTax();
                    } else {
                        salary -= employeeSyndicate.getMonthlyTax() / paymentEmployee.getPaymentSchedule().getFrequency();
                    }
                }

                PaymentHistory newPayment = new PaymentHistory(paymentEmployee, date, salary);

                paymentEmployee.setPaymentHistory(newPayment);
                paymentHistories.add(newPayment);
                System.out.println(newPayment);
            }
        }
        if (payRounded) {
            System.out.println("Finish");
        } else {
            System.out.println("Empty Payroll on the date");
        }
    }

    public static void printPaymentHistories (ArrayList<PaymentHistory> paymentHistories) {
        for (PaymentHistory paymentHistory: paymentHistories) {
            System.out.println(paymentHistory);
        }
    }

    public static PaymentSchedule getPaymentSchedule(int type, int frequency, int weekDay, ArrayList<PaymentSchedule> paymentSchedules) {
        for (PaymentSchedule paymentSchedule: paymentSchedules) {
            if (paymentSchedule.getType() == type &&
                    paymentSchedule.getFrequency() == frequency &&
                    paymentSchedule.getWeekDay() == weekDay) {
                return paymentSchedule;
            }
        }
        return null;
    }

    public static PaymentSchedule getPaymentSchedule(int type, int day, ArrayList<PaymentSchedule> paymentSchedules) {
        for (PaymentSchedule paymentSchedule: paymentSchedules) {
            if (paymentSchedule.getType() == type &&
                    paymentSchedule.getDay() == day) {
                return paymentSchedule;
            }
        }
        return null;
    }

    public void addPaymentSchedule (Scanner in, ArrayList<PaymentSchedule> paymentSchedules) {
        String schedule, scheduleArray[];
        System.out.println("Enter new Payment Schedule (ex: 'monthly $' -> last day of month, 'weekly 1 monday')");
        schedule = in.next();
        scheduleArray = schedule.split(" ");

        PaymentSchedule paymentSchedule = null;
        if (Objects.equals(scheduleArray[0], "monthly")) {
            int day;
            if (scheduleArray[1].charAt(0) == '$') {
                day = 0;
            } else {
                day = parseInt(scheduleArray[1]);
            }

            paymentSchedule = new PaymentSchedule(1, day);

        } else if (Objects.equals(scheduleArray[0], "weekly")) {
            int frequency, weekDay;

            frequency = parseInt(scheduleArray[1]);

            weekDay = DayOfWeek.valueOf(scheduleArray[2].toUpperCase()).getValue();

            paymentSchedule = new PaymentSchedule(2, frequency, weekDay);
        } else {
            System.out.println("incorrect type");
            return;
        }

        paymentSchedules.add(paymentSchedule);

        System.out.println("Successful added");
        System.out.println(paymentSchedule);

    }

    public void editEmployeePaymentSchedule (Scanner in, PaymentEmployee paymentEmployee, ArrayList<PaymentSchedule> paymentSchedules) {
        System.out.println(paymentSchedules);
        int type;
        System.out.println("Payment type (1 - monthly, 2 - weekly):");
        type = in.nextInt();

        PaymentSchedule paymentSchedule = null;
        if (type == 1) {
            int day;
            System.out.println("Payment day (0 for last month day)");
            day = in.nextInt();
            paymentSchedule = getPaymentSchedule(type, day, paymentSchedules);

        } else if (type == 2) {
            int frequency, weekDay;

            System.out.println("Frequency (1 - semanal, 2- bisemanal, 3 - trisemanal): ");
            frequency = in.nextInt();

            System.out.println("Weekday (1 - monday, ..., 5 - friday)");
            weekDay = in.nextInt();

            paymentSchedule = getPaymentSchedule(type, frequency, weekDay, paymentSchedules);
        } else {
            System.out.println("incorrect type, try again");
        }

        if (paymentSchedule == null) {
            System.out.println("Payment Schedule not found");
            return;
        }

        paymentEmployee.setPaymentSchedule(paymentSchedule);
        System.out.println("Successful edited");
        System.out.println(paymentSchedule);
    }









}
