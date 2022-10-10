package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class adminGUI extends JFrame {
  private JPanel contentPane;
  public JFrame f = new JFrame();

  // public static void main(String[] args) {
  // new adminGUI();
  // }

  // public adminGUI() {
  // initComponent();
  // }

  /**
   * Create the frame.
   * 
   * @return
   */
  public void initComponent() {
    setTitle("Trang admin");
    setSize(448, 476);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(230, 230, 250));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    Panel panel1 = new Panel();
    panel1.setBackground(new Color(50, 205, 50));
    panel1.setBounds(0, 0, 1286, 140);
    contentPane.add(panel1);
    panel1.setLayout(null);

    JLabel lbHeader = new JLabel("Quản lý khóa học");
    lbHeader.setBounds(10, 24, 418, 91);
    panel1.add(lbHeader);
    lbHeader.setForeground(new Color(240, 248, 255));
    lbHeader.setFont(new Font("SansSerif", Font.BOLD, 50));
    lbHeader.setBackground(new Color(175, 238, 238));

    JButton btnStudent = new JButton("Quản lý học viên");
    btnStudent.setBounds(74, 133, 277, 64);
    contentPane.add(btnStudent);
    btnStudent.setForeground(Color.BLACK);
    btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnStudent.setBackground(new Color(230, 230, 250));

    JButton btnTeacher = new JButton("Quản lý người dạy");
    btnTeacher.setBounds(74, 193, 277, 64);
    contentPane.add(btnTeacher);
    btnTeacher.setForeground(Color.BLACK);
    btnTeacher.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnTeacher.setBackground(new Color(230, 230, 250));

    JButton btnCourse = new JButton("Quản lý khóa học");
    btnCourse.setBounds(74, 253, 277, 64);
    contentPane.add(btnCourse);
    btnCourse.setForeground(Color.BLACK);
    btnCourse.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnCourse.setBackground(new Color(230, 230, 250));

    JButton btnPosition = new JButton("Quản lý phân công");
    btnPosition.setBounds(74, 313, 277, 64);
    contentPane.add(btnPosition);
    btnPosition.setForeground(Color.BLACK);
    btnPosition.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnPosition.setBackground(new Color(230, 230, 250));

    JButton btnResult = new JButton("Quản lý kết quả");
    btnResult.setBounds(74, 375, 277, 64);
    contentPane.add(btnResult);
    btnResult.setForeground(Color.BLACK);
    btnResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnResult.setBackground(new Color(230, 230, 250));

    btnResult.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    btnPosition.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    btnCourse.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    btnTeacher.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        teacherGUI p = new teacherGUI();
        p.f.setVisible(true);
      }
    });

    btnStudent.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        studentGUI p = new studentGUI();
        p.f.setVisible(true);
      }
    });

    f.add(contentPane);
    f.setSize(448, 476);
    f.setVisible(true);
    f.setResizable(false);
  }
}