package com.qount.wallremainders.database.dao.impl;

/**
 * 
 * @author apurva
 * @version 1.0
 * Feb 5th 2018
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qount.wallremainders.database.dao.WallRemaindersDAO;
import com.qount.wallremainders.model.WallRemainders;
import com.qount.wallremainders.utils.Constants;
import com.qount.wallremainders.utils.DatabaseUtilities;
import com.qount.wallremainders.utils.SqlQuerys;

public class WallRemaindersDAOImpl implements WallRemaindersDAO {

	private static Logger LOGGER = Logger.getLogger(WallRemaindersDAOImpl.class);

	private WallRemaindersDAOImpl() {
	}

	private static final WallRemaindersDAOImpl WallRemaindersdaoimpl = new WallRemaindersDAOImpl();

	public static WallRemaindersDAOImpl getWallRemaindersDAOImpl() {
		return WallRemaindersdaoimpl;
	}

	@Override
	public WallRemainders get(Connection conn, WallRemainders wallRemainders) {
		LOGGER.debug("entered get:" + wallRemainders);
		if (wallRemainders == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.GET_QRY);
				pstmt.setString(1, wallRemainders.getId());
				rset = pstmt.executeQuery();
				while (rset.next()) {
					wallRemainders.setId(rset.getString("id"));
					wallRemainders.setName(rset.getString("name"));
					wallRemainders.setDescription(rset.getString("description"));
					wallRemainders.setData(rset.getString("data"));
					wallRemainders.setStart_at(rset.getLong("start_at"));
					wallRemainders.setEnd_at(rset.getLong("end_at"));
					wallRemainders.setNext_fired_at(rset.getLong("next_fired_at"));
					wallRemainders.setPrevious_fired_at(rset.getLong("previous_fired_at"));
					wallRemainders.setFrequency(rset.getLong("frequency"));
					wallRemainders.setIs_active(rset.getBoolean("is_active"));
					wallRemainders.setState(rset.getString("state"));
					wallRemainders.setPriority(rset.getInt("priority"));
					wallRemainders.setCreated_at(rset.getLong("created_at"));
					wallRemainders.setCreated_by(rset.getString("created_by"));
					wallRemainders.setLast_updated_at(rset.getLong("last_updated_at"));
					wallRemainders.setLast_updated_by(rset.getString("last_updated_by"));
					wallRemainders.setCompany_id(rset.getString("company_id"));
					wallRemainders.setFired_count(rset.getInt("fired_count"));
					wallRemainders.setRecurring(rset.getBoolean("recurring"));
					wallRemainders.setType(rset.getString("type"));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error retrieving wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeResultSet(rset);
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited getAll:" + wallRemainders);
		return wallRemainders;
	}

	@Override
	public List<WallRemainders> getAll(Connection conn, WallRemainders input) {
		LOGGER.debug("entered getAll");
		List<WallRemainders> result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			if (conn != null) {
				result = new ArrayList<WallRemainders>();
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.GET_ALL_QRY);
				pstmt.setString(1, input.getCreated_by());
				pstmt.setString(2, input.getCompany_id());
				rset = pstmt.executeQuery();
				while (rset.next()) {
					WallRemainders wallRemainders = new WallRemainders();
					wallRemainders.setId(rset.getString("id"));
					wallRemainders.setName(rset.getString("name"));
					wallRemainders.setDescription(rset.getString("description"));
					wallRemainders.setData(rset.getString("data"));
					wallRemainders.setStart_at(rset.getLong("start_at"));
					wallRemainders.setEnd_at(rset.getLong("end_at"));
					wallRemainders.setNext_fired_at(rset.getLong("next_fired_at"));
					wallRemainders.setPrevious_fired_at(rset.getLong("previous_fired_at"));
					wallRemainders.setFrequency(rset.getLong("frequency"));
					wallRemainders.setIs_active(rset.getBoolean("is_active"));
					wallRemainders.setState(rset.getString("state"));
					wallRemainders.setPriority(rset.getInt("priority"));
					wallRemainders.setCreated_at(rset.getLong("created_at"));
					wallRemainders.setCreated_by(rset.getString("created_by"));
					wallRemainders.setLast_updated_at(rset.getLong("last_updated_at"));
					wallRemainders.setLast_updated_by(rset.getString("last_updated_by"));
					wallRemainders.setCompany_id(rset.getString("company_id"));
					wallRemainders.setFired_count(rset.getInt("fired_count"));
					wallRemainders.setRecurring(rset.getBoolean("recurring"));
					wallRemainders.setType(rset.getString("type"));
					result.add(wallRemainders);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error retrieving all wall_remainders", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeResultSet(rset);
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited getAll");
		return result;
	}

	@Override
	public List<WallRemainders> getAllRemindersByType(Connection conn, WallRemainders input) {
		LOGGER.debug("entered getAllRemindersByType");
		List<WallRemainders> result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			if (conn != null) {
				result = new ArrayList<WallRemainders>();
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.GET_ALL_BY_TYPE_QRY);
				pstmt.setString(1, input.getType());
				rset = pstmt.executeQuery();
				while (rset.next()) {
					WallRemainders wallRemainders = new WallRemainders();
					wallRemainders.setId(rset.getString("id"));
					wallRemainders.setName(rset.getString("name"));
					wallRemainders.setDescription(rset.getString("description"));
					wallRemainders.setData(rset.getString("data"));
					wallRemainders.setStart_at(rset.getLong("start_at"));
					wallRemainders.setEnd_at(rset.getLong("end_at"));
					wallRemainders.setNext_fired_at(rset.getLong("next_fired_at"));
					wallRemainders.setPrevious_fired_at(rset.getLong("previous_fired_at"));
					wallRemainders.setFrequency(rset.getLong("frequency"));
					wallRemainders.setIs_active(rset.getBoolean("is_active"));
					wallRemainders.setState(rset.getString("state"));
					wallRemainders.setPriority(rset.getInt("priority"));
					wallRemainders.setCreated_at(rset.getLong("created_at"));
					wallRemainders.setCreated_by(rset.getString("created_by"));
					wallRemainders.setLast_updated_at(rset.getLong("last_updated_at"));
					wallRemainders.setLast_updated_by(rset.getString("last_updated_by"));
					wallRemainders.setCompany_id(rset.getString("company_id"));
					wallRemainders.setFired_count(rset.getInt("fired_count"));
					wallRemainders.setRecurring(rset.getBoolean("recurring"));
					wallRemainders.setType(rset.getString("type"));
					result.add(wallRemainders);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error retrieving all wall_remainders by type", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeResultSet(rset);
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited getAllRemindersByType");
		return result;
	}

	@Override
	public WallRemainders delete(Connection conn, WallRemainders wall_remainders) {
		LOGGER.debug("entered delete:" + wall_remainders);
		if (wall_remainders == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.DELETE_QRY);
				pstmt.setString(1, wall_remainders.getId());
				int rowCount = pstmt.executeUpdate();
				if (rowCount == 0) {
					throw new WebApplicationException("no record deleted", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error deleting wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited delete:" + wall_remainders);
		return wall_remainders;
	}

	@Override
	public boolean delete(WallRemainders wall_remainders) {
		LOGGER.debug("entered delete:" + wall_remainders);
		if (wall_remainders == null) {
			return false;
		}
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.DELETE_QRY);
				pstmt.setString(1, wall_remainders.getId());
				int rowCount = pstmt.executeUpdate();
				if (rowCount > 0) {
					return true;
				}
				if (rowCount == 0) {
					throw new WebApplicationException("no record deleted", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error deleting wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited delete:" + wall_remainders);
		return false;
	}

	@Override
	public boolean deleteByIds(Connection conn, String commaSeparatedIds) {
		LOGGER.debug("entered delete:" + commaSeparatedIds);
		if (StringUtils.isBlank(commaSeparatedIds)) {
			throw new WebApplicationException("Invalid input", Constants.INVALID_INPUT);
		}
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				String query = SqlQuerys.WallRemainders.DELETE_BY_IDS_QRY;
				query += commaSeparatedIds + ");";
				pstmt = conn.prepareStatement(query);
				int rowCount = pstmt.executeUpdate();
				if (rowCount > 0) {
					return true;
				}
				if (rowCount == 0) {
					throw new WebApplicationException("no record deleted", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error deleting wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited delete:" + commaSeparatedIds);
		return false;
	}

	@Override
	public WallRemainders create(Connection conn, WallRemainders wall_remainders) {
		LOGGER.debug("entered create:" + wall_remainders);
		if (wall_remainders == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				int ctr = 1;
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.INSERT_QRY);
				pstmt.setString(ctr++, wall_remainders.getId());
				pstmt.setString(ctr++, wall_remainders.getName());
				pstmt.setString(ctr++, wall_remainders.getDescription());
				pstmt.setString(ctr++, wall_remainders.getData());
				pstmt.setLong(ctr++, wall_remainders.getStart_at());
				pstmt.setLong(ctr++, wall_remainders.getEnd_at());
				pstmt.setLong(ctr++, wall_remainders.getNext_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getPrevious_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getFrequency());
				pstmt.setBoolean(ctr++, wall_remainders.getIs_active());
				pstmt.setString(ctr++, wall_remainders.getState());
				pstmt.setInt(ctr++, wall_remainders.getPriority());
				pstmt.setLong(ctr++, wall_remainders.getCreated_at());
				pstmt.setString(ctr++, wall_remainders.getCreated_by());
				pstmt.setLong(ctr++, wall_remainders.getLast_updated_at());
				pstmt.setString(ctr++, wall_remainders.getLast_updated_by());
				pstmt.setString(ctr++, wall_remainders.getCompany_id());
				pstmt.setInt(ctr++, wall_remainders.getFired_count());
				pstmt.setBoolean(ctr++, wall_remainders.isRecurring());
				pstmt.setString(ctr++, wall_remainders.getType());
				int rowCount = pstmt.executeUpdate();
				if (rowCount == 0) {
					throw new WebApplicationException("no record inserted", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error inserting wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited create:" + wall_remainders);
		return wall_remainders;
	}

	@Override
	public WallRemainders update(Connection conn, WallRemainders wall_remainders) {
		LOGGER.debug("entered update:" + wall_remainders);
		if (wall_remainders == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				int ctr = 1;
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.UPDATE_QRY);
				pstmt.setString(ctr++, wall_remainders.getName());
				pstmt.setString(ctr++, wall_remainders.getDescription());
				pstmt.setString(ctr++, wall_remainders.getData());
				pstmt.setLong(ctr++, wall_remainders.getStart_at());
				pstmt.setLong(ctr++, wall_remainders.getEnd_at());
				pstmt.setLong(ctr++, wall_remainders.getNext_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getPrevious_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getFrequency());
				pstmt.setBoolean(ctr++, wall_remainders.getIs_active());
				pstmt.setString(ctr++, wall_remainders.getState());
				pstmt.setInt(ctr++, wall_remainders.getPriority());
				pstmt.setLong(ctr++, wall_remainders.getLast_updated_at());
				pstmt.setString(ctr++, wall_remainders.getLast_updated_by());
				pstmt.setString(ctr++, wall_remainders.getCompany_id());
				pstmt.setString(ctr++, wall_remainders.getId());
				int rowCount = pstmt.executeUpdate();
				if (rowCount == 0) {
					throw new WebApplicationException("no record updated", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error updating wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited update:" + wall_remainders);
		return wall_remainders;
	}

	@Override
	public boolean update(WallRemainders wall_remainders) {
		LOGGER.debug("entered update:" + wall_remainders);
		if (wall_remainders == null) {
			return false;
		}
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getReadWriteConnection();
			if (conn != null) {
				int ctr = 1;
				pstmt = conn.prepareStatement(SqlQuerys.WallRemainders.UPDATE_QRY_1);
				pstmt.setLong(ctr++, wall_remainders.getNext_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getPrevious_fired_at());
				pstmt.setLong(ctr++, wall_remainders.getLast_updated_at());
				pstmt.setString(ctr++, wall_remainders.getLast_updated_by());
				pstmt.setString(ctr++, wall_remainders.getCompany_id());
				pstmt.setInt(ctr++, wall_remainders.getFired_count());
				pstmt.setString(ctr++, wall_remainders.getId());
				int rowCount = pstmt.executeUpdate();
				if (rowCount > 0) {
					return true;
				}
				if (rowCount == 0) {
					throw new WebApplicationException("no record updated", Constants.EXPECTATION_FAILED);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error updating wall_remainders:", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		} finally {
			DatabaseUtilities.closeStatement(pstmt);
		}
		LOGGER.debug("exited update:" + wall_remainders);
		return false;
	}

}