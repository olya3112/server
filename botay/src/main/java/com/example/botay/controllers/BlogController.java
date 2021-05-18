package com.example.botay.controllers;

import com.example.botay.models.Post;
import com.example.botay.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

   @Autowired (required = false)
    private PostRepo postRepo;

   @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String titele, @RequestParam String  anonce, @RequestParam String fulltext,  Model model){
       Post post = new Post(titele, anonce, fulltext);
       postRepo.save(post);
       return "redirect:/blog";
    }

}
