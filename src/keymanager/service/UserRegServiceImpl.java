/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keymanager.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import keymanager.SSLClient;
import keymanager.SSLClientErrorException;
import keymanager.model.User;
import keymanager.view.UserReg;

/**
 *
 * @author angelukayetiu
 */
public class UserRegServiceImpl implements UserRegService {
    
    private SSLClient client;
    private String USERNAME;
    private User user;
    
    public UserRegServiceImpl() {
    }

    @Override
    public void generateKeys(String host, int port, String pubKey, String password) throws SSLClientErrorException{
        client = new SSLClient(USERNAME, host, port, pubKey, password);
        client.generateKeys();
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    

}
