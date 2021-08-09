package model.Employee;

import model.Payment.PaymentEmployee;
import model.Syndicate.EmployeeSyndicate;

import java.io.Serializable;
import java.util.UUID;

public class Employee implements Serializable {
    private UUID id;
    private String name;
    private Double salary;
    private String address;
    private EmployeeSyndicate employeeSyndicate;
    private PaymentEmployee paymentEmployee;

    public Employee() {
    }

    public Employee(String name, Double salary, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.employeeSyndicate = null;
    }

    public Employee(UUID id, String name, Double salary, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.employeeSyndicate = null;
    }

    public Employee(String name, Double salary, String address, Double mounthlySyndicateTax) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.employeeSyndicate = new EmployeeSyndicate(mounthlySyndicateTax);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeSyndicate getEmployeeSyndicate() {
        return employeeSyndicate;
    }

    public void setEmployeeSyndicate(EmployeeSyndicate employeeSyndicate) {
        this.employeeSyndicate = employeeSyndicate;
    }

    public PaymentEmployee getPaymentEmployee() {
        return paymentEmployee;
    }

    public void setPaymentEmployee(PaymentEmployee paymentEmployee) {
        this.paymentEmployee = paymentEmployee;
    }

    @Override
    public String toString() {
        String string = "Employee" + ":\n\t" +
                "id: " + this.id + "\n\t" +
                "name: " + this.name + "\n\t" +
                "salary: " + this.salary + "\n\t" +
                "address: " + this.address + "\n\t" +
                "Sindicalized: ";

        if (this.employeeSyndicate == null) {
            string += "No";
        } else {
            string += "Yes\n\t" + this.employeeSyndicate.toString();
        }

        string += this.paymentEmployee;

        return string;
    }
}
