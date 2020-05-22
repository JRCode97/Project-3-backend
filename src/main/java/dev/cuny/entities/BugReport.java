package dev.cuny.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BugReport {

	private int bId;
	private String title;
	private String description;
	private String repSteps;
	private String username;
	private String severity;
	private String priority;
	private String status;
	private String location;
	private long approvedTime;
	private long resolvedTime;
	private long dateCreated;
	private int pointValue;
	private Application app;

	private List<Solution> solutions;

	public BugReport() {
		super();
	}

	public BugReport(int bId, String title, String description, String repSteps, String username, String severity,
			String priority, String status, String location, long approvedTime, long resolvedTime, long createdTime,
			int pointValue, Application app) {
		super();
		this.bId = bId;
		this.title = title;
		this.description = description;
		this.repSteps = repSteps;
		this.username = username;
		this.severity = severity;
		this.priority = priority;
		this.status = status;
		this.location = location;
		this.approvedTime = approvedTime;
		this.resolvedTime = resolvedTime;
		this.dateCreated = createdTime;
		this.pointValue = pointValue;
		this.app = app;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
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

	public String getRepSteps() {
		return repSteps;
	}

	public void setRepSteps(String repSteps) {
		this.repSteps = repSteps;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(long approvedTime) {
		this.approvedTime = approvedTime;
	}

	public long getResolvedTime() {
		return resolvedTime;
	}

	public void setResolvedTime(long resolvedTime) {
		this.resolvedTime = resolvedTime;
	}

	public long getCreatedTime() {
		return dateCreated;
	}

	public void setCreatedTime(long createdTime) {
		this.dateCreated = createdTime;

	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	@Override
	public String toString() {
		return "BugReport [bId=" + bId + ", title=" + title + ", description=" + description + ", repSteps=" + repSteps
				+ ", username=" + username + ", severity=" + severity + ", priority=" + priority + ", status=" + status
				+ ", location=" + location + ", approvedTime=" + approvedTime + ", resolvedTime=" + resolvedTime
				+ ", createdDate=" + dateCreated + ", pointValue=" + pointValue + "]"; // app is removed from here

	}
}
