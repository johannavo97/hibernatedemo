package com.hibernate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    @Column(length = 150, unique = true, nullable = false)
    String name;
    @NonNull
    double salary;

    public Employee(int id, @NonNull String name, @NonNull double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    @ToString.Exclude
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Address> addresses = new java.util.ArrayList<>();

    public void addAddress(Address a){
        addresses.add(a);
        a.setEmployee(this);
    }
    public void removeAddress(Address a){
        addresses.remove(a);
        a.setEmployee(null);
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id"))
    Set<Project> projects = new LinkedHashSet<>();


}
