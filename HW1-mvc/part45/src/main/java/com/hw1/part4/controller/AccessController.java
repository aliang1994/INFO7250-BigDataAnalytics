/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hw1.part4.controller;

import com.hw1.part4.pojo.Access;
import com.hw1.part4.pojo.Movie;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.regex;
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
public class AccessController extends AbstractController {
    
    public AccessController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("access");
        System.out.println("connected to database");
        
        List<Access> result = new ArrayList<Access>();
        HttpSession session = request.getSession();
        
        if(request.getParameter("type").equals("ip")){
            String ip = request.getParameter("ip");
            FindIterable<Document> it = coll.find(regex("ipAddress", ".*" + Pattern.quote(ip) + ".*")).limit(200);
            System.out.println("iterable " + it.first()); //debug
            for(Document doc: it){
                System.out.println("print doc" + doc.toString()); //debug

                Access ac = new Access();
                ac.setDbid(doc.get("_id").toString());
                ac.setIpAddress(doc.get("ipAddress").toString());
                ac.setDatetime(doc.get("datetime").toString());
                ac.setTimezone(doc.get("timezone").toString());
                ac.setHttpMethod(doc.get("httpMethod").toString());
                ac.setUri(doc.get("uri").toString());
                ac.setHttpVersion(doc.get("httpVersion").toString());
                ac.setStatusCode(doc.get("statusCode").toString());
                ac.setResponseLength(doc.get("responseLength").toString());

                result.add(ac);
                System.out.println("print log" + ac.toString()); //debug
            }

            ModelAndView mav = new ModelAndView("access");
            mav.addObject("accessresult", result);
            return mav;
        }
        
        if(request.getParameter("type").equals("code")){
            String code = request.getParameter("code");
            FindIterable<Document> it = coll.find(regex("statusCode", ".*" + Pattern.quote(code) + ".*")).limit(200);
            System.out.println("iterable " + it.first()); //debug
            for(Document doc: it){
                System.out.println("print doc" + doc.toString()); //debug

                Access ac = new Access();
                ac.setDbid(doc.get("_id").toString());
                ac.setIpAddress(doc.get("ipAddress").toString());
                ac.setDatetime(doc.get("datetime").toString());
                ac.setTimezone(doc.get("timezone").toString());
                ac.setHttpMethod(doc.get("httpMethod").toString());
                ac.setUri(doc.get("uri").toString());
                ac.setHttpVersion(doc.get("httpVersion").toString());
                ac.setStatusCode(doc.get("statusCode").toString());
                ac.setResponseLength(doc.get("responseLength").toString());

                result.add(ac);
                System.out.println("print log" + ac.toString()); //debug
            }

            ModelAndView mav = new ModelAndView("access");
            mav.addObject("accessresult", result);
            return mav;
        }
        
        if(request.getParameter("type").equals("method")){
            String method = request.getParameter("method");
            FindIterable<Document> it = coll.find(regex("httpMethod", ".*" + Pattern.quote(method) + ".*")).limit(200);
            System.out.println("iterable " + it.first()); //debug
            for(Document doc: it){
                System.out.println("print doc" + doc.toString()); //debug

                Access ac = new Access();
                ac.setDbid(doc.get("_id").toString());
                ac.setIpAddress(doc.get("ipAddress").toString());
                ac.setDatetime(doc.get("datetime").toString());
                ac.setTimezone(doc.get("timezone").toString());
                ac.setHttpMethod(doc.get("httpMethod").toString());
                ac.setUri(doc.get("uri").toString());
                ac.setHttpVersion(doc.get("httpVersion").toString());
                ac.setStatusCode(doc.get("statusCode").toString());
                ac.setResponseLength(doc.get("responseLength").toString());

                result.add(ac);
                System.out.println("print log" + ac.toString()); //debug
            }
            

            ModelAndView mav = new ModelAndView("access");
            mav.addObject("accessresult", result);
            return mav;
        }
        return null;
    }
}