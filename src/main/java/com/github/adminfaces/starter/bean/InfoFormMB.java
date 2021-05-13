/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;


import com.github.adminfaces.starter.model.Information;
import com.github.adminfaces.starter.repository.InfoRepository;
import com.github.adminfaces.starter.service.InfoService;
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
public class InfoFormMB implements Serializable {
	
	@Inject
    InfoService carService;
	@Inject
	InfoRepository rep;
    private Long id;
    private Information car;
    private Integer r=0;

    public Integer getR() {
		return r;
	}

	public void setR(Integer r) {
		this.r = r;
	}

	public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            car = carService.findById(id);
        } else {
            car = new Information();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Information getCar() {
        return car;
    }

    public void setCar(Information car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(car) && has(car.getId())) {
            carService.remove(car);
            addDetailMessage("Information " + car.getQuestion()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/car-list.jsf");
        }
    }
public void rating()
	{carService.rateInfo(car);
	
}
    public void save() {
        String msg;
        if (car.getId() == null) {
            carService.insert(car);
            msg = "Information " + car.getQuestion() + " created successfully";
        } else {
            carService.update(car);
            msg = "Information " + car.getQuestion() + " updated successfully";
        }
        rep.save(car);
        addDetailMessage(msg);
    }

    public void clear() {
        car = new Information();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }


}
