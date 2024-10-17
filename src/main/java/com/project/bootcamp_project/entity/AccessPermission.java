package com.project.bootcamp_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "access_permissions")
public class AccessPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AccessPermission{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
