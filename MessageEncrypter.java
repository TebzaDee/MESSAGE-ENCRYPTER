/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.encryption;

/**
 *
 * @author IC
 */
public class MessageEncrypter 
{
   public static String encryptMessage(String message)
   {
        String encryptedData=""; 
        for (int i = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (Character.isLetter(c))
            {
                int index = (int) c;
                index += 3;  
                c = (char) index;
            }
            encryptedData+=c;
        }
        return encryptedData.toString();
   }
}
