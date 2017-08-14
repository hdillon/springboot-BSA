package bsa;

import org.json.JSONObject;

public class MessageBuilder {
	
	private JSONObject volatileData = new JSONObject();
	private JSONObject validationData = new JSONObject();
	private JSONObject additionalData = new JSONObject();
	private JSONObject JSONpostParameters = new JSONObject();

	public String getSaleMessage(RequestData requestmessage){
		volatileData.put("flag_tokenizacion", requestmessage.getFlag_tokenization());
		volatileData.put("public_request_key", requestmessage.getPublic_request_key());
		volatileData.put("flag_codigo_seguridad", requestmessage.getFlag_security_code());
		
		validationData.put("flag_selector_clave", requestmessage.getFlag_selector_key());
		validationData.put("public_request_key", requestmessage.getPublic_request_key());
		
		JSONpostParameters.put("public_token", requestmessage.getPublic_token());
		JSONpostParameters.put("issue_date", requestmessage.getIssue_date());
		JSONpostParameters.put("type", "sale");
		JSONpostParameters.put("volatile_data", volatileData);
		JSONpostParameters.put("volatile_encrypted_data", requestmessage.getVolatile_encrypted_data());
		JSONpostParameters.put("validation_data", validationData);
		JSONpostParameters.put("additional_data", additionalData);
		
		return JSONpostParameters.toString();
	}
	
	/*public String getRefoundMessage(RequestData requestmessage){
		JSONpostParameters.put("public_token", requestmessage.getPublic_token());
		JSONpostParameters.put("private_token", requestmessage.getPrivate_token());
		JSONpostParameters.put("issue_date", requestmessage.getIssue_date());
		JSONpostParameters.put("type", "refound");
		
		return JSONpostParameters.toString();
	}*/
}
