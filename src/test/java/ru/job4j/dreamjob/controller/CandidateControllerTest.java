package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.services.CandidateService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CandidateControllerTest {
    @Test
    public void whenPosts() {
        List<Candidate> candidates = Arrays.asList(
                new Candidate(1, "Ivan", "Java", new Date()),
                new Candidate(2, "Fedr", "Python", new Date())
        );
        Model model = mock(Model.class);
        CandidateService candidateService = mock(CandidateService.class);
        when(candidateService.findAll()).thenReturn(candidates);
        CandidateController candidateController = new CandidateController(
                candidateService
        );
        String page = candidateController.candidates(model, new MockHttpSession());
        verify(model).addAttribute("candidates", candidates);
        assertThat(page).isEqualTo("candidates");
    }

    @Test
    public void whenAddCandidate() {
        Candidate input = new Candidate(1, "Ivan", "Java", new Date());
        Model model = mock(Model.class);
        CandidateService candidateService = mock(CandidateService.class);
        when(candidateService.add(input)).thenReturn(input);
        CandidateController candidateController = new CandidateController(
                candidateService
        );
        String page = candidateController.addCandidate(model, new MockHttpSession());
        assertThat(page).isEqualTo("addCandidate");
    }

    @Test
    public void whenCreateCandidate() throws IOException {
        Candidate input = new Candidate(1, "Ivan", "Java", new Date());
        CandidateService candidateService = mock(CandidateService.class);
        when(candidateService.add(input)).thenReturn(input);
        CandidateController candidateController = new CandidateController(
                candidateService
        );
        String page = candidateController.createCandidate(input, new MockMultipartFile("1", new byte[1]));
        verify(candidateService).add(input);
        assertThat(page).isEqualTo("redirect:/candidates");
    }

    @Test
    public void whenUpdateCandidate() throws IOException {
        Candidate input = new Candidate(1, "Ivan", "Java", new Date());
        Candidate newIn = new Candidate(1, "Ivan", "Python", new Date());
        CandidateService candidateService = mock(CandidateService.class);
        when(candidateService.add(input)).thenReturn(input);
        CandidateController candidateController = new CandidateController(
                candidateService
        );
        String page = candidateController.updateCandidate(input, new MockMultipartFile("1", new byte[1]));
        verify(candidateService).update(newIn);
        assertThat(page).isEqualTo("redirect:/candidates");
    }
}