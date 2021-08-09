package model.Payment;

import model.Employee.Employee;
import util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PaymentEmployee implements Serializable {
    private Employee employee;
    private PaymentMethod paymentMethod;
    private final ArrayList<PaymentHistory> paymentHistories;
    private PaymentSchedule paymentSchedule;

    public PaymentEmployee(Employee employee, PaymentMethod paymentMethod, PaymentSchedule paymentSchedule) {
        this.employee = employee;
        this.paymentMethod = paymentMethod;
        this.paymentSchedule = paymentSchedule;
        this.paymentHistories = new ArrayList<PaymentHistory>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ArrayList<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistory(PaymentHistory paymentHistory) {
        this.paymentHistories.add(paymentHistory);
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentHistory getLastPayment() {
        int i = 0;
        PaymentHistory lastPayment = null;
        for (PaymentHistory payment: this.paymentHistories) {
            if (i == 0) {
                lastPayment = payment;
                i = 1;
            } else if (payment.getDate().isAfter(lastPayment.getDate())) {
                lastPayment = payment;
            }
        }

        return lastPayment;
    }

    public boolean isPayTime (LocalDate date) {
        if (DateUtil.isWeekend(date)) {
            System.out.println("Weekend");
            return false;
        }

        PaymentHistory lastPayment = this.getLastPayment();

        if (lastPayment != null) {
            if(lastPayment.getDate().equals(date) || lastPayment.getDate().isAfter(date)) {
                return false;
            }

        }


        int paymentType;
        paymentType= this.paymentSchedule.getType();

        if (paymentType == 1) /*monthly*/{
            int paymentDay = this.paymentSchedule.getDay();
            if (paymentDay == 0)/*last month day*/{
                return DateUtil.isLastWorkDayOfMonth(date);

            } else return DateUtil.isSameDay(date, paymentDay);

        } else if (paymentType == 2)/*weekly*/ {
            if (lastPayment != null) {
                return (DateUtil.isSameWeekDay(date, this.getPaymentSchedule().getWeekDay())) &&
                        ChronoUnit.WEEKS.between(lastPayment.getDate(), date) >= paymentSchedule.getFrequency();
            }
            return (DateUtil.isSameWeekDay(date, this.getPaymentSchedule().getWeekDay()));
        }

        return false;
    }

    @Override
    public String toString() {
        return "\n\tPayment Employee:" +
                "\n\t\t" + paymentMethod +
                "\n\t\t" + paymentSchedule +
                '}';
    }
}
