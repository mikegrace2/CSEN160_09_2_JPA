package com.csen160.dao;

import java.util.List;

import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.entities.CourseList;
import com.csen160.util.HibernateUtil;

public class CourseListDao {
    public void saveCourseList(CourseList courseList) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Save courseList
            session.persist(courseList);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void insertCourseList() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql = "INSERT INTO course_list (course_name, enrollment, start_date, end_date) "
                    + "SELECT course_name, enrollment, start_date, end_date FROM Student";
            Query query = session.createQuery(hql);
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

    public void updateCourseList(CourseList courseList) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // save the student object
            //String hql = "UPDATE CourseList set course_name = :course_name WHERE course_number = :course_number";
            String hql = "UPDATE CourseList set course_name = :course_name ";
            Query query = session.createQuery(hql);
            query.setParameter("course_name", courseList.getCourseName());
            //query.setParameter("course_number", 1);
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

    public void deleteCourseList(int enrollment) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a CourseList object
            CourseList courseList = session.get(CourseList.class, enrollment);
            if (courseList != null) {
                String hql = "DELETE FROM CourseList WHERE enrollment = :enrollment";
                Query query = session.createQuery(hql);
                query.setParameter("enrollment", enrollment);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public CourseList readCourse(String courseNumber) {
        Transaction transaction = null;
        CourseList courseList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            courseList = session.find(CourseList.class, courseNumber);

            transaction.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return courseList;
    }


    public CourseList getCourseList(int enrollment) {
        Transaction transaction = null;
        CourseList courseList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get an student object
            String hql = " FROM CourseList S WHERE S.enrollment = :enrollment";
            Query query = session.createQuery(hql);
            query.setParameter("enrollment", enrollment);

            @SuppressWarnings("rawtypes")
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                courseList = (CourseList) results.get(0);
            }

            transaction.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return courseList;
    }

    public List<CourseList> getCourseLists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CourseList", CourseList.class).list();
        }
    }
}