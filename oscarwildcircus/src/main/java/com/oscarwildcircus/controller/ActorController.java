package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Actor;
import com.oscarwildcircus.repository.ActorRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private StorageService storageService;

    public ActorController(StorageService storageService){
        this.storageService= storageService;
    }

    @GetMapping("/actor")
    public String actor(Model model){
        Actor currentActor = new Actor();
        model.addAttribute("actorList", actorRepository.findAll());
        return "pages/about";
    }

    @GetMapping("/actor/create")
    public String getAll(Model model) throws Exception{
        model.addAttribute("newActor", new Actor());
        return "pages/about";
    }

    @PostMapping("/actor/create")
    public String actorFormProcess(Actor actor){

        storageService.store(actor.getPortrait());
        actor.setPortraitUrl("/files/" + actor.getPortrait().getOriginalFilename());
        actorRepository.save(actor);
        return "redirect:/admin";

    }

}
