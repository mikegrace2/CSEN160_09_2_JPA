package com.csen160.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department implements Serializable {
    private static final long serialVersionUID = 5818839199564508135L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int department_id;

    private String name;

    public int getDepartmentId() {
        return department_id;
    }

    public void setDepartmentId(int departmentId) {
        this.department_id = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Constructors --------------------------
    public Department() {
        super();
    }

    public Department(String name) {
        super();
        this.name = name;
    }


}
