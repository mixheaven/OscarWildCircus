package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Reservation;
import com.oscarwildcircus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservation")
    public String reservation(Model model){
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
    public String getAll(Model model)throws Exception{
        model.addAttribute("newReservation", new Reservation());
        return "reservation";
    }

    @PostMapping("/reservation/create")
    public String reservationFromProcess(Reservation reservation){
        reservationRepository.save(reservation);
        return "redirect:/reservation/";
    }


}
