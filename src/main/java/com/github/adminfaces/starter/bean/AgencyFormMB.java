/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.service.AgencyService;
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
public class AgencyFormMB implements Serializable {

    @Inject
   AgencyService agencyService;

    private Long id;
    private Agency agency;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
        	agency = agencyService.findById_agency(id);
        } else {
        	agency = new Agency();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(agency) && has(agency.getId_agency())) {
        	agencyService.remove(agency);
            addDetailMessage("Agency " 
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("admin/agency-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (agency.getId_agency() == null) {
        	agencyService.insert(agency);
            msg = "Agency " + " created successfully";
        } else {
        	agencyService.update(agency);
            msg = "Agency " + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
    	agency = new Agency();
        id = null;
    }

    public boolean isNew() {
        return agency == null || agency.getId_agency() == null;
    }


}
