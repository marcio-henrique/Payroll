package model.Employee;

import java.util.UUID;

public class Salaried extends Employee {

    public Salaried() {
    }

    public Salaried(UUID id, String name, Double salary, String address) {
        super(id, name, salary, address);
    }

    public Salaried(String name, Double salary, String address) {
        super(name, salary, address);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t" + "type: " + "Salaried" ;
    }

}
