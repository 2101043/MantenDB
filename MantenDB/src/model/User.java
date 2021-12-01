package model;

public class User {
	private String id;
	private  String name;
	private String pass;


	public User() {

	}

	public User(String id,String pass) {
		this.id = id;
		this.pass = pass;
	}

	public User(String id,String name, String pass) {
		this.id   = id;
		this.name = name;
		this.pass = pass;
	}
	public String   getId() {return id;}
	public String getName() {return name;}
	public String getPass() {return pass;}
}
