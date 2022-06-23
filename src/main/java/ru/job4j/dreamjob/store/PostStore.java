package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {
    private final AtomicInteger id = new AtomicInteger(0);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(id.addAndGet(1), new Post(id.get(), "Junior Java Job", "For Junior", new Date()));
        posts.put(id.addAndGet(1), new Post(id.get(), "Middle Java Job", "For Middle", new Date()));
        posts.put(id.addAndGet(1), new Post(id.get(), "Senior Java Job", "For Senior", new Date()));
    }

    public void add(Post post) {
        int num = id.addAndGet(1);
        post.setId(num);
        post.setCreated(new Date());
        posts.put(num, post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        post.setCreated(new Date());
        posts.replace(post.getId(), post);
    }
}