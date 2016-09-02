package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Books implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String description;
	private double price;
	private String icoName;
	private String icoExt;
	private String family;
	
	public Books() {}
	
	public Books(String title, String description, double price, String icoName, String icoExt, String family) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.icoName = icoName;
		this.icoExt = icoExt;
		this.family = family;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getIcoName() {
		return icoName;
	}

	public String getIcoExt() {
		return icoExt;
	}

	public String getFamily() {
		return family;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

	public void setIcoExt(String icoExt) {
		this.icoExt = icoExt;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Books other = (Books) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
