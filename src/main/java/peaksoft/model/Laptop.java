package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.OperationSystem;

import java.time.LocalDate;
import java.util.Date;
import java.util.Formattable;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor

@Table(name = "laptops")

public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "laptops_id_generator")
    @SequenceGenerator(name = "laptops_id_generator",
    sequenceName = "laptops_seq",
    allocationSize =1)
    private Long id;
    private String brand;
    @Column(name = "operation_system")
    @Enumerated(EnumType.STRING)
    private OperationSystem operationSystem;
    private int memory;
    private int price;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    public Laptop(String brand, OperationSystem operationSystem, int memory, int price, LocalDate dateOfIssue) {
        this.brand = brand;
        this.operationSystem = operationSystem;
        this.memory = memory;
        this.price = price;
        this.dateOfIssue = dateOfIssue;
    }




}

