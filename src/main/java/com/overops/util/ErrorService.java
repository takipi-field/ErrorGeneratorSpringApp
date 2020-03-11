package com.overops.util;

import com.overops.util.ErrorType;
import com.overops.util.ErrorSequenceUtil;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorService {

	private final static Logger log = LoggerFactory.getLogger(ErrorSequenceUtil.class);

	public int invokeErrorType(String typeArg) {

		ErrorType type = ErrorType.valueOf(typeArg);

		int result = 0;

		log.info("ERRORSERVLET ErrorService.invokeErrorType type = " + type);
		
		if (type.equals(ErrorType.NoError)) {
			// Execute a clean transaction with no errors
			try {
				ErrorSequenceUtil.testNoError();
			} catch (Exception e) {
				log.error(RandomUtil.convertStackTraceToString(e));
			}

		} else if (type.equals(ErrorType.NullPointerException)) {
			// Induce a Caught NullPointerException
			ErrorSequenceUtil.testHandledNullPointer();

		} else if (type.equals(ErrorType.NumberFormatException)) {
			// Induce a Caught NumberFormatException
			ErrorSequenceUtil.testHandledNumberFormat();

		} else if (type.equals(ErrorType.ParseException)) {
			// Induce a Caught ParseException
			ErrorSequenceUtil.testHandledParseException();

		} else if (type.equals(ErrorType.UncaughtException)) {
			// Induce an Uncaught Exception
			ErrorSequenceUtil.testUnhandledException();

		} else {
			// Log an error:  INVALID SELECTION
			log.error("ERRORSERVLET ErrorService.invokeErrorType Invalid Error Type = " + type);
			result = 1;
		}

		return(result);
		
	} // invokeErrorType

} // class ErrorService
