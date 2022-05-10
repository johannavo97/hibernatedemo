package com.hibernate.services;

import com.hibernate.dao.ProjectI;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;
import com.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class ProjectService implements ProjectI {
    @Override
    public void addProjectAndEmployee(Project p) {
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
    public void addEmployeesToProject(Set<Employee> employees) {

    }
}
