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
public class InvalidRegistrationException extends Exception {

    /**
     * Creates a new instance of <code>InvalidRegistration</code> without detail
     * message.
     */
    public InvalidRegistrationException() {
    }

    /**
     * Constructs an instance of <code>InvalidRegistration</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidRegistrationException(String msg) {
        super(msg);
    }
}
