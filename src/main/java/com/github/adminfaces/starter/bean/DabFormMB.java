/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.model.Dab;

import com.github.adminfaces.starter.service.DabService;
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
public class DabFormMB implements Serializable {

    @Inject
    DabService dabService;

    private Long id;
    private Dab dab;


    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            dab = dabService.findById_dab(id);
        } else {
            dab = new Dab();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dab getDab() {
        return dab;
    }

    public void setDab(Dab dab) {
        this.dab = dab;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove dabs.");
        }
        if (has(dab) && has(dab.getId_dab())) {
            dabService.remove(dab);
            addDetailMessage("Dab " 
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("admin/dab-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (dab.getId_dab() == null) {
            dabService.insert(dab);
            msg = "Dab "  + " created successfully";
        } else {
            dabService.update(dab);
            msg = "Dab "  + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        dab = new Dab();
        id = null;
    }

    public boolean isNew() {
        return dab == null || dab.getId_dab() == null;
    }


}
