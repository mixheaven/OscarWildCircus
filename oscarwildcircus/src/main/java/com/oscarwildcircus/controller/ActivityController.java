package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ActivityController {

    @Autowired
    private final ActivityRepository activityRepository;


    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @PostMapping("activity/create")
    public String activityFormProcess(Activity activity){

        activityRepository.save(activity);
        return "redirect:/activity";
    }


    @GetMapping("/activity")
    public String activity(Model model){
        model.addAttribute("activityList", activityRepository.findAll());
        return"pages/activity";

    }
}
