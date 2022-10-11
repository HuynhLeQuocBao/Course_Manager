package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.SwingConstants;

public class managerGUI extends JFrame {
  public JFrame f = new JFrame();
  private JPanel contentPane;

  public static void main(String[] args) {
    new managerGUI();
  }

  public managerGUI() {
    initComponent();
  }

  /**
   * Create the frame.
   * 
   * @return
   */
  public void initComponent() {
    setTitle("Quản lý sinh viên");
    setSize(688, 426);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(50, 205, 50));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(220, 220, 220));
    panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
    panel.setBounds(-11, 57, 691, 340);
    contentPane.add(panel);
    panel.setLayout(null);
    f.setResizable(false);
    
        JButton btnPerson = new JButton("Quản lý nhân sự");
        btnPerson.setBounds(222, 50, 251, 38);
        panel.add(btnPerson);
        
        JButton btnCourse = new JButton("Quản lý khóa học");
        btnCourse.setBounds(222, 120, 251, 38);
        panel.add(btnCourse);
        
        JButton btnRole = new JButton("Quản lý phân công");
        btnRole.setBounds(222, 190, 251, 38);
        panel.add(btnRole);
        
        JButton btnResult = new JButton("Quản lý kết quả");
        btnResult.setBounds(222, 260, 251, 38);
        panel.add(btnResult);
        
        btnPerson.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
            studentGUI p = new studentGUI();
            p.f.setVisible(true);
          }
        });
        btnRole.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
            courseInstructorGUI p = new courseInstructorGUI();
            p.f.setVisible(true);
          }
        });
    Panel panel1 = new Panel();
    panel1.setBackground(new Color(50, 205, 50));
    panel1.setBounds(0, 0, 680, 62);
    contentPane.add(panel1);
    panel1.setLayout(null);

    JLabel lbTitle = new JLabel("PROJECT ONE");
    lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lbTitle.setForeground(Color.WHITE);
    lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
    lbTitle.setBounds(0, 0, 680, 62);
    panel1.add(lbTitle);
    f.add(contentPane);
    f.setSize(688, 426);
    f.setVisible(true);
  }
}