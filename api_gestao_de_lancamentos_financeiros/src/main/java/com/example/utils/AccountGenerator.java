package com.example.utils;

import java.security.SecureRandom;

public class AccountGenerator {
	
	private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int ACCOUNT_NUMBER_LENGTH = 6;
	
	private AccountGenerator() {
		
	}
	
	public static String generateAccountNumber() {
		SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
            accountNumber.append(randomChar);
        }

        return accountNumber.toString();
	}
}
