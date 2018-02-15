
package com.qount.wallremainders.utils;

public class SqlQuerys {

	public final class WallRemainders {

		public static final String INSERT_QRY = "INSERT INTO wall_remainders ( `id`, `name`, `description`, `data`, `start_at`, `end_at`, `next_fired_at`, `previous_fired_at`, `frequency`, `is_active`, `state`, `priority`, `created_at`, `created_by`, `last_updated_at`, `last_updated_by`,`company_id`,`fired_count`,`is_recurring`,`type`) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?);";
		public static final String UPDATE_QRY = "UPDATE wall_remainders SET `name` = ?, `description` = ?, `data` = ?, `start_at` = ?, `end_at` = ?, `next_fired_at` = ?, `previous_fired_at` = ?, `frequency` = ?, `is_active` = ?, `state` = ?, `priority` = ?, `last_updated_at` = ?, `last_updated_by` = ?,`company_id` = ? WHERE `id` = ?;";
		public static final String UPDATE_QRY_1 = "UPDATE wall_remainders SET `next_fired_at` = ?, `previous_fired_at` = ?,`last_updated_at` = ?, `last_updated_by` = ?,`company_id` = ? ,`fired_count` = ? WHERE `id` = ?;";
		public static final String DELETE_QRY = "DELETE FROM wall_remainders WHERE `id` = ?;";
		public static final String DELETE_BY_IDS_QRY = "DELETE FROM wall_remainders WHERE `id` IN (";
		public static final String GET_QRY = "SELECT `id`, `name`, `description`, `data`, `start_at`, `end_at`, `next_fired_at`, `previous_fired_at`, `frequency`, `is_active`, `state`, `priority`, `created_at`, `created_by`, `last_updated_at`, `last_updated_by`,`company_id`,`fired_count`,`is_recurring`,`type` FROM `wall_remainders` WHERE `id` = ?;";
		public static final String GET_ALL_QRY = "SELECT `id`, `name`, `description`, `data`, `start_at`, `end_at`, `next_fired_at`, `previous_fired_at`, `frequency`, `is_active`, `state`, `priority`, `created_at`, `created_by`, `last_updated_at`, `last_updated_by` ,`company_id`,`fired_count`,`is_recurring`,`type` FROM wall_remainders where created_by = ? and company_id = ?;";
		public static final String GET_ALL_BY_TYPE_QRY = "SELECT `id`, `name`, `description`, `data`, `start_at`, `end_at`, `next_fired_at`, `previous_fired_at`, `frequency`, `is_active`, `state`, `priority`, `created_at`, `created_by`, `last_updated_at`, `last_updated_by` ,`company_id`,`fired_count`,`is_recurring`,`type` FROM wall_remainders where type = ?;";
	}

}
