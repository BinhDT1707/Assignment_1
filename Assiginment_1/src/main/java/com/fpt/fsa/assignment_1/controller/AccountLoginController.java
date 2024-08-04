package com.fpt.fsa.assignment_1.controller;

import com.fpt.fsa.assignment_1.entity.Account;
import com.fpt.fsa.assignment_1.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AccountLoginController {
    private static final String EMPLOYEE_LOGIN = "/login";
    private final AccountServiceImpl accountServiceImpl;

    @GetMapping({"/","/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(EMPLOYEE_LOGIN);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Account account, BindingResult bindingResult,
                              HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(EMPLOYEE_LOGIN);
            return modelAndView;
        }
        Account existingAccount = (Account) accountServiceImpl.loadUserByUsername(account.getAccountName());
        if (existingAccount != null && accountServiceImpl.checkPassword(account.getPassword(), existingAccount.getPassword())) {
            session.setAttribute("account", existingAccount);
            modelAndView.setViewName("redirect:/employees");
            redirectAttributes.addFlashAttribute("message", "Login successfully");
        } else {
            modelAndView.setViewName(EMPLOYEE_LOGIN);
            modelAndView.addObject("error", "Invalid account name or password");
        }

        return modelAndView;
    }

}
