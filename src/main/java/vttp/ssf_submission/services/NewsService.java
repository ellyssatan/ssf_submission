package vttp.ssf_submission.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf_submission.models.Articles;
import vttp.ssf_submission.repositories.NewsRepository;

@Service
public class NewsService {

    private static final String url = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
    
    @Value("${CRYPTO_KEY}")
    private String key;

    @Autowired
    private NewsRepository nRepo;

    public List<Articles> getArticles() {

        String payload;

        // Create url with query string (add parameters)
        String uri = UriComponentsBuilder.fromUriString(url)
        .queryParam("api_key", key)
        .toUriString();

        // Create the GET request, GET url
        RequestEntity<Void> req = RequestEntity.get(uri).build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp;

        try {
            // Throws exception if status code is not between 200 - 399
            resp = template.exchange(req, String.class);
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e);
            return Collections.emptyList();
        }

        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error status code is not 200\n");
            return Collections.emptyList();
        }

        // Get payload 
        payload = resp.getBody();
        // System.out.println(">>> Payload: \n" + payload);
        
        // Convert payload into JsonObject
        // Convert string to a Reader
        Reader strReader = new StringReader(payload);

        // Create a JsonReader from reader
        JsonReader jsonReader = Json.createReader(strReader);

        // Read and save the payload as Json Object
        JsonObject jObject = jsonReader.readObject();
                                         // should tally with the object name from api
        JsonArray articleArray = jObject.getJsonArray("Data");

        List<Articles> list = new LinkedList<>();
        for (int i = 0; i < articleArray.size(); i++) {
            // loop through the top _ coins
            JsonObject jo = articleArray.getJsonObject(i);
            
            list.add(Articles.create(jo));
        }
        return list;
    }

    public List<Articles> saveArticles(String jsonStr) {
        System.out.printf(">>> JSONSTR:: %s\n\n", jsonStr);

        Articles a = Articles.create(jsonStr);
        System.out.printf(">>> ARTICLE GENERATED %s\n\n", a.toString());
        System.out.println("------------" + Articles.createJsonString(a.toJson()));

        String id = a.getId();
        System.out.printf(">>> ID RETRIEVED %s\n\n", id);

        List<Articles> list = new LinkedList<>();

        nRepo.saveNews(id, a);


        return list;

    }
    
}