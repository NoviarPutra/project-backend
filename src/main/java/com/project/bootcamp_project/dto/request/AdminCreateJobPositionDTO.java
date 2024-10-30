package com.project.bootcamp_project.dto.request;

import java.util.Objects;

public class AdminCreateJobPositionDTO {

    private String departmentId;

    private String title;

    private String description;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdminCreateJobPositionDTO{");
        sb.append("departmentId='").append(departmentId).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdminCreateJobPositionDTO that = (AdminCreateJobPositionDTO) object;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, title, description);
    }

}
