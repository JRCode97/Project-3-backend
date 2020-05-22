package dev.cuny.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Solution {
	private int id;
	private String title;
	private String description;
	private String status;
	private long timeSubmitted;
	private BugReport br;
	private Client client;

	public Solution() {
		super();
	}

	public Solution(int id, String title, String description, String status, int timeSubmitted, BugReport br,
			Client client) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.timeSubmitted = timeSubmitted;
		this.br = br;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(long timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public BugReport getBr() {
		return br;
	}

	public void setBr(BugReport br) {
		this.br = br;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", timeSubmitted=" + timeSubmitted + "]";
	}

}
