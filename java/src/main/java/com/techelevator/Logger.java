package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Logger {
	
	private File logFile;
	
	public String getLogPath() {
		String result;
		result = logFile.getAbsolutePath();
		return result;
	}
	
	public Logger() {}
	
	public Logger(String logFile) {
		this.logFile = new File(logFile);
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
}