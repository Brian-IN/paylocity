package pojos;

public class Employee {
    String id;
    String fistName;
    String lastName;
    String dependents;
    double salary;
    double grossPay;
    double benefitsCost;
    double netPlay;


    public void setId(String id) {
        this.id = id;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDependents(String dependents) {
        this.dependents = dependents;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setBenefitsCost(double benefitsCost) {
        this.benefitsCost = benefitsCost;
    }

    public void setNetPlay(double netPlay) {
        this.netPlay = netPlay;
    }

    public String getId() {
        return id;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDependents() {
        return dependents;
    }

    public double getSalary() {
        return salary;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getBenefitsCost() {
        return benefitsCost;
    }

    public double getNetPlay() {
        return netPlay;
    }


}
