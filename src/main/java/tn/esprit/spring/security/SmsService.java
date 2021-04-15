package tn.esprit.spring.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SmsService {

	    
	    private final String ACCOUNT_SID ="AC90a519e1fd0175da181b563c2ac46c03";

	    private final String AUTH_TOKEN = "0644fe5111d4dfe31cea4aa4637c44f2";

	    private final String FROM_NUMBER = "14422465096";

	    public void send(SmsPojo sms) {
	    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
	                .create();
	        System.out.println("here is my id:"+message.getSid());

	    }

	    public void receive(MultiValueMap<String, String> smscallback) {
	    }
	
}
