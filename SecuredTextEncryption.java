/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;
import java.io.*;
import java.util.*;
/**
 *
 * @author jiash
 */
public class SecuredTextEncryption {
    
     public void SecureTextEncryptionSelection() {
     while (true) {
        Scanner input = new Scanner(System.in);
        System.out.println("<<<Text Converter with More Secured Encryption>>>\n");
        System.out.println("""
                           Since the current Caesar Cipher uses simple shifting.It is very easy for someone to
                           guess the shifting used in the cipher.A new encryption algorithm that is highly secured
                           and not prone to leakage is produced.""");
        System.out.println("""
                           The algorithm we used is custom cipher algorithm which enables encryption and 
                           decryption of text.
                           """);
        System.out.println("1. <<  ENCRYPTION OF TEXT  >>");
        System.out.println("2. <<  DECRYPTION OF TEXT  >>");
        System.out.println("-1 Exit ");
        System.out.print("\n Please select: ");
        String select = input.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------\n");
        
                    switch (select) {
                case "1":
                    System.out.print("Text: ");
                    encryptText(input.nextLine());
                    break;
                case "2":
                    System.out.print("Cipher: ");
                    String cipher = input.nextLine();
                    if (cipher.matches("\\d+")) {
                        System.out.print("Key: ");
                        String key = input.nextLine();
                        System.out.println("\n--------------------------------------------------------\n");
                        try {
                            System.out.println("Text: " + decryptCipher(cipher, key));
                        } catch (Exception e) {
                            System.out.println("\n Error !!! \nCipher and key do not match");
                        }
                        System.out.println("\n--------------------------------------------------------\n");
                        System.out.println("Press enter to continue...");
                        try {
                            System.in.read();
                        } catch (IOException e) {
                            return;
                        }
                    } else {
                        System.out.println("\n Error !!!\nInvalid cipher");
                    }
                    break;
                case "-1":
                    return;
                default:
                    System.out.println("Invalid input: " + select + ", the allowed inputs are 1,2 and -1");
            }
        }
    }

    private void encryptText(String text) {
        String key = "";
        String cipher = "";
        String zeroInfo = "";
        String charIntString = "";
        String repeatString = "";
        List<String> cipherInfoMemory = new ArrayList<>();

        Random random = new Random();
        int divisor = random.nextInt(550 - 150 + 1) + 150; // Get a random divisor in the range 150 to 550
        int num = random.nextInt(9) + 1; // Get a random number between 1 and 9
        key += divisor; // Add the divisor value to the key
        int repeat = 0;

        for (int i = 0; i < text.length(); i++)
            charIntString += ((int) text.charAt(i)) + num; // Convert the characters to ASCII value and add the number

        while (charIntString.contains("0")) {
            zeroInfo += "-" + charIntString.indexOf("0");
            charIntString = charIntString.replaceFirst("0", "-");
        }
        charIntString = charIntString.replaceAll("-", "");

        while (charIntString.length() > 5) {
            int bufferInt = Integer.parseInt(charIntString.substring(0, 5));

            String cipherDouble = String.valueOf((double) bufferInt / (double) divisor);
            String cipherInfo = "[" + cipherDouble.indexOf(".");
            cipherDouble = cipherDouble.replace(".", "");
            cipherInfo += "/" + cipherDouble.length() + "|";

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
            charIntString = charIntString.substring(5);
            cipher += cipherDouble;
        }

        for (String cipherInfo : cipherInfoMemory)
            key += cipherInfo;

        key += "&" + num + "{" + charIntString + "}";
        key += zeroInfo + "-";

        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Cipher: " + cipher);
        System.out.println("Key: " + key);
        System.out.println("\n Warning !!! \nDo not reveal the key to anyone!");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            return;
        }
    }

    private String decryptCipher(String cipher, String key) throws Exception {
        String text = "";
        String intText = "";

        String zeroInfo = key.substring(key.indexOf("-"));
        String remainingPiece = key.substring(key.indexOf("{") + 1, key.indexOf("}"));
        int num = Integer.parseInt(key.substring(key.indexOf("&") + 1, key.indexOf("{")));
        int multiplier = Integer.parseInt(key.substring(0, key.indexOf("[")));
        key = key.substring(key.indexOf("["), key.indexOf("&"));

        while (!key.isEmpty()) {
            int position = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("/")));
            int lengthOfCipher = Integer.parseInt(key.substring(key.indexOf("/") + 1, key.indexOf("|")));
            String bufferCipher = cipher.substring(0, lengthOfCipher);
            bufferCipher = bufferCipher.substring(0, position) + "." + bufferCipher.substring(position);

            intText += Math.round(Double.parseDouble(bufferCipher) * multiplier);
            cipher = cipher.substring(lengthOfCipher);

            if (key.substring(key.indexOf("|") + 1, key.indexOf("]")).equals("0")) {
                key = key.substring(key.indexOf("]") + 1);
            } else {
                int occurrence = Integer.parseInt(key.substring(key.indexOf("|") + 1, key.indexOf("]")));
                key = key.substring(0, key.indexOf("|") + 1) + (occurrence - 1) + key.substring(key.indexOf("]"));
            }
        }

        intText += remainingPiece;
        while (!zeroInfo.equals("-")) {
            zeroInfo = zeroInfo.replaceFirst("-", "");
            int index = Integer.parseInt(zeroInfo.substring(0, zeroInfo.indexOf("-")));
            intText = intText.substring(0, index) + "0" + intText.substring(index);
            zeroInfo = zeroInfo.substring(zeroInfo.indexOf("-"));
        }

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
    public class EncryptionResult {
    private final String cipher;
    private final String key;

    public EncryptionResult(String cipher, String key) {
        this.cipher = cipher;
        this.key = key;
    }

    public String getCipher() {
        return cipher;
    }

    public String getKey() {
        return key;
    }
}
}
