package com.inndra.pm.web.controller;

import com.google.gson.Gson;
import com.inndra.pm.core.domain.model.Criteria;
import com.inndra.pm.core.domain.model.Record;
import com.inndra.pm.core.domain.model.User;
import com.inndra.pm.core.service.RecordService;
import com.inndra.pm.core.service.UserService;
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
 * Created by edwin.rubio on 6/2/2014.
 */
@Controller
@RequestMapping(value = "/record")
public class RecordController {

    private static final Logger log = LoggerFactory.getLogger(RecordController.class);

    public static final String PAGE_OVERVIEW = "records/overview.html";
    public static final String PAGE_ADD = "records/add.html";
    public static final String PAGE_CREATED = "records/created.html";
    public static final String PAGE_SEARCH = "records/search.html";
    public static final String PAGE_EDIT = "records/edit.html";

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String showMenu( Model model) {
        log.info("Record -->", "Record Overview");

        List<Record> recordList = recordService.
                getLastRecords(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        List<User> userList = new ArrayList<User>();
        fillRecordList(recordList,userList);

        if (recordList.isEmpty() || recordList.size() == 0) {
            log.info("recordList isEmpty");
            model.addAttribute("msg","None Activity");
            return PAGE_OVERVIEW;
        }
        model.addAttribute("recordList",recordList);
        model.addAttribute("userList",userList);
        return PAGE_OVERVIEW;
   }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String preAdd(@ModelAttribute Record record, BindingResult result,
                         Model model) {
        log.info("Record -->", "Adding records");
        if (result.hasErrors()) {
            log.info(" " + result);
        }
        model.addAttribute("userList", userService.getAllUsers());
        return PAGE_ADD;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Record record, BindingResult result,
                      Model model) {
        log.info("Record -->", "Record added");
        log.info("inbound --> " + record);

        if (result.hasErrors()) {
            log.info(" " + result);
            model.addAttribute("userList", userService.getAllUsers());
            return PAGE_ADD;
        }
        recordService.insertRecord(record);
        Record recordById = recordService.getRecordById(record.getId());
        if (recordById.getBeginDate() == null) {
            recordById.setBeginDate(recordById.getCreationDate());
            recordService.updateRecord(recordById);
        }
        return "redirect:menu";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute Record record, @ModelAttribute Criteria criteria) {
        log.info("Record -->", "Record search");
        log.info("inbound --> " + criteria);
        return PAGE_SEARCH;
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(@ModelAttribute @Valid Criteria criteria, BindingResult result,
                       Model model) {
        log.info("criteria found --> " + criteria);

        List<Record> recordList;
        List<User> userList = new ArrayList<User>();

        recordList = getRecords(criteria);
        fillRecordList(recordList, userList);

        if (result.hasErrors() || recordList.isEmpty() || recordList.size() == 0) {
            log.info(" " + result);
            log.info("recordList isEmpty");
            return PAGE_SEARCH;
        }
        model.addAttribute("users", userList);
        model.addAttribute("recordList", recordList);
        return PAGE_SEARCH;
    }

    private List<Record> getRecords(Criteria criteria) {
        List<Record> recordList;
        if (criteria.getType().equals(1)) {
            recordList = recordService.getRecordByUser(criteria.getText());
        } else {
            recordList = recordService.getRecordByText(criteria.getText());
        }
        return recordList;
    }

    private void fillRecordList(List<Record> recordList, List<User> userList) {
        for (Record rec : recordList) {
            userList.add(userService.getUserById(rec.getUserId()));
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String preUpdate(@RequestParam(value = "id") Integer id,
                            Model model) {
        log.info("Record -->", "Record editing");
        log.info("inbound --> " + id);

        Record recordById = recordService.getRecordById(id);
        model.addAttribute("record", recordById);
        log.info("date input--> " + recordById.getBeginDate());

        model.addAttribute("userRecord", userService.getUserById(recordById.getUserId()));
        model.addAttribute("userList", userService.getAllUsers());
        return PAGE_EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute @Valid Record record, BindingResult result,
                         Model model) {
        log.info("Record -->", "Record updated");
        log.info("inbound --> " + record);

        if (result.hasErrors()) {
            log.info(" " + result);
            model.addAttribute("userList", userService.getAllUsers());
            return PAGE_EDIT;
        }
        Record recordFound = recordService.getRecordById(record.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (dateFormat.format(recordFound.getCreationDate()).equals(dateFormat.format(new Date()))) {
            recordService.updateRecord(record);
            return "redirect:/record/menu";
        }
        recordService.updateRecord(record);
        log.info("date output--> " + record.getBeginDate());
        model.addAttribute("userRecord", userService.getUserById(record.getUserId()));
        model.addAttribute("record", recordService.getRecordById(record.getId()));
        return PAGE_CREATED;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {

        log.info("Record -->", "Record delete");
        log.info("inbound --> " + id);

        try {
            recordService.deleteRecord(id);
        } catch (RuntimeException r) {
            log.info("Delete Requirement ->" + " Error while deleting!");
            return new Gson().toJson("{'success':'false'}");
        }
        log.info("Delete Requirement ->" + " Success");
        return new Gson().toJson("{'success':'true'}");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

}