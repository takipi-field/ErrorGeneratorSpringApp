package com.overops.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.overops.util.ErrorSequenceUtil;
import com.overops.util.ErrorType;
import com.overops.util.ErrorResponseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@RestController
public class ErrorServiceController {

    private final static Logger log = LoggerFactory.getLogger(ErrorServiceController.class);

    @RequestMapping("/InvokeError") 
    @ResponseBody
    public void invokeError(@RequestParam String errorType) {

	ErrorType type = ErrorType.valueOf(errorType);
	log.info("ErrorServiceController.invokeError type = " + type);

	if (type.equals(ErrorType.NoError)) {
            // Execute a clean transaction with no errors
            try {
                    ErrorSequenceUtil.testNoError();
            } catch (Exception e) {
                    log.error(RandomUtil.convertStackTraceToString(e));
		    throw new ErrorResponseException("Bad NoError API Call");
            }

        } else if (type.equals(ErrorType.NullPointerException)) {
            // Induce a Caught NullPointerException
            ErrorSequenceUtil.testHandledNullPointer();
	    throw new ErrorResponseException("Executed NullPointerException Call");

        } else if (type.equals(ErrorType.NumberFormatException)) {
            // Induce a Caught NumberFormatException
            ErrorSequenceUtil.testHandledNumberFormat();
	    throw new ErrorResponseException("Executed NumberFormatException Call");

        } else if (type.equals(ErrorType.ParseException)) {
            // Induce a Caught ParseException
            ErrorSequenceUtil.testHandledParseException();
	    throw new ErrorResponseException("Executed ParseException Call");

        } else if (type.equals(ErrorType.UncaughtException)) {
            // Induce an Uncaught Exception
            ErrorSequenceUtil.testUnhandledException();

        } else {
            // Log an error:  INVALID SELECTION
            log.error("ErrorServiceController.invokeErrorType Invalid Error Type = " + type);
	    throw new ErrorResponseException("Invalid Error Type in API Call");
        }

	return;
    }

}
