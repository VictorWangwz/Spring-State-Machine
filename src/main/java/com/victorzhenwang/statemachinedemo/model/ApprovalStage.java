package com.victorzhenwang.statemachinedemo.model;

import java.util.ArrayList;
import java.util.List;

public class ApprovalStage {

    private static int sequence = 0;

    private List<Approver> approvers;

    private int id;

    public ApprovalStage(String[] approvers) {
        id = sequence++;
        this.approvers = new ArrayList<>();
        for(String s: approvers){
            Approver approver = new Approver(s);
            this.approvers.add(approver);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void onProcess(){

    }
}
