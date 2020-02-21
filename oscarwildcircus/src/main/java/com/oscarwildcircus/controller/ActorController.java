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
    private final ActorRepository actorRepository;
    @Autowired
    private final StorageService storageService;

    public ActorController(StorageService storageService, ActorRepository actorRepository){
        this.storageService= storageService;
        this.actorRepository = actorRepository;

    }
    @PostMapping("actor/create")
    public String actorFormProcess(Actor actor){

        storageService.store(actor.getPortrait());
        actor.setPortraitUrl("/files/" + actor.getPortrait().getOriginalFilename());
        actorRepository.save(actor);
        return "redirect:/about";

    }
    @GetMapping("/actor")
    public String actor(Model model){
        model.addAttribute("actorList", actorRepository.findAll());
        return "pages/actor";
    }


}
