package com.csen160.application;

import com.csen160.entities.CourseList;
import com.csen160.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class D_SomeTestQueries {
    private static final Logger logger = LoggerFactory.getLogger(D_SomeTestQueries.class);

    public static void testQueries1() {
        logger.info("testQueries1() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select e FROM CourseList e", CourseList.class);

            @SuppressWarnings("rawtypes")
            List results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                logger.info("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("01 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries2() {
        logger.info("\n\ntestQueries2() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            TypedQuery<CourseList> query = session.createQuery("Select e FROM CourseList e", CourseList.class);

            List<CourseList> results = query.getResultList();

            Iterator<CourseList> myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                logger.info("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("02 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries3() {
        logger.info("\n\ntestQueries3() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            TypedQuery<CourseList> query = session.createQuery("Select e FROM CourseList e WHERE e.enrollment > 10", CourseList.class);

            List<CourseList> results = query.getResultList();

            Iterator<CourseList> myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                logger.info("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("03 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries4() {
        logger.info("\n\ntestQueries4() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("SELECT e FROM CourseList e WHERE e.courseNumber = :cnum");
            query.setParameter("cnum", "COEN 120");
            CourseList result = (CourseList) query.getSingleResult();

            logger.info(result.toString());

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("04 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries5() {
        logger.info("\n\ntestQueries4() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select COUNT(e) FROM CourseList e");

            long result = (Long) query.getSingleResult();

            logger.info("Number of rows in CourseList = " + result);

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("05 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries6() {
        logger.info("\n\ntestQueries6() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select e.courseNumber FROM CourseList e");

            @SuppressWarnings("unchecked")
            List<String> results = query.getResultList();

            Iterator<String> myIt = results.iterator();

            while (myIt.hasNext()) {
                String myString = myIt.next();
                logger.info("course_number: " + myString);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("06 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries7() {
        logger.info("\n\ntestQueries7() ------------\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select e.courseNumber, e.enrollment FROM CourseList e");

            @SuppressWarnings("unchecked")
            List<Object[]> results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                Object[] myArr = (Object[]) myIt.next();

                String myString = (String) myArr[0];
                Integer myInt = (Integer) myArr[1];

                logger.info("course_number= " + myString + " enrollment= " + myInt);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("07 Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }
}
