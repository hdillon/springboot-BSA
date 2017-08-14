package bsa;

public class CardDataPlainResponse extends CardDataResponse {

	private String PAN;
	
	public CardDataPlainResponse() {}
	
	public String getPan() {
        return PAN;
    }
    
    public void setPan(String header, String data, String footer) {
        this.PAN = header + data + footer;
    }
    

	@Override
	public String toString() {
		return "CardDataPlainResponse [PAN=" + PAN + ", " + super.toString() + "]";
	}

	
    
}
