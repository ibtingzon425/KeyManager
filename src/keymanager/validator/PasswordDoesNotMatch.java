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
public class PasswordDoesNotMatch extends Exception {

    /**
     * Creates a new instance of <code>passwordDoesNotMatch</code> without
     * detail message.
     */
    public PasswordDoesNotMatch() {
    }

    /**
     * Constructs an instance of <code>passwordDoesNotMatch</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PasswordDoesNotMatch(String msg) {
        super(msg);
    }
}
