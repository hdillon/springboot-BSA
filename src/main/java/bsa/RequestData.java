package bsa;

public class RequestData {

    private String PUBLIC_TOKEN;
    private String ISSUE_DATE;
    private String PUBLIC_REQUEST_KEY;
    private String VOLATILE_ENCRYPTED_DATA;
    private String FLAG_TOKENIZATION;
    private String FLAG_SECURITY_CODE;
    private String FLAG_SELECTOR_KEY;
    private String FLAG_PEI;
    
    public RequestData() {}

    public String getPublic_token() {
        return PUBLIC_TOKEN;
    }
    
    public void setPublic_token(String publictoken) {
        this.PUBLIC_TOKEN = publictoken;
    }

    public String getIssue_date() {
        return ISSUE_DATE;
    }
    
    public void setIssue_date(String issuedate) {
        this.ISSUE_DATE = issuedate;
    }

    public String getFlag_tokenization() {
        return FLAG_TOKENIZATION;
    }
    
    public void setFlag_tokenization(String flagtokenization) {
        this.FLAG_TOKENIZATION = flagtokenization;
    }
    
    public String getPublic_request_key() {
        return PUBLIC_REQUEST_KEY;
    }
    
    public void setPublic_request_key(String publicrequestkey) {
        this.PUBLIC_REQUEST_KEY = publicrequestkey;
    }
    
    public String getFlag_security_code() {
        return FLAG_SECURITY_CODE;
    }
    
    public void setFlag_security_code(String flagsecuritycode) {
        this.FLAG_SECURITY_CODE = flagsecuritycode;
    }
    
    public String getFlag_selector_key() {
        return FLAG_SELECTOR_KEY;
    }
    
    public void setFlag_selector_key(String flagselectorclave) {
        this.FLAG_SELECTOR_KEY = flagselectorclave;
    }
    
    public String getVolatile_encrypted_data() {
        return VOLATILE_ENCRYPTED_DATA;
    }
    
    public void setVolatile_encrypted_data(String volatileencrypteddata) {
        this.VOLATILE_ENCRYPTED_DATA = volatileencrypteddata;
    }
     
    public String getFlag_pei() {
        return FLAG_PEI;
    }
    
    public void setFlag_pei(String flagpei) {
        this.FLAG_PEI = flagpei;
    }
    
}
