package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.entity.Actor;
import com.oscarwildcircus.entity.Wild;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/create")
    public String getAll(Model model) throws Exception {
        model.addAttribute("newWild", new Wild());
        model.addAttribute("newActivity",new Activity());
        model.addAttribute("newActor",new Actor());
        return "pages/admin";
    }


    @PostMapping("/create")
    public String adminPost(@Valid @ModelAttribute Activity activity, Wild wild, Actor actor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newActivity", new Activity());
            model.addAttribute("newWIld", new Wild());
            model.addAttribute("newActor", new Actor());
            return "pages/admin";
        }
        /*Wild currentWild= new Wild();
        Activity currentActivity = new Activity();
        Actor currentActor = new Actor();
        model.addAttribute("newActor", currentActor);
        model.addAttribute("newWild", currentWild);
        model.addAttribute("newActivity", currentActivity);*/
        return "redirect:/admin";
    }
    @GetMapping
    public String getAdminList(Model model) {
        Wild currentWild= new Wild();
        Activity currentActivity = new Activity();
        Actor currentActor = new Actor();
        model.addAttribute("newActor", currentActor);
        model.addAttribute("newActivity", currentActivity);
        model.addAttribute("newWild", currentWild);
        model.addAttribute("page", "adminListPage");
        return "pages/admin";
    }

}