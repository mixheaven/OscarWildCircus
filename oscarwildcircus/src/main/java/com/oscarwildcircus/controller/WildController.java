package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.WildRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WildController {

    @Autowired
    private WildRepository wildRepository;
    @Autowired
    private StorageService storageService;


    public WildController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("wildList", wildRepository.findAll());
        return "home";
    }

    @GetMapping("/create")
    public String getAll(Model model)throws Exception{
        model.addAttribute("newWild", new Wild());
        return "home";
    }

    @PostMapping("/create")
    public String wildFormProcess(Wild wild){

        storageService.store(wild.getPicture());
        wild.setPictureUrl("/files/" + wild.getPicture().getOriginalFilename());
        wildRepository.save(wild);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/delete")
    public String deleteWild(@PathVariable long id) throws Exception{
        wildRepository.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String updateAboutForm(@PathVariable long id, Model model) throws Exception{
        Optional<Wild> newWild = wildRepository.findById(id);
        model.addAttribute("pathMethod", "/" + id + "/edit");
        model.addAttribute("newWild", newWild);

        return "/wildForm";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id, @Valid @ModelAttribute("newWild") Wild newWild, BindingResult bindingResult, Model model) throws Exception{
        Optional<Wild> currentWild = wildRepository.findById(id);
        if (bindingResult.hasErrors()){
            model.addAttribute("newWild", newWild);
            model.addAttribute("currentWild", currentWild);
            model.addAttribute("pathMethod","/" + id + "/edit");
            return "/admin";
        }
        wildRepository.save(newWild);
        return "redirect:/admin";
    }
}
