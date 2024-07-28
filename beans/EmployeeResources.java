package beans;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class EmployeeResources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resourcesId;

    @Column(nullable = false)
    private String resourcesName;

    @Column(nullable = false)
    private Date IssueDate;
    
    @ManyToOne
    private Employee employee;

    public EmployeeResources() {
    }
}
