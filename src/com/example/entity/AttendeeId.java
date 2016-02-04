package com.example.entity;
// Generated Feb 4, 2016 9:45:19 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AttendeeId generated by hbm2java
 */
@Embeddable
public class AttendeeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int dataId;
	private int number;

	public AttendeeId() {
	}

	public AttendeeId(int dataId, int number) {
		this.dataId = dataId;
		this.number = number;
	}

	@Column(name = "data_id", nullable = false)
	public int getDataId() {
		return this.dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	@Column(name = "number", nullable = false)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AttendeeId))
			return false;
		AttendeeId castOther = (AttendeeId) other;

		return (this.getDataId() == castOther.getDataId()) && (this.getNumber() == castOther.getNumber());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDataId();
		result = 37 * result + this.getNumber();
		return result;
	}

}
