package vttp.ssf_submission.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.ssf_submission.services.NewsService;

@RestController
@RequestMapping
public class NewsRESTController {
    
    @Autowired
    private NewsService nSvc;

    @GetMapping(path = "/news/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> retrieveArticle(@PathVariable(name = "id") Integer id) {

        // Optional<A>
        // if ((count < 1) || (count>10)) {
        //     String errorMsg = "Valid dice count is between 1 and 10, %d exceeded range".formatted(count);
        //     return ResponseEntity
        //             // .status(HttpStatus.BAD_REQUEST)
        //             .badRequest()
        //             .body(errorMsg);
        // }
        // // Get dice roll
        // List<Integer> rolls = diceSvc.roll(count);

        // String csvString = rolls
        //         .stream()
        //         .map(v -> v.toString())
        //         .collect(Collectors.joining(","));
        
        // return ResponseEntity.ok(csvString);
        return null;

    }

}
