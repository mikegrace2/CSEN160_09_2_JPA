package com.csen160.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.csen160.entities.Address;
import com.csen160.entities.CourseList;
import com.csen160.entities.Department;
import com.csen160.entities.Employee;
import com.csen160.entities.ParkingSpace;
import com.csen160.entities.Project;
import com.csen160.entities.Student;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Singleton Design Pattern
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "Admin");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                // Important setup all new tables here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(CourseList.class);
                configuration.addAnnotatedClass(Department.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(ParkingSpace.class);
                configuration.addAnnotatedClass(Project.class);
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}