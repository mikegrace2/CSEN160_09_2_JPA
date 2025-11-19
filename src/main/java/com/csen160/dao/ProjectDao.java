package com.csen160.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.entities.Project;
import com.csen160.util.HibernateUtil;

public class ProjectDao {
    public void saveAddress(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Save project
            session.persist(project);

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
