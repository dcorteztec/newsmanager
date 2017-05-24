package br.com.newsmanagerApp.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public BusinessException() {
    	super();
    	dealingException(null,null);
	}
    
    public BusinessException(String message) {
    	super(message);
    	dealingException(message,null);
	}
    
    public BusinessException(Throwable cause) {
    	super(cause);
    	dealingException(null,cause);
	}
    
    public BusinessException(String message, Throwable cause) {
    	super(message, cause);
    	dealingException(message,cause);
	}

    public String getError() {
    	return message;
    }
    
    public String getMessage() {
    	return message;
    }
    
    private void dealingException(String message, Throwable cause) {
   	 this.message = message;
   	 if (this.message == null || "".equals(this.message.trim())) {
   		 this.message = PesistenceMessageCode.PERSISTENCE_ERROR_0000;
   		 if (cause != null && cause instanceof DaoException && cause.getMessage() != null) {
   			 this.message = cause.getMessage();
   		 }
   	 }
    }
}


