package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {
    private final AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
        posts.put(id.incrementAndGet(), new Candidate(id.get(), "Junior Java Job", "For Junior", new Date()));
        posts.put(id.incrementAndGet(), new Candidate(id.get(), "Middle Java Job", "For Middle", new Date()));
        posts.put(id.incrementAndGet(), new Candidate(id.get(), "Senior Java Job", "For Senior", new Date()));
    }

    public void add(Candidate candidate) {
        int num = id.incrementAndGet();
        candidate.setId(num);
        candidate.setCreated(new Date());
        posts.put(num, candidate);
    }

    public Collection<Candidate> findAll() {
        return posts.values();
    }

    public Candidate findById(int id) {
        return posts.get(id);
    }

    public void update(Candidate candidate) {
        candidate.setCreated(new Date());
        posts.replace(candidate.getId(), candidate);
    }
}