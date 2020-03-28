package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
        model.addAttribute("activityId", activityRepository.findById(activityId));
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

    /**
     * this controller is used to delete an activity
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}/delete")
    public String deleteActivity(@PathVariable long id) throws Exception {
        activityRepository.deleteById(id);
        return "redirect:/admin";
    }

    /**
     * this controller is used to throw a form to edit the activity to change
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}/edit")
    public String updateActivityForm(@PathVariable long id, Model model) throws Exception {
        Optional<Activity> newActivity = activityRepository.findById(id);
        model.addAttribute("pathMethod", "/activity/" + id + "/edit");
        model.addAttribute("newActivity", newActivity);

        return "/activityForm";
    }

    /**
     * this controller is used to edit the activity change
     *
     * @param id
     * @param newActivity
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id, @Valid @ModelAttribute("newActivity") Activity newActivity, BindingResult bindingResult, Model model) throws Exception{
        Optional<Activity> currentActivity = activityRepository.findById(id);
        if (bindingResult.hasErrors()){
            model.addAttribute("newActivity", newActivity);
            model.addAttribute("currentActivity", currentActivity);
            model.addAttribute("pathMethod","/activity/" + id + "/edit");
            return "/admin";
        }
        activityRepository.save(newActivity);
        return "redirect:/admin";

    }

}
