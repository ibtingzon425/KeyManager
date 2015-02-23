/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keymanager.model;

/**
 *
 * @author angelukayetiu
 */

import java.util.List;

public class User {
	
	private Long id;
	private String username;
	private String password;
        private List<Attribute> attributeList;
        private String appId;
        private String appSecret;
        private String firstName;
        private String lastName;
        
      	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

        public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Attribute> getAttribute() {
		return attributeList;
	}
	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        
}

