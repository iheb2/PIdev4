package tn.esprit.spring.response;

public class ResponseCsv {
	 private String message;

	  public ResponseCsv(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
