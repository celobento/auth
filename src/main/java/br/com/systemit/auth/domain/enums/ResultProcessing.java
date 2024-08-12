package br.com.systemit.auth.domain.enums;

public enum ResultProcessing {

    INVALID_PASSWORD(900, "Invalid password."),;

    private final Integer id;
    private final String description;
    
    ResultProcessing(Integer id, String description) {
        
        this.id = id;
        
        this.description = description;
        
    }
    
    public int getId() {

        return id;
    }

    public String getDescriprion() {
        
        return description;
    }

    public static ResultProcessing findById(Integer id) {
    	
    	ResultProcessing[] results = values();
    	
    	for (ResultProcessing result : results) {
			
    		if (result.getId() == id) {
    			
    			return result;
    			
    		}
    		
		}
    	
    	return null;    } 

}
