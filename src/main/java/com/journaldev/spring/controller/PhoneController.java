package com.journaldev.spring.controller;

import com.journaldev.spring.model.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

    private static List<Phone> phones = new ArrayList<>();

    static {
        phones.add(new Phone(22,"Honor p60", 3000));
        phones.add(new Phone(23,"Honor p80", 4000));
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPhone(@RequestBody Phone phone, Model model){
        boolean isAdded = phones.add(phone);
        model.addAttribute("msg", isAdded? "Phone with id " + phone.getId() + "added successfully" : "Not added");
        return "result";
    }
}
