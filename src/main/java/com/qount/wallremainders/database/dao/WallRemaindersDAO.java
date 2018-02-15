package com.qount.wallremainders.database.dao;

import java.sql.Connection;
import java.util.List;

import com.qount.wallremainders.model.WallRemainders;

public interface WallRemaindersDAO {

	WallRemainders get(Connection conn, WallRemainders wall_remainders);

	List<WallRemainders> getAll(Connection conn, WallRemainders input);

	WallRemainders delete(Connection conn, WallRemainders wall_remainders);
	
	boolean delete(WallRemainders wall_remainders);

	boolean deleteByIds(Connection conn, String commaSeparatedIds);

	WallRemainders create(Connection conn, WallRemainders wall_remainders);

	WallRemainders update(Connection conn, WallRemainders wall_remainders);
	
	boolean update(WallRemainders wall_remainders);

	List<WallRemainders> getAllRemindersByType(Connection conn, WallRemainders wall_remainders);

}