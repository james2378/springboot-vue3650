package com.project.demo.controller;

import com.project.demo.entity.ProjectSharing;
import com.project.demo.service.ProjectSharingService;
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
 *项目共享：(ProjectSharing)表控制层
 *
 */
@RestController
@RequestMapping("/project_sharing")
public class ProjectSharingController extends BaseController<ProjectSharing,ProjectSharingService> {

    /**
     *项目共享对象
     */
    @Autowired
    public ProjectSharingController(ProjectSharingService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
