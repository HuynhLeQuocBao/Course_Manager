package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BLL.courseInstructorBLL;
import BLL.TeacherBLL;
import BLL.courseBLL;
import java.awt.Panel;
import Base.CourseInstructor;
import javax.swing.JComboBox;

public class courseInstructorGUI extends JFrame {

  List<CourseInstructor> courseIntructorList = new ArrayList<CourseInstructor>();
  courseInstructorBLL instructorBBL = new courseInstructorBLL();
  TeacherBLL teaBLL = new TeacherBLL();
  List<String> teacherNameList = teaBLL.getTeacherName() ;
  courseBLL courseBLL = new courseBLL();
  List<String> courseList = courseBLL.getCourseTitleandID() ;
  public JFrame f = new JFrame();
  private JPanel contentPane;
  private JTextField tfFind;
  DefaultTableModel model = new DefaultTableModel();
  private JTable table;
  private JComboBox cbPerson, cbCourse;
  private int cbPersonIdValueClick, cbCourseIdValueClick;
  
  public courseInstructorGUI() {
    initComponent();
   displayCourseInstructorList();
  }

  /**
   * Create the frame.
   * 
   * @return
   */
  public void initComponent() {
    setTitle("Quản lý sinh viên");
    setSize(1366, 740);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(50, 205, 50));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(220, 220, 220));
    panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
    panel.setBounds(-11, 59, 336, 652);
    contentPane.add(panel);
    panel.setLayout(null);
    f.setResizable(false);
    JLabel lbCourseID = new JLabel("Course Name");
    lbCourseID.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbCourseID.setForeground(new Color(0, 0, 0));
    lbCourseID.setBounds(25, 134, 91, 28);
    panel.add(lbCourseID);

    JButton btnEdit = new JButton("Sửa");
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnEdit.setBounds(143, 496, 69, 28);
    panel.add(btnEdit);

    JButton btnAdd = new JButton("Thêm");
    btnAdd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
       btnAddActionPerformed(e);
      }
    });
    btnAdd.setBounds(259, 496, 69, 28);
    panel.add(btnAdd);

    JButton btnRemove = new JButton("Xóa");
    btnRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       btnRemoveActionPerformed(e);
      }
    });
    btnRemove.setBounds(25, 496, 69, 28);
    panel.add(btnRemove);

    JButton btnReset = new JButton("Reset");
    btnReset.setBounds(89, 545, 167, 38);
    panel.add(btnReset);

    JLabel lbPersonID = new JLabel("Person Name");
    lbPersonID.setForeground(Color.BLACK);
    lbPersonID.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbPersonID.setBounds(25, 46, 91, 28);
    panel.add(lbPersonID);
    
     cbPerson = new JComboBox(teacherNameList.toArray());
    cbPerson.setBounds(126, 48, 186, 28);
    panel.add(cbPerson);
    
     cbCourse = new JComboBox(courseList.toArray());
    cbCourse.setBounds(126, 134, 186, 28);
    panel.add(cbCourse);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(324, 59, 1036, 652);
    contentPane.add(scrollPane);
    table = new JTable();
    table.setModel(model);
    model.addColumn("STT");
    model.addColumn("PersonID");
    model.addColumn("CourseID");
    model.addColumn("FirstName");
    model.addColumn("LastName");
    model.addColumn("Title Course");
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {

      }
    });
    scrollPane.setViewportView(table);

    Panel panel1 = new Panel();
    panel1.setBackground(new Color(50, 205, 50));
    panel1.setBounds(0, 0, 1364, 62);
    contentPane.add(panel1);
    panel1.setLayout(null);

    JLabel lbTitle = new JLabel("Danh Sách Phân Công");
    lbTitle.setForeground(Color.WHITE);
    lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbTitle.setBounds(322, 0, 369, 62);
    panel1.add(lbTitle);

    JButton btnSearch = new JButton("Tìm kiếm");
    btnSearch.setBounds(1252, 12, 90, 38);
    panel1.add(btnSearch);
    tfFind = new JTextField();
    tfFind.setBounds(1010, 12, 243, 38);
    panel1.add(tfFind);
    tfFind.setColumns(10);

    JLabel lbInstructor = new JLabel("Tìm kiếm theo tên giảng viên");
    lbInstructor.setForeground(Color.WHITE);
    lbInstructor.setFont(new Font("Tahoma", Font.BOLD, 15));
    lbInstructor.setBounds(778, 0, 227, 62);
    panel1.add(lbInstructor);

    JButton btnReturn = new JButton("Quay lại");
    btnReturn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnReturn.setBounds(10, 9, 96, 46);
    panel1.add(btnReturn);
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       btnSearchActionPerformed(e);
      }
    });
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       btnEditActionPerformed(e);
      }
    });
    btnReset.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        btnResetActionPerformed(e);
      }
    });
    btnReturn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        managerGUI r = new managerGUI();
        r.f.setVisible(true);
      }
    });
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        jTableMouseClicked(e);
      }
    });
    f.add(contentPane);
    f.setSize(1366, 740);
    f.setVisible(true);
  }

  private void btnResetActionPerformed(ActionEvent e) {
    cbPerson.setSelectedIndex(0);
    cbCourse.setSelectedIndex(0);
    tfFind.setText("");
    displayCourseInstructorList();
  }

  private void jTableMouseClicked(MouseEvent e) {
    int selectedIndex = table.getSelectedRow();
    if (selectedIndex >= 0) {
      cbPerson
      .setSelectedItem(
          model.getValueAt(selectedIndex, 1)+ "_"+model.getValueAt(selectedIndex, 3)+" "+model.getValueAt(selectedIndex, 4)
      );
      cbCourse
      .setSelectedItem(
        model.getValueAt(selectedIndex, 2) + "_" +model.getValueAt(selectedIndex, 5)
      );
      //get personID and courseID when click 
      cbPersonIdValueClick= (int) model.getValueAt(selectedIndex, 1);
      cbCourseIdValueClick= (int) model.getValueAt(selectedIndex, 2);
    }
  }
 private void btnAddActionPerformed(ActionEvent e) {
   int personID = Integer.parseInt(cbPerson.getSelectedItem().toString().split("_")[0]);
   int courseID = Integer.parseInt(cbCourse.getSelectedItem().toString().split("_")[0]);
   if (cbPersonIdValueClick!=personID||cbCourseIdValueClick!=courseID) {
     try {

       CourseInstructor p = new CourseInstructor(courseID, personID);

       JOptionPane.showMessageDialog(null, instructorBBL.addCourseInstructor(p));

       displayCourseInstructorList();
       btnResetActionPerformed(e);
     } catch (NumberFormatException ex) {

     }
   } else {
     JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cần phân công khóa học");
   }
 }

 private void btnEditActionPerformed(ActionEvent e) {
   int selectedIndex = table.getSelectedRow();
   int personID = Integer.parseInt(cbPerson.getSelectedItem().toString().split("_")[0]);
   int courseID = Integer.parseInt(cbCourse.getSelectedItem().toString().split("_")[0]);
   System.out.println(personID);
   System.out.println(courseID);

   if (selectedIndex>=0 && (cbPersonIdValueClick!=personID||cbCourseIdValueClick!=courseID)) {
      
      CourseInstructor p = new CourseInstructor(courseID,personID);
     JOptionPane.showMessageDialog(null, instructorBBL.editCourseInstructor(p , cbPersonIdValueClick, cbCourseIdValueClick ));
     displayCourseInstructorList();
     btnResetActionPerformed(e);
   } else {
     JOptionPane.showMessageDialog(null, "Vui lòng chọn đày đủ thông tin phân công");
   }
 }

 private void btnRemoveActionPerformed(ActionEvent e) {
   int selectedIndex = table.getSelectedRow();
   int personID = Integer.parseInt(cbPerson.getSelectedItem().toString().split("_")[0]);
   int courseID = Integer.parseInt(cbCourse.getSelectedItem().toString().split("_")[0]);
   if (selectedIndex>=0) {
     int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phân công này?", "Question",
         JOptionPane.YES_NO_OPTION);

     if (option == JOptionPane.YES_OPTION) {
       int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Question",
           JOptionPane.YES_NO_OPTION);
       if (sure == JOptionPane.YES_OPTION) {
         JOptionPane.showMessageDialog(null, instructorBBL.deleteCourseInstructor(personID, courseID));
         displayCourseInstructorList();
         btnResetActionPerformed(e);
       }
     }
   } else {
     JOptionPane.showMessageDialog(null, "Vui lòng chọn phân công cần xóa");
   }
 }

  private void btnSearchActionPerformed(ActionEvent e) {
    String keyword = tfFind.getText();
    System.out.println(keyword);
    if (keyword != null && keyword.length() > 0) {
      courseIntructorList = instructorBBL.searchCourseInstructor(keyword);
      System.out.println("chuoi"+courseIntructorList);
      if (courseIntructorList.size() == 0) {
        JOptionPane.showMessageDialog(null, "Không có phân công cần tìm");
        displayCourseInstructorList();
      } else {
        model.setRowCount(0);
        int i = 0;
        while (i < courseIntructorList.size()) {
          CourseInstructor p = courseIntructorList.get(i);
          model.addRow(new Object[] {
            model.getRowCount() + 1, p.getPersonID(), p.getCourseID(), p.getFirstName(), p.getLastName(), p.getTitle()
          });
          i++;
        }
      }

    } else {
      JOptionPane.showMessageDialog(null, "Vui lòng nhập tên giảng viên hoặc tên khóa học cần tìm");
    }
  }

private void displayCourseInstructorList() {
  model.setRowCount(0);
  courseIntructorList = instructorBBL.getAllCourseInstructor();
  int i = 0;
  while (i < courseIntructorList.size()) {
    CourseInstructor p = courseIntructorList.get(i);
    model.addRow(new Object[] {
        
        model.getRowCount() + 1, p.getPersonID(), p.getCourseID(), p.getFirstName(), p.getLastName(), p.getTitle()
    });
    i++;
  }
}
}