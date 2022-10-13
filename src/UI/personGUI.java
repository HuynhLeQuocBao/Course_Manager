package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.Panel;
import javax.swing.JComboBox;
import Base.Student;
import Base.Teacher;
import BLL.StudentBLL;
import BLL.TeacherBLL;

public class personGUI extends JFrame {

  List<Student> studentList = new ArrayList<Student>();
  StudentBLL stuBBL = new StudentBLL();
  List<Teacher> teacherList = new ArrayList<Teacher>();
  TeacherBLL teaBLL = new TeacherBLL();
  public JFrame f = new JFrame();
  private JPanel contentPane;
  private JTextField tfLastName;
  private JTextField tfFirstName;
  private JTextField tfEnrollmentDate;
  DefaultTableModel model = new DefaultTableModel();
  private JTable table;
  private JTextField tfCode;
  private JTextField tfSearch;
  private JTextField tfHireDate;
  private JComboBox cbFilter;
  String[] personTypeList = { "Student", "Teacher" };

  public personGUI() {
    initComponent();
    displayStudentList();
  }

  /**
   * Create the frame.
   * 
   * @return
   */
  public void initComponent() {
    setTitle("Quản lý nhân sự");
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
    panel.setBounds(0, 79, 336, 624);
    contentPane.add(panel);
    panel.setLayout(null);
    f.setResizable(false);
    JLabel lbLastName = new JLabel("LastName");
    lbLastName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbLastName.setForeground(new Color(0, 0, 0));
    lbLastName.setBounds(23, 135, 91, 28);
    panel.add(lbLastName);

    tfLastName = new JTextField();
    tfLastName.setBounds(124, 137, 186, 28);
    panel.add(tfLastName);
    tfLastName.setColumns(10);

    JLabel lbFirstName = new JLabel("FirstName");
    lbFirstName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbFirstName.setForeground(new Color(0, 0, 0));
    lbFirstName.setBounds(23, 225, 106, 28);
    panel.add(lbFirstName);

    tfFirstName = new JTextField();
    tfFirstName.setBounds(124, 227, 186, 28);
    panel.add(tfFirstName);
    tfFirstName.setColumns(10);

    JLabel lbEnrollmentDate = new JLabel("EnrollmentDate");
    lbEnrollmentDate.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbEnrollmentDate.setForeground(new Color(0, 0, 0));
    lbEnrollmentDate.setBounds(23, 315, 91, 28);
    panel.add(lbEnrollmentDate);

    tfEnrollmentDate = new JTextField();
    tfEnrollmentDate.setBounds(124, 315, 186, 28);
    panel.add(tfEnrollmentDate);
    tfEnrollmentDate.setColumns(10);

    JButton btnEdit = new JButton("Sửa");
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnEdit.setBounds(141, 469, 69, 28);
    panel.add(btnEdit);

    JButton btnAdd = new JButton("Thêm");
    btnAdd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        btnAddActionPerformed(e);
      }
    });
    btnAdd.setBounds(257, 469, 69, 28);
    panel.add(btnAdd);

    JButton btnRemove = new JButton("Xóa");
    btnRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnRemoveActionPerformed(e);
      }
    });
    btnRemove.setBounds(23, 469, 69, 28);
    panel.add(btnRemove);

    JButton btnReset = new JButton("Reset");
    btnReset.setBounds(87, 518, 167, 38);
    panel.add(btnReset);

    JLabel lbCode = new JLabel("PersonID");
    lbCode.setForeground(Color.BLACK);
    lbCode.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbCode.setBounds(23, 47, 91, 28);
    panel.add(lbCode);

    tfCode = new JTextField();
    tfCode.setEnabled(true);
    tfCode.setColumns(10);
    tfCode.setBounds(124, 49, 186, 28);
    panel.add(tfCode);

    JLabel lbHireDate = new JLabel("HireDate");
    lbHireDate.setForeground(Color.BLACK);
    lbHireDate.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbHireDate.setBounds(23, 315, 91, 28);
    panel.add(lbHireDate);

    tfHireDate = new JTextField();
    tfHireDate.setColumns(10);
    tfHireDate.setBounds(124, 315, 186, 28);
    panel.add(tfHireDate);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(335, 79, 1025, 624);
    contentPane.add(scrollPane);
    table = new JTable();
    table.setModel(model);
    model.addColumn("STT");
    model.addColumn("PersonID");
    model.addColumn("Lastname");
    model.addColumn("Firstname");
    model.addColumn("EnrollmentDate");
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {

      }
    });
    scrollPane.setViewportView(table);

    Panel panel1 = new Panel();
    panel1.setLayout(null);
    panel1.setBackground(new Color(50, 205, 50));
    panel1.setBounds(0, 0, 1364, 78);
    contentPane.add(panel1);

    JLabel lbTeacher = new JLabel("Danh Sách Giáo Viên");
    lbTeacher.setForeground(Color.WHITE);
    lbTeacher.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbTeacher.setBounds(301, 6, 387, 36);
    panel1.add(lbTeacher);

    JLabel lbStudent = new JLabel("Danh Sách Sinh Viên");
    lbStudent.setForeground(Color.WHITE);
    lbStudent.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbStudent.setBounds(301, 6, 387, 36);
    panel1.add(lbStudent);

    JButton btnSearch = new JButton("Tìm kiếm");
    btnSearch.setBounds(1249, 6, 90, 38);
    panel1.add(btnSearch);

    tfSearch = new JTextField();
    tfSearch.setColumns(10);
    tfSearch.setBounds(1003, 6, 243, 38);
    panel1.add(tfSearch);

    JButton btnReturn = new JButton("Trở lại");
    btnReturn.setBounds(22, 13, 90, 38);
    panel1.add(btnReturn);

    cbFilter = new JComboBox(personTypeList);
    cbFilter.setBounds(828, 6, 165, 38);
    panel1.add(cbFilter);

    JLabel lbSearchStudent = new JLabel("Tìm kiếm theo tên sinh viên");
    lbSearchStudent.setForeground(Color.WHITE);
    lbSearchStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
    lbSearchStudent.setBounds(1023, 41, 216, 29);
    panel1.add(lbSearchStudent);

    JLabel lbSearchTeacher = new JLabel("Tìm kiếm theo tên giáo viên");
    lbSearchTeacher.setForeground(Color.WHITE);
    lbSearchTeacher.setFont(new Font("Tahoma", Font.BOLD, 15));
    lbSearchTeacher.setBounds(1023, 41, 216, 29);
    panel1.add(lbSearchTeacher);

    JLabel lblLcTheo = new JLabel("Lọc theo");
    lblLcTheo.setForeground(Color.WHITE);
    lblLcTheo.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblLcTheo.setBounds(756, 6, 72, 29);
    panel1.add(lblLcTheo);

    lbHireDate.setVisible(false);
    tfHireDate.setVisible(false);
    lbSearchTeacher.setVisible(false);
    lbTeacher.setVisible(false);
    btnReturn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        managerGUI p = new managerGUI();
        p.f.setVisible(true);
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
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSearchActionPerformed(e);
      }
    });
    cbFilter.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String courseType = cbFilter.getSelectedItem().toString();
        tfCode.setText("");
        tfLastName.setText("");
        tfFirstName.setText("");
        tfEnrollmentDate.setText("");
        tfHireDate.setText("");
        tfCode.setEnabled(true);
        if (courseType.equals("Teacher")) {
          scrollPane.setViewportView(table);

          lbEnrollmentDate.setVisible(false);
          tfEnrollmentDate.setVisible(false);
          lbSearchStudent.setVisible(false);
          lbStudent.setVisible(false);

          lbHireDate.setVisible(true);
          tfHireDate.setVisible(true);
          lbSearchTeacher.setVisible(true);
          lbTeacher.setVisible(true);

          displayTeacherList();

        } else {
          scrollPane.setViewportView(table);

          lbHireDate.setVisible(false);
          tfHireDate.setVisible(false);
          lbSearchTeacher.setVisible(false);
          lbTeacher.setVisible(false);

          lbEnrollmentDate.setVisible(true);
          tfEnrollmentDate.setVisible(true);
          lbSearchStudent.setVisible(true);
          lbStudent.setVisible(true);

          displayStudentList();
        }
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
    tfCode.setText("");
    tfLastName.setText("");
    tfFirstName.setText("");
    tfEnrollmentDate.setText("");
    tfHireDate.setText("");
    tfCode.setEnabled(true);
  }

  private void jTableMouseClicked(MouseEvent e) {
    int selectedIndex = table.getSelectedRow();
    if (selectedIndex >= 0) {
      tfCode.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
      tfLastName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
      tfFirstName.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
      tfEnrollmentDate.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
      tfHireDate.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
      tfCode.setEnabled(false);
    }
  }

  private void btnAddActionPerformed(ActionEvent e) {

    String courseType = cbFilter.getSelectedItem().toString();
    if (courseType.equals("Student")) {
      if (!tfLastName.getText().trim().equals("")
          && !tfFirstName.getText().trim().equals("") && !tfEnrollmentDate.getText().trim().equals("")) {
        try {
          int code = Integer.parseInt(tfCode.getText());
          String lastName = tfLastName.getText();
          String firstName = tfFirstName.getText();
          String enrollmentDate = tfEnrollmentDate.getText();

          Student p = new Student(code, lastName, firstName, enrollmentDate);

          JOptionPane.showMessageDialog(null, stuBBL.addStudent(p));

          displayStudentList();
          btnResetActionPerformed(e);
        } catch (NumberFormatException ex) {

        }
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sinh viên");
      }
    } else {
      if (!tfLastName.getText().trim().equals("")
          && !tfFirstName.getText().trim().equals("") && !tfHireDate.getText().trim().equals("")) {
        try {
          int code = Integer.parseInt(tfCode.getText());
          String lastName = tfLastName.getText();
          String firstName = tfFirstName.getText();
          String HireDate = tfHireDate.getText();

          Teacher p = new Teacher(code, lastName, firstName, HireDate);

          JOptionPane.showMessageDialog(null, teaBLL.addTeacher(p));

          displayTeacherList();
          btnResetActionPerformed(e);
        } catch (NumberFormatException ex) {

        }
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin giáo viên");
      }
    }

  }

  private void btnEditActionPerformed(ActionEvent e) {

    String courseType = cbFilter.getSelectedItem().toString();
    int index = table.getSelectedRow();

    if (courseType.equals("Student")) {
      if (index >= 0 && !tfCode.getText().trim().equals("") && !tfLastName.getText().trim().equals("")
          && !tfFirstName.getText().trim().equals("") && !tfEnrollmentDate.getText().trim().equals("")) {
        Student p = new Student();
        p.setPersonID(Integer.parseInt(tfCode.getText()));
        p.setLastName(tfLastName.getText());
        p.setFirstName(tfFirstName.getText());
        p.setEnrollmentDate(tfEnrollmentDate.getText());
        JOptionPane.showMessageDialog(null, stuBBL.editStudent(p));
        displayStudentList();
        btnResetActionPerformed(e);
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sinh viên");
      }
    } else {
      if (index >= 0 && !tfCode.getText().trim().equals("") && !tfLastName.getText().trim().equals("")
          && !tfFirstName.getText().trim().equals("") && !tfHireDate.getText().trim().equals("")) {
        Teacher p = new Teacher();
        p.setPersonID(Integer.parseInt(tfCode.getText()));
        p.setLastName(tfLastName.getText());
        p.setFirstName(tfFirstName.getText());
        p.setHireDate(tfHireDate.getText());
        JOptionPane.showMessageDialog(null, teaBLL.editTeacher(p));
        displayTeacherList();
        btnResetActionPerformed(e);
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin giáo viên");
      }
    }
  }

  private void btnRemoveActionPerformed(ActionEvent e) {
    int selectedIndex = table.getSelectedRow();
    String courseType = cbFilter.getSelectedItem().toString();
    if (courseType.equals("Student")) {
      if (selectedIndex >= 0 && !tfCode.getText().equals("")) {
        int code = Integer.parseInt(tfCode.getText());
        int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sinh viên này?", "Question",
            JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
          int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Question",
              JOptionPane.YES_NO_OPTION);
          if (sure == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, stuBBL.deleteStudent(code));
            displayStudentList();
            btnResetActionPerformed(e);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn sinh viên cần xóa");
      }
    } else {
      if (selectedIndex >= 0 && !tfCode.getText().equals("")) {
        int code = Integer.parseInt(tfCode.getText());
        int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa giáo viên này?", "Question",
            JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
          int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Question",
              JOptionPane.YES_NO_OPTION);
          if (sure == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, teaBLL.deleteTeacher(code));
            displayTeacherList();
            btnResetActionPerformed(e);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn giáo viên cần xóa");
      }
    }
  }

  private void btnSearchActionPerformed(ActionEvent e) {

    String courseType = cbFilter.getSelectedItem().toString();
    if (courseType.equals("Student")) {
      String studentName = tfSearch.getText();
      if (studentName != null && studentName.length() > 0) {
        studentList = stuBBL.searchStudentByName(studentName);
        if (studentList.size() == 0) {
          JOptionPane.showMessageDialog(null, "Không có sinh viên cần tìm");
          displayStudentList();
        } else {
          model.setRowCount(0);
          int i = 0;
          while (i < studentList.size()) {
            Student p = studentList.get(i);
            model.addRow(new Object[] {
                model.getRowCount() + 1, p.getPersonID(), p.getLastName(), p.getFirstName(), p.getEnrollmentDate()
            });
            i++;
          }
        }

      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sinh viên cần tìm");
      }
    } else {
      String TeacherName = tfSearch.getText();
      if (TeacherName != null && TeacherName.length() > 0) {
        teacherList = teaBLL.searchTeacherByName(TeacherName);
        if (teacherList.size() == 0) {
          JOptionPane.showMessageDialog(null, "Không có giáo viên cần tìm");
          displayTeacherList();
        } else {
          model.setRowCount(0);
          int i = 0;
          while (i < teacherList.size()) {
            Teacher p = teacherList.get(i);
            model.addRow(new Object[] {
                model.getRowCount() + 1, p.getPersonID(), p.getLastName(), p.getFirstName(), p.getHireDate()
            });
            i++;
          }
        }

      } else {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên giáo viên cần tìm");
      }
    }
  }

  private void displayStudentList() {
    model.setRowCount(0);
    studentList = stuBBL.getAllStudent();
    int i = 0;
    while (i < studentList.size()) {
      Student p = studentList.get(i);
      model.addRow(new Object[] {
          model.getRowCount() + 1, p.getPersonID(), p.getLastName(), p.getFirstName(), p.getEnrollmentDate()
      });
      i++;
    }
  }

  private void displayTeacherList() {
    model.setRowCount(0);
    teacherList = teaBLL.getAllTeacher();
    int i = 0;
    while (i < teacherList.size()) {
      Teacher p = teacherList.get(i);
      model.addRow(new Object[] {
          model.getRowCount() + 1, p.getPersonID(), p.getLastName(), p.getFirstName(), p.getHireDate()
      });
      i++;
    }
  }
}