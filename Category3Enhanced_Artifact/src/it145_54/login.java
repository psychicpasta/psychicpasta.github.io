package it145_54;

import java.security.MessageDigest;
import java.util.Scanner;

public class login {
	private static String username = "admanick";
	private static String passwordInHash = "37a8eec1ce19687d132fe29051dca629d164e2c4958ba141d5f4133a33f0688f";
	
	public static boolean loginPrompt() {
		Scanner scnr = new Scanner(System.in);
		
		System.out.print("Enter username : ");
		String usernameInput = scnr.nextLine();
		
		System.out.print("Enter password : ");
		String passwordInput = scnr.nextLine();
		
		String inputHash = hash(passwordInput);
		
		if (username.equals(usernameInput) && passwordInHash.equals(inputHash)) {
			System.out.println("Welcome " + username + "\n");
			return true;
		}
		else {
			System.out.println("Invalid credentials.\n");
			return false;
		}
	}
	
	public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
