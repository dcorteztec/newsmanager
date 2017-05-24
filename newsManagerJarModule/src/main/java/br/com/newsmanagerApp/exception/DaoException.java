package br.com.newsmanagerApp.exception;

import org.hibernate.exception.ConstraintViolationException;

public class DaoException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public DaoException() {
    	super();
    	dealingException(null, null);
	}
    
    public DaoException(String message) {
    	super(message);
    	dealingException(message, null);
	}
    
    public DaoException(Throwable cause) {
    	super(cause);
    	dealingException(null, cause);
	}
    
    public DaoException(String message, Throwable cause) {
    	super(message, cause);
    	dealingException(message, cause);
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
   		 //this.message = ResourceUtil.getResource(PesistenceMessageCode.PERSISTENCE_ERROR_0000);
   		 if (cause != null && cause instanceof ConstraintViolationException) {
       		 ConstraintViolationException constraintViolationException = (ConstraintViolationException) cause;
       		 this.message = PesistenceMessageCode.PERSISTENCE_ERROR_0007;
       		 if (constraintViolationException.getErrorCode() ==  1) {
       			 this.message = PesistenceMessageCode.PERSISTENCE_ERROR_0005;
       		 } else if (constraintViolationException.getErrorCode() ==  1062) {
       			 this.message = PesistenceMessageCode.PERSISTENCE_ERROR_0005;
       		 } else if (constraintViolationException.getErrorCode() ==  1451 || constraintViolationException.getErrorCode() ==  2292) {
       			 this.message = PesistenceMessageCode.PERSISTENCE_ERROR_0006;
       		 }
      		 }
   	 }
    }
}
