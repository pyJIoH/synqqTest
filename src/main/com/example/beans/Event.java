package main.com.example.beans;
// Generated Feb 4, 2016 9:45:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name = "events", catalog = "pyjioh", 
	indexes = {@Index(name = "startTimeIndex",  columnList="startTime", unique = false)})
public class Event implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int startTime;
	private Set<Attendee> attendees = new HashSet<Attendee>();

	public Event() {
	}

	public Event(int id, int startTime) {
		this.id = id;
		this.startTime = startTime;
	}

	public Event(int id, int startTime, Set<Attendee> attendees) {
		this.id = id;
		this.startTime = startTime;
		this.attendees = attendees;
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

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Set<Attendee> getAttendees() {
		return this.attendees;
	}
	
	@Transient //Ignore from Hibernate mapping
	public List<Integer> getAttendeesNumber() {
		List<Integer> numbers = new LinkedList<>();
		for (Attendee attendee : attendees) {
			numbers.add(attendee.getId().getNumber());
		}
		return numbers;
	}

	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}

}
