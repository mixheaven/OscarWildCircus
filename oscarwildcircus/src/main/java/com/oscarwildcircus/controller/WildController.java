package com.oscarwildcircus.controller;

import com.oscarwildcircus.entity.Wild;
import com.oscarwildcircus.repository.WildRepository;
import com.oscarwildcircus.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("wildList", wildRepository.findAll());
        return "pages/home";
    }

    @GetMapping("wild/create")
    public String getAll(Model model)throws Exception{
        model.addAttribute("newWild", new Wild());
        return "pages/home";
    }

    @PostMapping("wild/create")
    public String wildFormProcess(Wild wild){

        storageService.store(wild.getPicture());
        wild.setPictureUrl("/files/" + wild.getPicture().getOriginalFilename());
        wildRepository.save(wild);
        return "redirect:/admin";
    }

}
