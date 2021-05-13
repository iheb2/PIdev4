/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Request;

import com.github.adminfaces.starter.service.RequestService;
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
public class RequestFormMB implements Serializable {

    @Inject
   RequestService reqService;

    private Long id;
    private Request req;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
        	req = reqService.findById_request(id);
        } else {
            req = new Request();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getCar() {
        return req;
    }

    public void setCar(Request req) {
        this.req = req;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove reqs.");
        }
        if (has(req) && has(req.getId_request())) {
            reqService.remove(req);
            addDetailMessage("Request " 
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/request-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (req.getId_request() == null) {
            reqService.insert(req);
            msg = "Request "  + " created successfully";
        } else {
            reqService.update(req);
            msg = "Request "  + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        req = new Request();
        id = null;
    }

    public boolean isNew() {
        return req == null || req.getId_request() == null;
    }


}
