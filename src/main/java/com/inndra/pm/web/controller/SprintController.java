package com.inndra.pm.web.controller;

import com.inndra.pm.core.domain.model.*;
import com.inndra.pm.core.domain.model.*;
import com.inndra.pm.core.service.PriorityService;
import com.inndra.pm.core.service.ProjectService;
import com.inndra.pm.core.service.RequirementService;
import com.inndra.pm.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * SprintController.
 *
 * @author moises.villa
 * @version 1.0
 * @since 2014-05-20
 */
@Controller
public class SprintController {

    private static final Logger log = LoggerFactory.getLogger(SprintController.class);

    //Services needed
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private RequirementService requirementService;

    //Maps the request and validates the input
    @RequestMapping(value = "st", method = RequestMethod.POST)
    public String st(@ModelAttribute @Valid Sprint inbound, BindingResult result, Model model) {
        log.info("inbound --> " + inbound);

        if (result.hasErrors()) {
            log.info(" " + result);
            populateModel(model);
            return "requirements/add.html";
        }

        log.info("Requirement Created -> " + inbound.getRequirements());

        List<Requirement> createdItems = new ArrayList<Requirement>();
        List<User> assignedUsers = new ArrayList<User>();

        for (Requirement requirement : inbound.getRequirements()) {
            requirementService.insertRequirement(requirement);
            createdItems.add(requirementService.getRequirementById(requirement.getId()));
            assignedUsers.add(userService.getUserById(requirement.getUserId()));
        }

        model.addAttribute("createdItems", createdItems);
        model.addAttribute("assignedUsers", assignedUsers);

        return "requirements/created.html";

    }

    public void populateModel(Model model) {

        List<Priority> priorities = priorityService.getAllPriorities();
        model.addAttribute("prioritiesList", priorities);

        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projectsList", projects);

        List<User> users = userService.getAllUsers();
        model.addAttribute("usersList", users);

    }

    public void setPriorityService(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRequirementService(RequirementService requirementService) {
        this.requirementService = requirementService;
    }
}
