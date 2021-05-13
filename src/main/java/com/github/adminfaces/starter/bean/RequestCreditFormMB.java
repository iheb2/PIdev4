/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.service.IRequestCreditService;
import com.github.adminfaces.starter.service.RequestCreditService;
import com.github.adminfaces.starter.util.Utils;
import com.github.adminfaces.template.exception.AccessDeniedException;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
public class RequestCreditFormMB implements Serializable {

    @Inject
    RequestCreditService carService;
    IRequestCreditService IService;

    private Long id;
    private double amount;
    private RequestCredit car;
    private double months;
   

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getMonths() {
		return months;
	}

	public void setMonths(double months) {
		this.months = months;
	}

	public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            car = carService.findById_request(id);
        } else {
            car = new RequestCredit();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestCredit getCar() {
        return car;
    }

    public void setCar(RequestCredit car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(car) && has(car.getId_request())) {
            carService.remove(car);
            addDetailMessage("Car " 
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/car-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (car.getId_request() == null) {
            carService.insert(car);
            msg = "Car "  + " created successfully";
        } else {
            carService.update(car);
            msg = "Car " + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        car = new RequestCredit();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId_request() == null;
    }

    public String monthlyPaymentSim(double amount, double months) {
		  return IService.simulationMensualite(amount, months);
		  }
    
}