package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *项目申报：(ProjectDeclaration)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ProjectDeclaration")
public class ProjectDeclaration implements Serializable {

    //ProjectDeclaration编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_declaration_id")
    private Integer project_declaration_id;
    // 项目名称
    @Basic
    private String entry_name;
    // 项目类型
    @Basic
    private String project_type;
    // 所属学院
    @Basic
    private String affiliated_college;
    // 学生工号
    @Basic
    private Integer student_job_number;
    // 学生姓名
    @Basic
    private String student_name;
    // 联系手机
    @Basic
    private String contact_mobile_phone;
    // 项目认定书
    @Basic
    private String project_identification;
    // 项目附件
    @Basic
    private String project_annex;
    // 指导老师
    @Basic
    private Integer instructor;
    // 填表日期
    @Basic
    private Timestamp date_of_filling;
    // 老师评分
    @Basic
    private String teacher_rating;
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
