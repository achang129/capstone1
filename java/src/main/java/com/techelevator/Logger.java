package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logger {
	
	private File logFile;
	
	public String getLogPath() {
		String result;
		result = logFile.getAbsolutePath();
		return result;
	}
	
	public Logger() {
		this.logFile = new File("Log.txt");
	}

	public void logPurchase(String itemName, String slotNumber, double beforePayment, double currentMoney) throws FileNotFoundException {
		try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))){
			logWriter.printf(java.time.LocalDate.now() + " " + java.time.LocalTime.now() + " | %s %s $%.2f $%.2f\n", itemName, slotNumber, beforePayment, currentMoney);
		}
	}

	public void logFeed(double priorMoney, double currentMoney) throws FileNotFoundException {
		try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))){
			logWriter.printf(java.time.LocalDate.now() + " " + java.time.LocalTime.now() + " | FEED MONEY: $%.2f $%.2f\n", priorMoney, currentMoney);
		}
	}
	
	public void logChange(double change, double currentMoney) throws FileNotFoundException {
		try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))){
			logWriter.printf(java.time.LocalDate.now() + " " + java.time.LocalTime.now() + " | GIVE CHANGE: $%.2f $%.2f\n", change, currentMoney);
		}
	}
	
	public List<String> printDayofLog(String year, String month, String day){
		List<String> reverseList = new ArrayList<String>();
		try(FileReader fReader = new FileReader(logFile);
				BufferedReader reader = new BufferedReader(fReader)) {
			while(reader.ready()) {
				reverseList.add(reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.reverse(reverseList);
		List<String> returnList =  new ArrayList<String>();
		for(String log : reverseList) {
			if(log.contains(year+"-"+month+"-"+day)) {
				returnList.add(log);
			}
		}
		for(String print : returnList)
			System.out.println(print);
		return returnList;
	}
}