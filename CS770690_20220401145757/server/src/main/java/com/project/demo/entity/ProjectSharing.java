package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *项目共享：(ProjectSharing)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ProjectSharing")
public class ProjectSharing implements Serializable {

    //ProjectSharing编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_sharing_id")
    private Integer project_sharing_id;
    // 项目名称
    @Basic
    private String entry_name;
    // 项目类型
    @Basic
    private String project_type;
    // 所属学院
    @Basic
    private String affiliated_college;
    // 项目附件
    @Basic
    private String project_annex;
    // 项目内容
    @Basic
    private String project_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
