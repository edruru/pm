package com.inndra.pm.web.controller;

import com.google.gson.Gson;
import com.inndra.pm.core.domain.model.Criteria;
import com.inndra.pm.core.domain.model.User;
import com.inndra.pm.core.service.UserService;
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
 * Created by edwin.rubio on 6/30/2014.
 */

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static final String PAGE_MENU = "users/menu.html";
    public static final String PAGE_ADD = "users/add.html";
    public static final String PAGE_CREATED = "users/created.html";
    public static final String PAGE_SEARCH = "users/search.html";
    public static final String PAGE_EDIT = "users/edit.html";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String showMenu() {
        log.info("Users -->", "Users menu loaded");
        return PAGE_MENU;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String preAdd(@ModelAttribute User user, Model model, BindingResult result) {
        log.info("Users -->", "Adding users");
        if (result.hasErrors()) {
            log.info(" " + result);
        }
        return PAGE_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid User user, BindingResult result,
                      Model model) {
        log.info("Users -->", "Adding users" + user);
        if (result.hasErrors()) {
            log.info(" " + result);
            return PAGE_ADD;
        }
        userService.insertUser(user);
        model.addAttribute("user", user);
        return PAGE_CREATED;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute User user, @ModelAttribute Criteria criteria) {
        log.info("Users -->", "Search users" + user);
        log.info("Inbound --> " + criteria);
        return PAGE_SEARCH;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(@ModelAttribute @Valid Criteria criteria, BindingResult result,
                       Model model) {
        log.info("criteria found --> " + criteria);

        List<User> userList = new ArrayList<User>();

        userList = getUsers(criteria);

        if (result.hasErrors() || userList.isEmpty()) {
            log.info(" " + result);
            log.info("userList isEmpty");
            return PAGE_SEARCH;
        }
        model.addAttribute("userList", userList);
        return PAGE_SEARCH;
    }

    private List<User> getUsers(Criteria criteria) {
        List<User> userList;
        if (criteria.getType() == 1) {
            userList = userService.getRecordByUserId(criteria.getText());
        } else {
            userList = userService.getRecordByUserName(criteria.getText());
        }
        return userList;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String preUpdate(@RequestParam(value = "id") Integer id,
                            Model model) {
        log.info("User -->", "User editing");
        log.info("Inbound --> " + id);

        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return PAGE_EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid User user, BindingResult result,
                         Model model) {
        log.info("User -->", "User updated");
        log.info("inbound --> " + user);

        if (result.hasErrors()) {
            log.info("error" + result);
            return PAGE_EDIT;
        }
        userService.updateUser(user);
        model.addAttribute("user", userService.getUserById(user.getId()));
        return PAGE_CREATED;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {

        log.info("Record -->", "Record delete");
        log.info("inbound --> " + id);

        try {
            userService.deleteUser(id);
        } catch (RuntimeException r) {
            log.info("Delete User ->" + " Error while deleting!");
            return new Gson().toJson("{'success':'false'}");
        }
        log.info("Delete Requirement ->" + " Success");
        return new Gson().toJson("{'success':'true'}");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
