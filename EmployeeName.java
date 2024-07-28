package beans;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EmployeeName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nameID;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    public EmployeeName() {
    }
}
