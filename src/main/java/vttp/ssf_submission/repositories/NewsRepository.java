package vttp.ssf_submission.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public void saveNews(String id, Articles a) {

        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
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

        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        
        // returns how many items in the cart
        long size = valueOps.size(id);
        
        if (size > 0) {
            Object news = valueOps.get(id);
            System.out.println("OBJECT ------------- " + news);
            result = Articles.create((JsonObject) news);
        }
    
        return Optional.of(result);
	}
    
    public boolean IdExists(String id) {
        // System.out.println("Entered idexists");
        if (redisTemplate.hasKey(id)) {
            System.out.println("USER EXISTS");
              return true;
        }
        System.out.println("USER DOESNT EXIST");
        return false;
    }
}
