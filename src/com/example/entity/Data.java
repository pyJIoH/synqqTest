package com.example.entity;
// Generated Feb 4, 2016 9:45:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

/**
 * Data generated by hbm2java
 */
@Entity
@Table(name = "data", catalog = "pyjioh")
public class Data implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int startTime;
	private Set<Attendee> attendeeses = new HashSet<Attendee>();

	public Data() {
	}

	public Data(int id, int startTime) {
		this.id = id;
		this.startTime = startTime;
	}

	public Data(int id, int startTime, Set<Attendee> attendeeses) {
		this.id = id;
		this.startTime = startTime;
		this.attendeeses = attendeeses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "startTime", nullable = false)
	public int getStartTime() {
		return this.startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "data")
	@OrderBy(clause = "number")
	public Set<Attendee> getAttendeeses() {
		return this.attendeeses;
	}

	public void setAttendeeses(Set<Attendee> attendeeses) {
		this.attendeeses = attendeeses;
	}

}
