/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;
import java.util.*;
import java.io.*;
/**
 *
 * @author jiash
 */
public class EncryptedText {
    // ASCII values for char
private int MAX_CHAR_VALUE = 122; 
private int MIN_CHAR_VALUE = 97; 
private int CASE_DIFFERENCE = 32; // Difference between lowercase and uppercase ASCII values

public void EncryptedTextSelection() {
    while(true) {
        Scanner writetext = new Scanner(System.in);
        
        // Presenting options to the user
        System.out.println("   1. Normal Mode  --- Encrypted Text");
        System.out.println("   ~~~ Help Pang Tong to decipher the secret letter sent by Sun Quan. ");
        System.out.println("   ~~~ Caeser Clipher is used as their encryption algorithm. \n ");

        System.out.println("   2. Advanced Mode  --- Text Converter with More Secured Encryption");
        System.out.println("   ~~~ Different encryption algorithm is highly secured and not prone to leakage. ");
        System.out.println("   ~~~ This mode allows both encryption and decryption of the text.");
        System.out.println("   ~~~ Custom Cipher algorithm is used in our text convention. \n");
        
        System.out.println("   -1. Exit Page");
        System.out.print("\n Please Select:  ");
        String choose = writetext.nextLine();
        System.out.println("\n--------------------------------------------------------------------------\n");

        if (choose.matches("\\d")) {
            switch(choose) {
                case "1" : 
                    textDecryption();
                    break;
                case "2" : 
                    useCustomAlgorithm();
                    break;
                default : 
                    System.out.println("Invalid input : " + choose + ". Allowed inputs: [1,2,-1]");
            } 
        } else if (choose.equals("-1")){
            return; // Exit from the function if -1 is selected
        } else {
            System.out.println("Invalid input : " + choose + ". Allowed inputs: [1,2,-1]");
            System.out.println("\n---------------------------------------------------------\n");
        }  
    }
}

//Text :^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.
//Shift: 7

    private void textDecryption() {
    // Begin loop
    while (true) {
        // Get user inputs
        Scanner input = new Scanner(System.in); 
        System.out.println("\nHelp Pang Tong to decipher the letter sent by Sun Quan.");
        System.out.print("\nText : ");
        String text = input.nextLine();
        System.out.print("\nShift : ");
        String shift = input.nextLine();
        System.out.println("\n---------------------------------------------------------\n");

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
            System.out.println("\n--------------------------------------------------------\n");
            return;
        } else {
            System.out.println("\n## Error ##\nShift has to be an integer value");
            System.out.println("\n--------------------------------------------------------\n");
        }
    }
}

