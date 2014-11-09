import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class GenealogyInfoSystem extends JFrame{

	private Date date;
	Person grandpa,grandma,father1,father2,mother1,child1,child2;
	private JLabel genealogyLabel,genealogyLabelInfo,personListInfo,relationshipInfo;
	JTextField searchResultField;
	JComboBox personListComboBox,relationshipListComboBox;
	Button searchButton;
	public GenealogyInfoSystem(){
		child1=new Person("梁小德",true,"台北","1990.09.18","無","無","無","無","無");
		child2=new Person("梁小笨",true,"桃園","1990.09.22","無","無","無","無","無");
		father1=new Person("梁阿爸",true,"金門","1940.12.11","無","無","台北","1970.03.22","無");
		mother1=new Person("黃小欣",false,"金門","1940.09.15","無","無","台北","1970.03.22","無");
		father2=new Person("梁阿爹",true,"金門","1940.05.11","無","無","台中","1965.12.22","無");
		grandpa=new Person("梁老爹",true,"浙江","1910.03.21","金門","2013.09.21","金門","1970.03.22","無");
		grandma=new Person("王小雪",false,"廈門","1911.08.11","無","無","金門","1970.03.22","無");
		
		System.out.println("QQ");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		this.setTitle("OOCS hw4 edited by Shih-Ping, Liang, 系統登入時間:"+dateFormat.format(date));
		this.pack();
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		Container container=this.getContentPane();
		container.setLayout(null);
		
		Person.FamilyLink(grandpa, grandma, mother1,father1, new Person[] { child1, child2 });
		
		Person.FamilyLink(null, null, grandma,grandpa, new Person[] { });
		Person.FamilyLink(grandpa, grandma, null,father2, new Person[] { });
		Person.FamilyLink(null, null, grandma,grandpa, new Person[] { father1, father2 });
		Person.FamilyLink(father1, mother1, child1,null, new Person[] { });
		Person.FamilyLink(grandpa, grandma, null,father2, new Person[] { });
		
		System.out.println(father1.name+"的爸爸是: "+father1.getName());
		System.out.println(child1.name+"的爸爸是: "+child1.getFather().name);
		genealogyLabel=new JLabel();
		genealogyLabel.setSize(2000, 100);
		genealogyLabel.setLocation(120, 10);
		
		genealogyLabelInfo=new JLabel();
		genealogyLabelInfo.setSize(2000, 100);
		genealogyLabelInfo.setLocation(50, 30);
		
		genealogyLabel.setText("<html>三代家庭族譜共有1對祖父母,1對父母,個叔叔和2個兒子,清單如下:</html>祖父:");
		genealogyLabelInfo.setText("祖父:"+grandpa.getName()+",祖母:"+grandma.getName()+",父親:"+father1.getName()+",母親:"+mother1.getName()+",叔叔:"+father2.getName()+",大兒子:"+child1.getName()+",小兒子:"+child2.getName());
		
		personListComboBox=new JComboBox(new String[] {"請選擇查詢對象", grandpa.getName(), grandma.getName(), father1.getName(), mother1.getName(), father2.getName(), child1.getName(), child2.getName()});
		personListComboBox.setSize(100, 30);
		personListComboBox.setLocation(250, 150);
		/*點選查詢者的下拉選單事件觸發資訊  */
		personListComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(personListComboBox.getSelectedIndex()>0){
		    		relationshipInfo.setVisible(true);
		    		relationshipListComboBox.setVisible(true);
		    	}
		    }
		});
		
		personListInfo=new JLabel();
		personListInfo.setSize(250,100);
		personListInfo.setLocation(210, 85);
		personListInfo.setText("請點選要搜尋對應族譜關係的人");
		
		relationshipInfo=new JLabel();
		relationshipInfo.setSize(250, 100);
		relationshipInfo.setLocation(230, 170);
		relationshipInfo.setText("請選擇要搜尋的祖譜資訊");
		relationshipInfo.setVisible(false);
		
		relationshipListComboBox=new JComboBox(new String[] {"請選擇要查詢的祖譜資訊","祖父/母","父/母","伴侶","兒子/女兒"});
		relationshipListComboBox.setSize(100, 30);
		relationshipListComboBox.setLocation(250, 230);
		relationshipListComboBox.setVisible(false);
		/*點選查詢者祖譜的下拉選單事件觸發資訊  */
		relationshipListComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(relationshipListComboBox.getSelectedIndex()>0){
		    		searchButton.enable();

		    	}
		    }
		});
		
		searchResultField=new JTextField();
		searchResultField.setEditable(false);
		searchResultField.setText("搜尋結果");
		searchResultField.setSize(500, 80);
		searchResultField.setLocation(50, 370);
		searchResultField.setHorizontalAlignment(JTextField.CENTER);
		
		
		searchButton=new Button("查詢");
		searchButton.disable();
		searchButton.setSize(100, 30);
		searchButton.setLocation(250, 300);
		searchButton.addActionListener ( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(relationshipListComboBox.getSelectedItem());
				
				String temp1=personListComboBox.getSelectedItem().toString();
				System.out.println("temp1="+temp1);
				Person tpPerson=null;
				if(temp1.equals(grandpa.getName())==true)tpPerson=grandpa;
				else if(temp1.equals(grandma.getName())==true)tpPerson=grandma;
				else if(temp1.equals(father1.getName())==true)tpPerson=father1;
				else if(temp1.equals(mother1.getName())==true)tpPerson=mother1;
				else if(temp1.equals(father2.getName())==true)tpPerson=father2;
				else if(temp1.equals(child1.getName())==true)tpPerson=child1;
				else if(temp1.equals(child2.getName())==true)tpPerson=child2;
				
				// TODO Auto-generated method stub
				if(relationshipListComboBox.getSelectedIndex()==1)
				{
					Person tpgarndparent=tpPerson.getFather();
					if(tpgarndparent==null||(tpgarndparent.father==null && tpgarndparent.mother==null))
						searchResultField.setText("姓名:"+tpPerson.getName()+",祖父/母:無資訊");
					else 
						searchResultField.setText("姓名:"+tpPerson.getName()+",祖父/母:"+tpgarndparent.getFather().name+"/"+tpgarndparent.getMother().name);

				}
				else if(relationshipListComboBox.getSelectedIndex()==2)
				{
					if(tpPerson.father==null && tpPerson.mother==null)
						searchResultField.setText("姓名:"+tpPerson.getName()+",父/母:無資訊");
					else 
						searchResultField.setText("姓名:"+tpPerson.getName()+",父/母:"+tpPerson.getFather().name+"/"+tpPerson.getMother().name);

				}
				else if(relationshipListComboBox.getSelectedIndex()==3)
				{
					if(tpPerson.husband==null && tpPerson.wife==null)
						searchResultField.setText("姓名:"+tpPerson.getName()+",伴侶:無資訊");
					else if(tpPerson.husband!=null && tpPerson.wife==null)
						searchResultField.setText("姓名:"+tpPerson.getName()+",伴侶:"+tpPerson.husband.name);
					else 
						searchResultField.setText("姓名:"+tpPerson.getName()+",伴侶:"+tpPerson.wife.name);
					
				}
				else if(relationshipListComboBox.getSelectedIndex()==4)
				{
					System.out.println("QQ~~~");
					if(tpPerson.children.size()>0)
					{
						String result=new String();
						int k;
						for(k=0;k<tpPerson.children.size();k++)
						{
							result+=tpPerson.children.elementAt(k).getName();
							if(k+1!=tpPerson.children.size())result+=",";
						}
						searchResultField.setText("姓名:"+tpPerson.getName()+",子女:"+result);
					}
					else {
						searchResultField.setText("姓名:"+tpPerson.getName()+",子女:無");
					}
				}
				System.out.println("搜尋事件觸發");
			}
		});
		container.add(genealogyLabel);
		container.add(genealogyLabelInfo);
		container.add(personListComboBox);
		container.add(personListInfo);
		container.add(relationshipInfo);
		container.add(relationshipListComboBox);
		container.add(searchButton);
		container.add(searchResultField);
		
		this.setVisible(true);
		
		Timer timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	date = new Date();
		    	GenealogyInfoSystem.this.setTitle("OOCS hw4 edited by Shih-Ping, Liang, 系統登入時間:"+dateFormat.format(date));
		    }
		});
		timer.start();

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenealogyInfoSystem();
	}

}
