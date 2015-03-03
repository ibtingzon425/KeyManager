/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keymanager.service;

import keymanager.model.User;

/**
 *
 * @author angelukayetiu
 */

public interface UserRegService {

    public void configureConnection(String host, int port, String pubKey, String password) throws SSLClientErrorException;

    public void setUser(User user);
    
}
