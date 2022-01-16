package model;

public class User {
	private String id;
	private  String name;
	private String pass;
	private String pass2;


	public User() {

	}

	public User(String id,String pass) {
		this.id = id;
		this.pass = pass;
	}

	public User(String id,String name, String pass, String pass2) {
		this.id   = id;
		this.name = name;
		this.pass = pass;
		this.pass2 = pass2;
	}
	public String   getId() {return id;}
	public String getName() {return name;}
	public String getPass() {return pass;}
	public String getPass2() {return pass2;}
}
