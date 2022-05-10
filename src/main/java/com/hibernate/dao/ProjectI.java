package com.hibernate.dao;

import com.hibernate.models.Employee;
import com.hibernate.models.Project;

import java.util.Set;

public interface ProjectI {

    void addProjectAndEmployee(Project p);
    void addEmployeesToProject(Set<Employee> employees);
}
