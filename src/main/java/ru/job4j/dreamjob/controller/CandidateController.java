package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.CandidateStore;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CandidateController {

    private final CandidateStore store = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String posts(Model model) {
        model.addAttribute("candidates", store.findAll());
        model.addAttribute("formatter", new SimpleDateFormat("dd.MM.yyyy HH:mm"));
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("post", new Candidate(0, "Заполните поле", "Заполните описание", new Date()));
        return "addCandidate";
    }
}
