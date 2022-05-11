package com.hibernate.dao;

import com.hibernate.models.Employee;
import com.hibernate.models.Project;

import java.util.List;
import java.util.Set;

public interface ProjectI {

    void addEmployeeToProject(int projectId, int employeeId);
    void createProject(Project p);

    List<Employee> getAllEmployeesInProject(Project p);
}
