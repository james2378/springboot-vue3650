package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *结题验收：(FinalAcceptance)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FinalAcceptance")
public class FinalAcceptance implements Serializable {

    //FinalAcceptance编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_acceptance_id")
    private Integer final_acceptance_id;
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
    // 合同金额
    @Basic
    private Integer contract_amount;
    // 到位金额
    @Basic
    private Integer amount_in_place;
    // 实际支出
    @Basic
    private Integer actual_expenditure;
    // 结题附件
    @Basic
    private String conclusion_annex;
    // 答辩ppt
    @Basic
    private String reply_ppt;
    // 填表日期
    @Basic
    private Timestamp date_of_filling;
    // 院级审核
    @Basic
    private String hospital_level_audit;
    // 结题原因
    @Basic
    private String reason_for_conclusion;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
