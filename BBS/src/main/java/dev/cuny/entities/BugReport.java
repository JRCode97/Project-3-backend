package dev.cuny.entities;

public class BugReport {

	private int bId;
	private String subject;
	private String description;
	private String repSteps;
	private String username;
	private String severity;
	private String priority;
	private String status;
	private String location;
	private int approvedTime;
	private int resolvedTime;
	private int createdTime;
	private int pointValue;
	
	private Application app;

	public BugReport() {
		super();
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public int getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(int approvedTime) {
		this.approvedTime = approvedTime;
	}

	public int getResolvedTime() {
		return resolvedTime;
	}

	public void setResolvedTime(int resolvedTime) {
		this.resolvedTime = resolvedTime;
	}

	public int getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(int createdTime) {
		this.createdTime = createdTime;
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

	@Override
	public String toString() {
		return "BugReport [bId=" + bId + ", subject=" + subject + ", description=" + description + ", repSteps="
				+ repSteps + ", username=" + username + ", severity=" + severity + ", priority=" + priority
				+ ", status=" + status + ", location=" + location + ", approvedTime=" + approvedTime + ", resolvedTime="
				+ resolvedTime + ", createdTime=" + createdTime + ", pointValue=" + pointValue + "]";	// app is removed from here
	}
}
