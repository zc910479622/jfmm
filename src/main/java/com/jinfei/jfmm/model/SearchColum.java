package com.jinfei.jfmm.model;

public class SearchColum {
    private String startTime;
    private String endTime;
    private Integer pageSize;
    private Integer pageNum;
    private Integer offset;
    private String orderByColumn;
    private String isAsc;
    private String assignee1;
    private String assignee2;
    private String execution_id;
    private String task_id;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    public String getAssignee1() {
        return assignee1;
    }

    public void setAssignee1(String assignee1) {
        this.assignee1 = assignee1;
    }

    public String getAssignee2() {
        return assignee2;
    }

    public void setAssignee2(String assignee2) {
        this.assignee2 = assignee2;
    }

    public String getExecution_id() {
        return execution_id;
    }

    public void setExecution_id(String execution_id) {
        this.execution_id = execution_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String taskId) {
        this.task_id = taskId;
    }
}
