package com.example.a19507801_dangvansang;

public class Course {
    private int id;
    private String course;
    private int status;

    public Course() {
    }

    public Course(String course, Integer status) {
        this.course = course;
        this.status = status;
    }

    public Course(String course) {
        this.course = course;
    }

    public Course(String course, int i) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
