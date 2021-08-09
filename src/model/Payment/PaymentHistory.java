package model.Payment;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentHistory implements Serializable {
    private PaymentEmployee paymentEmployee;
    private LocalDate date;
    private Double value;

    public PaymentHistory(PaymentEmployee paymentEmployee, LocalDate date, Double value) {
        this.paymentEmployee = paymentEmployee;
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentEmployee getPaymentEmployee() {
        return paymentEmployee;
    }

    public void setPaymentEmployee(PaymentEmployee paymentEmployee) {
        this.paymentEmployee = paymentEmployee;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PaymentHistory{" +
                "Employee id=" + paymentEmployee.getEmployee().getId() +
                ", date=" + date +
                ", value=" + value +
                paymentEmployee.getPaymentMethod() +
                "}\n";
    }
}
