package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Employee {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String id;
    String firstName;
    String lastName;
    String userName;
    int dependants;
    double salary;
    double grossPay;
    double benefitsCost;
    double netPlay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDependants(int dependants) {
        this.dependants = dependants;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDependants() {
        return dependants;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dependents=" + dependants +
                ", salary=" + salary +
                ", grossPay=" + grossPay +
                ", benefitsCost=" + benefitsCost +
                ", netPlay=" + netPlay +
                '}';
    }

    public String toJson() {
        try {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
