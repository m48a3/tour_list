package com.tour.tour_list.controllers;

import com.tour.tour_list.models.Tour;
import com.tour.tour_list.repo.tour_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private tour_repository _tour_repository;

    @GetMapping("/")
    public String home( Model model) {
        Iterable<Tour> tours = _tour_repository.findAll();
        model.addAttribute("Tours",tours);
        model.addAttribute("title", "Главная страница");

        return "home";
    }

    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "О работе");
        return "about";
    }

    @GetMapping("/{id}")
    public String new_tour(@PathVariable(value = "id")long id, Model model) {
        Optional<Tour> tour = _tour_repository.findById(id);
        ArrayList<Tour> res = new ArrayList<>();
        tour.ifPresent(res::add);
        model.addAttribute("title", "Тур");
        model.addAttribute("tour",res);
        return "new_tour";
    }
}