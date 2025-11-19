package com.csen160.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = -6347481878898504405L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int project_id;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    private String projectName;

    public int getProjectId() {
        return project_id;
    }

    public void setProjectId(int projectId) {
        this.project_id = projectId;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Project() {
        super();
    }

    public Project(Set<Employee> employees, String projectName) {
        super();
        this.employees = employees;
        this.projectName = projectName;
    }
}