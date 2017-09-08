package fsit03_HitoBasebal;

public class TeamModel {
	private String teamId;
	private String teamname;
	private String fb;
	private String email;
	
	public TeamModel() {}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public void setFb(String fb) {
		this.fb = fb;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTeamId() {return teamId;}
	public String getTeamname() {return teamname;}
	public String getFb() {return fb;}
	public String getEmail() {return email;}
}
