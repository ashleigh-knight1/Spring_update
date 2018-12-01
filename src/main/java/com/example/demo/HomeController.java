package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Messages());
        return "messageForm";
    }

    @PostMapping("/process")
    public String processMessages(@Valid Messages message,
    BindingResult result) {
        if (result.hasErrors()) {
            return "messageForm";
        }
        messagesRepository.save(message);
        return "redirect:/";
    }
   /* @RequestMapping("messages/{id}")
    public String viewMessages(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messagesRepository.findById(id));
        return "view";
        //findOne (id) doesnt work so i used find By id. */
    }

