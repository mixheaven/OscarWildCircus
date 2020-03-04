package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Actor;
import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.ActorRepository;
import com.oscarwildcircus.repository.WildRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ActorController {

    @Autowired
    private WildRepository wildRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private StorageService storageService;


    public ActorController(StorageService storageService){
        this.storageService= storageService;
    }

    /**
     * this controller is used to display the about page
     *
     * @param model
     * @return template page about
     */
    @GetMapping("/actor")
    public String actor(Model model){
        Actor currentActor = new Actor();
        Wild currentWild = new Wild();
        model.addAttribute("wildList",wildRepository.findAll());
        model.addAttribute("actorList", actorRepository.findAll());
        return "pages/about";
    }

    /**
     * this controller is used to display the actor form
     *
     * @param model
     * @return template of creation form
     * @throws Exception
     */
    @GetMapping("/actor/create")
    public String getAll(Model model) throws Exception{
        model.addAttribute("newActor", new Actor());
        return "pages/about";
    }

    /**
     * this controller is used to create an to save an actor
     *
     * @param actor
     * @return directly at admin page for other completion
     */
    @PostMapping("/actor/create")
    public String actorFormProcess(Actor actor){

        storageService.store(actor.getPortrait());
        actor.setPortraitUrl("/files/" + actor.getPortrait().getOriginalFilename());
        actorRepository.save(actor);
        return "redirect:/admin";

    }


}
