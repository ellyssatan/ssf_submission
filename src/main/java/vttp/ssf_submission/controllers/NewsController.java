package vttp.ssf_submission.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf_submission.models.Articles;
import vttp.ssf_submission.repositories.NewsRepository;
import vttp.ssf_submission.services.NewsService;

@Controller
@RequestMapping
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @Autowired
    private NewsRepository nRepo;


    @GetMapping(path = "/")
    public String getNews(Model model) {
        List<Articles> newsList = newsSvc.getArticles();

        model.addAttribute("newsList", newsList);
        return "index";
    }

    @PostMapping(path = "/articles")
    public String saveArticles(@RequestBody MultiValueMap<String, String> form, Model model) {
        List<String> newslist = form.get("saveNews");

        System.out.println(">>>> NEWSLIST:" + newslist);

        for (String l : newslist) {
           nRepo.saveNews(l); 
        }
        return "index";
    }

}
