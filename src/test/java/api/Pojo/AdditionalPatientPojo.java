package api.Pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Weight", "Height", "Temperature", "SP", "DP"})
public class AdditionalPatientPojo {
	
	
	 private float Weight;
	 private float Height;
	 private float Temperature;
	 private int SP;
	 private int DP;
	 
	

		
		@JsonGetter(value = "Weight")
		 public float getWeight() {
				return Weight;
			}
			public void setWeight(float weight) {
				Weight = weight;
			}
			@JsonGetter(value = "Height")
			public float getHeight() {
				return Height;
			}
			public void setHeight(float height) {
				Height = height;
			}
			@JsonGetter(value = "Temperature")
			public float getTemperature() {
				return Temperature;
			}
			public void setTemperature(float temperature) {
				Temperature = temperature;
			}
			@JsonGetter(value = "SP")
			public int getSP() {
				return SP;
			}
			public void setSP(int sP) {
				SP = sP;
			}
			@JsonGetter(value = "DP")
			public int getDP() {
				return DP;
			}
			public void setDP(int dP) {
				DP = dP;
			}
			

		

	}

	
	
