/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keymanager.validator;

/**
 *
 * @author angelukayetiu
 */
public class RegistrationValidator {
    /*
    private String firstName;
    private String lastName;
    private String password;
    private String retypePassword;
    */
    public static void validate(String firstName, String lastName, String password, String retypePassword) throws InvalidRegistrationException, PasswordException{
        if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || retypePassword.isEmpty())
            throw new InvalidRegistrationException();
        if (!password.equals(retypePassword))
            throw new PasswordException();
    }
    
}
