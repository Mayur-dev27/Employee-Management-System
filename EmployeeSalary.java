package beans;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class EmployeeSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int salaryId;

    @Column(nullable = false)
    private double basicSalary;

    @Column(nullable = false)
    private double dearnessAllowance;

    @Column(nullable = false)
    private double houseRentAllow;

    @Column(nullable = false)
    private double yearlyBonus;

    public EmployeeSalary() {
    }
}
