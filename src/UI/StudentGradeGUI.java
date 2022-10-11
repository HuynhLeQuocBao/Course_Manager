package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.StudentGradeBLL;
import Base.Product;
import Base.Student;
import Base.StudentGrade;

public class StudentGradeGUI
{
  
  JFrame jf = new JFrame("Quản lí kết quả khóa học");
  ArrayList<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();
  StudentGradeBLL studentgradeBLL = new StudentGradeBLL();
  private JPanel contentPane;
  private JComboBox<String> cbxcourseID;
  private JTextField tfcourseName;
  private JTextField tfStudentName;
  private JComboBox <String> cbxstudentID;
  private JTextField tfgrade;
  private JTextField tfFind;
  private int enrollmentID = 0;
  DefaultTableModel model = new DefaultTableModel();
  private JComboBox<String> cbxcoursefind;
  
  private JTable table;
  
  
  JLabel courseID,StudentID;
  StudentGradeGUI()
  {
    this.initComponent();
    showtable();
  }
  public void initComponent()
  {
    jf.setVisible(true);
    jf.setSize(1366, 740);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(50, 205, 50));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    jf.setContentPane(contentPane);
    
    JPanel panel = new JPanel();
    panel.setBackground(new Color(220, 220, 220));
    panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
    panel.setBounds(-11, 59, 336, 652);
    contentPane.add(panel);
    panel.setLayout(null);
    jf.setResizable(false);
    
    JLabel lCodecourse = new JLabel("Mã khóa học ");
    lCodecourse.setFont(new Font("SansSerif", Font.BOLD, 12));
    lCodecourse.setForeground(new Color(0, 0, 0));
    lCodecourse.setBounds(25, 10, 91, 28);
    panel.add(lCodecourse);
    
    cbxcourseID =  new JComboBox(studentgradeBLL.getAllCourseID().toArray());
    cbxcourseID.setBounds(126, 12, 186, 28);
    panel.add(cbxcourseID);
    
    
    JLabel lCourseName = new JLabel("Tên Khóa học");
    lCourseName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lCourseName.setForeground(new Color(0, 0, 0));
    lCourseName.setBounds(25, 70, 91, 28);
    panel.add(lCourseName);
    
