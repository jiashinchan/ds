/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;
import java.util.*;
/**
 *
 * @author jiash
 */
public class EncryptedText {
    // ASCII values for char
private static final int MAX_CHAR_VALUE = 122; 
private static final int MIN_CHAR_VALUE = 97; 
private static final int CASE_DIFFERENCE = 32; // Difference between lowercase and uppercase ASCII values

//Text :^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.
//Shift: 7

    public static void EncryptedTextSelection() {
    // Begin loop
    while (true) {
        // Get user inputs
        System.out.println("<<<Encrypted Text>>>>");
        System.out.println("""
                           To penetrate the enemy forces,steal the enemy information,and manipulate the
                           decisions.Sun Quan assigned a spy,Pang Tong as Cao Cao's loyal minister.For
                           Sun Quan to communicate with Pang Tong,they cannot write letters in a pure
                           language but instead,have to encrypt the text so that only both of them can
                           understand the text.
                           """);
        Scanner input = new Scanner(System.in); 
        System.out.println("\nHelp Pang Tong to decipher the letter sent by Sun Quan.");
        System.out.print("\nText : ");
        String text = input.nextLine();
        System.out.print("Shift : ");
        String shift = input.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------\n");

        // Validate shift input
        if (shift.matches("\\d+")) {
            // Initialize variables
            String textdecryption = "";
            String textinvertion = "";
            char symbol = ' '; 

            // Loop through characters in text
            for (int i = 0; i < text.length(); i++) {
                char chr = text.charAt(i);

                // Check if char is lower case letter or ')'
                if (chr >= 97 && chr <= MAX_CHAR_VALUE || chr == ')') {
                    // Set symbol as ')' if char is ')'
                    if (chr == ')') symbol = chr; 
                    // Get original char position in ASCII
                    int intOfChr = chr - Integer.parseInt(shift);
                    // Check for any overflow
                    int newIntOfChr = overflow(intOfChr);
                    // Process text based on current symbol
                    switch(symbol) {
                        case ')' : { 
                            for (int o = textinvertion.length() - 1; o >= 0; o--)
                                textdecryption += textinvertion.charAt(o); 
                            textinvertion = "";
                            symbol = ' ';
                        } break;
                        case '(' : { 
                            textinvertion += (char) newIntOfChr;
                        } break;
                        case '^' : { 
                            newIntOfChr -= CASE_DIFFERENCE;
                            symbol = ' '; 
                        } // Allow fall through
                        default : 
                            textdecryption += (char) newIntOfChr;
                    }
                } else if (chr == '$') {
                    textdecryption += ' ';
                } else if (chr == '^' || chr == '(') {
                    symbol = chr;
                } else {
                    textdecryption += chr;
                }
            }
            System.out.println("Secret from Sun Quan : " + textdecryption);
            System.out.println("\n-----------------------------------------------------------------------------\n");
            System.out.print("\nPress Enter to go back to Main");
            input.nextLine();
            System.out.println("\n-----------------------------------------------------------------------------\n");
            return;
        } 
        else {
            System.out.println("\nError!!!! \nShift has to be an integer value");
            System.out.println("\n-----------------------------------------------------------------------------\n");
        }
    }
}

// Method to maintain ASCII value in range of lowercase letters
    private static int overflow(int n) {
        if (n < MIN_CHAR_VALUE) { 
            n = MAX_CHAR_VALUE - ((MIN_CHAR_VALUE - n) - 1); 
            return overflow(n);
        } else {
            return n;
        }
    }
}
