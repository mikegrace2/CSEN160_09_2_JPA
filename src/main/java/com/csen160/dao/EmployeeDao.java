package com.csen160.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.entities.Employee;
import com.csen160.util.HibernateUtil;

public class EmployeeDao {
    public void saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Save employee
            session.persist(employee);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
