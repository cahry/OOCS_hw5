import java.util.Vector;


public class Person {
	Person father;
	Person mother;
	Person wife;
	Person husband;
	Vector<Person> children;
	
	String name;
	private boolean sex;
	private String placeOfBirth;
	private String dateOfBirth;
	private String placeOfDeath;
	private String dateOfDeath;
	private String placeOfMarriage;
	private String dateOfMarriage;
	private String dateOfDivorce;
	
	public Person(){
		
	}
	
	public Person(String name,boolean sex){
		this.name=name;
		this.sex=sex;
		mother = father = husband = wife = null;
	    children = new Vector<Person>();
	}
	
	public Person(String name,boolean sex,String plaBirth,String dateBirth,String plaDeath,String dateDeath,String plaMarriage,String dateMarriage,String dateDivoce){
		this.name=name;
		this.sex=sex;
		this.placeOfBirth=plaBirth;
		this.dateOfBirth=dateBirth;
		this.placeOfDeath=plaDeath;
		this.dateOfDeath=dateDeath;
		this.placeOfMarriage=plaMarriage;
		this.dateOfMarriage=dateMarriage;
		this.dateOfDivorce=dateDivoce;
		children = new Vector<Person>();
		wife=null;
		husband=null;
		father=null;
		mother=null;
		
		
	}
	//name getter/setter
	void setName(String name)
	{
		this.name=name;
	}
	String getName()
	{
		return this.name;
	}
	
	//sex getter/setter
	void setSex(boolean ismale)
	{
		this.sex=ismale;
	}
	boolean getSex(){
		return this.sex;
	}
	
	//placeofbirth getter/setter
	void setPlaceOfBirth(String placeofbirth)
	{
		this.placeOfBirth=placeofbirth;
	}
	String getPlaceOfBirth()
	{
		return this.placeOfBirth;
	}
	
	//dateofdeath getter/setter
	void setDateOfBirth(String dateofbirth)
	{
		this.dateOfBirth=dateofbirth;
	}
	String getDateOfBirth()
	{
		return this.dateOfBirth;
	}
	
	//placeofdeath getter/setter
	void setPlaceOfDeath(String placeofdeath)
	{
			this.placeOfDeath=placeofdeath;
	}
	String getPlaceOfDeath()
	{
			return this.placeOfDeath;
	}
	 public Person getFather() {
		    return father;
	}

		  public Person getMother() {
		    return mother;
	}

	public int getChildCount() {
		    return children.size();
	}
	public Person getChildAt(int i) {
		    return (Person) children.elementAt(i);
	}

	public int getIndexOfChild(Person kid) {
		    return children.indexOf(kid);
	}
	public static void FamilyLink(Person father,Person mother,Person wife,Person husband,Person[] childs)
	{
		int len = childs.length;
		System.out.println(len);
	    Person child = null;
	    for (int i = 0; i < len; i++) {
	    	child = childs[i];
	    	//System.out.println(child.name);
	    	husband.children.addElement(child);
	    	wife.children.addElement(child);
	    	System.out.println(husband.getName()+",~"+husband.children.elementAt(0).name);
	    	System.out.println(wife.getName()+",~"+wife.children.elementAt(0).name);
	    	child.father = husband;
	    	child.mother = wife;
	    	System.out.println(child.getName()+",~"+child.father.getName());
	    	System.out.println(child.getName()+",~"+child.mother.getName());
	    }
	    //System.out.println("SIZE="+father.children.size());
	    if(wife!=null)wife.husband=husband;
	    if(husband!=null)husband.wife=wife;
	}
	
}
