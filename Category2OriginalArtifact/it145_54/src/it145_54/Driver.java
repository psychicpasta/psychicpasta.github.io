package it145_54;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	//..Two array lists for the two different types of rescue animals.
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    
    //..Grazioso Salvare lists only these monkey species that are eligible for training.
    private static String[] acceptedMonkeys = {"Capuchin", "Guenon","Macaque","Marmoset","Squirrel monkey","Tamarin"};
    
    public static void main(String[] args) {
    	
    	String userInput;
    	Scanner scnr = new Scanner(System.in);
    	
        initializeDogList();
        initializeMonkeyList();

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
        	case "q":
        		System.out.println("Have a nice day. Goodbye.");
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
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
    	Monkey monkey1 = new Monkey("Greg", "Capuchin", "male", "2", "10.5", "2.4", "0.9", "2.5", "06-03-2025", "United States", "intake", false, "United States" );
    	Monkey monkey2 = new Monkey("Gina", "Guenon", "female", "1", "6.5", "1.6", "0.5", "1.4", "03-21-2025", "Canada", "Phase I", false, "United States" );
    	Monkey monkey3 = new Monkey("Bill", "Tamarin", "male", "4", "14.3", "3.2", "1.2", "2.9", "10-14-2024", "United States", "in service", false, "Mexico" );

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
        Dog dog = new Dog(name, species, gender, age, weight, acquisitionDate, acquisitonCountry,
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
            Monkey monkey = new Monkey(name, species, gender, age, weight, height, tailLength, bodyLength, 
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
        			System.out.println(dog.getName() + " " + dog.getTrainingStatus() + " " +
        					dog.getAcquisitionLocation() + " " + dog.getReserved());
        		}
        	}
        	//..This list prints animals that are "not reserved".
        	else if(list.equals("available")) {
        		//..Prints unreserved dogs
        		for(Dog dog : dogList) {
        			if((!dog.getReserved()) && (dog.getTrainingStatus().toUpperCase().equals("IN SERVICE"))) {
        				System.out.println(dog.getName() + " " + dog.getTrainingStatus() + " " +
            				dog.getAcquisitionLocation() + " " + dog.getReserved());
        			}
        		}
        		//..Prints unreserved monkeys
        		for(Monkey monkey : monkeyList) {
        			if((!monkey.getReserved()) && (monkey.getTrainingStatus().toUpperCase().equals("IN SERVICE"))) {
        				System.out.println(monkey.getName() + " " + monkey.getTrainingStatus() + " " +
                				monkey.getAcquisitionLocation() + " " + monkey.getReserved());
        			}
        		}
        	}
        }
}
