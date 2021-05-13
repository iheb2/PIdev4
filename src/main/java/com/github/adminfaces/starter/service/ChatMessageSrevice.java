/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.ChatMessage;
import com.github.adminfaces.starter.repository.ChatMessageRepository;
import com.github.adminfaces.template.exception.BusinessException;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rmpestano
 *         Car Business logic
 */
@Component
public class ChatMessageSrevice implements Serializable {

 
    List<ChatMessage> allCars;
    @Autowired
    ChatMessageRepository  ch;

    public void insert(ChatMessage car) {
      
        car.setId(allCars.stream()
                .mapToLong(c -> c.getId())
                .max()
                .getAsLong()+1);
        allCars.add(car);
    }



    public void remove(ChatMessage car) {
        allCars.remove(car);
    }



    public ChatMessage findById(Long id) {
        return allCars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Car not found with id " + id));
    }

    public void update(ChatMessage car) {
        allCars.remove(allCars.indexOf(car));
        allCars.add(car);
    }
}
