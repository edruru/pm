package com.inndra.pm.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.inndra.pm.core.domain.model.*;
import com.inndra.pm.core.service.*;
import com.inndra.pm.core.domain.model.*;
import com.inndra.pm.core.service.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RequirementController.java
 *
 * @author moises.villa
 * @version 1.0
 * @since 2014-05-20
 */
@Controller
@RequestMapping(value = "requirement")
public class RequirementController {

    private static final Logger log = LoggerFactory.getLogger(RequirementController.class);

    @Autowired
    private PriorityService priorityService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private RequirementService requirementService;
    @Autowired
    private RequirementHistoryService requirementHistoryService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String view(Model model) {
        log.info("Requirement ->" + "Loading menu");

        List<Requirement> requirementList = new ArrayList<Requirement>();
        List<User> usersAssignedList = new ArrayList<User>();
        List<Project> projectsAssignedList = new ArrayList<Project>();
        List<Priority> prioritiesAssignedList = new ArrayList<Priority>();
        List<String> statusList = new ArrayList<String>();

        //TODO: Fill requirementList by searching today's date.
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        requirementList = requirementService.getLastRequirements(ft.format(dNow));

        filterRequirements(usersAssignedList, requirementList, projectsAssignedList, prioritiesAssignedList, statusList);

        model.addAttribute("requirementsList", requirementList);
        model.addAttribute("usersAssignedList", usersAssignedList);
        model.addAttribute("projectsAssignedList", projectsAssignedList);
        model.addAttribute("prioritiesAssignedList", prioritiesAssignedList);
        model.addAttribute("statusList", statusList);

        return "requirements/menu.html";
    }


    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newRequirement(@ModelAttribute Requirement requirement, BindingResult result, Model model) {
        log.info("New requirement request");
        populateModel(model);
        return "requirements/new.html";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newValidRequirement(@ModelAttribute @Valid Requirement requirement, BindingResult result, Model model) {
        populateModel(model);
        if (result.hasErrors()) {
            log.info("Errors in: " + result);
            return "requirements/new.html";
        }
        return "requirements/new.html";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@ModelAttribute Requirement requirement, @ModelAttribute Criteria criteria) {
        log.info("Requirement search -> " + criteria );
        return "requirements/search.html";
    }


    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(@ModelAttribute Requirement requirement, @ModelAttribute Criteria criteria, BindingResult result,
                       Model model) {
        log.info("Requirement found ->" + criteria);

        List<Requirement> requirementList = new ArrayList<Requirement>();
        List<User> usersAssignedList = new ArrayList<User>();
        List<Project> projectsAssignedList = new ArrayList<Project>();
        List<Priority> prioritiesAssignedList = new ArrayList<Priority>();
        List<String> statusList = new ArrayList<String>();

        switch (criteria.getType()) {
            case 1:
                requirementList = requirementService.getRequirementByUser(criteria.getText());
                break;
            case 2:
                requirementList = requirementService.getRequirementByText(criteria.getText());
                break;
            case 3:
                requirementList = requirementService.getRequirementByProject(criteria.getText());
                break;
            case 4:
                requirementList = requirementService.getRequirementByBeginDate(criteria.getBeginDate(), criteria.getFinishDate());
                break;
        }

        filterRequirements(usersAssignedList, requirementList, projectsAssignedList, prioritiesAssignedList, statusList);


        if (result.hasErrors() || requirementList.isEmpty()) {
            log.info(" " + result);
            return "requirements/search.html";
        }
        model.addAttribute("requirementsList", requirementList);
        model.addAttribute("usersAssignedList", usersAssignedList);
        model.addAttribute("projectsAssignedList", projectsAssignedList);
        model.addAttribute("prioritiesAssignedList", prioritiesAssignedList);
        model.addAttribute("statusList", statusList);
        return "requirements/search.html";
    }


    private void filterRequirements(List<User> usersAssignedList, List<Requirement> requirementList, List<Project> projectsAssignedList, List<Priority> prioritiesAssignedList, List<String> statusList) {
        for (Requirement req : requirementList) {
            if (req.getUserId() != null) {
                usersAssignedList.add(userService.getUserById(req.getUserId()));
            } else {
                usersAssignedList.add(new User());
            }

            if (req.getProjectId() != null) {
                projectsAssignedList.add(projectService.getProjectById(req.getProjectId()));
            } else {
                projectsAssignedList.add(new Project());
            }

            if (req.getPriorityId() != null) {
                prioritiesAssignedList.add(priorityService.getPriorityById(req.getPriorityId()));
            } else {
                prioritiesAssignedList.add(new Priority());
            }

            //Getting the last status set for this requirement.
            statusList.add(statusName(requirementHistoryService.getRHByRequirementId(req.getId()).get(requirementHistoryService.getRHByRequirementId(req.getId()).size() - 1).getStatusId()));

        }
    }

    private String statusName(Integer statusId) {
        switch (statusId) {
            case 1:
                return "New";
            case 2:
                return "Working";
            case 3:
                return "On-Hold";
            case 4:
                return "Change";
            case 5:
                return "Cancel";
            default:
                return "Complete";
        }
    }

    //fetchSearchedItems
    @RequestMapping(value = "/fetchSearchedItems", params = {"beginDate", "finishDate", "text", "criteria"}, method = RequestMethod.GET)
    @ResponseBody
    public String fetchSearchedItems(@RequestParam String beginDate, @RequestParam String finishDate, @RequestParam String text, @RequestParam int criteria) {
        log.info("Requirementd Fetched --> " + ", id: " + criteria);
        List<Requirement> requirementList = new ArrayList<Requirement>();

        switch (criteria) {
            case 1:
                requirementList = requirementService.getRequirementByUser(text);
                break;
            case 2:
                requirementList = requirementService.getRequirementByText(text);
                break;
            case 3:
                requirementList = requirementService.getRequirementByProject(text);
                break;
            case 4:
                requirementList = requirementService.getRequirementByBeginDate(beginDate, finishDate);
                break;
        }

        return generateJSON(requirementList);

    }


    private String generateJSON(List<Requirement> requirementList) {
        //JsonObject jo = new JsonObject();
        JsonArray ja = new JsonArray();
        for (Requirement req : requirementList) {
            JsonObject jo = new JsonObject();
            jo.addProperty("id", req.getId());
            jo.addProperty("userAssigned", req.getUserId() != null ? userService.getUserById(req.getUserId()).getName() : "Not Assigned");
            jo.addProperty("text", req.getText());
            jo.addProperty("project", req.getProjectId() != null ? projectService.getProjectById(req.getProjectId()).getName() : "Not Assigned");
            jo.addProperty("priority", priorityService.getPriorityById(req.getPriorityId()).getType());
            jo.addProperty("creationDate", req.getCreationDate());
            jo.addProperty("beginDate", req.getBeginDate() != null ? req.getBeginDate() : "Not Started Yet");
            jo.addProperty("finishDate", req.getFinishDate() != null ? req.getFinishDate() : "Not Finished Yet");
            jo.addProperty("status", statusName(requirementHistoryService.getRHByRequirementId(req.getId()).get(requirementHistoryService.getRHByRequirementId(req.getId()).size() - 1).getStatusId()));
            ja.add(jo);
        }
        return new Gson().toJson(ja);
    }

    /* Controllers used in Ajax Requests */

    @RequestMapping(value = "/fetchDetail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String fetchRequirement(@PathVariable int id) {
        log.info("Requirement Fetched --> " + ", id: " + id);
        JsonObject value = new JsonObject();
        //Getting the requirement
        Requirement requirement = requirementService.getRequirementById(id);
        value.addProperty("id", String.valueOf(requirement.getId()));
        if (requirement.getUserId() != null)
            value.addProperty("userAssigned", userService.getUserById(requirement.getUserId()).getName());
        value.addProperty("text", requirement.getText());
        if (requirement.getProjectId() != null)
            value.addProperty("projectAssigned", projectService.getProjectById(requirement.getProjectId()).getName());
        value.addProperty("priorityAssigned", priorityService.getPriorityById(requirement.getPriorityId()).getType());
        value.addProperty("creationDate", requirement.getCreationDate());
        value.addProperty("beginDate", requirement.getBeginDate());
        value.addProperty("finishDate", requirement.getFinishDate());
        value.addProperty("status", statusName(requirementHistoryService.getRHByRequirementId(requirement.getId()).get(requirementHistoryService.getRHByRequirementId(requirement.getId()).size() - 1).getStatusId()));
        return new Gson().toJson(value);
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode fetchRequirementDetail(@PathVariable int id) {
        log.info("Requirementd Fetched --> " + ", id: " + id);
        JsonNode jn = new ObjectMapper().valueToTree(requirementService.getRequirementById(id));
        //Adding the history of this requirement as a node, named "history".
        ((ObjectNode) jn).put("history", new ObjectMapper().valueToTree(requirementHistoryService.getRHByRequirementId(id)));
        return jn;
    }

    @RequestMapping(value = "/fetchUsersList", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode fetchUsersList() {
        return new ObjectMapper().valueToTree(userService.getAllUsers());
    }

    @RequestMapping(value = "/fetchProjectsList", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode fetchProjectsList() {
        return new ObjectMapper().valueToTree(projectService.getAllProjects());
    }

    @RequestMapping(value = "/fetchPriorityList", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode fetchPriorityList() {
        return new ObjectMapper().valueToTree(priorityService.getAllPriorities());
    }

    @RequestMapping(value = "updateRequirement", method = RequestMethod.PUT)
    @ResponseBody
    public String updateRequirement(@RequestBody JsonNode data) {
        JsonObject jo = new JsonObject();
        try {
            Requirement r = parseJsonAsRequirement(data);
            if (r.getBeginDate().isEmpty())
                r.setBeginDate(null);
            if (r.getFinishDate().isEmpty())
                r.setFinishDate(null);
            r.setUserId(r.getUserId());
            r.setProjectId(r.getProjectId());
            requirementService.updateRequirement(r);
        } catch (RuntimeException r) {
            log.info("Update Requirement ->" + " Error while updating!");
            jo.addProperty("success", "false");
            return new Gson().toJson(jo);
        }
        log.info("Update Requirement ->" + " Success");
        jo.addProperty("success", "true");
        return new Gson().toJson(jo);
    }

    @RequestMapping(value = "deleteRequirement/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteRequirement(@PathVariable int id) {
        JsonObject jo = new JsonObject();
        try {
            requirementService.deleteRequirement(id);
        } catch (RuntimeException r) {
            log.info("Delete Requirement ->" + " Error while deleting!");
            jo.addProperty("success", "false");
            return new Gson().toJson(jo);
        }
        log.info("Delete Requirement ->" + " Success");
        jo.addProperty("success", "true");
        return new Gson().toJson(jo);
    }


    private Requirement parseJsonAsRequirement(JsonNode data) {
        return new Gson().fromJson(String.valueOf(data), Requirement.class);
    }

    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    @ResponseBody
    public String changeStatus(@RequestBody JsonNode rh) {
        JsonObject jo = new JsonObject();
        requirementHistoryService.insertRH(new RequirementHistory(rh.get("id").getValueAsInt(), rh.get("status").getValueAsInt(), rh.get("text").getValueAsText()));

        //Status to check: 2 (Working), 5(Cancel), 6 (Complete).
        if (rh.get("status").getValueAsInt() == 2 || (rh.get("status").getValueAsInt() == 5 || rh.get("status").getValueAsInt() == 6)) {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Requirement r = requirementService.getRequirementById(rh.get("id").getValueAsInt());

            //If the status is 2 (Working), we must check if the requirement has a BEGIN date, if not we assign the current one.
            if (rh.get("status").getValueAsInt() == 2 && r.getBeginDate() == null) {
                r.setBeginDate(ft.format(dNow));
            }

            if (rh.get("status").getValueAsInt() == 5 || rh.get("status").getValueAsInt() == 6) {
                r.setFinishDate(ft.format(dNow));
            }

            requirementService.updateRequirement(r);
        }

        return new Gson().toJson(jo);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Requirement requirement, BindingResult result,
                         Model model) {
        log.info("Requirement --> " + " Creating requirement " + requirement);

        if (result.hasErrors()) {
            log.info(" " + result);
            populateModel(model);
            return "requirements/new.html";
        }


        requirementService.insertRequirement(requirement);
        requirementHistoryService.insertRH(new RequirementHistory(requirement.getId(), new Integer(1), "New requirement created", requirementService.getRequirementById(requirement.getId()).getCreationDate()));
        if (requirement.getUserId() != null) {
            model.addAttribute("user", userService.getUserById(requirement.getUserId()));
        } else {
            model.addAttribute("user", new User("Not assigned"));
        }
        model.addAttribute("createdItem", requirementService.getRequirementById(requirement.getId()));

        log.info("Requirement Created -> " + requirement);

        //TODO: Check for specific exceptions thrown in database, then return a custom error page.

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

    public void setRequirementHistoryService(RequirementHistoryService requirementHistoryService) {
        this.requirementHistoryService = requirementHistoryService;
    }
}
