package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
        posts.put(1, new Candidate(1, "Junior Java Job", "For Junior", new Date()));
        posts.put(2, new Candidate(2, "Middle Java Job", "For Middle", new Date()));
        posts.put(3, new Candidate(3, "Senior Java Job", "For Senior", new Date()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return posts.values();
    }
}