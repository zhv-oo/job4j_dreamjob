package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateDbStore;
import java.util.Collection;

@ThreadSafe
@Service
public class CandidateService {
    private final CandidateDbStore store;

    private CandidateService(CandidateDbStore store) {
        this.store = store;
    }

    public Candidate add(Candidate candidate) {
        store.add(candidate);
        return candidate;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }
}
