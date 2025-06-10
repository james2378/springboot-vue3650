package com.project.demo.controller;

import com.project.demo.entity.ProjectRentalTeam;
import com.project.demo.service.ProjectRentalTeamService;
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
 *项目租队：(ProjectRentalTeam)表控制层
 *
 */
@RestController
@RequestMapping("/project_rental_team")
public class ProjectRentalTeamController extends BaseController<ProjectRentalTeam,ProjectRentalTeamService> {

    /**
     *项目租队对象
     */
    @Autowired
    public ProjectRentalTeamController(ProjectRentalTeamService service) {
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
