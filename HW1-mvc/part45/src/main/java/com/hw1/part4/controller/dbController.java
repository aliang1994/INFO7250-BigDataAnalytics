/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hw1.part4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aliceliang
 */
public class dbController extends AbstractController {
    
    public dbController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(request.getParameter("select").equals("movie")) return new ModelAndView("movie");
        if(request.getParameter("select").equals("access")) return new ModelAndView("access");
        return null;
    }
    
}
