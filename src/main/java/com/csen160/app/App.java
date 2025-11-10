package com.csen160.app;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csen160.dao.CourseListDao;
import com.csen160.dao.StudentDao;
import com.csen160.model.CourseList;
import com.csen160.model.Student;
import com.csen160.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        //App.testStudentTable();
        App.testCourseList(); // error
        //App.testQueries1();
        //App.testQueries2();
        //App.testQueries3();
        //App.testQueries4(); // error
        //App.testQueries5();
        //App.testQueries6();
        //App.testQueries7();
        //App.testQueries8_EmployeeAndParkingSpace(); // error
    }

    public static void testStudentTable() {
        // Student Student Student Student Student Student Student Student
        System.out.println("\n\nStudent Student Student Student Student Student Student Student\n\n");
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
            System.out.println(studentX.getFirstName());
        }

        // get single student
        Student student3 = studentDao.getStudent(1);
        System.out.println(student3.getFirstName());

        // delete student
        studentDao.deleteStudent(1);

        System.out.println("\n\nGet Students as SQL Statement ------------\n\n");
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get an student object
            String hql = "Select e from Student e";
            Query query = session.createQuery(hql);

            @SuppressWarnings("rawtypes")
            List results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                Student student = (Student) myIt.next();
                System.out.println("Student: " + student);
            }

            transaction.commit(); // commit transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void testCourseList() {
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        System.out.println("\n\nCourseList CourseList CourseList CourseList CourseList CourseList CourseList\n\n");
        CourseListDao myCourseListDao = new CourseListDao();
        CourseList courseList1 = new CourseList("COEN179", 179, "23/10/22", "24/04/11");
        CourseList courseList2 = new CourseList("COEN11", 11, "23/10/22", "24/04/11");
        myCourseListDao.saveCourseList(courseList1);
        myCourseListDao.saveCourseList(courseList2);

        // update CourseList
        CourseList courseList3 = new CourseList("COEN180", 179, "23/10/22", "24/04/11");
        myCourseListDao.updateCourseList(courseList3);

        // get CourseLists
        System.out.println("\n\n\nCourseLists:");
        List<CourseList> myCourseLists = myCourseListDao.getCourseLists();
        for (CourseList courseListX : myCourseLists) {
            System.out.println(courseListX);
        }

        // get single CourseList
        System.out.println("\n\n\nCourseList with enrollement=1:");
        CourseList courseList4 = myCourseListDao.getCourseList(11);
        System.out.println(courseList4);

        System.out.println("\n\n\nCourseList with ID=1:");
        CourseList courseList5 = myCourseListDao.readCourse("2");
        System.out.println(courseList5);

        // delete CourseList
        myCourseListDao.deleteCourseList(1);

        // ---------------------------------------------------------------------------------------------------------------
        // CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao
        System.out.println("\n\nCourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao\n\n");
        CourseListDao courseDao = new CourseListDao();

        CourseList course = new CourseList();
        course.setCourseName("COEN 160");
        course.setCourseNumber(815);
        course.setStartDate("23/10/18");
        course.setEndDate("24/01/19");

        courseDao.saveCourseList(course);

        System.out.println("\n\nGet CourseList as SQL Statement ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            // get an student object
            String hql = "Select c FROM CourseList c";
            Query query = session.createQuery(hql);

            @SuppressWarnings("rawtypes")
            List results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                System.out.println("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries1() {
        System.out.println("\n\ntestQueries1() ------------\n\n");
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
                System.out.println("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries2() {
        System.out.println("\n\ntestQueries2() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            TypedQuery<CourseList> query = session.createQuery("Select e FROM CourseList e", CourseList.class);

            List<CourseList> results = query.getResultList();

            Iterator<CourseList> myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                System.out.println("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries3() {
        System.out.println("\n\ntestQueries3() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            TypedQuery<CourseList> query = session.createQuery("Select e FROM CourseList e WHERE e.enrollment > 10", CourseList.class);

            List<CourseList> results = query.getResultList();

            Iterator<CourseList> myIt = results.iterator();

            while (myIt.hasNext()) {
                CourseList myCourse = (CourseList) myIt.next();
                System.out.println("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries4() {
        System.out.println("\n\ntestQueries4() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("SELECT e FROM CourseList e WHERE e.course_number = :cnum");
            query.setParameter("cnum", "COEN 120");
            CourseList result = (CourseList) query.getSingleResult();

            System.out.println(result);

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries5() {
        System.out.println("\n\ntestQueries4() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select COUNT(e) FROM CourseList e");

            long result = (Long) query.getSingleResult();

            System.out.println("Number of rows in CourseList = " + result);

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries6() {
        System.out.println("\n\ntestQueries6() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select e.course_number FROM CourseList e");

            @SuppressWarnings("unchecked")
            List<String> results = query.getResultList();

            Iterator<String> myIt = results.iterator();

            while (myIt.hasNext()) {
                String myString = myIt.next();
                System.out.println("course_number: " + myString);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }

    public static void testQueries7() {
        System.out.println("\n\ntestQueries7() ------------\n\n");
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction2 = session.beginTransaction();

            Query query = session.createQuery("Select e.course_number, e.enrollment FROM CourseList e");

            @SuppressWarnings("unchecked")
            List<Object[]> results = query.getResultList();

            @SuppressWarnings("rawtypes")
            Iterator myIt = results.iterator();

            while (myIt.hasNext()) {
                Object[] myArr = (Object[]) myIt.next();

                String myString = (String) myArr[0];
                Integer myInt = (Integer) myArr[1];

                System.out.println("course_number= " + myString + " enrollment= " + myInt);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }
}