/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.service.TransactionService1;
import com.github.adminfaces.starter.util.Utils;
import com.github.adminfaces.template.exception.AccessDeniedException;
import org.omnifaces.util.Faces;

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
public class TransactionFormMB implements Serializable {

    @Inject
    TransactionService1 carService;

    private Long id;
    private Transaction car;
    private double a;
    private String m;
   private String e;
   
    public String getE() {
	return e;
}

public void setE(String e) {
	this.e = e;
}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            car = carService.findById(id);
        } else {
            car = new Transaction();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getCar() {
        return car;
    }

    public void setCar(Transaction car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(car) && has(car.getId())) {
            carService.remove(car);
            addDetailMessage("Car " + car.getState()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/car-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (car.getId() == null) {
            carService.insert(car);
            msg = "Car " + car.getState() + " created successfully";
        } else {
            carService.update(car);
            msg = "Car " + car.getState() + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        car = new Transaction();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }
public void give(){carService.retaita(m,a);}
public void  direct(){carService.direct(id);}



}