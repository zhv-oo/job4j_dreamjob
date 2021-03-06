package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class PostStore {
    private final AtomicInteger id = new AtomicInteger(0);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(id.incrementAndGet(), new Post(id.get(), "Junior Java Job", "For Junior", new Date(), new City(1, "Москва")));
        posts.put(id.incrementAndGet(), new Post(id.get(), "Middle Java Job", "For Middle", new Date(), new City(2, "СПб")));
        posts.put(id.incrementAndGet(), new Post(id.get(), "Senior Java Job", "For Senior", new Date(), new City(3, "Екб")));
    }

    public void add(Post post) {
        int num = id.incrementAndGet();
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