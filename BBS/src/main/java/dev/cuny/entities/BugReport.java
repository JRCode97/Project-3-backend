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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bug_report")
public class BugReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bug_report_id")
	private int bId;
	@Column(name = "bug_report_title")
	private String title;
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
	private long approvedTime;
	@Column(name="resolved_time")
	private long resolvedTime;
	@Column(name="date_created")
	private long dateCreated;
	@Column(name="point_value")
	private int pointValue;
	@ManyToOne
	@JoinColumn(name="application_id")
	@JsonIgnoreProperties({"reports"})
	private Application app;

	@OneToMany(mappedBy="br", fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"client","br"})
	private List<Solution> solutions;
	
	public BugReport() {
		super();
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

	public void setCreatedTime(long dateCreated) {
		this.dateCreated = dateCreated;

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
		return "BugReport [bId= " + bId + ", title=" + title + ", description=" + description + ", repSteps="
				+ repSteps + ", username= " + username + ", severity=" + severity + ", priority=" + priority
				+ ", status=" + status + ", location=" + location + ", approvedTime=" + approvedTime + ", resolvedTime="
				+ resolvedTime + ", createdDate=" + dateCreated + ", pointValue=" + pointValue + "]";	// app is removed from here

	}
}
