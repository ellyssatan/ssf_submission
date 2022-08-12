package vttp.ssf_submission.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Articles {
    private String id;
    private String publishedOn;
    private String title;
    private String url;
    private String imageUrl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {   return id;    }
    public void setId(String id) {  this.id = id;   }

    public String getPublishedOn() {   return publishedOn;    }
    public void setPublishedOn(String publishedOn) {  this.publishedOn = publishedOn;   }

    // public long getPublishedOn() {   return publishedOn;    }
    // public void setPublishedOn(long publishedOn) {  this.publishedOn = publishedOn;   }
   
    public String getTitle() {      return title;   }
    public void setTitle(String title) {    this.title = title;     }

    public String getUrl() {    return url;     }
    public void setUrl(String url) {    this.url = url;     }

    public String getImageUrl() {   return imageUrl;    }
    public void setImageUrl(String imageUrl) {  this.imageUrl = imageUrl;   }

    public String getBody() {   return body;    }
    public void setBody(String body) {      this.body = body;   }
    
    public String getTags() {   return tags;    }
    public void setTags(String tags) {    this.tags = tags;   }

    public String getCategories() {     return categories;  }
    public void setCategories(String categories) {    this.categories = categories;     }

    public static Articles create(JsonObject jo) {
        Articles n = new Articles();
        n.setId(jo.getString("id"));
        n.setPublishedOn(jo.getString("published_on"));
        n.setTitle(jo.getString("title"));
        n.setUrl(jo.getString("url"));
        n.setImageUrl(jo.getString("imageurl"));
        n.setBody(jo.getString("body"));
        n.setTags(jo.getString("tags"));
        n.setCategories(jo.getString("categories"));
        // System.out.println("<<<<<PUBLISHED ON>>>>>" + jo.getString("published_on"));
        return n;

    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("title", title)
            .add("body", body)
            // .add("published_on", publishedOn)
            .add("url", url)
            .add("imageurl", imageUrl)
            .add("tags", tags)
            .add("categories", categories)
            .build();
    }

    public static Articles create(String jsonStr) {
        System.out.printf(">>>>JSON STRING: %s", jsonStr);
        StringReader reader = new StringReader(jsonStr);
        JsonReader r = Json.createReader(reader);

        return create(r.readObject());
    }
}