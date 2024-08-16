package api.Pojo;

public class PatientPojo {
	
	private String FirstName;
	private String LastName;
	private String ContactNumber;
	private String Email;
	private String Allergy;
	private String FoodPrefernce;
	private String CuisineCategory;
	
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getContactNumber() {
		return ContactNumber;
	}
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAllergy() {
		return Allergy;
	}
	public void setAllergy(String allergy) {
		Allergy = allergy;
	}
	public String getFoodPrefernce() {
		return FoodPrefernce;
	}
	public void setFoodPrefernce(String foodPrefernce) {
		FoodPrefernce = foodPrefernce;
	}
	public String getCuisineCategory() {
		return CuisineCategory;
	}
	public void setCuisineCategory(String cuisineCategory) {
		CuisineCategory = cuisineCategory;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	private String DateOfBirth;
	
	
}
