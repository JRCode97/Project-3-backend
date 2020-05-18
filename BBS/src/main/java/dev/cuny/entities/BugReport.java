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

@Entity
@Table(name = "bug_report")
public class BugReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bug_report_id")
	private int bId;
	@Column(name = "bug_report_title")
	private String subject;
	@Column(name="bug_report_description")
	private String description;
	@Column(name="bug_report_reproduction_steps")
	private String repSteps;
	@Column(name="client_username")
	private String username;
	@Column(name="severity")
	private String severity;
	@Column(name="priority")
	private String priority;
	@Column(name="status")
	private String status;
	@Column(name="location")
	private String location;
	@Column(name="approved_time")
	private int approvedTime;
	@Column(name="resolved_time")
	private int resolvedTime;
	@Column(name="date_created")
	private int dateCreated;
	@Column(name="point_value")
	private int pointValue;
	@ManyToOne
	@JoinColumn(name="application_id")
	private Application app;

	@OneToMany(mappedBy="br", fetch=FetchType.LAZY)
	private List<Solution> solutions;
	
	public BugReport() {
		super();
	}
	

	public BugReport(int bId, String subject, String description, String repSteps, String username, String severity,
			String priority, String status, String location, int approvedTime, int resolvedTime, int createdTime,
			int pointValue, Application app) {
		super();
		this.bId = bId;
		this.subject = subject;
		this.description = description;
		this.repSteps = repSteps;
		this.username = username;
		this.severity = severity;
		this.priority = priority;
		this.status = status;
		this.location = location;
		this.approvedTime = approvedTime;
		this.resolvedTime = resolvedTime;
		this.dateCreated= createdTime;
		this.pointValue = pointValue;
		this.app = app;
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
		return dateCreated;
	}

	public void setCreatedTime(int createdTime) {
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
		return "BugReport [bId=" + bId + ", subject=" + subject + ", description=" + description + ", repSteps="
				+ repSteps + ", username=" + username + ", severity=" + severity + ", priority=" + priority
				+ ", status=" + status + ", location=" + location + ", approvedTime=" + approvedTime + ", resolvedTime="
				+ resolvedTime + ", createdDate=" + dateCreated + ", pointValue=" + pointValue + "]";	// app is removed from here

	}
}
