package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.entity.Actor;
import com.oscarwildcircus.entity.Reservation;
import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.repository.ActorRepository;
import com.oscarwildcircus.repository.ReservationRepository;
import com.oscarwildcircus.repository.WildRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private WildRepository wildRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /*
    @GetMapping("/create")
    public String getAll(Model model) throws Exception {
        model.addAttribute("newWild", new Wild());
        model.addAttribute("newActivity",new Activity());
        model.addAttribute("newActor",new Actor());
        return "pages/admin";
    }
*/

/*    @PostMapping("/create")
    public String adminPost(@Valid @ModelAttribute Activity activity, Wild wild, Actor actor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newActivity", new Activity());
            model.addAttribute("newWild", new Wild());
            model.addAttribute("newActor", new Actor());
            return "pages/admin";
        }
        return "redirect:/admin";
    }*/

    /**
     * this controller is used to show a list of distinct information
     *
     * @param model
     * @return page admin
     */
    @GetMapping
    public String getAdminList(Model model) {
        Wild currentWild= new Wild();
        Activity currentActivity = new Activity();
        Actor currentActor = new Actor();
        model.addAttribute("newActor", currentActor);
        model.addAttribute("newActivity", currentActivity);
        model.addAttribute("newWild", currentWild);
        model.addAttribute("page", "adminListPage");
        model.addAttribute("wildList", wildRepository.findAll());
        model.addAttribute("activityList", activityRepository.findAll());
        model.addAttribute("actorList", actorRepository.findAll());

        return "admin";
    }
    @GetMapping("/ticket")
    public String ticket(Model model){
        Reservation currentReservation = new Reservation();
        model.addAttribute("newReservation",currentReservation);
        model.addAttribute("reservationList",reservationRepository.findAll());
        return "ticket";
    }

}