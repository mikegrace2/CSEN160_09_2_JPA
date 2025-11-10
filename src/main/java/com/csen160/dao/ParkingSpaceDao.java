package com.csen160.dao;

import java.io.Serializable;

import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.model.ParkingSpace;
import com.csen160.model.Student;
import com.csen160.util.HibernateUtil;

public class ParkingSpaceDao {
    public void saveParkingSpace(ParkingSpace parkingSpace) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Save parkingSpace
            session.persist(parkingSpace);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void updateParkingSpace(ParkingSpace parkingSpace) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // save the student object
            String hql = "UPDATE parking_space set employee_id = :employee_id WHERE parking_id = :parking_id";
            Query query = session.createQuery(hql);
            query.setParameter("employee_id", parkingSpace.getEmployee().getEmployeeId());
            query.setParameter("parking_id", parkingSpace.getParkingId());
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
