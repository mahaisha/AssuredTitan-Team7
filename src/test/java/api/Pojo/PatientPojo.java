package api.Pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"FirstName","LastName","ContactNumber","Email","Allergy","FoodPreference","DateOfBirth"})
public class PatientPojo {
	
	private String FirstName;
	private String LastName;
	private String ContactNumber;
	private String Email;
	private String Allergy;
	private String FoodPreference;
	private String CuisineCategory;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String DateOfBirth;
	

		@JsonGetter(value = "FirstName")
		public String getFirstName() {
			return FirstName;
		}
		public void setFirstName(String firstName) {
			FirstName = firstName;
		}
		@JsonGetter(value = "LastName")
		public String getLastName() {
			return LastName;
		}
		public void setLastName(String lastName) {
			LastName = lastName;
		}
		@JsonGetter(value = "ContactNumber")
		public String getContactNumber() {
			return ContactNumber;
		}
		public void setContactNumber(String string) {
			ContactNumber = string;
		}
		@JsonGetter(value = "Email")
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		@JsonGetter(value = "Allergy")
		public String getAllergy() {
			return Allergy;
		}
		public void setAllergy(String allergy) {
			Allergy = allergy;
		}
		@JsonGetter(value = "FoodPreference")
		public String getFoodPreference() {
			return FoodPreference;
		}
		public void setFoodPreference(String foodPreference) {
			FoodPreference = foodPreference;
		}
		@JsonGetter(value = "DateOfBirth")
		public String getDateOfBirth() {
			return DateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			DateOfBirth = dateOfBirth;
		}
		
		@JsonGetter(value = "CuisineCategory")
		public String getCuisineCategory() {
			return CuisineCategory;
		}
		public void setCuisineCategory(String cuisineCategory) {
			CuisineCategory = cuisineCategory;
		}

	}
