package tn.esprit.pidev.model;

public class AgencyResponse {
	private final Long id;
    private final String email;

    private final String address;

    public AgencyResponse(Agency agency) {
        this.id = agency.getId_agency();
        this.email = agency.getEmail();
        this.address = agency.getAdress();
    }

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}
    
}
