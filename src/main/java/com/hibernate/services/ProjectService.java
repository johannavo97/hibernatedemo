package com.hibernate.services;

import com.hibernate.dao.ProjectI;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;
import com.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectService implements ProjectI {


    @Override
    public void addEmployeeToProject(int projectId, int employeeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            Employee e = s.get(Employee.class, employeeId);
            Project p = s.get(Project.class,projectId);
            p.addEmployee(e);
            s.merge(p);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }

    }

    @Override
    public void createProject(Project p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();

            s.persist(p);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
    }

    @Override
    public List<Employee> getAllEmployeesInProject(Project p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Employee> employeeList = null;
        try {
            tx = s.beginTransaction();
            // text block is a java 15 feature """ """
            NativeQuery q = s.createNativeQuery("""
                    select e.id, e.name, e.salary from Employee as e
                    join employee_project as ep on e.id = ep.employee_id
                    join Project as p on p.id = ep.projects_id
                    where p.id = :id """,Employee.class);


            q.setParameter("id",p.getId());
            employeeList = q.getResultList();
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return employeeList;
    }

    public List<Employee> getAllEmployeesInProjectHql(Project p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        List<Employee> e = null;
        try {
            tx = s.beginTransaction();

            // bug detected -> results are incorrect
            Query q = s.createQuery("select e.id, e.name, e.salary from Employee as e left join fetch Project as p where p.id = :id");
            q.setParameter("id",p.getId());
            List<Object[]> employeeList = q.getResultList();

             e = employeeList.stream().map(objects -> new Employee((Integer)objects[0],(String)objects[1],(Double)objects[2])).
                    collect(Collectors.toList());
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return e;
    }


}
