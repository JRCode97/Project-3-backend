package dev.cuny.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private int id;
	@Column(name="application_title")
	private String title;
	@Column(name="application_git_link")
	private String gitLink;
	@OneToMany(mappedBy="app", fetch=FetchType.LAZY)
	private List<BugReport> reports;
	
	public Application() {
		super();
	}
	public Application(int id, String title, String gitLink) {
		super();
		this.id = id;
		this.title = title;
		this.gitLink = gitLink;
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
	public String getGitLink() {
		return gitLink;
	}
	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}
	
	public List<BugReport> getReports() {
		return reports;
	}
	public void setReports(List<BugReport> reports) {
		this.reports = reports;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", title=" + title + ", gitLink=" + gitLink + "]";
	}
	
	


}
