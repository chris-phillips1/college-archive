package objectTest;

public class Record {
	
	private String name;
	private int age;
	
	public void setName(String nameIn){
		this.name = nameIn;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setAge(int ageIn){
		this.age = ageIn;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public String toString(){
		return "Name: " + name + "\n" + "Age: " + age;
	}
}
