/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Report;
import com.github.adminfaces.starter.repository.ReportRepository;
import com.github.adminfaces.starter.service.ReportService;
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
public class ReportFormMB implements Serializable {

    @Inject
   ReportService carService;
    @Inject
    ReportRepository rep;
    private Long id;
    private Report car;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            car = carService.findById(id);
        } else {
            car = new Report();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Report getCar() {
        return car;
    }

    public void setCar(Report car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(car) && has(car.getId())) {
            carService.remove(car);
            addDetailMessage("Report " + car.getState()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/report-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (car.getId() == null) {
            carService.insert(car);
            msg = "Report " + car.getState() + " created successfully";
        } else {
            carService.update(car);
            msg = "Report " + car.getState() + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        car = new Report();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }


}
