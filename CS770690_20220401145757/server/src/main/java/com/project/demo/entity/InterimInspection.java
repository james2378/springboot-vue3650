package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *中期检查：(InterimInspection)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InterimInspection")
public class InterimInspection implements Serializable {

    //InterimInspection编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interim_inspection_id")
    private Integer interim_inspection_id;
    // 项目名称
    @Basic
    private String entry_name;
    // 项目类型
    @Basic
    private String project_type;
    // 所属学院
    @Basic
    private String affiliated_college;
    // 学生学号
    @Basic
    private Integer student_number;
    // 学生姓名
    @Basic
    private String student_name;
    // 联系手机
    @Basic
    private String contact_mobile_phone;
    // 指导老师
    @Basic
    private Integer instructor;
    // 项目开始时间
    @Basic
    private Timestamp project_start_time;
    // 计划完成时间
    @Basic
    private Timestamp planned_completion_time;
    // 项目经费总额
    @Basic
    private Integer total_project_funds;
    // 研究进展
    @Basic
    private String research_progress;
    // 阶段成果
    @Basic
    private String stage_results;
    // 项目附件
    @Basic
    private String project_annex;
    // 老师审核
    @Basic
    private String teacher_review;
    // 院级审核
    @Basic
    private String hospital_level_audit;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
