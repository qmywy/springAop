package com.fly.util;

public enum LogType {

    DEFAULT(""),
    INSERT("添加"),
    UPDATE("修改"),
    STUDENT_QUERY("查询"),
    DELETE("删除");

    private String description;
    LogType(String s) {
        this.description = s;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
