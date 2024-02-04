package com.journaldev.spring.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MathController {

    @RequestMapping(value = "/math", method = RequestMethod.GET)
    public String showMathGet(@RequestParam Double a, @RequestParam Double b, @RequestParam Double c, Model model) {
        double d=Math.pow(b,2)-4*a*c;

        if(d>0)
        {
            model.addAttribute("x1", (-b+Math.sqrt(d))/(2*a));
            model.addAttribute("x2", (-b-Math.sqrt(d))/(2*a));
            model.addAttribute("msg", "Two roots");
        }
        else if (d==0)
        {
            model.addAttribute("msg", "One roots");
            model.addAttribute("x1", (b/(-2*a)));
        }else {
            model.addAttribute("msg", "No roots found");
            System.out.println("No solutions found");
        }
        return "result";
    }

    @RequestMapping(value = "/show_calc", method = RequestMethod.GET)
    public String showMath() {
        return "math";
    }

    @RequestMapping(value = "/triangle", method = RequestMethod.GET)
    public String showTriangle(@RequestParam Double a, @RequestParam Double b, @RequestParam Double c, Model model) {
        if(!triangleIsExists(a,b,c)){
            return "error";
        }
        Double p = (a + b + c)/2;
        Double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        model.addAttribute("square" , square);
        return "square";
    }
    @RequestMapping(value = "/triangle_square", method = RequestMethod.GET)
    public String showTriangle() {
        return "triangle";
    }

    private boolean triangleIsExists(double a , double b , double c){
        if (a + b <= c){
            return false;
        }
        if(a + c <= b){
            return false;
        }
        if(b + c <= a){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/math", method = RequestMethod.POST)
    public String showMathPost(@RequestParam Double a, @RequestParam Double b, @RequestParam Double c, Model model) {
        double d=Math.pow(b,2)-4*a*c;

        if(d>0)
        {
            model.addAttribute("x1", (-b+Math.sqrt(d))/(2*a));
            model.addAttribute("x2", (-b-Math.sqrt(d))/(2*a));
            model.addAttribute("msg", "Two roots");
        }
        else if (d==0)
        {
            model.addAttribute("msg", "One roots");
            model.addAttribute("x1", (b/(-2*a)));
        }else {
            model.addAttribute("msg", "No roots found");
            System.out.println("No solutions found");
        }
        return "result";
    }

    @RequestMapping(value = "/math_sum", method = RequestMethod.GET)
    public String showMathWSum(@RequestParam List<Integer> nums, Model model) {
        model.addAttribute("msg", " Size of list : " + nums.size());

        return "result";
    }

    @RequestMapping(value = "/print_all", method = RequestMethod.GET)
    public String printAllGet(@RequestParam Map<String , String> all, Model model) {
        StringBuilder builder = new StringBuilder(128);
        all.forEach((k,v) -> {
            builder.append("<br>");
            builder.append("Key : " + k);
            builder.append(" -> Value : " + v);

        });
        model.addAttribute("msg", " All pairs : " + builder.toString());

        return "result";
    }
    @RequestMapping(value = "/print_all", method = RequestMethod.POST)
    public String printAllPost(@RequestParam Map<String , String> all, Model model) {
        StringBuilder builder = new StringBuilder(128);
        all.forEach((k,v) -> {
            builder.append("<br>");
            builder.append("Key : " + k);
            builder.append(" -> Value : " + v);

        });
        model.addAttribute("msg", " All pairs : " + builder.toString());

        return "result";
    }
}
