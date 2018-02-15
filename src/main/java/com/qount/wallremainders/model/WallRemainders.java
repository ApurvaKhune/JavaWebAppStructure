package com.qount.wallremainders.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WallRemainders {

	private String id;
	private String name;
	private String description;
	private String data;
	private long start_at;
	private long end_at;
	private long next_fired_at;
	private long previous_fired_at;
	private long frequency;
	private boolean is_active;
	private String state;
	private int priority;
	private long created_at;
	private String created_by;
	private long last_updated_at;
	private String last_updated_by;
	private String company_id;
	private int fired_count;
	private boolean recurring;
	private String type;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getData() {
		return data;
	}

	public long getStart_at() {
		return start_at;
	}

	public long getEnd_at() {
		return end_at;
	}

	public long getNext_fired_at() {
		return next_fired_at;
	}

	public long getPrevious_fired_at() {
		return previous_fired_at;
	}

	public long getFrequency() {
		return frequency;
	}

	public boolean getIs_active() {
		return is_active;
	}

	public String getState() {
		return state;
	}

	public int getPriority() {
		return priority;
	}

	public long getCreated_at() {
		return created_at;
	}

	public String getCreated_by() {
		return created_by;
	}

	public long getLast_updated_at() {
		return last_updated_at;
	}

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setStart_at(long start_at) {
		this.start_at = start_at;
	}

	public void setEnd_at(long end_at) {
		this.end_at = end_at;
	}

	public void setNext_fired_at(long next_fired_at) {
		this.next_fired_at = next_fired_at;
	}

	public void setPrevious_fired_at(long previous_fired_at) {
		this.previous_fired_at = previous_fired_at;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public void setLast_updated_at(long last_updated_at) {
		this.last_updated_at = last_updated_at;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getFired_count() {
		return fired_count;
	}

	public void setFired_count(int fired_count) {
		this.fired_count = fired_count;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.toString();
	}

	public static void main(String[] args) {
		System.out.println(System.getenv("SERVER_INSTANCE_MODE"));
	}
}