package com.hibernate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String name;
    @NonNull


    @ToString.Exclude
    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Set<Employee> employees = new LinkedHashSet<>();


}
