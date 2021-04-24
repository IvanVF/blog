package com.fprojects.blog.controllers;

import com.fprojects.blog.entitys.Post;
import com.fprojects.blog.repositorys.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    /**
     *Страница блога со всеми статьями
     */
    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blogMain";
    }

    /**
     *Страница добавления статьи в блог
     */
    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blogAdd";
    }

    /**
     *Добавить статью в блог
     */
    @PostMapping("/blog/add")
    public String blogAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model) {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /**
     *Получить одну статью из блога
     */
    @GetMapping("/blog/{id}")
    public String blogGet(@PathVariable(value = "id") UUID id, Model model) {
        return changeOrGet("blogDetails", id, model);
    }

    /**
     *Изменить статью
     */
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") UUID id, Model model) {
        return changeOrGet("blogEdit", id, model);
    }

    /**
     *Редактирование статьи
     */
    @PostMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable (value = "id") UUID id,
                           @RequestParam String title,
                           @RequestParam String anons,
                           @RequestParam String fullText,
                           Model model) {
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        post.setAnons(anons);
        post.setFullText(fullText);
        post.setTitle(title);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /**
     *Редактирование статьи
     */
    @PostMapping("/blog/{id}/remove")
    public String blogDelete(@PathVariable (value = "id") UUID id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        postRepository.delete(post);
        return "redirect:/blog";
    }

    private String changeOrGet(String page, UUID id,  Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return page;
    }




}
