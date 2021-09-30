package com.caresoft.clinicapp;
import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements PHICompliantUser, PHIAdminCompliant {
	private Integer employeeID;
	private String role;
	private ArrayList<String> securityIncidents;
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

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

