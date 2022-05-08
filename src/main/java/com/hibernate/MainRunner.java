package com.hibernate;

import com.hibernate.models.Address;
import com.hibernate.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;

public class MainRunner {
    private static SessionFactory factory;
    public static void main(String[] args) {

        // create session factory
        try {
            factory = new Configuration()
                    .configure(new File("src/main/resources/hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Throwable ex){
            ex.printStackTrace();
        }

        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        Employee e1 = new Employee("jafer",70000);
        Employee e2 = new Employee("john",60000);
        Employee e3 = new Employee("jane",50000);
        Address a1 = new Address("123 st", "", "plano",75000, e1);

        s.persist(a1);
        s.persist(e2);
        s.persist(e3);
        t.commit();
        List<Employee> e = s.createQuery("from Employee").list();

        System.out.println(e);
        // t.rollback();

        s.close();




    } // main method
} // MainRunner
