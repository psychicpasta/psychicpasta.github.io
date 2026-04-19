package it145_54;

public class Monkey extends RescueAnimal {
	
	//..The listed data elements for a Monkey
	private String tailLength;
	private String height;
	private String bodyLength;
	private String species;
	
	//..Constructor method.
	public Monkey(String name, String species, String gender, String age,
	String weight, String height, String tailLength, String bodyLength, String acquisitionDate, 
	String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry) {
	    setName(name);
	    setSpecies(species);
	    setGender(gender);
	    setAge(age);
	    setWeight(weight);
	    setHeight(height);
	    setTailLength(tailLength);
	    setBodyLength(bodyLength);
	    setAcquisitionDate(acquisitionDate);
	    setAcquisitionLocation(acquisitionCountry);
	    setTrainingStatus(trainingStatus);
	    setReserved(reserved);
	    setInServiceCountry(inServiceCountry);
	}
	
	//..Mutator methods
	
	//..Mutator method for tail length.
	public void setTailLength(String givenTailLength) {
		tailLength = givenTailLength;
	}
	
	//..Mutator method for height.
	public void setHeight(String givenHeight) {
		height = givenHeight;
	}
	
	//..Mutator method for body length.
	public void setBodyLength(String givenBodyLength) {
		bodyLength = givenBodyLength;
	}
	
	//..Mutator method for species.
	public void setSpecies(String givenSpecies) {
		species = givenSpecies;
	}
	
	//..Access methods
	
	//..Access method for tail length.
	public String getTailLength() {
		return tailLength;
	}
	
	//..Access method for height.
	public String getHeight() {
		return height;
	}
	
	//..Access method for body length.
	public String getBodyLength() {
		return bodyLength;
	}
	
	//..Access method for species.
	public String getSpecies() {
		return species;
	}
}
