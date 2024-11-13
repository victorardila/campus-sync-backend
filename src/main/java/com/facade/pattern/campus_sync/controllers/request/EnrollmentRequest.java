package com.facade.pattern.campus_sync.controllers.request;

import java.util.List;

public class EnrollmentRequest {
    private List<Long> coursesIds;
    private Long scholarshipId;

    public List<Long> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Long> coursesIds) {
        this.coursesIds = coursesIds;
    }

    public Long getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(Long scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

}
