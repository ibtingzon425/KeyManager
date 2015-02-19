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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class User {
	
	private Long id;
	private String username;
	private String password;
        private List<Attribute> attributeList;
        private String appId;
        private String appSecret;
        private String firstName;
        private String lastName;
        
        
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="key_user")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(unique=true, nullable=false, name="fld_username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(nullable=false, name="fld_password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="key_attribute")
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

	@Column(nullable=false)
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

	@Column(nullable=false)
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        
}

