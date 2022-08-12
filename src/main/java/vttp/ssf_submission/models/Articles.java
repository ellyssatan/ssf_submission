package vttp.ssf_submission.models;

import java.util.ArrayList;

import jakarta.json.JsonObject;

public class Articles {
    private String id;
    private String publishedOn;
    private String title;
    private String url;
    private String imageUrl;
    private String body;
    private List<String> tags;
    private List<String> categories;

    public String getId() {   return id;    }
    public void setId(String id) {  this.id = id;   }

    public String getPublishedOn() {   return publishedOn;    }
    public void setPublishedOn(String publishedOn) {  this.publishedOn = publishedOn;   }
   
    public String getTitle() {      return title;   }
    public void setTitle(String title) {    this.title = title;     }

    public String getUrl() {    return url;     }
    public void setUrl(String url) {    this.url = url;     }

    public String getImageUrl() {   return imageUrl;    }
    public void setImageUrl(String imageUrl) {  this.imageUrl = imageUrl;   }

    public String getBody() {   return body;    }
    public void setBody(String body) {      this.body = body;   }
    
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public static Articles create(JsonObject jo) {
        Articles n = new Articles();
        n.setId(jo.getString("id"));
        n.setPublishedOn(jo.getString("published_on"));
        n.setTitle(jo.getString("title"));
        n.setUrl(jo.getString("url"));
        n.setImageUrl(jo.getString("imageurl"));
        n.setBody(jo.getString("body"));
        n.setTags(n.split(jo.getString("tags")));
        n.setCategories(n.split(jo.getString("categories")));
        return n;

    }

    public ArrayList<String> split(String jsonStr) {
        ArrayList<String> list = new ArrayList<>();
        String[] separated = jsonStr.split("\\|");
        for (String l :separated) {
            list.add(l);
        }
        System.out.printf(">>> LIST: %s\n\n", list);
        return list;
    }
}
