package beans;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    @Column(nullable = false)
    private Date joiningDate;

    @OneToOne
    private EmployeeName employeeName;

    @OneToOne
    private EmployeeSalary employeeSalary;

//    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeResources> employeeResources;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String address;


    public Employee(){
    }





}
