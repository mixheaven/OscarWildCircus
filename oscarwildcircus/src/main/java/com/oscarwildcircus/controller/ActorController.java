package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Actor;
import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.ActorRepository;
import com.oscarwildcircus.repository.WildRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/actor")
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
    @GetMapping
    public String actor(Model model){
        Actor currentActor = new Actor();
        Wild currentWild = new Wild();
        model.addAttribute("wildList",wildRepository.findAll());
        model.addAttribute("actorList", actorRepository.findAll());
        return "about";
    }

    /**
     * this controller is used to display the actor form
     *
     * @param model
     * @return template of creation form
     * @throws Exception
     */
    @GetMapping("/create")
    public String getAll(Model model) throws Exception{
        model.addAttribute("newActor", new Actor());
        model.addAttribute("htTpMethod", "PUT");
        model.addAttribute("pathMethod", "/actor/create");
        return "about";
    }

    /**
     * this controller is used to create an to save an actor
     *
     * @param actor
     * @return directly at admin page for other completion
     */
    @PostMapping("/create")
    public String actorFormProcess(Actor actor){

        storageService.store(actor.getPortrait());
        actor.setPortraitUrl("/files/" + actor.getPortrait().getOriginalFilename());
        actorRepository.save(actor);
        return "redirect:/admin";

    }
    @GetMapping("/{id}/delete")
    public String deleteActor(@PathVariable long id) throws Exception {
        actorRepository.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String upDateActorForm(@PathVariable long id, Model model) throws Exception{
        Optional<Actor> newActor = actorRepository.findById(id);
        model.addAttribute("pathMethod", "/actor/" + id + "/edit");
        model.addAttribute("newActor", newActor);

        return "/actorForm";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id, @Valid @ModelAttribute("newActor") Actor newActor, BindingResult bindingResult, Model model) throws Exception{
        Optional<Actor> currentActor = actorRepository.findById(id);
        if (bindingResult.hasErrors()){
            model.addAttribute("newActor", newActor);
            model.addAttribute("currentActor", currentActor);
            model.addAttribute("pathMethod","/actor/" + id + "/edit");
            return "/admin";
        }
        storageService.store(newActor.getPortrait());
        newActor.setPortraitUrl("/files/" + newActor.getPortrait().getOriginalFilename());
        actorRepository.save(newActor);
        return "redirect:/admin";

    }

}
