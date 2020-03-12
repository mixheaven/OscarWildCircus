package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.entity.Reservation;
import com.oscarwildcircus.repository.ActivityRepository;
import com.oscarwildcircus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservation")
    public String reservation(Model model){

        model.addAttribute("activityList",activityRepository.findAll());
        model.addAttribute("newReservation", new Reservation());
        return "reservation";
    }

    /**
     * this controller is used to display reservation form
     * @param model
     * @return to the reservation form page
     * @throws Exception
     */
    @GetMapping("/reservation/create")
    public String getAll(@PathVariable long id, Model model)throws Exception{
        Optional<Activity> activityOne = activityRepository.findById(id);
        model.addAttribute("currentActivity", activityOne);
        model.addAttribute("activityList", activityRepository.findAll());
        model.addAttribute("newReservation", new Reservation());
        return "reservation";
    }

    @PostMapping("/reservation/create")
    public String reservationFromProcess( Reservation reservation, Model model){

        reservationRepository.save(reservation);
        return "redirect:/reservation/";
    }


}
