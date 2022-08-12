package vttp.ssf_submission.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.ssf_submission.models.Articles;

@Repository
public class NewsRepository {


    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, Object> redisTemplate;
    // private RedisTemplate<String, Articles> rt;

    public void saveNews(Articles a) {

        String id = a.getId();
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
		// ListOperations<String, Articles> listOps = rt.opsForList();
        System.out.println(">>> SAVED\n\n" + id);
        System.out.println(">>> LOAD\n\n" + a);
        valueOps.set(id, a);
    }

    // Get article by id
    public Optional<Articles> getArticleById(String id) {

        System.out.printf("<<ID>> IS %s\n\n", id);
        Articles result = new Articles();

		// check if empty
        if (!redisTemplate.hasKey(id)) {
            System.out.printf(">>>> ID %s NOT FOUND\n\n", id);
            return Optional.empty();
        }

        System.out.println("HERRERERERE");

        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        
        // returns how many items in the cart
        long size = valueOps.size(id);
        
        if (size > 0) {
            Object news = valueOps.get(id);
            System.out.println("OBJECT -------------" + news);
            result = Articles.create((JsonObject) news);
        }
    
        return Optional.of(result);
	}
    // if (!redisTemplate.hasKey(id)) {
            

    // }
}
