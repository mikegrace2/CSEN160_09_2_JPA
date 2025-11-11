package com.csen160.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A_Main {
    private static final Logger logger = LoggerFactory.getLogger(A_Main.class);

    public static void main(String[] args) {
        B_StudentManagement.testStudentTable();
        C_DoCourseListStuff.testCourseList();
        D_SomeTestQueries.testQueries1();
        D_SomeTestQueries.testQueries2();
        D_SomeTestQueries.testQueries3();
        //D_SomeTestQueries.testQueries4(); // error
        D_SomeTestQueries.testQueries5();
        D_SomeTestQueries.testQueries6();
        D_SomeTestQueries.testQueries7();
    }
}