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
import BLL.TeacherBLL;
import java.awt.Panel;
import Base.*;

public class teacherGUI extends JFrame {

  List<Teacher> teacherList = new ArrayList<Teacher>();
  TeacherBLL teaBLL = new TeacherBLL();
  public JFrame f = new JFrame();
  private JPanel contentPane;
  private JTextField tfLastName;
  private JTextField tfFirstName;
  private JTextField tfHireDate;
  private JTextField tfFind;
  DefaultTableModel model = new DefaultTableModel();
  private JTable table;
  private JTextField tfCode;

  public teacherGUI() {
    initComponent();
    displayList();
  }

  /**
   * Create the frame.
   * 
   * @return
   */
  public void initComponent() {
    setTitle("Quản lý giáo viên");
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
    JLabel lbLastName = new JLabel("LastName");
    lbLastName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbLastName.setForeground(new Color(0, 0, 0));
    lbLastName.setBounds(25, 134, 91, 28);
    panel.add(lbLastName);

    tfLastName = new JTextField();
    tfLastName.setBounds(126, 136, 186, 28);
    panel.add(tfLastName);
    tfLastName.setColumns(10);

    JLabel lbFirstName = new JLabel("FirstName");
    lbFirstName.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbFirstName.setForeground(new Color(0, 0, 0));
    lbFirstName.setBounds(25, 224, 106, 28);
    panel.add(lbFirstName);

    tfFirstName = new JTextField();
    tfFirstName.setBounds(126, 226, 186, 28);
    panel.add(tfFirstName);
    tfFirstName.setColumns(10);

    JLabel lbHireDate = new JLabel("HireDate");
    lbHireDate.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbHireDate.setForeground(new Color(0, 0, 0));
    lbHireDate.setBounds(25, 314, 91, 28);
    panel.add(lbHireDate);

    tfHireDate = new JTextField();
    tfHireDate.setBounds(126, 314, 186, 28);
    panel.add(tfHireDate);
    tfHireDate.setColumns(10);

    JButton btnEdit = new JButton("Sửa");
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnEdit.setBounds(141, 416, 69, 28);
    panel.add(btnEdit);

    JButton btnAdd = new JButton("Thêm");
    btnAdd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        btnAddActionPerformed(e);
      }
    });
    btnAdd.setBounds(257, 416, 69, 28);
    panel.add(btnAdd);

    JButton btnRemove = new JButton("Xóa");
    btnRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnRemoveActionPerformed(e);
      }
    });
    btnRemove.setBounds(23, 416, 69, 28);
    panel.add(btnRemove);

    JButton btnReset = new JButton("Reset");
    btnReset.setBounds(87, 465, 176, 38);
    panel.add(btnReset);

    JLabel lbCode = new JLabel("PersonID");
    lbCode.setForeground(Color.BLACK);
    lbCode.setFont(new Font("SansSerif", Font.BOLD, 12));
    lbCode.setBounds(25, 46, 91, 28);
    panel.add(lbCode);

    tfCode = new JTextField();
    tfCode.setEnabled(true);
    tfCode.setColumns(10);
    tfCode.setBounds(126, 48, 186, 28);
    panel.add(tfCode);

    JButton btnReturn = new JButton("Xem danh sách sinh viên");
    btnReturn.setBounds(43, 528, 251, 38);
    panel.add(btnReturn);
    btnReturn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        studentGUI p = new studentGUI();
        p.f.setVisible(true);
      }
    });
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(324, 59, 1036, 652);
    contentPane.add(scrollPane);
    table = new JTable();
    table.setModel(model);
    model.addColumn("STT");
    model.addColumn("PersonID");
    model.addColumn("Lastname");
    model.addColumn("Firstname");
    model.addColumn("HireDate");
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

    JLabel lbTitle = new JLabel("Danh Sách Giáo Viên");
    lbTitle.setForeground(Color.WHITE);
    lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbTitle.setBounds(315, 0, 309, 62);
    panel1.add(lbTitle);

    JButton btnSearch = new JButton("Tìm kiếm");
    btnSearch.setBounds(1252, 12, 90, 38);
    panel1.add(btnSearch);
    tfFind = new JTextField();
    tfFind.setBounds(1010, 12, 243, 38);
    panel1.add(tfFind);
    tfFind.setColumns(10);

    JLabel lblNewLabel = new JLabel("Tìm kiếm theo tên giáo viên");
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblNewLabel.setBounds(786, 0, 214, 62);
    panel1.add(lblNewLabel);

    JButton btnReturn_1 = new JButton("Quay lại");
    btnReturn_1.setBounds(10, 6, 96, 46);
    panel1.add(btnReturn_1);
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
        btnResetActionPerformed(e);
      }
    });
    btnReturn_1.addActionListener(new ActionListener() {
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
    tfCode.setText("");
    tfLastName.setText("");
    tfFirstName.setText("");
    tfHireDate.setText("");
    tfCode.setEnabled(true);
  }

  private void jTableMouseClicked(MouseEvent e) {
    int selectedIndex = table.getSelectedRow();
    if (selectedIndex >= 0) {
      tfCode.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
      tfLastName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
      tfFirstName.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
      tfHireDate.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
      tfCode.setEnabled(false);
    }
  }

  private void btnAddActionPerformed(ActionEvent e) {
    if (!tfLastName.getText().trim().equals("")
        && !tfFirstName.getText().trim().equals("") && !tfHireDate.getText().trim().equals("")) {
      try {
        int code = Integer.parseInt(tfCode.getText());
        String lastName = tfLastName.getText();
        String firstName = tfFirstName.getText();
        String HireDate = tfHireDate.getText();

        Teacher p = new Teacher(code, lastName, firstName, HireDate);

        JOptionPane.showMessageDialog(null, teaBLL.addTeacher(p));

        displayList();
        btnResetActionPerformed(e);
      } catch (NumberFormatException ex) {

      }
    } else {
      JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin giÃ¡o viÃªn");
    }
  }

  private void btnEditActionPerformed(ActionEvent e) {
    int index = table.getSelectedRow();
    if (index >= 0 && !tfCode.getText().trim().equals("") && !tfLastName.getText().trim().equals("")
        && !tfFirstName.getText().trim().equals("") && !tfHireDate.getText().trim().equals("")) {
      Teacher p = new Teacher();
      p.setPersonID(Integer.parseInt(tfCode.getText()));
      p.setLastName(tfLastName.getText());
      p.setFirstName(tfFirstName.getText());
      p.setHireDate(tfHireDate.getText());
      JOptionPane.showMessageDialog(null, teaBLL.editTeacher(p));
      displayList();
      btnResetActionPerformed(e);
    } else {
      JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin giÃ¡o viÃªn");
    }
  }

  private void btnRemoveActionPerformed(ActionEvent e) {
    int selectedIndex = table.getSelectedRow();
    if (selectedIndex >= 0 && !tfCode.getText().equals("")) {
      int code = Integer.parseInt(tfCode.getText());
      int option = JOptionPane.showConfirmDialog(null, "Báº¡n muá»‘n xÃ³a giÃ¡o viÃªn nÃ y?", "Question",
          JOptionPane.YES_NO_OPTION);

      if (option == JOptionPane.YES_OPTION) {
        int sure = JOptionPane.showConfirmDialog(null, "Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a?", "Question",
            JOptionPane.YES_NO_OPTION);
        if (sure == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(null, teaBLL.deleteTeacher(code));
          displayList();
          btnResetActionPerformed(e);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Vui lÃ²ng chá»�n giÃ¡o viÃªn cáº§n xÃ³a");
    }
  }

  private void btnSearchActionPerformed(ActionEvent e) {
    String TeacherName = tfFind.getText();
    if (TeacherName != null && TeacherName.length() > 0) {
      teacherList = teaBLL.searchTeacherByName(TeacherName);
      if (teacherList.size() == 0) {
        JOptionPane.showMessageDialog(null, "KhÃ´ng cÃ³ giÃ¡o viÃªn cáº§n tÃ¬m");
        displayList();
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
      JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p tÃªn giÃ¡o viÃªn cáº§n tÃ¬m");
    }
  }

  private void displayList() {
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