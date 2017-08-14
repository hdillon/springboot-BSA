package bsa;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

public abstract class CardDataResponse {

	private String SECURITY_CODE;
    private String CARD_EXPIRATION;
    private String ERROR_TYPE;
    private ArrayList<ValidationError> VALIDATION_ERRORS;
    
    CardDataResponse(){
    	VALIDATION_ERRORS = new ArrayList<ValidationError>();
    }

    public String getSecurity_code() {
        return SECURITY_CODE;
    }
    
    public void setSecurity_code(String securitycode) {
        this.SECURITY_CODE = securitycode;
    }
    
    public String getCard_expiration() {
        return CARD_EXPIRATION;
    }
    
    public void setCard_expiration(String cardexpiration) {
        this.CARD_EXPIRATION = cardexpiration;
    }
    
    public String getError_type() {
        return ERROR_TYPE;
    }
    
    public void setError_type(String errortype) {
        this.ERROR_TYPE = errortype;
    }
    
    public ArrayList<ValidationError> getValidation_errors() {
        return VALIDATION_ERRORS;
    }
    
    @Override
	public String toString() {
		return "SECURITY_CODE=" + SECURITY_CODE + ", CARD_EXPIRATION=" + CARD_EXPIRATION
				+ ", ERROR_TYPE=" + ERROR_TYPE + ", VALIDATION_ERRORS=" + VALIDATION_ERRORS.toString();
	}
    
}
