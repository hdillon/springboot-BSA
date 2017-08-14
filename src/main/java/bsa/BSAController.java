package bsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class BSAController {
	
	@Value("${tokenizador.boveda_plana}")
	private String UrlServicioBovedaPlana;
	@Value("${tokenizador.boveda_cifrada}")
	private String UrlServicioBovedaCifrada;
	private String postMessage;
	private URL url;
	JSONObject jsonRespuestaTokenizador = new JSONObject();
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping(value = "/tokenizador", method = RequestMethod.POST)
    public CardDataResponse tokenizador(@RequestBody RequestData requestData) {
    	CardDataResponse cardDataResponse = null;
    	if (requestData != null) {
    		MessageBuilder mb = new MessageBuilder();
    		postMessage = mb.getSaleMessage(requestData);
			try {
	    		if(requestData.getFlag_tokenization().equals("1")){
	    			log.info("Mensaje Enviado (BOVEDA CIFRADA): " + postMessage);
					url = new URL(UrlServicioBovedaCifrada);
					postTokenizador();
					//cardDataResponse = makeEncryptedResponseMock();
					cardDataResponse = buildEncryptedResponse();
	    		}else{
	    			log.info("Mensaje Enviado (BOVEDA PLANA): " + postMessage);
					url = new URL(UrlServicioBovedaPlana);
					postTokenizador();
					//cardDataResponse = buildPlainResponseMock();
					cardDataResponse = buildPlainResponse();
	    		}
			}catch (MalformedURLException e) {
				log.error("Error en la URL: " + e.toString());
			}catch (SocketTimeoutException e) {
				log.error("Timeout en el socket: " + e.toString());
			}catch (IOException e) {
				log.error("Error de comunicacion: "+ e.toString());
			}catch(Exception e){
    			log.error(e.toString());
    		}
        }
    	
    	log.info("Boot response: " + cardDataResponse.toString());
    	
        return cardDataResponse;
    }
    
    private void postTokenizador() throws IOException{
    	HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
    	urlconnection.setRequestMethod("POST");
    	urlconnection.setConnectTimeout(10000);
		urlconnection.setDoOutput(true);
		urlconnection.setDoInput(true);
		urlconnection.setUseCaches(false);
		urlconnection.setRequestProperty("Content-type", "application/json");
		OutputStreamWriter myout = new OutputStreamWriter(urlconnection.getOutputStream());
		myout.write(postMessage);
		myout.flush();
		myout.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
		StringBuffer respuesta = new StringBuffer();
		int num;
		while (-1 != (num = in.read())) {
			respuesta.append((char) num);
		}
		in.close();
		jsonRespuestaTokenizador = new JSONObject(respuesta.toString());
		
		log.info("Mensaje Recibido: " + jsonRespuestaTokenizador);
	}
    
    private CardDataResponse buildPlainResponse(){
    	CardDataPlainResponse plainResponse = new CardDataPlainResponse();
    	String errorCode = jsonRespuestaTokenizador.optString("error", null);
    	String errorMsg = jsonRespuestaTokenizador.optString("msg", null);
    	if(errorCode != null){
			ValidationError ve = ValidationError.getErrorByCode(Integer.parseInt(errorCode), errorMsg);
			plainResponse.setError_type(ve != null ? "invalid_request_error" : "");
			plainResponse.getValidation_errors().add(ve);
		}
    	else{
			JSONObject jsonVolatileDecryptedData = new JSONObject(jsonRespuestaTokenizador.optString("volatile_decrypted_data"));
			plainResponse.setPan(jsonRespuestaTokenizador.optString("header"), jsonRespuestaTokenizador.optString("data"), jsonRespuestaTokenizador.optString("footer"));
			plainResponse.setSecurity_code(jsonVolatileDecryptedData.getString("codigo_seguridad"));
			plainResponse.setCard_expiration(jsonVolatileDecryptedData.getString("fecha_vencimiento"));
    	}
		return plainResponse;
	}
	
	private CardDataResponse buildEncryptedResponse(){
		CardDataEncryptedResponse encryptedResponse = new CardDataEncryptedResponse();
		String errorCode = jsonRespuestaTokenizador.optString("error", null);
		String errorMsg = jsonRespuestaTokenizador.optString("msg", null);
		if(errorCode != null){
			ValidationError ve = ValidationError.getErrorByCode(Integer.parseInt(errorCode), errorMsg);
			encryptedResponse.setError_type(ve != null ? "invalid_request_error" : "");
			encryptedResponse.getValidation_errors().add(ve);
		}
		else{
			JSONObject jsonVolatileDecryptedData = new JSONObject(jsonRespuestaTokenizador.optString("volatile_decrypted_data"));
			encryptedResponse.setPrivate_token(jsonRespuestaTokenizador.getString("private_token"));
			encryptedResponse.setAditionalcrypt(jsonRespuestaTokenizador.getString("aditional_crypt"));
			encryptedResponse.setHexsum(jsonRespuestaTokenizador.getString("hexsum"));
			encryptedResponse.setCounter(jsonRespuestaTokenizador.getString("counter"));
			encryptedResponse.setSecurity_code(jsonVolatileDecryptedData.getString("codigo_seguridad"));
			encryptedResponse.setCard_expiration(jsonVolatileDecryptedData.getString("fecha_vencimiento"));
		}
		return encryptedResponse;
	}
	
	private CardDataResponse buildPlainResponseMock(){
      CardDataPlainResponse plainResponse = new CardDataPlainResponse();
      plainResponse.setPan("450799", "000000", "4905");
      plainResponse.setSecurity_code("775");
      plainResponse.setCard_expiration("1910");
     
      return plainResponse;
   }
	
	private CardDataResponse buildEncryptedResponseMock(){
		CardDataEncryptedResponse encryptedResponse = new CardDataEncryptedResponse();
		encryptedResponse.setPrivate_token("4507994564847787");
		encryptedResponse.setAditionalcrypt("D490406BF5");
		encryptedResponse.setHexsum("101001");
		encryptedResponse.setCounter("86F29");
		encryptedResponse.setSecurity_code("");
		encryptedResponse.setCard_expiration("1905");
		
		return encryptedResponse;
	}
    
    public static void main(String[] args) throws Exception {
		SpringApplication.run(BSAController.class, args);
	}
}
