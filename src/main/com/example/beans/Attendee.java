package main.com.example.beans;
// Generated Feb 6, 2016 6:26:22 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Attendee generated by hbm2java
 */
@Entity
@Table(name = "attendees", catalog = "pyjioh")
public class Attendee implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private AttendeeId id;
	private Event event;

	public Attendee() {
	}

	public Attendee(AttendeeId id, Event event) {
		this.id = id;
		this.event = event;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "eventId", column = @Column(name = "event_id", nullable = false) ),
			@AttributeOverride(name = "number", column = @Column(name = "number", nullable = false) ) })
	public AttendeeId getId() {
		return this.id;
	}

	public void setId(AttendeeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
