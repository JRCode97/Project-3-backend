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
@Entity
@Table(name = "solution")
public class Solution {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "solution_id")
	private int id;
	@Column(name="solution_title")
	private String title;
	@Column(name="solutiion")
	private String description;
	@Column(name="status")
	private String status;
	@Column(name="submitted_time")
	private int timeSubmitted;
	@ManyToOne
	@JoinColumn(name="bug_report_id")
	@JsonIgnore
	private BugReport br;
	@ManyToOne
	@JoinColumn(name="solver_client_id")
	private Client client;
	
	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}
}

