package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ReservationRepository reservationRepository;


    @Autowired
    private final ActivityRepository activityRepository;


    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @GetMapping
    public String activity(@PathVariable long id, Model model){
        Activity currentActivity = new Activity();
        Optional<Activity> activityOne = activityRepository.findById(id);
        model.addAttribute("activityOne", activityRepository.findById(id));
        model.addAttribute("activityList", activityRepository.findAll());
        return"activity";

    }
    @GetMapping("/create")
    public String getAll(Model model) throws Exception{
        model.addAttribute("newActivity", new Activity());
        return "activity";
    }


    @PostMapping("/create")
    public String activityFormProcess(Activity activity){

        activityRepository.save(activity);
        return "redirect:/admin";
    }

}
