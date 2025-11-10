package com.csen160.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_list")
public class CourseList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_number")
    private Integer courseNumber;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "enrollment")
    private Integer enrollment;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    public CourseList() {}

    public CourseList(String courseName, Integer enrollment, String startDate, String endDate) {
        this.courseName = courseName;
        this.enrollment = enrollment;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getCourseNumber() { return courseNumber; }
    public void setCourseNumber(Integer courseNumber) { this.courseNumber = courseNumber; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getEnrollment() { return enrollment; }
    public void setEnrollment(Integer enrollment) { this.enrollment = enrollment; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "CourseList [courseNumber=" + courseNumber + ", courseName=" + courseName +
                ", enrollment=" + enrollment + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}