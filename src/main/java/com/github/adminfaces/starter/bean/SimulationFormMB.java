/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.RequestCredit;

import com.github.adminfaces.starter.service.RequestCreditService;
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
public class SimulationFormMB implements Serializable {

    @Inject
   RequestCreditService requestCreditService;

    private Long id;
    private RequestCredit requestCredit;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
        	requestCredit = requestCreditService.findById_request(id);
        } else {
        	requestCredit = new RequestCredit();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestCredit getRequestCredit() {
        return requestCredit;
    }

    public void setRequestCredit(RequestCredit requestCredit) {
        this.requestCredit = requestCredit;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(requestCredit) && has(requestCredit.getId_request())) {
        	requestCreditService.remove(requestCredit);
            addDetailMessage("RequestCredit " 
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("admin/requestCredit-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (requestCredit.getId_request() == null) {
        	requestCreditService.insert(requestCredit);
            msg = "RequestCredit " + " created successfully";
        } else {
        	requestCreditService.update(requestCredit);
            msg = "RequestCredit " + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
    	requestCredit = new RequestCredit();
        id = null;
    }

    public boolean isNew() {
        return requestCredit == null || requestCredit.getId_request() == null;
    }


}
