package com.csen160.application;

import com.csen160.dao.StudentDao;
import com.csen160.model.Student;
import com.csen160.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class B_StudentManagement {
    private static final Logger logger = LoggerFactory.getLogger(B_StudentManagement.class);

    public static void testStudentTable() {
        // Student Student Student Student Student Student Student Student
        logger.info("\n\nStudent Student Student Student Student Student Student Student\n");

        StudentDao studentDao = new StudentDao();
        Student student1 = new Student("Mike", "Schimpf", "mschimpf@scu.edu");
        Student student11 = new Student("Olec", "Balder", "ob@scu.edu");
        studentDao.saveStudent(student11);
        studentDao.saveStudent(student1);
        studentDao.insertStudent();

        // update student
        Student student2 = new Student("Michael", "Schimpf", "mschimpf@scu.edu");
        studentDao.updateStudent(student2);

        // get students
        System.out.println("\n\n\nStudents:");
        List<Student> students = studentDao.getStudents();
        for (Student studentX : students) {
            logger.info(studentX.getFirstName());
        }

        // get single student
        Student student3 = studentDao.getStudent(1);
        logger.info(student3.getFirstName());

        // delete student
        studentDao.deleteStudent(1);

        logger.info("\n\nGet Students as SQL Statement ------------\n");
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get a student object
            String hql = "Select e from Student e";
            Query query = session.createQuery(hql);

            @SuppressWarnings("rawtypes")
            List results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                Student student = (Student) myIt.next();
                logger.info("Student: " + student);
            }

            transaction.commit(); // commit transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error in testStudentTable", e);
        }
    }
}
