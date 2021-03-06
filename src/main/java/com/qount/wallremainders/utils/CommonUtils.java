package com.qount.wallremainders.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class CommonUtils {
	private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static String toCommaSeparatedString(List<String> strings) {
		Joiner joiner = Joiner.on(",").skipNulls();
		return joiner.join(strings);
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

	public static List<String> fromCommaSeparatedString(String string) {
		if (StringUtils.isBlank(string)) {
			return new ArrayList<>();
		}
		return Splitter.on("-").trimResults().splitToList(string);
	}

	public static JSONObject getJsonFromString(String str) {
		JSONObject result = null;
		try {
			if (!StringUtils.isBlank(str)) {
				result = new JSONObject(str);
			}
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return result;
	}

	public static List<String> getListString(String str) {
		List<String> result = null;
		try {
			if (!StringUtils.isBlank(str)) {
				JSONArray emailArr = getJsonArrayFromString(str);
				if (isValidJSONArray(emailArr)) {
					result = new ArrayList<String>();
					for (int i = 0; i < emailArr.length(); i++) {
						result.add(emailArr.optString(i));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return result;
	}

	public static JSONArray getJsonArrayFromString(String str) {
		JSONArray result = null;
		try {
			if (!StringUtils.isBlank(str)) {
				result = new JSONArray(str);
			}
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return result;
	}

	public static JSONArray getJsonArrayFromList(List<String> lst) {
		JSONArray result = null;
		try {
			if (lst != null && !lst.isEmpty()) {
				result = new JSONArray();
				for (int i = 0; i < lst.size(); i++) {
					result.put(lst.get(i));
				}
			}
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return result;
	}

	public static boolean isValidJSON(JSONObject jsonObject) {
		boolean result = false;
		try {
			if (null != jsonObject && jsonObject.length() != 0) {
				result = true;
			}
		} catch (Exception e) {
			LOGGER.error(getErrorStackTrace(e));
		}
		return result;
	}

	public static boolean isValidJSONArray(JSONArray jsonArray) {
		boolean result = false;
		try {
			if (null != jsonArray && jsonArray.length() != 0) {
				result = true;
			}
		} catch (Exception e) {
			LOGGER.error(getErrorStackTrace(e));
		}
		return result;
	}

	public static String getErrorStackTrace(Throwable th) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		return sw.toString();
	}

	public static Response constructResponse(String message, int statusHeader) {
		JSONObject responseJSON = new JSONObject();
		responseJSON.put(MESSAGE, message);
		return Response.status(statusHeader).entity(responseJSON.toString()).type(MediaType.APPLICATION_JSON_TYPE)
				.build();
	}

	public static String convertDate(String sourceDate, SimpleDateFormat sourceDateFormat,
			SimpleDateFormat resultDateFormat) {
		try {
			return resultDateFormat.format(sourceDateFormat.parse(sourceDate));
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static Date getDate(String sourceDate, SimpleDateFormat sourceDateFormat) {
		try {
			return sourceDateFormat.parse(sourceDate);
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return null;
	}

	public static boolean isValidStrings(String... strings) throws Exception {
		try {
			if (strings == null || strings.length == 0) {
				throw new Exception("empty input");
			}
			for (String str : strings) {
				if (StringUtils.isEmpty(str)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return false;
	}

	public static boolean isAnyStringValid(String... strings) throws Exception {
		try {
			if (strings == null || strings.length == 0) {
				throw new Exception("empty input");
			}
			for (String str : strings) {
				if (StringUtils.isNotBlank(str)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
		}
		return false;
	}

	public static void removeKeysIfNull(JSONObject input, String... keys) {
		try {
			for (String key : keys) {
				if (StringUtils.isEmpty(input.optString(key))) {
					input.remove(key);
				}
			}
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
			throw e;
		}
	}

}
