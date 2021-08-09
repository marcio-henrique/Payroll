package model.Syndicate;

import model.Employee.TimeCard;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class EmployeeSyndicate implements Serializable {
    private UUID employeeSyndicateId;
    private Double monthlyTax;
    private ArrayList<AdditionalServiceTax> additionalServicesTaxes;

    public EmployeeSyndicate(Double monthlyTax) {
        this.employeeSyndicateId = UUID.randomUUID();
        this.monthlyTax = monthlyTax;
        this.additionalServicesTaxes = new ArrayList<AdditionalServiceTax>();
    }

    public UUID getEmployeeSyndicateId() {
        return employeeSyndicateId;
    }

    public void setEmployeeSyndicateId(UUID employeeSyndicateId) {
        this.employeeSyndicateId = employeeSyndicateId;
    }

    public Double getMonthlyTax() {
        return monthlyTax;
    }

    public void setMonthlyTax(Double monthlyTax) {
        this.monthlyTax = monthlyTax;
    }

    public ArrayList<AdditionalServiceTax> getAdditionalServicesTaxes() {
        return additionalServicesTaxes;
    }
    public void setAdditionalServiceTax(AdditionalServiceTax additionalServiceTax) {
        this.additionalServicesTaxes.add(additionalServiceTax);
    }

    public Double getAdditionalServiceTaxes(LocalDate dateTime) {
        Double taxesValue = 0.0;
        for (AdditionalServiceTax tax: this.additionalServicesTaxes) {
            if (tax.getDate().isAfter(dateTime)) {
                taxesValue += tax.getValue();
            }
        }

        return taxesValue;
    }

    public Double getAdditionalServiceTaxes() {
        Double taxesValue = 0.0;
        for (AdditionalServiceTax tax: this.additionalServicesTaxes) {
                taxesValue += tax.getValue();
        }

        return taxesValue;
    }

    @Override
    public String toString() {
        return "\tEmployeeSyndicate:" +
                "\n\t\temployeeSyndicateId=" + employeeSyndicateId +
                "\n\t\tmonthlyTax=" + monthlyTax +
                "\n\t\tAdditional Service Taxes:" + additionalServicesTaxes;
    }
}
