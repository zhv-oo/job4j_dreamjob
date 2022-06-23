package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
        posts.put(id.addAndGet(1), new Candidate(id.get(), "Junior Java Job", "For Junior", new Date()));
        posts.put(id.addAndGet(1), new Candidate(id.get(), "Middle Java Job", "For Middle", new Date()));
        posts.put(id.addAndGet(1), new Candidate(id.get(), "Senior Java Job", "For Senior", new Date()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public void add(Candidate candidate) {
        int num = id.addAndGet(1);
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