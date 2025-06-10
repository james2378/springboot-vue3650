package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *院级审批者：(HospitalLevelApprover)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HospitalLevelApprover")
public class HospitalLevelApprover implements Serializable {

    //HospitalLevelApprover编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_level_approver_id")
    private Integer hospital_level_approver_id;
    // 审批者工号
    @Basic
    private String approver_job_number;
    // 审批者姓名
    @Basic
    private String name_of_approver;
    // 审批者性别
    @Basic
    private String gender_of_approver;
    // 审批者年龄
    @Basic
    private String age_of_reviewer;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
