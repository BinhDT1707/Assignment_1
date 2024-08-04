package com.fpt.fsa.assignment_1.entity;

import com.fpt.fsa.assignment_1.enums.EGender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private EGender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 20)
    private String phone;

    private String address;

    @Column(nullable = false)
    private String departmentName;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Account account;

    public void addAccount (Account account) {
        this.account = account;
        account.setEmployee(this);
    }
}
