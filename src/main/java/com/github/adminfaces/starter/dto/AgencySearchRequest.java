package com.github.adminfaces.starter.dto;

import com.github.adminfaces.starter.model.Agency;

public class AgencySearchRequest {

    private Long id;
    private String Email;
    private String address;

    public Agency toAgency() {
    	Agency agency = new Agency();

    	agency.setEmail(this.Email);
    	agency.setAdress(this.address);

        if(this.id != null) {
        	agency.setId_agency(this.id);
        }

        return agency;
    }
}
