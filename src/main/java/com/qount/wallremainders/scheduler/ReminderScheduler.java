package com.qount.wallremainders.scheduler;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.qount.wallremainders.controllerImpl.WallRemaindersControllerImpl;
import com.qount.wallremainders.database.mySQL.MySQLManager;
import com.qount.wallremainders.model.WallRemainders;
import com.qount.wallremainders.parser.WallRemaindersParser;
import com.qount.wallremainders.utils.Constants;
import com.qount.wallremainders.utils.ResponseUtil;

/**
 * 
 * @author apurva
 * @version 1.0 Feb 7th 2018
 */
public class ReminderScheduler extends Thread {

	private static Logger LOGGER = Logger.getLogger(ReminderScheduler.class);

	public void run() {
		LOGGER.debug("entered reminderScheduler");

		while (true) {
			try {
				Thread.sleep(10 * 60 * 1000);
				executeRemainders();

			} catch (InterruptedException e) {
				LOGGER.error("error in reminderScheduler ", e);
				e.printStackTrace();
			}
		}
	}

	private static void executeRemainders() {
		LOGGER.debug("entered executeremainders");
		try {
			List<WallRemainders> reminders_list = WallRemaindersControllerImpl.getAllWallRemaindersByType("wall_post");
			for (WallRemainders wallRemainders : reminders_list) {
				System.out.println(wallRemainders);
				boolean isFired = false;
				long next_fired_at = wallRemainders.getNext_fired_at();
				long system_time_in_millis = System.currentTimeMillis();
				int fired_count = wallRemainders.getFired_count();
				if (next_fired_at <= system_time_in_millis) {
					isFired = true; // invoke ravi service
				}
				if (isFired) {
					if (wallRemainders.isRecurring()) {
						wallRemainders.setPrevious_fired_at(next_fired_at);
						wallRemainders.setNext_fired_at(next_fired_at + wallRemainders.getFrequency());
						wallRemainders.setFired_count(++fired_count);
						wallRemainders = WallRemaindersParser.getWallRemaindersObj(wallRemainders,
								wallRemainders.getCreated_by(), wallRemainders.getCompany_id());
						boolean is_reminder_updated = MySQLManager.getWallRemaindersDAO().update(wallRemainders);
						if (!is_reminder_updated) {
							throw new WebApplicationException(
									ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
											"error updating wall reminders", Status.EXPECTATION_FAILED));
						}
					} else {
						boolean is_reminder_deleted = MySQLManager.getWallRemaindersDAO().delete(wallRemainders);
						if (!is_reminder_deleted) {
							throw new WebApplicationException(
									ResponseUtil.constructResponse(Constants.FAILURE_STATUS_STR,
											"error in deleting wall reminders", Status.EXPECTATION_FAILED));
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error in executing wall_remainders", e);
			throw new WebApplicationException(e.getMessage(), Constants.EXPECTATION_FAILED);
		}
		LOGGER.debug("exited executeremainders");

	}

}
