package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *项目租队：(ProjectRentalTeam)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ProjectRentalTeam")
public class ProjectRentalTeam implements Serializable {

    //ProjectRentalTeam编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_rental_team_id")
    private Integer project_rental_team_id;
    // 项目名称
    @Basic
    private String entry_name;
    // 项目类型
    @Basic
    private String project_type;
    // 学生工号
    @Basic
    private Integer student_job_number;
    // 学生姓名
    @Basic
    private String student_name;
    // 联系手机
    @Basic
    private String contact_mobile_phone;
    // 需求人数
    @Basic
    private String demand_number;
    // 组队需求
    @Basic
    private String team_requirements;
    // 项目内容
    @Basic
    private String project_content;
    // 审核状态
    @Basic
    private String examine_state;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
