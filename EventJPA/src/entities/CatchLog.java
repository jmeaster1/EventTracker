package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CatchLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="common_name")
	private String commonName;
	
	@Column(name="fly_used")
	private String flyUsed;
	
	private Integer length;
	
	private Integer weight;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getFlyUsed() {
		return flyUsed;
	}

	public void setFlyUsed(String flyUsed) {
		this.flyUsed = flyUsed;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public CatchLog(int id, String commonName, String flyUsed, int length, int weight) {
		super();
		this.id = id;
		this.commonName = commonName;
		this.flyUsed = flyUsed;
		this.length = length;
		this.weight = weight;
	}

	public CatchLog() {
	}

	@Override
	public String toString() {
		return "CatchLog [id=" + id + ", commonName=" + commonName + ", flyUsed=" + flyUsed + ", length=" + length
				+ ", weight=" + weight + "]";
	}
	
	
	
	
	
	
	
	
	
}
