/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hw1.part4.controller;

import com.hw1.part4.pojo.Movie;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Filters.text;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bson.Document;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aliceliang
 */
public class MovieController extends AbstractController {
    
    public MovieController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("movies");
        System.out.println("connected to database");
        
        List<Movie> result = new ArrayList<Movie>();
        HttpSession session = request.getSession();
        boolean hiding;
        
        if(request.getParameter("type").equals("name")){
            String name = request.getParameter("name");
            FindIterable<Document> it = coll.find(regex("title", ".*" + Pattern.quote(name) + ".*"));
            System.out.println("iterable " + it.first()); //debug
            for(Document doc: it){
                System.out.println("print doc" + doc.toString()); //debug

                Movie m = new Movie();
                m.setDbid(doc.get("_id").toString());
                m.setGenres(doc.get("genres").toString());
                m.setMovieid(doc.get("movieId").toString());
                m.setTitle(doc.get("title").toString());

                result.add(m);
                System.out.println("print movie" + m.toString()); //debug
            }

            ModelAndView mav = new ModelAndView("movie");
            mav.addObject("result", result);
            return mav;
        }
        if(request.getParameter("type").equals("genre")){
            String genre = request.getParameter("genre");
            FindIterable<Document> it = coll.find(regex("genres", ".*" + Pattern.quote(genre) + ".*"));
            for(Document doc: it){
                Movie m = new Movie();
                m.setDbid(doc.get("_id").toString());
                m.setGenres(doc.get("genres").toString());
                m.setMovieid(doc.get("movieId").toString());
                m.setTitle(doc.get("title").toString());

                result.add(m);
                System.out.println("print movie" + m.toString()); //debug
            }

            ModelAndView mav = new ModelAndView("movie");
            mav.addObject("result", result);
            return mav;
        }
        if(request.getParameter("type").equals("kw")){
            String kw = request.getParameter("keyword");
            //FindIterable<Document> it = coll.find(regex("genres", ".*" + Pattern.quote(kw) + ".*"));
            //text search
            FindIterable<Document> it = coll.find(text(kw));
            for(Document doc: it){
                Movie m = new Movie();
                m.setDbid(doc.get("_id").toString());
                m.setGenres(doc.get("genres").toString());
                m.setMovieid(doc.get("movieId").toString());
                m.setTitle(doc.get("title").toString());

                result.add(m);
                System.out.println("print movie" + m.toString()); //debug
            }

            ModelAndView mav = new ModelAndView("movie");
            mav.addObject("result", result);
            return mav;
        }
        return null;
    }
}
