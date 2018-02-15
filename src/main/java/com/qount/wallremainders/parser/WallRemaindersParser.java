package com.qount.wallremainders.parser;

/**
 * 
 * @author apurva
 * @version 1.0
 * Feb 5th 2018
 */
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qount.wallremainders.model.WallRemainders;
import com.qount.wallremainders.utils.CommonUtils;
import com.qount.wallremainders.utils.Constants;

public class WallRemaindersParser {

	private static final Logger LOGGER = Logger.getLogger(WallRemaindersParser.class);

	public static WallRemainders getWallRemaindersObj(WallRemainders wall_remainders, String userId, String companyId) {
		if (wall_remainders == null || StringUtils.isAnyBlank(userId, companyId)) {
			throw new WebApplicationException("userId, companyId, currency are mandatory", Constants.INVALID_INPUT);
		}
		try {
			if (StringUtils.isBlank(wall_remainders.getId())) {
				wall_remainders.setId(UUID.randomUUID().toString());
				wall_remainders.setType("wall_post");
				wall_remainders.setFired_count(0);
				wall_remainders.setCreated_by(userId);
				wall_remainders.setCreated_at(System.currentTimeMillis());
			}
			wall_remainders.setCompany_id(companyId);
			wall_remainders.setLast_updated_by(userId);
			wall_remainders.setLast_updated_at(System.currentTimeMillis());
		} catch (Exception e) {
			LOGGER.error(CommonUtils.getErrorStackTrace(e));
			throw new WebApplicationException(e.getLocalizedMessage(), 500);
		}
		return wall_remainders;

	}

	public static void main(String[] argv) throws InterruptedException {
		while (true) {
			System.out.println(new Date());
			Thread.sleep(1 * 60 * 1000);
		}
	}

}
