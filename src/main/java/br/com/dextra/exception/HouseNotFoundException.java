package br.com.dextra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "House not found")
public class HouseNotFoundException extends RuntimeException{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5103259979421041102L;

	public HouseNotFoundException(String message) {
        super(message);
    }

}
