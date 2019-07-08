package com.victorzhenwang.statemachinedemo.model;

import java.util.*;

public class ApprovalFlow {

    private String id;

    private String state;

    private List<ApprovalStage> stages;

    public ApprovalFlow(String id, String state, String[][] stages) {
        super();
        this.id = id;
        this.state = state;
        this.stages = new ArrayList<>();
        for(String[] approvers: stages){
            ApprovalStage stage = new ApprovalStage(approvers);
            this.stages.add(stage);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ApprovalStage getStage(int stageId) {
        for(ApprovalStage stage: this.stages) {
            if(stage.getId() == stageId){
                return stage;
            }
        }
        return null;
    }
}
