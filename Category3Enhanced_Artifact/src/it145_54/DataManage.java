package it145_54;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class DataManage {
	private static String monkeyFile = "monkeys.json";
	private static String dogFile = "dogs.json";
	
	private static Gson gson = new Gson();
	
	//..Saves the data from the current monkey array list when the program is running.
	public static void monkeySave(ArrayList<Monkey> monkeyList) {
		try (Writer writer = new FileWriter(monkeyFile)) {
			gson.toJson(monkeyList, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//..Loads the data from the saved json file when the program starts
	public static ArrayList<Monkey> monkeyLoad() {
        try (Reader reader = new FileReader(monkeyFile)) {
            Type listType = new TypeToken<ArrayList<Monkey>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
	
	//..Saves the data from the current dog array list when the program is running.
	public static void dogSave(ArrayList<Dog> dogList) {
		try (Writer writer = new FileWriter(dogFile)) {
			gson.toJson(dogList, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//..Loads the data from the saved json file when the program starts
	public static ArrayList<Dog> dogLoad() {
        try (Reader reader = new FileReader(dogFile)) {
            Type listType = new TypeToken<ArrayList<Dog>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
	
