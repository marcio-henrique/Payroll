package model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Commissioned extends Employee {
    private Double commissionPercentage;
    private ArrayList<SaleResult> salesResults;

    public Commissioned() {
    }

    public Commissioned(UUID id, String name, Double salary, String address, Double commissionPercentage) {
        super(id, name, salary, address);
        this.commissionPercentage = commissionPercentage;
        this.salesResults = new ArrayList<SaleResult>();
    }

    public Commissioned(String name, Double salary, String address, Double commissionPercentage) {
        super(name, salary, address);
        this.commissionPercentage = commissionPercentage;
        this.salesResults = new ArrayList<SaleResult>();
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public ArrayList<SaleResult> getSalesResults() {
        return salesResults;
    }

    public void setSaleResult(SaleResult saleResult) {
        this.salesResults.add(saleResult);
    }

    public Double getCommissionsValues() {
        Double value = 0.0;

        for (SaleResult saleResult: this.salesResults) {
            value += saleResult.getValue() * this.commissionPercentage;
        }

        return value;
    }
    public Double getCommissionsValues(LocalDate date) {
        Double value = 0.0;

        for (SaleResult saleResult: this.salesResults) {
            if (saleResult.getDate().isAfter(date))
                value += saleResult.getValue() * this.commissionPercentage;
        }

        return value;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t" + "type: Commissioned" +
                "\n\t" + "Commission Percentage: " + this.commissionPercentage +
                "\n\t" + "Sales Results: " + this.salesResults;
    }
}
