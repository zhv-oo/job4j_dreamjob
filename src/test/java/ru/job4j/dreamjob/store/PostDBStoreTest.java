package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", new City(1, "Москва"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(post.getName());
    }
    @Test
    public void whenCreateTwoPosts() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", new City(1, "Москва"));
        Post postTwo = new Post(1, "Python Job", new City(2,  "СПБ"));
        store.add(post);
        store.add(postTwo);
        Post postInDb = store.findById(postTwo.getId());
        assertThat(postInDb.getName()).isEqualTo(postTwo.getName());
    }
    @Test
    public void whenFindAllPosts() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", new City(1, "Москва"));
        Post postTwo = new Post(1, "Python Job", new City(2, "СПБ"));
        int size = store.findAll().size();
        store.add(post);
        store.add(postTwo);
        List<Post> postInDb = store.findAll();
        assertThat(postInDb.size()).isEqualTo(size + 2);
    }
    @Test
    public void whenFUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", new City(1, "Москва"));
        store.add(post);
        Post postTwo = new Post(post.getId(), "Python Job", new City(2,  "СПБ"));
        store.update(postTwo);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(postTwo.getName());
    }
}