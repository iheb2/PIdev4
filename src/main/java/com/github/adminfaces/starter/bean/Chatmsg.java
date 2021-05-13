/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.ChatMessage;
import com.github.adminfaces.starter.repository.ChatMessageRepository;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.service.ChatMessageSrevice;
import com.github.adminfaces.starter.util.Utils;
import com.github.adminfaces.template.exception.AccessDeniedException;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
public class Chatmsg implements Serializable {

    @Inject
    ChatMessageSrevice carService;
    @Autowired
    ChatMessageRepository rep;
    
    private Long id;
    private ChatMessage car;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            car = carService.findById(id);
        } else {
            car = new ChatMessage();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatMessage getCar() {
        return car;
    }

    public void setCar(ChatMessage car) {
        this.car = car;
    }




    public void save() {
    	    rep.save(car);
            carService.insert(car);
            
   
    }



    public boolean isNew() {
        return car == null || car.getId() == null;
    }


}
