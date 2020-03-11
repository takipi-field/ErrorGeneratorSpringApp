package com.overops.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.takipi.api.client.ApiClient;
import com.takipi.api.client.RemoteApiClient;
import com.takipi.api.client.data.view.SummarizedView;
import com.takipi.api.client.request.view.ViewsRequest;
import com.takipi.api.client.result.view.ViewsResult;
import com.takipi.api.core.url.UrlClient.Response;

// import com.overops.util.ErrorLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorSequenceUtil {
	private final static Logger log = LoggerFactory.getLogger(ErrorSequenceUtil.class);

	
	public static void testErrors () {
	
		sleep();
		testErrorSequence();

	}

	public static void testHandledNullPointer() {

		String strValue = null;

                /* generate a handled NullPointerException */
                try {
                        if (strValue.equalsIgnoreCase("hello")) {
                        }
                } catch (Exception e) {
                        log.error(RandomUtil.convertStackTraceToString(e));
                }

		//sleep();
	}

	public static void testHandledNumberFormat() {

                String numValue = "One";

                /* generate a handled NumberFormatException */
                try {
                        Integer.parseInt(numValue);
                } catch (NumberFormatException e) {
                        log.error(RandomUtil.convertStackTraceToString(e));
			String oo = e.getMessage().replace("[", "");
			oo = oo.replace("]", "");
                }

		sleep();
	}

	public static void testHandledParseException() {

                String dateValue = "100-100-1100";

                /* generate a ParseException */
                try {
                        long value = RandomUtil.convertDate(dateValue);
                } catch (Exception e) {
                        log.error("Error converting date, " + RandomUtil.convertStackTraceToString(e));
			String oo = e.getMessage().replace("[", "");
			oo = oo.replace("]", "");
                }

		sleep();
	}

	public static void testLoggedError() {
		String errorMsg = "This is a logged error message";
		log.error(errorMsg);

		sleep();
	}

	public static void testUnhandledException() {

		Object obj = new Integer(100);

                /* generate an unhandled exception */
		String strObj = (String)obj;
	}

	public static void testNoError() {
/*
		HttpClient client = new DefaultHttpClient();
		HttpGet request = 
			new HttpGet("https://api.overops.com/api/v1/services/S35461/views");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
  		}
*/
		// create a new API Client
		ApiClient apiClient = RemoteApiClient.newBuilder()
        		.setHostname("https://api.overops.com")
        		.setApiKey("OxX58Z44WwARo1NTv0fzPbwCCqXcxRhatqsKP8oE")
        		.build();

		ViewsRequest viewsRequest = ViewsRequest.newBuilder()
			.setServiceId("S35461")
			.build();

/*
		Response<ViewsResult> viewsResponse = apiClient.get(viewsRequest);

		if (viewsResponse.isBadResponse()) {
			throw new IllegalStateException("Failed to get views.");
		}

		ViewsResult viewsResult = viewsResponse.data;

		// print all views
    		for(SummarizedView view : viewsResult.views) {
      			System.out.println(view.name + "(" + view.id + ")");
    		}
*/
		log.info("Dummy transaction");
	}

	/*
	 * This creates a sequence of caught then an uncaught error
	 */
	public static void testErrorSequence () {

		testHandledNullPointer();
		testHandledNumberFormat();
		testHandledParseException();
		testLoggedError();
		testUnhandledException();

	}
	
	public static void sleep () {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
