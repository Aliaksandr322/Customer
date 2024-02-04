package com.journaldev.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Root Page Requested, Server locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String test() {
//		System.out.println("Test");
//
//		return "home";
//	}
//
//	@RequestMapping(value = "/old", method = RequestMethod.GET)
//	public void testOld(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.getWriter().println("Hello from Spring Controller");
//	}
//
//	@RequestMapping(value = "/test2", method = RequestMethod.GET)
//	public ModelAndView testOld(ModelAndView modelAndView) throws IOException {
//		modelAndView.addObject("first", Math.random());
//		modelAndView.addObject("second", Math.random());
//		modelAndView.setViewName("fs");
//		return modelAndView;
//	}
//	@RequestMapping(value = "/test3", method = RequestMethod.GET)
//	public String testOld2(Model model) throws IOException {
//		model.addAttribute("first", Math.random());
//		model.addAttribute("second", Math.random());
//		return "fs";
//	}
//
//
//
//
//	@RequestMapping(value = "/test4", method = RequestMethod.GET)
//	public ModelAndView testOld2() throws IOException {
//		ModelAndView modelAndView = new ModelAndView("fs");
//		modelAndView.addObject("first", Math.random());
//		modelAndView.addObject("second", Math.random());
//		return modelAndView;
//	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String hello(@Validated User user, Model model) {
		if(user.getLogin().equals("John@gmail.com") && user.getPassword().equals("123")){
			model.addAttribute("name", "John");
			return "welcome";
		}
		else return "error";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getName());
		return "user";
	}
}