    tfcourseName = new JTextField();
    tfcourseName.setBounds(126, 70, 186, 28);
    panel.add(tfcourseName);
    tfcourseName.setColumns(10);
    tfcourseName.setEditable(false);
    cbxcourseID.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        int courseid = Integer.parseInt(courseIDbycbx(cbxcourseID.getSelectedItem()+""));
        tfcourseName.setText(""+studentgradeBLL.courseName(courseid));
      }
  });
    
    JLabel lCodestudent = new JLabel("Mã Sinh viên");
    lCodestudent.setFont(new Font("SansSerif", Font.BOLD, 12));
    lCodestudent.setForeground(new Color(0, 0, 0));
    lCodestudent.setBounds(25, 130, 106, 28);
    panel.add(lCodestudent);

    cbxstudentID = new JComboBox(studentgradeBLL.getAllStudentID().toArray());
    cbxstudentID.setBounds(126, 132, 186, 28);
    panel.add(cbxstudentID);
    
    JLabel lStudentName = new JLabel("Tên Sinh viên");
    lStudentName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lStudentName.setForeground(new Color(0, 0, 0));
    lStudentName.setBounds(25, 200, 106, 28);
    panel.add(lStudentName);

    tfStudentName = new JTextField();
    tfStudentName.setBounds(126, 200, 186, 28);
    panel.add(tfStudentName);
    tfStudentName.setColumns(10);
    tfStudentName.setEditable(false);
    cbxstudentID.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        int courseid = Integer.parseInt(courseIDbycbx(cbxstudentID.getSelectedItem()+""));
        tfStudentName.setText(""+studentgradeBLL.StudentName(courseid));
      }
  });
    
    JLabel lGrade = new JLabel("Điểm");
    lGrade.setFont(new Font("SansSerif", Font.BOLD, 12));
    lGrade.setForeground(new Color(0, 0, 0));
    lGrade.setBounds(25, 270, 106, 28);
    panel.add(lGrade);

    tfgrade = new JTextField();
    tfgrade.setBounds(126, 270, 186, 28);
    panel.add(tfgrade);
    tfgrade.setColumns(10);
    
    JButton btnEdit = new JButton("Sửa");
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnEditActionPerformed(e);
      }
    });
    btnEdit.setBounds(170, 400, 69, 28);
    panel.add(btnEdit);
    
    JButton btnAdd = new JButton("Thêm");
    btnAdd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        btnAddActionPerformed(e);
      }
    });
    btnAdd.setBounds(250, 400, 69, 28);
    panel.add(btnAdd);
    
    JButton btnRemove = new JButton("Xóa");
    btnRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnRemoveActionPerformed(e);
      }
    });
    btnRemove.setBounds(10, 400, 69, 28);
    panel.add(btnRemove);
    
    JButton btnReset = new JButton("Reset");
    btnReset.setBounds(90, 400, 75, 28);
    panel.add(btnReset);
    btnReset.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        btnResetActionPerformed(e);
      }
    });
    
    table = new javax.swing.JTable(){
      public boolean isCellEditable(int row,int column)
      {
          return false;
      }
    };
    table.setModel(model);
    model.addColumn("EnrollmentID");
   
    model.addColumn("CourseID");
    model.addColumn("Course Name");
    model.addColumn("StudentID");
    model.addColumn("Student Name");
    model.addColumn("Grade");
    // ẩn cột enrollmentID đi
    table.removeColumn(table.getColumnModel().getColumn(0));
    
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(324, 59, 1036, 652);
    contentPane.add(scrollPane);
    
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex >= 0) {
            enrollmentID = Integer.parseInt(String.valueOf(model.getValueAt(selectedIndex, 0)));  
            String courseIDname = String.valueOf(model.getValueAt(selectedIndex,1))+"_"+
                                  String.valueOf(model.getValueAt(selectedIndex,2));
            String studentIDname =String.valueOf(model.getValueAt(selectedIndex,3))+"_"+
                                  String.valueOf(model.getValueAt(selectedIndex,4));
            cbxcourseID.setSelectedItem(String.valueOf(""+courseIDname));
            cbxstudentID.setSelectedItem(String.valueOf(""+studentIDname));
            tfStudentName.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
            tfgrade.setText(String.valueOf(model.getValueAt(selectedIndex, 5)));
            System.out.print(studentIDname);
        }
      }
    });
    
    scrollPane.setViewportView(table);

    Panel panel1 = new Panel();
    panel1.setBackground(new Color(50, 205, 50));
    panel1.setBounds(0, 0, 1364, 62);
    contentPane.add(panel1);
    panel1.setLayout(null);
    
    JLabel lbTitle = new JLabel("Danh Sách Kết Quả Khóa Học");
    lbTitle.setForeground(Color.WHITE);
    lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbTitle.setBounds(150, 6, 500, 36);
    panel1.add(lbTitle);
    
    JLabel lFilter= new JLabel("Filter by courseID");
    lFilter.setForeground(Color.WHITE);
    lFilter.setFont(new Font("SansSerif", Font.BOLD, 14));
    lFilter.setBounds(670,15, 500, 36);
    panel1.add(lFilter);
    
    
    cbxcoursefind =  new JComboBox(studentgradeBLL.getAllCourseID().toArray());
    cbxcoursefind.setBounds(800, 20, 186,30);
    panel1.add(cbxcoursefind);
    cbxcoursefind.addItemListener(new ItemListener() {
      
      @Override
      public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        enrollmentID = 0;
        String itemselected= cbxcoursefind.getSelectedItem()+"";
        int id  = Integer.parseInt(courseIDbycbx(itemselected));
        model.setRowCount(0);
        studentGradeList = studentgradeBLL.searchbyCourseID(id);
        int i = 0;
        while (i < studentGradeList.size()) {
          StudentGrade p = studentGradeList.get(i);
          model.addRow(new Object[] 
              {
              p.getEnrollmentID(), p.getCourseID(),studentgradeBLL.courseName(p.getCourseID()), 
              p.getStudenID(),studentgradeBLL.StudentName(p.getStudenID()),p.getGrade()
              });
          
          i++; 
        }
      }
    });
        
    JButton btnSearch = new JButton("Tìm kiếm");
    btnSearch.setBounds(1252, 12, 90, 38);
    panel1.add(btnSearch);
    btnSearch.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        btnSearchActionPerformed(e); 
      }
    });
    
    tfFind = new JTextField();
    tfFind.setBounds(1010, 12, 243, 38);
    panel1.add(tfFind);
    tfFind.setColumns(10);
    
    JButton btnTrLi = new JButton("");
    btnTrLi.setBounds(0, 0, 50, 50);
    panel1.add(btnTrLi);
    btnTrLi.setBackground(new Color(50, 205, 50));
    btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));

  }
  public void showtable()
  {

    model.setRowCount(0);
    studentGradeList = studentgradeBLL.findAll();
    int i = 0;
    while (i < studentGradeList.size()) {
      StudentGrade p = studentGradeList.get(i);
      model.addRow(new Object[] 
          {
          p.getEnrollmentID(), p.getCourseID(),studentgradeBLL.courseName(p.getCourseID()), 
          p.getStudenID(),studentgradeBLL.StudentName(p.getStudenID()),p.getGrade()
          });
      
      i++; 
    }
  }
  private void btnAddActionPerformed(ActionEvent e) {
    
    try {
      int courseID  = Integer.parseInt(""+courseIDbycbx(""+cbxcourseID.getSelectedItem()));
      System.out.println(courseID);
      int studentID = Integer.parseInt("" + courseIDbycbx(""+cbxstudentID.getSelectedItem()));
      if((""+tfgrade.getText()).equals(""))
      {
        JOptionPane.showMessageDialog(null, studentgradeBLL.addwithoutGrade(courseID, studentID));
      }
      else
      {
        Double grade = Double.parseDouble(""+tfgrade.getText());
        StudentGrade std = new StudentGrade(this.enrollmentID,courseID,studentID,grade);
        
        JOptionPane.showMessageDialog(null, studentgradeBLL.add(std));
        
      }

      displayList();
      btnResetActionPerformed(e);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Điểm không hợp lệ!!");
    }

    }
  private void btnResetActionPerformed(ActionEvent e)
  {
        enrollmentID = 0;
        cbxcourseID.setSelectedIndex(0);
        cbxstudentID.setSelectedIndex(0);
        cbxcoursefind.setSelectedIndex(0);
        tfgrade.setText("");
        displayList();
  }
  private void btnRemoveActionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    int selectedIndex = table.getSelectedRow();
    if(enrollmentID == 0)
    {
      JOptionPane.showMessageDialog(null, "Vui lòng chọn kết quả khóa học muốn xóa");
    }
    else if(selectedIndex >= 0 && enrollmentID != 0)
    {
      int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa kết quả khóa học này không?", "Question",
          JOptionPane.YES_NO_OPTION);

      if (option == JOptionPane.YES_OPTION) {
          int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa kết quả khóa học này không?", "Question",
              JOptionPane.YES_NO_OPTION);
            if (sure == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, studentgradeBLL.delete(enrollmentID));
                displayList();
                btnResetActionPerformed(e);
            }
        }
  }
  }
  //cắt chuỗi trong combobox để lấy mã
  public String courseIDbycbx(String a)
  {
    StringTokenizer st = new StringTokenizer(a,"_");
    return st.nextToken();
  }
  private void btnEditActionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    try {
      int index = table.getSelectedRow();
          
          int enrollmentID = this.enrollmentID;
          int courseID  = Integer.parseInt(""+courseIDbycbx(""+cbxcourseID.getSelectedItem()));
          System.out.println(courseID);
          int studentID = Integer.parseInt("" + courseIDbycbx(""+cbxstudentID.getSelectedItem()));
          double grade;
          if(tfgrade.getText().equals(""))
          {
            grade = 0.0;
            StudentGrade std = new StudentGrade(enrollmentID, courseID, studentID, grade);
            JOptionPane.showMessageDialog(null, studentgradeBLL.update(std));
          }
          else
          {
             grade = Double.parseDouble(""+tfgrade.getText());
             StudentGrade std = new StudentGrade(enrollmentID, courseID, studentID, grade);
             JOptionPane.showMessageDialog(null, studentgradeBLL.update(std));
            
            
          }
          displayList();
          btnResetActionPerformed(e);
     

  } catch (InputMismatchException ex) {
      JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của sản phẩm");
  } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(null, "Điểm phải là số!!");
  }
  }

  private void btnSearchActionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    try
    {
      model.setRowCount(0);
      int codeid = Integer.parseInt(tfFind.getText().trim());
      studentGradeList = studentgradeBLL.searchbyCode(codeid);
      int i = 0;
      while (i < studentGradeList.size()) {
        StudentGrade p = studentGradeList.get(i);
        model.addRow(new Object[] 
            {
            p.getEnrollmentID(), p.getCourseID(),studentgradeBLL.courseName(p.getCourseID()), 
            p.getStudenID(),studentgradeBLL.StudentName(p.getStudenID()),p.getGrade()
            });
        
        i++; 
      }
    }
    catch(NumberFormatException ex)
    {
           String name = tfFind.getText().trim();
           if(name.equals(""))
           {
             displayList();
           }
           else
           {
             model.setRowCount(0);
             studentGradeList = studentgradeBLL.searchByname(name);
             int i = 0;
             while (i < studentGradeList.size()) {
               StudentGrade p = studentGradeList.get(i);
               model.addRow(new Object[] 
                   {
                   p.getEnrollmentID(), p.getCourseID(),studentgradeBLL.courseName(p.getCourseID()), 
                   p.getStudenID(),studentgradeBLL.StudentName(p.getStudenID()),p.getGrade()
                   });
               
               i++; 
             }
           }

    }
  }
  private void displayList() {
    model.setRowCount(0);
    studentGradeList = studentgradeBLL.findAll();
    int i = 0;
    while (i < studentGradeList.size()) {
      StudentGrade p = studentGradeList.get(i);
      model.addRow(new Object[] 
          {
          p.getEnrollmentID(), p.getCourseID(),studentgradeBLL.courseName(p.getCourseID()), 
          p.getStudenID(),studentgradeBLL.StudentName(p.getStudenID()),p.getGrade()
          });
      
      i++; 
    }

}
  public static void main(String[] args) {
    new StudentGradeGUI();
  }
}