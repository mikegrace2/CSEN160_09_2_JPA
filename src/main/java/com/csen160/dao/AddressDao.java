package com.csen160.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.entities.Address;
import com.csen160.util.HibernateUtil;

public class AddressDao {
    public void saveAddress(Address address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Save address
            session.persist(address);

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
