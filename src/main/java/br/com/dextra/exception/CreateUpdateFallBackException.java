package br.com.dextra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "Create/Update with problem, please try again later")
public class CreateUpdateFallBackException extends RuntimeException{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5103259979421041102L;

	public CreateUpdateFallBackException(String message) {
        super(message);
    }

}
