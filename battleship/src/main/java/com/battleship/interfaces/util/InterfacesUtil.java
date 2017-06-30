package com.battleship.interfaces.util;

import static com.battleship.interfaces.util.InterfacesConstants.COMMA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * This is generic utility class provides application level common functions such as validation.
 * 
 * @author pmalsh
 *
 */
@Component
public class InterfacesUtil {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Method that iterates the validation errors and returns a comma separated
	 * error message.
	 * 
	 * @param errors
	 * @return
	 */
	public StringBuilder getValidationErrors(Errors errors) {

		StringBuilder errorMessages = new StringBuilder();

		for (ObjectError objErr : errors.getAllErrors()) {
			if (!StringUtils.isEmpty(errorMessages))
				logger.debug("Error Message is : ", objErr.getDefaultMessage());
			errorMessages = errorMessages.append(objErr.getDefaultMessage()).append(COMMA);
		}
		return errorMessages;
	}

}