// Method to maintain ASCII value in range of lowercase letters
private int overflow(int n) {
    if (n < MIN_CHAR_VALUE) { 
        n = MAX_CHAR_VALUE - ((MIN_CHAR_VALUE - n) - 1); 
        return overflow(n);
    } else {
        return n;
    }
}

    public void useCustomAlgorithm() {
    while (true) {
        Scanner input = new Scanner(System.in);
        System.out.println("M9k3 y0ur t3xt m0r3 s3cur3 w1th 0ur cu5t0m 9lg0r1thm!\n");
        System.out.println("1 <<<  ENCRYPTION OF TEXT  >>>");
        System.out.println("2 <<<  DECRYPTION OF TEXT  >>>");
        System.out.println("-1 Exit page");
        System.out.print("\n Please select: ");
        String select = input.nextLine();
        System.out.println("\n--------------------------------------------------------\n");
        
        switch(select) {
            case "1" : {
                System.out.print("Text : ");
                encrypt(input.nextLine());
            }break;
                
            case "2" : {
                System.out.print("Cipher : ");
                String cipher = input.nextLine();
                
                if (cipher.matches("\\d+")) {
                    System.out.print("Key : ");
                    String key = input.nextLine();
                    System.out.println("\n--------------------------------------------------------\n");
                    
                    try {
                        System.out.println("Text : " + decrypt(cipher, key));
                    } catch (Exception e) {
                        System.out.println("\n## Error ##\nCipher and key are not matching");
                    }
                    System.out.println("\n--------------------------------------------------------\n");
                    System.out.println("Press Enter to Continue......");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        return;
                    }
                } else {
                    System.out.println("\n## Error ##\nInvalid cipher");
                }
            }break;   
            case "-1" :
                return;
            default :
                System.out.println("Invalid input : " + select + ", allowed inputs : [1,2,-1]");
        }
        System.out.println("\n--------------------------------------------------------\n");
    }
}
    
    private void encrypt(String text){
        // Variable declarations
        String key = "", cipher = "",zeroInfo = "", charIntString = "", repeatString = "";
        List<String> cipherInfoMemory = new ArrayList<>();
        
        Random r = new Random();
        int divisor = r.nextInt(550 - 150 + 1) + 150; // Random divisor in range 150 to 550
        int num = r.nextInt(9) + 1; // Random num between 1 and 9
        key += divisor;
        int repeat = 0;

        for (int i = 0; i < text.length(); i++) {
        charIntString += ((int) text.charAt(i)) + num;
        
        while (charIntString.contains("0")) { // remove the zero(s) from the ASCII characters
            zeroInfo += "-" + charIntString.indexOf("0");
            charIntString = charIntString.replaceFirst("0", "-");
        }
        charIntString = charIntString.replaceAll("-", "");
        
        // the loops only runs when there are 5 characters available
        while (charIntString.length() > 5) {
            int bufferInt = Integer.parseInt(charIntString.substring(0, 5)); // get the 5 characters and convert it to Integers
            
            String cipherDouble = String.valueOf((double) bufferInt / (double) divisor); // divide it with the divisor and store the double value
            String cipherInfo = "["  + cipherDouble.indexOf("."); // get the position of the dot and store the index
            cipherDouble = cipherDouble.replace(".", ""); // remove the dot
            cipherInfo += "/" + cipherDouble.length() + "|"; // get the length of the double value and store it
            
            // the below implementation is to reduce the length of cipher info by adding an occurence value (repeat) and save it temporarily in cipherInfoMemory list 
            if (repeatString.equals(cipherInfo)) {
                repeat++;
                cipherInfo = cipherInfo.substring(0, cipherInfo.indexOf("|"));
                cipherInfo = cipherInfo + "|" + repeat + "]";
                cipherInfoMemory.remove(cipherInfoMemory.size() - 1);
                cipherInfoMemory.add(cipherInfo);
            } else {
                repeat = 0;
                repeatString = cipherInfo;
                cipherInfo = cipherInfo.substring(0, cipherInfo.indexOf("|"));
                cipherInfo = cipherInfo + "|" + repeat + "]";
                cipherInfoMemory.add(cipherInfo);
            }
            charIntString = charIntString.substring(5); // remove the first 5 characters which has been encrypted and make the 6th element as the first element
            cipher += cipherDouble; // store the cipher
        }
        for (String cipherInfo : cipherInfoMemory) key += cipherInfo; // saving the cipherInfoMemory in the key 
        key += "&" + num + "{" + charIntString + "}"; // adding the ramaining text in the key if exists
        key += zeroInfo + "-";
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Cipher : " + cipher);
        System.out.println("Key : " + key);
        System.out.println("\n## Warning ##\n do not reveal the key to anyone!");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            return;
        }
    }
}
       
    private String decrypt(String cipher, String key) throws Exception{
        // String declaration
        String text = "", intText = "";
        String zeroInfo = key.substring(key.indexOf("-"));
        String remainingPiece = key.substring(key.indexOf("{") + 1, key.indexOf("}")); // extract the remaning piece
        int num = Integer.parseInt(key.substring(key.indexOf("&") + 1, key.indexOf("{")));
        int multiplier = Integer.parseInt(key.substring(0, key.indexOf("["))); // extract the divisor and use it as multiplier
        key = key.substring(key.indexOf("["), key.indexOf("&")); // filter the key
        
        while (!key.isEmpty()) {
            
            int position = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("/"))); // extract the position of the dot
            int lengthOfCipher = Integer.parseInt(key.substring(key.indexOf("/") + 1, key.indexOf("|"))); // extract the lenght of the double value
            String bufferCipher = cipher.substring(0, lengthOfCipher); // extract the double value
            bufferCipher = bufferCipher.substring(0, position) + "." + bufferCipher.substring(position); // make it double value by adding the dot 
            
            intText += Math.round(Double.parseDouble(bufferCipher) * multiplier); // multipy the double value to convert it back to the original integer value
            cipher = cipher.substring(lengthOfCipher); // remove the cipher that has been decrypted
            
            // the below implementation is to check whether the extract sections of the key is needed in the next iteration or not
            if (key.substring(key.indexOf("|") + 1, key.indexOf("]")).equals("0")) { // if no, remove the used sections
                key = key.substring(key.indexOf("]") + 1);
            } else { // otherwise keep the section but reduce the occurence by 1
                int occurence = Integer.parseInt(key.substring(key.indexOf("|") + 1, key.indexOf("]")));
                key = key.substring(0, key.indexOf("|") + 1) + (occurence - 1) + key.substring(key.indexOf("]"));
            }
        }
        intText += remainingPiece; // add the remaining piece
        while(!zeroInfo.equals("-")) { // add the zero(s) back in the ASCII characters
            zeroInfo = zeroInfo.replaceFirst("-", "");
            int index = Integer.parseInt(zeroInfo.substring(0, zeroInfo.indexOf("-")));
            intText = intText.substring(0, index) + "0" + intText.substring(index);
            zeroInfo = zeroInfo.substring(zeroInfo.indexOf("-"));
        }
        
         // Converting ASCII characters to plain text
        while (!intText.isEmpty()) {
            if (intText.charAt(0) == '1') {
                text += (char) (Integer.parseInt(intText.substring(0, 3)) - num);
                intText = intText.substring(3);
            } else {
                text += (char) (Integer.parseInt(intText.substring(0, 2)) - num);
                intText = intText.substring(2);
            }
        }
        return text;
    }
}
        