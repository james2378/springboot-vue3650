package com.project.demo.controller;

import com.project.demo.entity.HospitalLevelApprover;
import com.project.demo.service.HospitalLevelApproverService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *院级审批者：(HospitalLevelApprover)表控制层
 *
 */
@RestController
@RequestMapping("/hospital_level_approver")
public class HospitalLevelApproverController extends BaseController<HospitalLevelApprover,HospitalLevelApproverService> {

    /**
     *院级审批者对象
     */
    @Autowired
    public HospitalLevelApproverController(HospitalLevelApproverService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapapprover_job_number = new HashMap<>();
        mapapprover_job_number.put("approver_job_number",String.valueOf(paramMap.get("approver_job_number")));
        List listapprover_job_number = service.select(mapapprover_job_number, new HashMap<>()).getResultList();
        if (listapprover_job_number.size()>0){
            return error(30000, "字段审批者工号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
