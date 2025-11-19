package com.csen160.application;

import com.csen160.dao.CourseListDao;
import com.csen160.entities.CourseList;
import com.csen160.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class C_DoCourseListStuff {
    private static final Logger logger = LoggerFactory.getLogger(C_DoCourseListStuff.class);

    public static void testCourseList() {
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        // CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList CourseList
        logger.info("\n\nCourseList CourseList CourseList CourseList CourseList CourseList CourseList\n");

        CourseListDao myCourseListDao = new CourseListDao();
        CourseList courseList1 = new CourseList("COEN179", 179, "23/10/22", "24/04/11");
        CourseList courseList2 = new CourseList("COEN11", 11, "23/10/22", "24/04/11");
        myCourseListDao.saveCourseList(courseList1);
        myCourseListDao.saveCourseList(courseList2);

        // update CourseList
        CourseList courseList3 = new CourseList("COEN180", 179, "23/10/22", "24/04/11");
        myCourseListDao.updateCourseList(courseList3);

        // get CourseLists
        logger.info("\n\n\nCourseLists:");
        List<CourseList> myCourseLists = myCourseListDao.getCourseLists();
        for (CourseList courseListX : myCourseLists) {
            logger.info(courseListX.toString());
        }

        // get single CourseList
        logger.info("\n\n\nCourseList with enrollement=1:");
        CourseList courseList4 = myCourseListDao.getCourseList(11);
        logger.info(courseList4.toString());

        logger.info("\n\n\nCourseList with ID=1:");
        CourseList courseList5 = myCourseListDao.readCourse("2");
        logger.info(courseList5.toString());

        // delete CourseList
        myCourseListDao.deleteCourseList(1);

        // ---------------------------------------------------------------------------------------------------------------
        // CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao
        logger.info("\n\nCourseListDao CourseListDao CourseListDao CourseListDao CourseListDao CourseListDao\n");
        CourseListDao courseDao = new CourseListDao();

        CourseList course = new CourseList();
        course.setCourseName("COEN 160");
        course.setCourseNumber(815);
        course.setStartDate("23/10/18");
        course.setEndDate("24/01/19");

        courseDao.saveCourseList(course);

        logger.info("\n\nGet CourseList as SQL Statement ------------\n\n");
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
                logger.info("CourseList: " + myCourse);
            }

            transaction2.commit(); // commit transaction
        } catch (Exception e) {
            logger.error("Error in CourseList table", e);
            if (transaction2 != null) {
                transaction2.rollback();
            }
        }
    }
}
