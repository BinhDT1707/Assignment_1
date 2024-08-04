package com.fpt.fsa.assignment_1.controller;

import com.fpt.fsa.assignment_1.entity.Employee;
import com.fpt.fsa.assignment_1.enums.EGender;
import com.fpt.fsa.assignment_1.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private static final String GENDER = "genders";

    @GetMapping({"/", ""})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employeeService.getAllEmployees());
        modelAndView.setViewName("employee/listEmployee");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject(GENDER, EGender.values());
        modelAndView.setViewName("employee/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/add");
        if (result.hasErrors()) {
            return modelAndView;
        }
        employeeService.addEmployee(employee);
        modelAndView.setViewName("redirect:/employee/");
        return modelAndView;
    }
}
