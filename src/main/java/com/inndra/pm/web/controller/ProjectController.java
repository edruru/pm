package com.inndra.pm.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inndra.pm.core.domain.model.Criteria;
import com.inndra.pm.core.domain.model.Project;
import com.inndra.pm.core.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectController
 *
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-06-27
 */

@Controller
@RequestMapping(value = "project")
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String view(Model model) {
        log.info("Project ->" + "Loading menu");
        return "catalog/projects/menu.html";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newRequirement(@ModelAttribute Project project, BindingResult result, Model model) {
        log.info("New project request");
        return "catalog/projects/new.html";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newValidRequirement(@ModelAttribute @Valid Project project, BindingResult result, Model model) {
        log.info("New project request");
        if (result.hasErrors()) {
            log.info("Errors in: " + result);
            return "requirements/new.html";
        }
        return "catalog/projects/new.html";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Project project, BindingResult result, Model model) {
        log.info("Project --> " + " Creating project " + project);

        if (result.hasErrors()) {
            log.info(" " + result);
            return "catalog/projects/new.html";
        }

        projectService.insertProject(project);
        model.addAttribute("createdItem", projectService.getProjectById(project.getId()));
        return "catalog/projects/created.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute Project project, @ModelAttribute Criteria criteria) {
        log.info("Project -->", "Search project" + project);
        log.info("Inbound --> " + criteria);
        return "catalog/projects/search.html";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(@ModelAttribute @Valid Criteria criteria, BindingResult result,
                       Model model) {
        log.info("criteria found --> " + criteria);

        List<Project> projectsList = new ArrayList<Project>();

        projectsList = getProjects(criteria);

        if (result.hasErrors() || projectsList.isEmpty()) {
            log.info(" " + result);
            log.info("projectsList isEmpty");
            return "catalog/projects/search.html";
        }
        model.addAttribute("projectsList", projectsList);
        return "catalog/projects/search.html";
    }

    private List<Project> getProjects(Criteria criteria) {
        List<Project> projectsList = null;
        if (criteria.getType() == 1) {
            projectsList = new ArrayList<Project>();
            projectsList.add(projectService.getProjectById(Integer.parseInt(criteria.getText())));
        } else {
            projectsList = projectService.getProjectByName(criteria.getText());
        }
        return projectsList;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String preUpdate(@RequestParam(value = "id") Integer id,
                            Model model) {
        log.info("Project -->", "Project editing");
        log.info("Inbound --> " + id);

        Project projectById = projectService.getProjectById(id);
        model.addAttribute("project", projectById);
        return "catalog/projects/edit.html";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Project project, BindingResult result,
                         Model model) {
        log.info("Project -->", "Project updated");
        log.info("inbound --> " + project);

        if (result.hasErrors()) {
            log.info("error" + result);
            return "catalog/projects/edit.html";
        }
        projectService.updateProject(project);
        model.addAttribute("createdItem", projectService.getProjectById(project.getId()));
        return "catalog/projects/created.html";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        JsonObject jo = new JsonObject();

        log.info("Project -->", "Project delete");
        log.info("inbound --> " + id);

        try {
            projectService.deleteProject(id);
        } catch (RuntimeException r) {
            log.info("Delete Project ->" + " Error while deleting!");
            jo.addProperty("success", "false");
            return new Gson().toJson(jo);
        }
        log.info("Delete Project ->" + " Success");
        jo.addProperty("success", "true");
        return new Gson().toJson(jo);
    }


    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
