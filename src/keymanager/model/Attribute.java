package keymanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author angelukayetiu
 */
@Entity
class Attribute {
    
	private Long id;
	private String attribute;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="key_attribute")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false, unique=true, name="fld_role_type")
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
