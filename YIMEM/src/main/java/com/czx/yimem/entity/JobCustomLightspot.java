package com.czx.yimem.entity;

public class JobCustomLightspot {
    private Integer id;

    private Integer jobId;

    private Integer jobLightenId;

    private String state;

    public JobCustomLightspot(Integer id, Integer jobId, Integer jobLightenId, String state) {
        this.id = id;
        this.jobId = jobId;
        this.jobLightenId = jobLightenId;
        this.state = state;
    }

    public JobCustomLightspot() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobLightenId() {
        return jobLightenId;
    }

    public void setJobLightenId(Integer jobLightenId) {
        this.jobLightenId = jobLightenId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}