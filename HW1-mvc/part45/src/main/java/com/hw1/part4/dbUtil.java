/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hw1.part4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.bson.Document;


/**
 *
 * @author aliceliang
 */
public class dbUtil {
    public void readLinks(){
        // connect to database
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("links");
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/HW1/ml-20m/links.csv");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            in.readLine(); // skip the first line
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("movieId", tokens[0]);
                    if (k==1) doc.append("imdbId", tokens[1]);
                    if (k==2) doc.append("tmdbId", tokens[2]);
                }
                coll.insertOne(doc);
            }
            // method 2
            /* 
            StringTokenizer st = new StringTokenizer(line, ",");
            String heading1 = st.nextToken(); //SKIP THE FIRST LINE
            st.nextToken();
            st.nextToken();
            st.nextToken();
            st.nextToken();
            st.nextToken();
            st.nextToken();
            st.nextToken();
            st.nextToken();

            while (st.hasMoreTokens()) {
                String exchange = st.nextToken();
                String stockSymbol = st.nextToken();
                String date = st.nextToken();
                st.nextToken();
                st.nextToken();
                st.nextToken();
                st.nextToken();
                st.nextToken();
                st.nextToken();

                System.out.println("EXCHANGE: " + exchange + "\nSTOCK SYMBOL: " + stockSymbol + "\nDATE: " + date);
                System.out.println("---------------------------------");
            }
            */   
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public void readMovies(){
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("movies");
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/HW1/ml-20m/movies.csv");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            in.readLine(); // skip the first line
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("movieId", tokens[0]);
                    if (k==1) doc.append("title", tokens[1]);
                    if (k==2) doc.append("genres", tokens[2]);
                }
                coll.insertOne(doc);
            }
           
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public void readRatings(){
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework2");
        MongoCollection<Document> coll = db.getCollection("ratings");
        try{
            // read csv file
            //File inputFile = new File("/Users/aliceliang/CS/7250/HW1/ml-20m/ratings.csv");
            File inputFile = new File("/Users/aliceliang/CS/7250/HW2/ml-1m/ratings.dat");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            //in.readLine(); // skip the first line
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split("::");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("userId", tokens[0]);
                    if (k==1) doc.append("movieId", tokens[1]);
                    if (k==2) doc.append("rating", tokens[2]);
                    if (k==3) doc.append("timestamp", tokens[3]);
                }
                coll.insertOne(doc);
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public void readUsers(){
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework2");
        MongoCollection<Document> coll = db.getCollection("users");
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/HW2/ml-1m/users.dat");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split("::");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("userId", tokens[0]);
                    if (k==1) doc.append("gender", tokens[1]);
                    if (k==2) doc.append("age", tokens[2]);
                    if (k==3) doc.append("occupation", tokens[3]);
                    if (k==4) doc.append("zipcode", tokens[4]);
                }
                coll.insertOne(doc);
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public void readTags(){
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("tags");
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/HW1/ml-20m/tags.csv");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            in.readLine(); // skip the first line
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("userId", tokens[0]);
                    if (k==1) doc.append("movieId", tokens[1]);
                    if (k==2) doc.append("tag", tokens[2]);
                    if (k==3) doc.append("timestamp", tokens[3]);
                }
                coll.insertOne(doc);
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public void readLogs(){
        MongoClient mongoClient = MongoClients.create(); // localhost:27017
        MongoDatabase db = mongoClient.getDatabase("homework1");
        MongoCollection<Document> coll = db.getCollection("access");
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/HW1/access.log");
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            in.readLine(); // skip the first line
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(" |\"");
                // create document and insert to collection 
                Document doc = new Document();
                for (int k=0; k<tokens.length; k++){
                    if (k==0) doc.append("ipAddress", tokens[0]);
                    if (k==3) doc.append("datetime", tokens[3].substring(1));
                    if (k==4) doc.append("timezone", tokens[4].substring(0, tokens[4].length()-1));
                    if (k==6) doc.append("httpMethod", tokens[6]);
                    if (k==7) doc.append("uri", tokens[7]);
                    if (k==8) doc.append("httpVersion", tokens[8]);
                    if (k==10) doc.append("statusCode", tokens[10]);
                    if (k==11) doc.append("responseLength", tokens[11]);
                }
                System.out.println(doc.toJson());
                coll.insertOne(doc);
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        dbUtil u = new dbUtil();
        //u.readLinks(coll);
        //u.readMovies();
        u.readRatings();
        //u.readTags();
        //u.readLogs();
        //u.readUsers();
    }
}