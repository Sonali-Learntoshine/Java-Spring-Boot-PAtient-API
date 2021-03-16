package com.dxc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

	List findAllByappointmentDateTimeBetween(
			Date dateTimeStart,
			Date dateTimeEnd);
}
