package com.journaldev.spring.controller;

import com.journaldev.spring.dao.CustomerDao;
import com.journaldev.spring.model.Customer;


import com.journaldev.spring.utils.EmailUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/CustomerManager")
@EnableWebMvc
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);
    private EmailUtils email;


    @Autowired
    public void setEmail(EmailUtils email) {
        this.email = email;
    }


    private CustomerDao dao;
    @Autowired
    public void setDao(CustomerDao dao) {
        this.dao = dao;
    }

    @RequestMapping
    public ModelAndView home() {
        List<Customer> listCustomer = dao.getCustomers();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }

    @RequestMapping(value = "/new")
    public String newCustomerForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        logger.info("hello");
        return "new_customer";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        String customerEmail = customer.getEmail();
        email.send(customerEmail, "Hi there", "Server Date :" + new Date());
        dao.save(customer);
        return "redirect:/CustomerManager/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("customer") Customer customer) {
        dao.update(customer);
        return "redirect:/CustomerManager/";
    }

    @RequestMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Customer customer = dao.getCustomerById(id);
        mav.addObject("customer", customer);
        return mav;
    }
    @RequestMapping(value ="/delete", method = RequestMethod.GET)
    public String deleteCustomerForm(@RequestParam int id) {
        dao.delete(id);
        return "redirect:/CustomerManager/";
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = dao.getCustomersByParameter(keyword);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("result", result);
        return mav;
    }

}
