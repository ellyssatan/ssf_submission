package vttp.ssf_submission.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.ssf_submission.models.Articles;
import vttp.ssf_submission.repositories.NewsRepository;

@RestController
@RequestMapping(path = "/news")
public class NewsRESTController {

    @Autowired
    private NewsRepository nRepo;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> retrieveArticle(@PathVariable(name = "id") String id) {

        if (!nRepo.IdExists(id)) {
            JsonObject errorMsg = Json.createObjectBuilder()
                .add("error", "Cannot find news article %s".formatted(id))
                .build();
                
            String payload = errorMsg.toString();
            return ResponseEntity
                    .badRequest()
                    .body(payload);
        }

        // Get article by id
        Articles op = nRepo.getArticleById(id).get();

        // Create response payload
        JsonObject jsonArticle = op.toJson();
        System.out.println(">>>>> CONVERTED:   " + jsonArticle);

        return ResponseEntity.ok(jsonArticle.toString());

    }

}
