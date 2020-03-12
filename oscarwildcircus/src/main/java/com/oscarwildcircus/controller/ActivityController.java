package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


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

    /**
     * this controller is used to display a list of activity
     * @param model
     * @return on activity page
     */
    @GetMapping
    public String getActivity( Model model){
        Activity currentActivity = new Activity();
        model.addAttribute("activityList", activityRepository.findAll());
        return"activity";

    }

    /**
     * this controller is used to create a list of activity
     *
     *
     *
     * @param model
     * @return to activity page
     * @throws Exception
     */
    @GetMapping("/create")
    public String getAll(@PathVariable long activityId, Model model) throws Exception{
        model.addAttribute("newActivity", new Activity());
        model.addAttribute("activityOne", activityRepository.findById(activityId));
        return "activity";
    }

    /**
     * this controller is used to save an activity
     * @param activity
     * @return on page admin if there is other things to create
     */
    @PostMapping("/create")
    public String activityFormProcess(Activity activity){

        activityRepository.save(activity);
        return "redirect:/admin";
    }



}
