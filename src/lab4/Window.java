package lab4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;


 
public class Window extends JFrame { //�������� �� JFrame �� �������� ��� ���������������� ����
  private JTabbedPane tabs = new JTabbedPane();
  static StudentDAO ms = new StudentDAO();
  private JTextField fnameField = new JTextField(50); 
  private JTextField lnameField = new JTextField(50);
  private JTextField yearField = new JTextField(50);
  public static ArrayList<JTextField> allFields = new ArrayList<JTextField>();
  public static ArrayList<JButton> allButtons = new ArrayList<JButton>();
  
  

  
static JPanel listPanel = new JPanel();
  
  public Window(){
    super("Student List"); //��������� ����
    setBounds(200, 200, 600, 600); //���� �� ��������� ������ � ��������� - �� ���� ����� ������ � ����������
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //��� ����� ��� ���� ����� ��� �������� ���� ����������� � ���������, ����� ��� ��������� ������ � ���������
    //manageStudent = new ManageStudent();
    //List list = manageStudent.listStudents();
    add(tabs);
    addStudentPanel();
    addListPanel();
    
  }
  private void addStudentPanel(){
	  	JPanel addPanel = new JPanel();
	  	tabs.add(addPanel, "Add to DataBase");
	  	addPanel.setLayout(new FlowLayout());
	  	addPanel.add(new JLabel("First Name"));
	  	addPanel.add(fnameField);	  	
	  	addPanel.add(new JLabel("Last Name"));
	  	addPanel.add(lnameField);
	  	addPanel.add(new JLabel("Year"));
	  	addPanel.add(yearField);
	   
	    JButton addBtn=new JButton("Add Student");
	    addPanel.add(addBtn);
	    addBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer id = ms.addStudent(fnameField.getText(), lnameField.getText(), new Integer(yearField.getText()));
				JTextField tmp;
				  tmp = new JTextField(id.toString(), 30);
				  tmp.setEditable(false);
				  listPanel.add(tmp);		  
				  allFields.add(tmp);	
				  
				  tmp = new JTextField(fnameField.getText(), 30);
				  tmp.setEditable(false);
				  listPanel.add(tmp);
				  allFields.add(tmp);	
				  
				  tmp = new JTextField(lnameField.getText(), 30);
				  tmp.setEditable(false);
				  listPanel.add(tmp);
				  allFields.add(tmp);	
				  
				  tmp = new JTextField(yearField.getText(), 30);
				  tmp.setEditable(false);
				  listPanel.add(tmp);
				  allFields.add(tmp);	
				  MyEditButton editBtn=new MyEditButton("Edit");
				  editBtn.setActionCommand(new Integer(allFields.size()-1).toString());
				  listPanel.add(editBtn);
				  allButtons.add(editBtn);
				  
				  MyDeleteButton delBtn=new MyDeleteButton("Delete");
				  delBtn.setActionCommand(new Integer(allFields.size()-1).toString());
			      listPanel.add(delBtn);
			      allButtons.add(delBtn);
					    
				listPanel.repaint();
				listPanel.revalidate();
				
				
			}
	    	
	    });  
  }
  private void addListPanel(){
	  
	  listPanel.setLayout(new GridLayout(0, 6));
	  tabs.add(listPanel, "View DataBase");
	  ArrayList<Student> db = ms.listStudents();
	  JTextField tmp;
	  listPanel.add(new JLabel("id"));
	  listPanel.add(new JLabel("First Name"));
	  listPanel.add(new JLabel("Last Name"));
	  listPanel.add(new JLabel("year"));
	  listPanel.add(new JLabel(""));
	  listPanel.add(new JLabel(""));
	  for (int i=0; i<db.size(); i++){
		  tmp = new JTextField(((Integer)db.get(i).getId()).toString(), 30);
		  tmp.setEditable(false);
		  listPanel.add(tmp);
		  allFields.add(tmp);	
		  
		  tmp = new JTextField(db.get(i).getFirstName(), 30);
		  tmp.setEditable(false);
		  listPanel.add(tmp);
		  allFields.add(tmp);	
		  
		  tmp = new JTextField(db.get(i).getLastName(), 30);
		  tmp.setEditable(false);
		  listPanel.add(tmp);
		  allFields.add(tmp);	
		  
		  tmp = new JTextField(((Integer) db.get(i).getYear()).toString(), 30);
		  tmp.setEditable(false);
		  listPanel.add(tmp);
		  allFields.add(tmp);	
		  
		  MyEditButton editBtn=new MyEditButton("Edit");
		  editBtn.setActionCommand(new Integer(allFields.size()-1).toString());
		  listPanel.add(editBtn);
		  allButtons.add(editBtn);
		  
		  MyDeleteButton delBtn=new MyDeleteButton("Delete");
		  delBtn.setActionCommand(new Integer(allFields.size()-1).toString());
	      listPanel.add(delBtn);
	      allButtons.add(delBtn);
		  
	  }
	  
	 
  
  }
  public static void main(String[] args) { //��� ������� ����� ���� � � ������ ������
    Window app = new Window(); //������� ��������� ������ ����������
    app.setVisible(true); //� ����� ������� ���������� ��������!
  }
}