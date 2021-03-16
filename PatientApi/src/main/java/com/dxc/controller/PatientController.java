package com.dxc.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.Exception.ResourceNotFoundException;
import com.dxc.model.Patient;
import com.dxc.repository.PatientRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "PatientRestController")
@RestController	//used to create RESTful web services using Spring MVC and takes care of mapping request data
@RequestMapping("/api")	//used to map web requests onto specific handler classes and/or handler methods
public class PatientController {
	
	
	//**************************Creation of Patients*******************************

	@ApiOperation(value = "Add Patient ", 
			response = Patient.class, tags = "postPatient")
	 @PostMapping("/patients") 
	 public Patient createPatient(@RequestBody Patient patient) { 
		 return patientRepository.save(patient); 
	  }
	 	
	
	
	// **********************Viewing all details of Patients************************
	
	@Autowired   //inject the object dependency implicitly
	private PatientRepository patientRepository; //Object creation for PatientRepository class
	
	@ApiOperation(value = "Get list of Patient ", 
			response = Iterable.class, tags = "getAllPatient")
	@GetMapping("/patient") //handle the HTTP GET requests matched with given URI expression
	public List<Patient> getAllPatients(){
			return patientRepository.findAll();// Retrieves all the informations from the db
	}
	
	 
	//***********************Updating Patient Details*****************************

	@ApiOperation(value = "Update Patient Details ", 
			response = Patient.class, tags = "putPatient")
	@PutMapping("/patients/{patient_Id}")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient patient, @PathVariable long patient_Id) {

		 Optional<Patient> patientOptional = patientRepository.findById((int) patient_Id);

		 if (!patientOptional.isPresent())
		 		return ResponseEntity.notFound().build();

		 patientRepository.save(patient);
		 //return ResponseEntity.noContent().build();
		 return ResponseEntity.ok("Succesfully Updated");
		 }
	
	
	//*********************Searching Patients by Id****************************

	@ApiOperation(value = "Get Patient by Id ", 
			response = Patient.class, tags = "getPatientId")
	@GetMapping("/patient/{patient_Id}")
	    public ResponseEntity<Patient> getPatientById(@PathVariable(value = "patient_Id") Integer patientId)
	        throws ResourceNotFoundException {
	        Patient patient = patientRepository.findById(patientId)
	        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
	        return ResponseEntity.ok().body(patient);
	    }
	
	
	
	//*******************Searching Patients by Date of Appointment*********************
	@ApiOperation(value = "Get Patient in between dates ", 
			response = Patient.class, tags = "getPatientByDate")
		@GetMapping("/patients/getallbyappointmentDateTimebetween")
		public List getAllByappointmentDateTimeBetween(
				@RequestParam("startdate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startdate,
				@RequestParam("enddate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date enddate) {
			
			return patientRepository.findAllByappointmentDateTimeBetween(startdate, enddate);
		}	 
}
	  
	
	  
	  
	

