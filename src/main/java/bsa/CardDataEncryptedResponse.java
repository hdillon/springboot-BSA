package bsa;

public class CardDataEncryptedResponse extends CardDataResponse{

	public CardDataEncryptedResponse() {}
	
	private String PRIVATE_TOKEN;
    private String ADITIONALCRYPT;
    private String HEXSUM;
    private String COUNTER;
    
    public String getPrivate_token() {
        return PRIVATE_TOKEN;
    }
    
    public void setPrivate_token(String tokenprivado) {
        this.PRIVATE_TOKEN = tokenprivado;
    }
    
    public String getAditionalcrypt() {
        return ADITIONALCRYPT;
    }
    
    public void setAditionalcrypt(String aditionalcrypt) {
        this.ADITIONALCRYPT = aditionalcrypt;
    }
    
    public String getHexsum() {
        return HEXSUM;
    }
    
    public void setHexsum(String hexsum) {
        this.HEXSUM = hexsum;
    }
    
    public String getCounter() {
        return COUNTER;
    }
    
    public void setCounter(String counter) {
        this.COUNTER = counter;
    }

	@Override
	public String toString() {
		return "CardDataEncryptedResponse [PRIVATE_TOKEN=" + PRIVATE_TOKEN + ", ADITIONALCRYPT=" + ADITIONALCRYPT
				+ ", HEXSUM=" + HEXSUM + ", COUNTER=" + COUNTER + ", " + super.toString() + "]";
	}
	
	
}
