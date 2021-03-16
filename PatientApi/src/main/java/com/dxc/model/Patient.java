package com.dxc.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data              
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"appointmentDateTime"},allowGetters = true)

@Entity
public class Patient implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
		@Id				
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int patient_Id;
		private String patient_name;
		private String phoneNumber;
		private String address;		
		private String diagnosis_reason;
		private String doctor_name;
		private int age;
		
		@Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    @CreatedDate
		private Date appointmentDateTime = new Date();
		
		public Date getappointmentDateTime() {
	        return appointmentDateTime;
	    }

	    public void setappointmentDateTime(Date appointmentDateTime) {
	        this.appointmentDateTime = appointmentDateTime;
	    }
}
