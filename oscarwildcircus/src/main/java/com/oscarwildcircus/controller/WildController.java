package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.WildRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WildController {

    @Autowired
    private WildRepository wildRepository;
    @Autowired
    private StorageService storageService;


    public WildController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/wild/create")
    public String wildFormProcess(Wild wild){

        storageService.store(wild.getPicture());
        wild.setPictureUrl("/files/" + wild.getPicture().getOriginalFilename());
        wildRepository.save(wild);
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("wildList", wildRepository.findAll());
        return "pages/home";
    }
}
