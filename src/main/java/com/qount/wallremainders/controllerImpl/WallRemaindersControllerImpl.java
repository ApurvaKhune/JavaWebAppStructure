package com.qount.wallremainders.controllerImpl;

/**
 * 
 * @author apurva
 * @version 1.0
 * Feb 5th 2018
 */
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qount.wallremainders.database.mySQL.MySQLManager;
import com.qount.wallremainders.model.WallRemainders;
import com.qount.wallremainders.parser.WallRemaindersParser;
import com.qount.wallremainders.utils.Constants;
import com.qount.wallremainders.utils.DatabaseUtilities;
import com.qount.wallremainders.utils.ResponseUtil;
import com.qount.wallremainders.utils.Utilities;

public class WallRemaindersControllerImpl {

	private static Logger LOGGER = Logger.getLogger(WallRemaindersControllerImpl.class);

	public static WallRemainders getWallRemainder(String userId, String companyId, String id) {
		Connection conn = null;
		if (StringUtils.isAnyBlank(userId, companyId, id)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":reminder id ,userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			WallRemainders wall_remainders = new WallRemainders();
			wall_remainders.setId(id);
			wall_remainders = MySQLManager.getWallRemaindersDAO().get(conn, wall_remainders);
			return wall_remainders;
		} catch (WebApplicationException e) {
			LOGGER.error("getWall_remainders", e);
			throw new WebApplicationException(Utilities.constructResponse(e.getMessage(), e.getResponse().getStatus()));
		} catch (Exception e) {
			LOGGER.error("getWall_remainders", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
	}

	public static List<WallRemainders> getAllWallRemainders(String userId, String companyId) {
		Connection conn = null;
		if (StringUtils.isAnyBlank(userId, companyId)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			WallRemainders wall_remainders = new WallRemainders();
			wall_remainders.setCreated_by(userId);
			wall_remainders.setCompany_id(companyId);
			List<WallRemainders> wall_remainders_list = MySQLManager.getWallRemaindersDAO().getAll(conn,
					wall_remainders);
			return wall_remainders_list;
		} catch (WebApplicationException e) {
			LOGGER.error("getWall_remainderss", e);
			throw new WebApplicationException(Utilities.constructResponse(e.getMessage(), e.getResponse().getStatus()));
		} catch (Exception e) {
			LOGGER.error("getWall_remainderss", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
	}

	public static List<WallRemainders> getAllWallRemaindersByType(String type) {
		Connection conn = null;
		if (StringUtils.isAnyBlank(type)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			WallRemainders wall_remainders = new WallRemainders();
			wall_remainders.setType(type);
			List<WallRemainders> wall_remainders_list = MySQLManager.getWallRemaindersDAO().getAllRemindersByType(conn,
					wall_remainders);
			return wall_remainders_list;
		} catch (WebApplicationException e) {
			LOGGER.error("getWall_remainderss", e);
			throw new WebApplicationException(Utilities.constructResponse(e.getMessage(), e.getResponse().getStatus()));
		} catch (Exception e) {
			LOGGER.error("getWall_remainderss", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
	}

	public static Response deleteWall_remainders(String userId, String companyId, String id) {
		if (StringUtils.isAnyBlank(userId, companyId, id)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":reminder id ,userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			WallRemainders wall_remainders = new WallRemainders();
			wall_remainders.setId(id);
			wall_remainders = MySQLManager.getWallRemaindersDAO().delete(conn, wall_remainders);
			return Response.ok(wall_remainders).build();
		} catch (WebApplicationException e) {
			LOGGER.error("deleteWall_remainders", e);
			throw new WebApplicationException(Utilities.constructResponse(e.getMessage(), e.getResponse().getStatus()));
		} catch (Exception e) {
			LOGGER.error("deleteWall_remainders", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
	}

	public static Response createWallRemainder(String userId, String companyId, WallRemainders wall_remainders) {
		LOGGER.debug("entered createWallRemainder(String userID:" + userId + ",companyID:" + companyId
				+ " WallRemainders)" + wall_remainders);
		Connection conn = null;
		if (wall_remainders == null || StringUtils.isAnyBlank(userId, companyId)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			wall_remainders = WallRemaindersParser.getWallRemaindersObj(wall_remainders, userId, companyId);
			WallRemainders wall_remainders_result = MySQLManager.getWallRemaindersDAO().create(conn, wall_remainders);
			if (wall_remainders_result != null) {
				return Response.ok(wall_remainders).build();
			}
		} catch (WebApplicationException e) {
			LOGGER.error("createWall_remainders", e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("createWall_remainders", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
		throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
				Constants.UNEXPECTED_ERROR_STATUS_STR, Status.EXPECTATION_FAILED));
	}

	public static Response updateWall_remainders(String userId, String companyId, String id,
			WallRemainders wallRemainders) {
		LOGGER.debug("entered updateWall_remainders(String userID:" + userId + ",companyID:" + companyId
				+ " WallRemainders :" + wallRemainders + " wallRemainder id :" + id);
		Connection conn = null;
		if (wallRemainders == null || StringUtils.isAnyBlank(userId, companyId, id)) {
			throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
					Constants.PRECONDITION_FAILED_STR + ":userID and companyID are mandatory",
					Status.PRECONDITION_FAILED));
		}
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn == null) {
				throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
						"Database Error", Status.EXPECTATION_FAILED));
			}
			wallRemainders.setId(id);
			wallRemainders = WallRemaindersParser.getWallRemaindersObj(wallRemainders, userId, companyId);
			WallRemainders wallRemainders_result = MySQLManager.getWallRemaindersDAO().update(conn, wallRemainders);
			if (wallRemainders_result != null) {
				return Response.ok(wallRemainders_result).build();
			}
		} catch (WebApplicationException e) {
			LOGGER.error("updateWall_remainders", e);
			throw new WebApplicationException(Utilities.constructResponse(e.getMessage(), e.getResponse().getStatus()));
		} catch (Exception e) {
			LOGGER.error("updateWall_remainders", e);
			throw new WebApplicationException(
					Utilities.constructResponse(e.getMessage(), Constants.EXPECTATION_FAILED));
		} finally {
			DatabaseUtilities.closeConnection(conn);
		}
		throw new WebApplicationException(ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
				Constants.UNEXPECTED_ERROR_STATUS_STR, Status.EXPECTATION_FAILED));
	}

	public static void main(String[] args) throws ParseException {
		// String myDate = "02/06/2018 00:00:00";
		// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// Date date = sdf.parse(myDate);
		// long millis = date.getTime();
		// System.out.println(millis);

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		String millis = "1517855400000";
		long tempo1 = Long.parseLong(millis);
		System.out.println(tempo1);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(tempo1);
		System.out.println(formatter.format(calendar.getTime()));
	}

}