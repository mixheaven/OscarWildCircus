package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private final ActivityRepository activityRepository;


    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @GetMapping
    public String activity(Model model){
        Activity currentActivity = new Activity();
        model.addAttribute("activityList", activityRepository.findAll());
        return"pages/activity";

    }
    @GetMapping("/create")
    public String getAll(Model model) throws Exception{
        model.addAttribute("newActivity", new Activity());
        return "pages/activity";
    }


    @PostMapping("/create")
    public String activityFormProcess(@Valid @ModelAttribute Activity activity, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("newActivity", new Activity());
            return "pages/admin";
        }
        activityRepository.save(activity);
        return "redirect:/admin";
    }
}
