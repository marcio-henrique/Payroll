package model.Company;

import model.Employee.Employee;
import model.Payment.PaymentEmployee;
import model.Payment.PaymentHistory;
import model.Payment.PaymentMethod;
import model.Payment.PaymentSchedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
    private ArrayList<PaymentSchedule> paymentSchedules;
    private ArrayList<Employee> employees;
    private ArrayList<PaymentEmployee> paymentEmployees;
    private ArrayList<PaymentHistory> paymentHistories;

    public Company() {
        this.paymentSchedules = new ArrayList<PaymentSchedule>();
        this.employees = new ArrayList<Employee>();
        this.paymentEmployees = new ArrayList<PaymentEmployee>();
        this.paymentHistories = new ArrayList<PaymentHistory>();

        this.initializePaymentSchedules();
    }

    private void initializePaymentSchedules() {
        PaymentSchedule hourlySchedule = new PaymentSchedule(2, 1, 5);
        PaymentSchedule salariedSchedule = new PaymentSchedule(1, 0);
        PaymentSchedule commissionedSchedule = new PaymentSchedule(2, 2, 5);

        paymentSchedules.add(hourlySchedule);
        paymentSchedules.add(salariedSchedule);
        paymentSchedules.add(commissionedSchedule);
    }

    public PaymentSchedule getPaymentSchedule(int type, int frequency, int weekDay) {
        for (PaymentSchedule paymentSchedule: this.paymentSchedules) {
            if (paymentSchedule.getType() == type &&
                    paymentSchedule.getFrequency() == frequency &&
                    paymentSchedule.getWeekDay() == weekDay) {
                return paymentSchedule;
            }
        }
        return  null;
    }

    public PaymentSchedule getPaymentSchedule(int type, int day) {
        for (PaymentSchedule paymentSchedule: this.paymentSchedules) {
            if (paymentSchedule.getType() == type &&
                    paymentSchedule.getDay() == day) {
                return paymentSchedule;
            }
        }
        return  null;
    }

    public ArrayList<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(ArrayList<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<PaymentEmployee> getPaymentEmployees() {
        return paymentEmployees;
    }

    public void setPaymentEmployees(ArrayList<PaymentEmployee> paymentEmployees) {
        this.paymentEmployees = paymentEmployees;
    }

    public ArrayList<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }
}
