package it145_54;

import java.util.ArrayList;

import java.util.Scanner;

public class Driver {
	//..Two array lists for the two different types of rescue animals.
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    
    public static int newID = 1000;
    
    //..Grazioso Salvare lists only these monkey species that are eligible for training.
    private static String[] acceptedMonkeys = {"Capuchin", "Guenon","Macaque","Marmoset","Squirrel monkey","Tamarin"};
    
    @SuppressWarnings("resource")
	public static void main(String[] args) {
    	
    	String userInput;
    	Scanner scnr = new Scanner(System.in);
    	
    	//..Gives the user the login prompt. If it fails, the program terminates.
    	if(!login.loginPrompt()) {
    		return;
    	}
    	
    	//..Load data from the previously saved json files.
    	dogList = DataManage.dogLoad();
    	monkeyList = DataManage.monkeyLoad();

        //..This loop will iterate and display the menu until the user chooses to quit.
        do {
        	displayMenu();
        	userInput = scnr.nextLine();
        	
        	switch (userInput) {
        	case "1":
        		intakeNewDog(scnr);
        		break;
        	case "2":
        		intakeNewMonkey(scnr);
        		break;
        	case "3":
        		reserveAnimal(scnr);
        		break;
        	case "4":
        		printAnimals("dogs");
        		break;
        	case "5":
        		printAnimals("monkeys");
        		break;
        	case "6":
        		printAnimals("available");
        		break;
        	case "7":
        		updateAnimal();
        		break;
        	case "8":
        		deleteAnimal();
        		break;
        	case "q":
        		System.out.println("Have a nice day. Goodbye.");
        		DataManage.dogSave(dogList);
        		DataManage.monkeySave(monkeyList);
        		break;
        	default:
        		System.out.println("Invalid Input. Please try again.");
        		break;
        	}
        	
        } while(!userInput.equals("q"));
        
       //..End of main
        return;

    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[7] Update an animal in the system");
        System.out.println("[8] Delete an animal from the system");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog(newID++, "Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog(newID++, "Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog(newID++, "Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
    	Monkey monkey1 = new Monkey(newID++, "Greg", "Capuchin", "male", "2", "10.5", "2.4", "0.9", "2.5", "06-03-2025", "United States", "intake", false, "United States" );
    	Monkey monkey2 = new Monkey(newID++, "Gina", "Guenon", "female", "1", "6.5", "1.6", "0.5", "1.4", "03-21-2025", "Canada", "Phase I", false, "United States" );
    	Monkey monkey3 = new Monkey(newID++, "Bill", "Tamarin", "male", "4", "14.3", "3.2", "1.2", "2.9", "10-14-2024", "United States", "in service", false, "Mexico" );

    	monkeyList.add(monkey1);
    	monkeyList.add(monkey2);
    	monkeyList.add(monkey3);
    }


    //..Function for adding a new dog to the system.
    public static void intakeNewDog(Scanner scanner) {
    	//..This checks to see if the dog already exists in the system
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }
        
        //..Prompts the user for the necessary information
        System.out.println("What is the dog's species?");
        String species = scanner.nextLine();
        
        System.out.println("What is the dog's gender?");
        String gender = scanner.nextLine();
        
        System.out.println("What is the dog's age in years?");
        String age = scanner.nextLine();
        
        System.out.println("What is the dog's weight in pounds?");
        String weight = scanner.nextLine();
        
        System.out.println("What is the dog's acquisition date? MM-DD-YYYY");
        String acquisitionDate = scanner.nextLine();
        
        System.out.println("What is the dog's acquisition country?");
        String acquisitonCountry = scanner.nextLine();
        
        System.out.println("What is the dog's training status?");
        String trainingStatus = scanner.nextLine();
        
        System.out.println("What is the dog's reserved status? true / false");
        Boolean reserved = scanner.nextBoolean();
        scanner.nextLine(); //..Clears the "enter key" on the line
        
        System.out.println("What is the dog's service country?");
        String serviceCountry = scanner.nextLine();
        
        //..Creates the dog object
        Dog dog = new Dog(newID++, name, species, gender, age, weight, acquisitionDate, acquisitonCountry,
        		trainingStatus, reserved, serviceCountry);
        
        //..Adds the dog to the list.
        dogList.add(dog);
        System.out.println("Dog sucessfully added.");
        return;
    }


    	//..Function for adding a new monkey to the system.
        public static void intakeNewMonkey(Scanner scanner) {
        	
        	//..Variable to track whether the monkey species is accepted.
        	Boolean accepted = false;
        	
            System.out.println("What is the monkey's name?");
            String name = scanner.nextLine();
            //..Checks to see if the monkey already exists in the system.
            for(Monkey monkey: monkeyList) {
            	if(monkey.getName().equalsIgnoreCase(name)) {
                    System.out.println("\n\nThis monkey is already in our system\n\n");
                    return; //..Returns to menu
                }
            }
            
            System.out.println("What is the monkey's species?");
            String species = scanner.nextLine();
            //..Checks to see if it is a trainable species
            for(String monkeySpecies: acceptedMonkeys) {
            	if((monkeySpecies.toUpperCase().equals(species.toUpperCase()))) {
            		accepted = true;
            	}   	
            }
            
            //..If not accepted...
            if(!accepted) {
	            System.out.println("\n\nThis is not an accepted monkey species.");
	    		return; //..Returns to menu
            }
    		
            //..Prompts the user for the remaining necessary data.
            System.out.println("What is the monkey's gender?");
            String gender = scanner.nextLine();
            
            System.out.println("What is the monkey's age in years?");
            String age = scanner.nextLine();
            
            System.out.println("What is the monkey's weight in pounds?");
            String weight = scanner.nextLine();
            
            System.out.println("What is the monkey's height in feet?");
            String height = scanner.nextLine();
            
            System.out.println("What is the monkey's tail length in feet?");
            String tailLength = scanner.nextLine();
            
            System.out.println("What is the monkey's body length in feet?");
            String bodyLength = scanner.nextLine();
            
            System.out.println("What is the monkey's acquisition date?  MM-DD-YYYY");
            String acquisitionDate = scanner.nextLine();
            
            System.out.println("What is the monkey's acquisition country?");
            String acquisitonCountry = scanner.nextLine();
            
            System.out.println("What is the monkey's training status?");
            String trainingStatus = scanner.nextLine();
            
            System.out.println("What is the monkey's reserved status? true / false");
            Boolean reserved = scanner.nextBoolean();
            scanner.nextLine(); //..Clears the "enter key" on the line
            
            System.out.println("What is the monkey's service country?");
            String serviceCountry = scanner.nextLine();
            
            //..Creates the monkey object
            Monkey monkey = new Monkey(newID++, name, species, gender, age, weight, height, tailLength, bodyLength, 
            		acquisitionDate, acquisitonCountry, trainingStatus, reserved, serviceCountry);
            
            //..Adds the monkey to the list.
            monkeyList.add(monkey);
            System.out.println("Monkey sucessfully added.");
            return;
        }
     
        //..Function for reserving one of the animals in the system
        public static void reserveAnimal(Scanner scanner) {
        	//..User inputs the animal type and country
            System.out.print("Please enter the animal type and your country separated by a comma: ");
            //..Input is separated by a space
            String[] input = scanner.nextLine().split(",");
            
            //..Stores the split input into two variables
            String animalType = input[0];
            String userCountry = input[1];
            
            //..Checks to see if it's a dog
            if(animalType.toUpperCase().equals("DOG")) {
            	//..Iterates through the available dogs looking for an unreserved in the desired country
            	for(Dog dog : dogList) {
            		//..If it finds one, it marks it as reserved and prints a confirmation.
            		if((userCountry.equals(dog.getInServiceLocation())) 
            				&& !(dog.getReserved())) {
            			dog.setReserved(true);
            			System.out.println(dog.getName() + " has been reserved.");
            			return;
            		}
            	}
            	//..No match was found.
            	System.out.println("There was no dog found in that country. Please check again later.");
            }
            else if(animalType.toUpperCase().equals("MONKEY")) {
            	//..Iterates through the available monkeys looking for an unreserved in the desired country
            	for(Monkey monkey : monkeyList) {
            		//..If it finds one, it marks it as reserved and prints a confirmation.
            		if((userCountry.equals(monkey.getInServiceLocation())) 
            				&& !(monkey.getReserved())) {
            			monkey.setReserved(true);
            			System.out.println(monkey.getName() + " has been reserved.");
            			return;
            		}
            	}
            	//..No match was found
            	System.out.println("There was no monkey found in that country. Please check again later.");
            }
            //..The user didn't enter "dog" or "monkey".
            else {
            	System.out.println("Invalid service animal. The options are Monkey and Dog.");
            }
            
            return;
        }

        // Complete printAnimals
        // Include the animal name, status, acquisition country and if the animal is reserved.
	// Remember that this method connects to three different menu items.
        // The printAnimals() method has three different outputs
        // based on the listType parameter
        // dog - prints the list of dogs
        // monkey - prints the list of monkeys
        // available - prints a combined list of all animals that are
        // fully trained ("in service") but not reserved 
	// Remember that you only have to fully implement ONE of these lists. 
	// The other lists can have a print statement saying "This option needs to be implemented".
	// To score "exemplary" you must correctly implement the "available" list.
        public static void printAnimals(String list) {
        	//..The monkeys list needs to be completed
        	if(list.equals("monkeys")) {
        		System.out.println("The method printAnimals needs to be implemented");
        	}
        	else if(list.equals("dogs")) {
        		for(Dog dog: dogList) {
        			System.out.println(dog.getID() + " " + dog.getName() + " " + dog.getTrainingStatus() + " " +
        					dog.getAcquisitionLocation() + " " + dog.getReserved());
        		}
        	}
        	//..This list prints animals that are "not reserved".
        	else if(list.equals("available")) {
        		//..Prints unreserved dogs
        		for(Dog dog : dogList) {
        			if((!dog.getReserved()) && (dog.getTrainingStatus().toUpperCase().equals("IN SERVICE"))) {
        				System.out.println(dog.getID() + " " + dog.getName() + " " + dog.getTrainingStatus() + " " +
            				dog.getAcquisitionLocation() + " " + dog.getReserved());
        			}
        		}
        		//..Prints unreserved monkeys
        		for(Monkey monkey : monkeyList) {
        			if((!monkey.getReserved()) && (monkey.getTrainingStatus().toUpperCase().equals("IN SERVICE"))) {
        				System.out.println(monkey.getID() + " " + monkey.getName() + " " + monkey.getTrainingStatus() + " " +
                				monkey.getAcquisitionLocation() + " " + monkey.getReserved());
        			}
        		}
        	}
        }
        
        //..Method for updating the requested animal.
        //..Will enter 1 of 2 submethods to update the specific type
        public static void updateAnimal() {
        	Scanner scnr = new Scanner(System.in);
        	int userInput;
        	int ID;
        	
        	//..Will decide which logic branch is taken
        	System.out.println("What type of animal do you want to update?");
        	System.out.println("[1]: Dog");
        	System.out.println("[2]: Monkey");
        	userInput = scnr.nextInt();
        	
        	//..This is used to identify which animal is being updated.
        	//..Each animal has a unique identifier so that is the best choice
        	//..to avoid data loss.
        	System.out.println("What is the ID of the animal you are updating?");
        	ID = scnr.nextInt();
        	
        	switch(userInput) {
        	case 1:
        		updateDog(ID);
        		break;
        	case 2:
        		updateMonkey(ID);
        		break;
        	default:
        		//..1 or 2 was not selected
        		System.out.println("Invalid animal. Try again.");
        		return;
        	}
        }
        
        //..Method for updating a specific monkey object in the monkeyList
        //..Takes the ID of the monkey being updated as the parameter
        public static void updateMonkey(int ID) {
        	Scanner scnr = new Scanner(System.in);
        	String inputStr;
        	boolean inputBool;
        	int index = 0;
        	
        	//..getMonkey returns the monkey object that matches the given ID
        	//..and stores it in a temp variable we can manipulate
        	Monkey upMonkey = getMonkey(ID);
        	
        	//..The temp monkey from getMonkey will have a null name, so if upMonkey
        	//..has a null name, that means that a matching ID was not found.
        	if (upMonkey.getName().equals(null)) {
        		System.out.println("Monkey not found. Try again.");
        		return;
        	}
        	
        	//..Prints the updates available for a monkey
        	int option = printUpdateOptions("monkey");
        	
        	//..Each option corresponds to the ones displayed to the user previously
        	//..Depending on the choice, that data is updated
        	switch (option) {
        	case 1:
        		System.out.print("New name?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setName(inputStr);
        		break;
        	case 2:
        		System.out.print("New Gender?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setGender(inputStr);
        		break;
        	case 3:
        		System.out.print("New Age?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setAge(inputStr);
        		break;
        	case 4:
        		System.out.print("New Weight?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setWeight(inputStr);
        		break;
        	case 5:
        		System.out.print("New Acquisition Date?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setAcquisitionDate(inputStr);
        		break;
        	case 6:
        		System.out.print("New Aquisition Country?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setAcquisitionLocation(inputStr);
        		break;
        	case 7:
        		System.out.print("New Training Status?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setTrainingStatus(inputStr);
        		break;
        	case 8:
        		System.out.print("New Reserved Status?: ");
        		inputBool = scnr.nextBoolean();
        		upMonkey.setReserved(inputBool);
        		break;
        	case 9:
        		System.out.print("New Service Country?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setInServiceCountry(inputStr);
        		break;
        	case 10:
        		System.out.print("New Species?: ");
        		inputStr = scnr.nextLine();
        		break;
        	case 11:
        		System.out.print("New Height?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setHeight(inputStr);
        		break;
        	case 12:
        		System.out.print("New Tail Length?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setTailLength(inputStr);
        		break;
        	case 13:
        		System.out.print("New Body Length?: ");
        		inputStr = scnr.nextLine();
        		upMonkey.setBodyLength(inputStr);
        		break;
        	default:
        		System.out.println("Invalid option choice. Try again");
        		return;
        	}
        	
        	//..This will get the index of the monkey in monkeyList that has a matching ID
        	//..Index starts at 0 and implements each time a matching ID is not found.
        	//..When it stops, then we know we found the right index. We know this won't go out of bounds
        	//..because it was already confirmed a matching monkey exists
        	for(Monkey monkey: monkeyList) {
        		if (monkey.getID() == ID) {
        			break;
        		}
        		index++;
        	}
        	
        	//..Updates the monkey list with the new monkey and replaces the old at the found index
        	monkeyList.set(index, upMonkey);
        	System.out.println("Field Updated.");
        	
        	return;
        }
        
      //..Method for updating a specific dog object in the dogList
        //..Takes the ID of the dog being updated as the parameter
        public static void updateDog(int ID) {
        	Scanner scnr = new Scanner(System.in);
        	String inputStr;
        	Boolean inputBool;
        	int index = 0;
        	
        	//..getDog returns the dog object that matches the given ID
        	//..and stores it in a temp variable we can manipulate
        	Dog upDog = getDog(ID);
        	
        	//..The temp dog from getDog will have a null name, so if upDog
        	//..has a null name, that means that a matching ID was not found.
        	if (upDog.getName().equals(null)) {
        		System.out.println("Dog not found. Try again.");
        		return;
        	}
        	
        	//..Prints the updates available for a dog
        	int option = printUpdateOptions("dog");
        	
        	//..Each option corresponds to the ones displayed to the user previously
        	//..Depending on the choice, that data is updated
        	switch (option) {
        	case 1:
        		System.out.print("New name?: ");
        		inputStr = scnr.nextLine();
        		upDog.setName(inputStr);
        		break;
        	case 2:
        		System.out.print("New Gender?: ");
        		inputStr = scnr.nextLine();
        		upDog.setGender(inputStr);
        		break;
        	case 3:
        		System.out.print("New Age?: ");
        		inputStr = scnr.nextLine();
        		upDog.setAge(inputStr);
        		break;
        	case 4:
        		System.out.print("New Weight?: ");
        		inputStr = scnr.nextLine();
        		upDog.setWeight(inputStr);
        		break;
        	case 5:
        		System.out.print("New Acquisition Date?: ");
        		inputStr = scnr.nextLine();
        		upDog.setAcquisitionDate(inputStr);
        		break;
        	case 6:
        		System.out.print("New Aquisition Country?: ");
        		inputStr = scnr.nextLine();
        		upDog.setAcquisitionLocation(inputStr);
        		break;
        	case 7:
        		System.out.print("New Training Status?: ");
        		inputStr = scnr.nextLine();
        		upDog.setTrainingStatus(inputStr);
        		break;
        	case 8:
        		System.out.print("New Reserved Status?: ");
        		inputBool = scnr.nextBoolean();
        		upDog.setReserved(inputBool);
        		break;
        	case 9:
        		System.out.print("New Service Country?: ");
        		inputStr = scnr.nextLine();
        		upDog.setInServiceCountry(inputStr);
        		break;
        	case 10:
        		System.out.print("New Breed?: ");
        		inputStr = scnr.nextLine();
        		upDog.setBreed(inputStr);
        		break;
        	default:
        		System.out.println("Invalid option choice. Try again");
        		break;
        	}
        	
        	//..This will get the index of the dog in dogList that has a matching ID
        	//..Index starts at 0 and implements each time a matching ID is not found.
        	//..When it stops, then we know we found the right index. We know this won't go out of bounds
        	//..because it was already confirmed a matching dog exists
        	for(Dog dog: dogList) {
        		if (dog.getID() == ID) {
        			break;
        		}
        		index++;
        	}
        	
        	//..Updates the dog list with the new dog and replaces the old at the found index
        	dogList.set(index, upDog);
        	System.out.println("Field Updated.");
        	
        	return;
        	
        }

        //..Returns a monkey object from monkeyList that has a matching ID as the parameter
        public static Monkey getMonkey(int ID) {
        	//..Create a temp monkey
        	Monkey retMonkey = new Monkey(ID, null, null, null, null, null, null, null, null, null, null, null, false, null);
        	
        	//..Go through the monkey list and when a match is found, save that monkey and exit
        	for(Monkey monkey: monkeyList) {
        		if (monkey.getID() == ID) {
        			retMonkey = monkey;
        			break;
        		}
        	}
        	//..Return the saved monkey
        	return retMonkey;
        }
        
      //..Returns a dog object from dogList that has a matching ID as the parameter
        public static Dog getDog(int ID) {
        	Dog retDog = new Dog(ID, null, null, null, null, null, null, null, null, false, null);
        	
        	//..Go through the dog list and when a match is found, save that dog and exit
        	for (Dog dog: dogList) {
        		if (dog.getID() == ID) {
        			retDog = dog;
        		}
        	}
        	//..Return the saved dog
        	return retDog;
        }
        
        //..Prints the available update options for either a monkey or dog
        public static int printUpdateOptions(String animal) {
        	
        	Scanner scnr = new Scanner(System.in);
        	
        	System.out.println("What field do you want to update?");
        	System.out.println("[1]: Name");
        	System.out.println("[2]: Gender");
        	System.out.println("[3]: Age");
        	System.out.println("[4]: Weight");
        	System.out.println("[5]: Acquisition Date");
        	System.out.println("[6]: Acquisition Country");
        	System.out.println("[7]: Training Status");
        	System.out.println("[8]: Reserved Status");
            System.out.println("[9]: Service Country");
        	
            //..If a dog is being updated...
        	if (animal.equals("dog")) {
        		System.out.println("[10]: Breed");
        	}
        	//..If a monkey is being updated...
        	else if(animal.equals("monkey")){
        		System.out.println("[10]: Species");
        		System.out.println("[11]: Height");
        		System.out.println("[12]: Tail length");
        		System.out.println("[13]: Body length");
        	}
        	
        	return scnr.nextInt();
        }
        
        //..Method for deleting an animal from the rescue animal system
        public static void deleteAnimal() {
        	Scanner scnr = new Scanner(System.in);
        	int userInput;
        	int ID;
        	int index = 0;
        	
        	
        	//..We need to know this to either search through the dog or monkey list
        	System.out.println("What type of animal do you want to delete?");
        	System.out.println("[1]: Dog");
        	System.out.println("[2]: Monkey");
        	userInput = scnr.nextInt();
        	
        	//..Get the unique ID so that the wrong animal is not deleted
        	System.out.println("What is the ID of the animal you are deleting?");
        	ID = scnr.nextInt();
        	
        	//..If it's a dog...
        	if (userInput == 1) {
        		//..Try to find a matching dog and when it is,
        		//..the incrementing index is stopped at the right location
        		for(Dog dog: dogList) {
        			if (dog.getID() == ID) {
        				break;
        			}
        			index++;
        		}
        		//..If the index ever equals or is bigger than the size of the list,
        		//..then that means a match was never found.
        		if (index >= dogList.size()) {
        			System.out.println("Dog not found.");
        			return;
        		}
        		//..Removes the dog at that index from the list
        		else {
        			dogList.remove(index);
        			System.out.println("Animal deleted.");
        		}
        	}
        	//..If it's a monkey...
        	else if (userInput == 2) {
        		//..Try to find a matching monkey and when it is,
        		//..the incrementing index is stopped at the right location
        		for(Monkey monkey: monkeyList) {
        			if (monkey.getID() == ID) {
        				break;
        			}
        			index++;
        		}
        		//..If the index ever equals or is bigger than the size of the list,
        		//..then that means a match was never found.
        		if (index >= monkeyList.size()) {
        			System.out.println("Monkey not found.");
        			return;
        		}
        		//..Removes the monkey at that index from the list
        		else {
        			monkeyList.remove(index);
        			System.out.println("Animal deleted.");
        		}
        	}
        	//..An invalid option was chosen.
        	else {
        		System.out.println("Invalid input. Try again.");
        	}
        	
        	return;
        }
        
        
}
