package bsa;

public class ValidationError {
	
	private String CODE;
    private String PARAM;
    
    ValidationError(String code, String param){
    	this.CODE = code;
    	this.PARAM = param;
    }
    
	public String getCode() {
		return CODE;
	}
	public void setCode(String code) {
		CODE = code;
	}
	public String getParam() {
		return PARAM;
	}
	public void setParam(String param) {
		PARAM = param;
	}
	
	public static ValidationError getErrorByCode(int errorCode, String errorMsg){
		if(errorCode >= 30900 && errorCode <= 30902 || errorCode >= 20900 && errorCode <= 20902)
			return new ValidationError("invalid_param", "issue_date");
		else if(errorCode >= 31000 && errorCode <= 31005 || errorCode >= 21000 && errorCode <= 21005)
			return new ValidationError("invalid_param", "public_token");
		else if(errorCode >= 30700 && errorCode <= 30701 || errorCode >= 20700 && errorCode <= 20701)
			return new ValidationError("invalid_param", "volatile_encrypted_data");
		else if(errorCode >= 30502 && errorCode <= 30502 || errorCode >= 20502 && errorCode <= 20502)
			return new ValidationError("invalid_param", getErrorField(errorMsg));
		else if(errorCode >= 30602 && errorCode <= 30602 || errorCode >= 20602 && errorCode <= 20602)
			return new ValidationError("invalid_param", "flag_selector_key");
		else
			return null;
	}
	
	private static String getErrorField(String msg){
		if(msg.contains("flag_tokenizacion"))
			return "flag_tokenization";
		else if(msg.contains("flag_codigo_seguridad"))
			return "flag_security_code";
		else
			return "public_request_key";
	}
	
	@Override
	public String toString() {
		return "ValidationError [CODE=" + CODE + ", PARAM=" + PARAM + "]";
	}
    
}
