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
		child1=new Person("��p�w",true,"�x�_","1990.09.18","�L","�L","�L","�L","�L");
		child2=new Person("��p��",true,"���","1990.09.22","�L","�L","�L","�L","�L");
		father1=new Person("�����",true,"����","1940.12.11","�L","�L","�x�_","1970.03.22","�L");
		mother1=new Person("���p�Y",false,"����","1940.09.15","�L","�L","�x�_","1970.03.22","�L");
		father2=new Person("����R",true,"����","1940.05.11","�L","�L","�x��","1965.12.22","�L");
		grandpa=new Person("��ѯR",true,"����","1910.03.21","����","2013.09.21","����","1970.03.22","�L");
		grandma=new Person("���p��",false,"�H��","1911.08.11","�L","�L","����","1970.03.22","�L");
		
		System.out.println("QQ");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		this.setTitle("OOCS hw4 edited by Shih-Ping, Liang, �t�εn�J�ɶ�:"+dateFormat.format(date));
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
		
		System.out.println(father1.name+"�������O: "+father1.getName());
		System.out.println(child1.name+"�������O: "+child1.getFather().name);
		genealogyLabel=new JLabel();
		genealogyLabel.setSize(2000, 100);
		genealogyLabel.setLocation(120, 10);
		
		genealogyLabelInfo=new JLabel();
		genealogyLabelInfo.setSize(2000, 100);
		genealogyLabelInfo.setLocation(50, 30);
		
		genealogyLabel.setText("<html>�T�N�a�x���Ц@��1�ﯪ����,1�����,�Ө����M2�Ө�l,�M��p�U:</html>����:");
		genealogyLabelInfo.setText("����:"+grandpa.getName()+",����:"+grandma.getName()+",����:"+father1.getName()+",����:"+mother1.getName()+",����:"+father2.getName()+",�j��l:"+child1.getName()+",�p��l:"+child2.getName());
		
		personListComboBox=new JComboBox(new String[] {"�п�ܬd�߹�H", grandpa.getName(), grandma.getName(), father1.getName(), mother1.getName(), father2.getName(), child1.getName(), child2.getName()});
		personListComboBox.setSize(100, 30);
		personListComboBox.setLocation(250, 150);
		/*�I��d�ߪ̪��U�Կ��ƥ�Ĳ�o��T  */
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
		personListInfo.setText("���I��n�j�M�����������Y���H");
		
		relationshipInfo=new JLabel();
		relationshipInfo.setSize(250, 100);
		relationshipInfo.setLocation(230, 170);
		relationshipInfo.setText("�п�ܭn�j�M�����и�T");
		relationshipInfo.setVisible(false);
		
		relationshipListComboBox=new JComboBox(new String[] {"�п�ܭn�d�ߪ����и�T","����/��","��/��","��Q","��l/�k��"});
		relationshipListComboBox.setSize(100, 30);
		relationshipListComboBox.setLocation(250, 230);
		relationshipListComboBox.setVisible(false);
		/*�I��d�ߪ̯��Ъ��U�Կ��ƥ�Ĳ�o��T  */
		relationshipListComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(relationshipListComboBox.getSelectedIndex()>0){
		    		searchButton.enable();

		    	}
		    }
		});
		
		searchResultField=new JTextField();
		searchResultField.setEditable(false);
		searchResultField.setText("�j�M���G");
		searchResultField.setSize(500, 80);
		searchResultField.setLocation(50, 370);
		searchResultField.setHorizontalAlignment(JTextField.CENTER);
		
		
		searchButton=new Button("�d��");
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
						searchResultField.setText("�m�W:"+tpPerson.getName()+",����/��:�L��T");
					else 
						searchResultField.setText("�m�W:"+tpPerson.getName()+",����/��:"+tpgarndparent.getFather().name+"/"+tpgarndparent.getMother().name);

				}
				else if(relationshipListComboBox.getSelectedIndex()==2)
				{
					if(tpPerson.father==null && tpPerson.mother==null)
						searchResultField.setText("�m�W:"+tpPerson.getName()+",��/��:�L��T");
					else 
						searchResultField.setText("�m�W:"+tpPerson.getName()+",��/��:"+tpPerson.getFather().name+"/"+tpPerson.getMother().name);

				}
				else if(relationshipListComboBox.getSelectedIndex()==3)
				{
					if(tpPerson.husband==null && tpPerson.wife==null)
						searchResultField.setText("�m�W:"+tpPerson.getName()+",��Q:�L��T");
					else if(tpPerson.husband!=null && tpPerson.wife==null)
						searchResultField.setText("�m�W:"+tpPerson.getName()+",��Q:"+tpPerson.husband.name);
					else 
						searchResultField.setText("�m�W:"+tpPerson.getName()+",��Q:"+tpPerson.wife.name);
					
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
						searchResultField.setText("�m�W:"+tpPerson.getName()+",�l�k:"+result);
					}
					else {
						searchResultField.setText("�m�W:"+tpPerson.getName()+",�l�k:�L");
					}
				}
				System.out.println("�j�M�ƥ�Ĳ�o");
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
		    	GenealogyInfoSystem.this.setTitle("OOCS hw4 edited by Shih-Ping, Liang, �t�εn�J�ɶ�:"+dateFormat.format(date));
		    }
		});
		timer.start();

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenealogyInfoSystem();
	}

}
