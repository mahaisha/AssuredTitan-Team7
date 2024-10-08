package api.Pojo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DieticianPojo {

	@JsonProperty("Firstname")
	private String firstName;

	@JsonProperty("Lastname")
	private String lastName;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("ContactNumber")
	private String contactNumber;

	@JsonProperty("Education")
	private String education;

	@JsonProperty("DateOfBirth")
	private String dateOfBirth;

	@JsonProperty("HospitalName")
	private String hospitalName;

	@JsonProperty("HospitalStreet")
	private String hospitalStreet;

	@JsonProperty("HospitalPincode")
	private String hospitalPincode;

	@JsonProperty("HospitalCity")
	private String hospitalCity;

	@JsonProperty("id")
	private String id;

	@JsonProperty("loginPassword")
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalStreet() {
		return hospitalStreet;
	}

	public void setHospitalStreet(String hospitalStreet) {
		this.hospitalStreet = hospitalStreet;
	}

	public String getHospitalPincode() {
		return hospitalPincode;
	}

	public void setHospitalPincode(String hospitalPincode) {
		this.hospitalPincode = hospitalPincode;
	}

	public String getHospitalCity() {
		return hospitalCity;
	}

	public void setHospitalCity(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, dateOfBirth, education, email, firstName, hospitalCity, hospitalName,
				hospitalPincode, hospitalStreet, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DieticianPojo other = (DieticianPojo) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(education, other.education) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(hospitalCity, other.hospitalCity)
				&& Objects.equals(hospitalName, other.hospitalName)
				&& Objects.equals(hospitalPincode, other.hospitalPincode)
				&& Objects.equals(hospitalStreet, other.hospitalStreet) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Dietician [firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", email=" + email 
				+ ", contactNumber=" + contactNumber 
				+ ", education=" + education 
				+ ", dateOfBirth=" + dateOfBirth 
				+ ", hospitalName=" + hospitalName 
				+ ", hospitalStreet=" + hospitalStreet 
				+ ", hospitalPincode=" + hospitalPincode
				+ ", hospitalCity=" + hospitalCity 
				+ ", id=" + id 
				+ ", password=" + password + "]";
	}
}
