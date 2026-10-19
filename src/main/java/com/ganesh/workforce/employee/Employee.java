package com.ganesh.workforce.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(name = "uk_employee_email", columnNames = "email"))
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80) private String firstName;
    @Column(nullable = false, length = 80) private String lastName;
    @Column(nullable = false, length = 160, unique = true) private String email;
    @Column(nullable = false, length = 100) private String department;
    @Column(nullable = false, length = 100) private String role;

    protected Employee() {}
    public Employee(String firstName, String lastName, String email, String department, String role) {
        this.firstName = firstName; this.lastName = lastName; this.email = email;
        this.department = department; this.role = role;
    }
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public void setFirstName(String v) { firstName = v; }
    public void setLastName(String v) { lastName = v; }
    public void setEmail(String v) { email = v; }
    public void setDepartment(String v) { department = v; }
    public void setRole(String v) { role = v; }
}
