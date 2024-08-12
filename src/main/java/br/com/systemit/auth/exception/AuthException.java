package br.com.systemit.auth.exception;

import br.com.systemit.auth.domain.enums.ResultProcessing;

public class AuthException extends RuntimeException{

    private ResultProcessing result;
	
	public AuthException(ResultProcessing result) {
        
        super(result.getId() + " - " + result.getDescriprion());
        this.result = result;

    }
	
	public AuthException(ResultProcessing result, String details) {
        
        super(result.getId() + " - " + result.getDescriprion() + " | " + details);
        this.result = result;

    }

    public ResultProcessing getResult() {
        return result;
    }

    public void setResult(ResultProcessing result) {
        this.result = result;
    }
    
}
