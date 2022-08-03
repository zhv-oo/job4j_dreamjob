package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CandidateDbStoreTest {
    private final CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
    @Test
    public void whenAddOneCandidate() {
        Candidate candidate = new Candidate(0, "Иванов", "Junior", new Date());
        store.add(candidate);
        Candidate canInDb = store.findById(candidate.getId());
        assertThat(canInDb.getName()).isEqualTo(candidate.getName());
    }
    @Test
    public void whenAddTwoCandidate() {
        Candidate candidate = new Candidate(0, "Иванов", "Junior", new Date());
        Candidate candidateTwo = new Candidate(1, "Петров", "NewBe", new Date());
        store.add(candidate);
        store.add(candidateTwo);
        Candidate canInDb = store.findById(candidateTwo.getId());
        assertThat(canInDb.getName()).isEqualTo(candidateTwo.getName());
    }
    @Test
    public void whenFindAllCandidates() {
        Candidate candidate = new Candidate(0, "Иванов", "Junior", new Date());
        Candidate candidateTwo = new Candidate(1, "Петров", "NewBe", new Date());
        int size = store.findAll().size();
        store.add(candidate);
        store.add(candidateTwo);
        List<Candidate> canInDb = store.findAll();
        assertThat(canInDb.size()).isEqualTo(size + 2);
    }
    @Test
    public void whenAUpdateCandidate() {
        Candidate candidate = new Candidate(0, "Иванов", "Junior", new Date());
        store.add(candidate);
        Candidate candidateTwo = new Candidate(candidate.getId(), "Петров", "NewBe", new Date());
        store.update(candidateTwo);
        Candidate canInDb = store.findById(candidate.getId());
        assertThat(canInDb.getName()).isEqualTo(candidateTwo.getName());
    }
}