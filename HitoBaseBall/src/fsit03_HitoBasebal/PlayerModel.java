package fsit03_HitoBasebal;

public class PlayerModel {//10
	private String playerId;
	private String temid;
	private String number;
	private String name;
	private String position;
	private String hit;
	private String thr;
	private String height;
	private String weight;
	private String birthday;
	
	public PlayerModel() {}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public void setTemid(String temid) {
		this.temid = temid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public void setThr(String thr) {
		this.thr = thr;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getPlayerId() {return playerId;}
	public String getTemid() {return temid;}
	public String getNumber() {return number;}
	public String getName() {return name;}
	public String getPosition() {return position;}
	public String getHit() {return hit;}
	public String getThr() {return thr;}
	public String getHeight() {return height;}
	public String getWeight() {return weight;}
	public String getBirthday() {return birthday;}
	
}
