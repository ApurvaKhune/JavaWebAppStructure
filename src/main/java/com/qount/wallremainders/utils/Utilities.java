package com.qount.wallremainders.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qount.wallremainders.clients.httpClient.HTTPClient;
import com.qount.wallremainders.common.PropertyManager;

public class Utilities {

	private static final Logger LOGGER = Logger.getLogger(Utilities.class);
	private static final Map<String, String> currencyCache = new HashMap<>();
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static Response constructResponse(String message, int statusHeader) {
		JSONObject responseJSON = new JSONObject();
		responseJSON.put("message", message);
		return Response.status(statusHeader).entity(responseJSON.toString()).type(MediaType.APPLICATION_JSON_TYPE)
				.build();
	}

	/*
	 * @param status
	 * 
	 * @param message
	 * 
	 * @param statusHeader
	 * 
	 * @return
	 */
	public static Response constructResponse(String status, String message, Status statusHeader) {
		JSONObject responseJSON = new JSONObject();
		responseJSON.put(STATUS, status);
		responseJSON.put(MESSAGE, message);
		return Response.status(statusHeader).entity(responseJSON.toString()).type(MediaType.APPLICATION_JSON_TYPE)
				.build();
	}

	public static String getLtmUrl(String hostName, String portName) {
		String path = null;
		try {
			String internalLinkingAddress = null, internalLinkingPort = null;
			internalLinkingAddress = System.getenv(hostName);
			internalLinkingPort = System.getenv(portName);
			LOGGER.debug("internalLinkingAddress:" + internalLinkingAddress);
			LOGGER.debug("internalLinkingPort:" + internalLinkingPort);
			if (!StringUtils.isBlank(internalLinkingAddress) && !StringUtils.isBlank(internalLinkingPort)) {
				path = "http://" + internalLinkingAddress + ":" + internalLinkingPort + "/";
			}
			return path;
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static String getCurrencySymbol(String currencyCode) {
		String symbol = null;
		try {
			if (StringUtils.isNotBlank(currencyCode)) {
				symbol = currencyCache.get(currencyCode);
				if (StringUtils.isNotBlank(symbol)) {
					return symbol;
				}
				// if ("INR".equalsIgnoreCase(currencyCode)) {
				// return "₹";
				// }
				if ("AUD".equalsIgnoreCase(currencyCode)) {
					return "$";
				}
				if ("USD".equalsIgnoreCase(currencyCode)) {
					return "$";
				}
				symbol = Currency.getInstance(currencyCode).getSymbol();
				currencyCache.put(currencyCode, symbol);
			}
		} catch (Exception e) {
			LOGGER.error("Error fetching currency symbol for code [ " + currencyCode + "] ", e);
		}
		return symbol;
	}

	public static String getCurrencyHtmlSymbol(String currencyHtmlSymbol) {
		try {
			if (StringUtils.isNotBlank(currencyHtmlSymbol)) {
				if (currencyHtmlSymbol.contains(",")) {
					return currencyHtmlSymbol.split(",")[0];
				} else {
					return currencyHtmlSymbol;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error currencyHtmlSymbol [ " + currencyHtmlSymbol + "] ", e);
		}
		return "";
	}

	public static String convertDate(String dateFrom, SimpleDateFormat dateFromFormat, SimpleDateFormat dateToFormat) {
		try {
			return dateToFormat.format(dateFromFormat.parse(dateFrom));
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static void throwPreExceptionForEmptyString(String... strings) {
		if (strings == null || strings.length == 0) {
			throw new WebApplicationException(Constants.PRECONDITION_FAILED_STR);
		}
		for (String str : strings) {
			if (StringUtils.isEmpty(str)) {
				throw new WebApplicationException(Constants.PRECONDITION_FAILED_STR);
			}
		}
	}

	public static String unschduleInvoiceJob(String jobId) {
		try {
			LOGGER.debug("entered unscheduling job: " + jobId);
			if (StringUtils.isEmpty(jobId)) {
				return null;
			}
			LOGGER.debug("unscheduling job: " + jobId);
			String remainderServieUrl = Utilities.getLtmUrl(
					PropertyManager.getProperty("remainder.service.docker.hostname"),
					PropertyManager.getProperty("remainder.service.docker.port"));
			LOGGER.debug("unscheduling job url:" + remainderServieUrl);
			remainderServieUrl += "RemainderService/mail/unschedule/" + jobId;
			// remainderServieUrl =
			// "http://remainderservice-dev.be0c8795.svc.dockerapp.io:93/RemainderService/mail/unschedule/"
			// + jobId;
			String result = HTTPClient.delete(remainderServieUrl);
			LOGGER.debug("unscheduling result:" + result);
			return result;
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static String unschduleInvoiceJobs(List<String> jobIds) {
		try {
			LOGGER.debug("entered unscheduling jobs: " + jobIds);
			if (jobIds == null || jobIds.isEmpty()) {
				return null;
			}
			LOGGER.debug("unscheduling jobs: " + jobIds);
			String remainderServieUrl = Utilities.getLtmUrl(
					PropertyManager.getProperty("remainder.service.docker.hostname"),
					PropertyManager.getProperty("remainder.service.docker.port"));
			LOGGER.debug("unscheduling job url:" + remainderServieUrl);
			remainderServieUrl += "RemainderService/mail/unschedule";
			JSONArray jobIdsArr = new JSONArray(jobIds);
			Object result = HTTPClient.postObject(remainderServieUrl, jobIdsArr.toString());
			LOGGER.debug("unscheduling result:" + result);
			return result.toString();
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("asdf");
		list.add("asdf1");
		list.add("asdf2");
		JSONArray listArr = new JSONArray(list);
		System.out.println(listArr.toString());
	}

	public static String toQoutedCommaSeparatedString(List<String> strings) {
		String result = null;
		if (strings != null && !strings.isEmpty()) {
			result = "";
			for (int i = 0; i < strings.size(); i++) {
				result += "'" + strings.get(i) + "',";
			}
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	public static String getErrorStackTrace(Throwable th) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		return sw.toString();
	}
}
