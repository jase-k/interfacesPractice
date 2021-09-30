package com.caresoft.clinicapp;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class Physician extends User implements PHIAdminCompliant, PHICompliantUser {
    
    private HashSet<Patient> patients;
    private ArrayList<String> securityIncidents;
    
    // ... you see other existing member variables. ...
	private int pinNumber; 

	@Override
	public boolean assignPin(int pin) {
		if(pin < 99999) {
			this.setPinNumber(pin);
			return true;
		}
		return false;
	}

	@Override
	public boolean isAuthorized(Integer confirmedAuthID) {
		if(confirmedAuthID == this.getPinNumber() ) {
			return true;
		}
		this.authIncident();
		return false;
	}
	
	@Override
	public ArrayList<String> reportSecurityIncidents(){
		return this.getSecurityIncidents();
	}
    
    public Physician(HashSet<Patient> patients, ArrayList<String> securityIncidents) {
		super();
		this.patients = patients;
		this.securityIncidents = securityIncidents;
	}

	public void prescribeRXTo(Patient patient, Integer rxNumber) {
        patient.currentPrescriptionsByRX.add(rxNumber);
    }
    
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

	public HashSet<Patient> getPatients() {
		return patients;
	}

	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}

	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

}
