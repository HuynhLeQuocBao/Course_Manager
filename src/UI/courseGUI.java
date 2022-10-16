package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import BLL.courseBLL;
import Base.*;
import javax.swing.ImageIcon;
import java.awt.Panel;

public class courseGUI extends JFrame {
    courseBLL courseBLL = new courseBLL();
    String[] courseTypeList = { "Course Onsite", "Course Online" };
    String[] departmentListForCB = {};
    int courseId = 0;
    List<String> departmentList = courseBLL.getAllDepartment();
    List<OnsiteCourse> courseOnsiteList = new ArrayList<OnsiteCourse>();
    List<OnlineCourse> courseOnlineList = new ArrayList<OnlineCourse>();

    OnsiteCourse onsiteCourse;
    OnlineCourse onlineCourse;

    public JFrame f = new JFrame();
    private JPanel contentPane;
    private JTextField tfCredits;
    private JTextField tfSearch;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelOnline = new DefaultTableModel();

    private JTable table, tableOnlineCourse;
    private JTextField tfCourseTitle;
    private JComboBox cbFilter, cbCourseType, cbDepartmentID;
    private JTextField tfUrl;
    private JTextField tfLocation;
    private JTextField tfDay;
    private JTextField tfTime;

    public static void main(String[] args) {
        new courseGUI();
    }

    public courseGUI() {
        initComponent();
        displayList(cbFilter.getSelectedItem().toString());
    }

    /**
     * Create the frame.
     * 
     * @return
     */
    public void initComponent() {
        setTitle("Quáº£n lÃ­ sáº£n pháº©m");
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
        panel.setBounds(-11, 79, 336, 632);
        contentPane.add(panel);
        panel.setLayout(null);
        f.setResizable(false);
        JLabel lbCredits = new JLabel("Credits");
        lbCredits.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbCredits.setForeground(new Color(0, 0, 0));
        lbCredits.setBounds(25, 98, 91, 28);
        panel.add(lbCredits);

        tfCredits = new JTextField();
        tfCredits.setBounds(126, 100, 186, 28);
        panel.add(tfCredits);
        tfCredits.setColumns(10);

        JLabel lbDepartmentID = new JLabel("Department ID");
        lbDepartmentID.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbDepartmentID.setForeground(new Color(0, 0, 0));
        lbDepartmentID.setBounds(25, 154, 106, 28);
        panel.add(lbDepartmentID);

        JLabel lbCourseType = new JLabel("Course Type");
        lbCourseType.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbCourseType.setForeground(new Color(0, 0, 0));
        lbCourseType.setBounds(25, 208, 91, 28);
        panel.add(lbCourseType);

        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEditActionPerformed(e);
            }
        });
        btnEdit.setBounds(143, 508, 69, 28);
        panel.add(btnEdit);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                btnAddActionPerformed(e);
            }
        });
        btnAdd.setBounds(259, 508, 69, 28);
        panel.add(btnAdd);

        JButton btnRemove = new JButton("Xóa");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRemoveActionPerformed(e);
            }
        });
        btnRemove.setBounds(25, 508, 69, 28);
        panel.add(btnRemove);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(89, 557, 176, 38);
        panel.add(btnReset);

        JLabel lbCourseTitle = new JLabel("Course Title");
        lbCourseTitle.setForeground(Color.BLACK);
        lbCourseTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbCourseTitle.setBounds(25, 46, 91, 28);
        panel.add(lbCourseTitle);

        tfCourseTitle = new JTextField();
        tfCourseTitle.setColumns(10);
        tfCourseTitle.setBounds(126, 48, 186, 28);
        panel.add(tfCourseTitle);

        cbDepartmentID = new JComboBox(departmentList.toArray());
        cbDepartmentID.setBounds(126, 155, 186, 28);
        panel.add(cbDepartmentID);

        cbCourseType = new JComboBox(courseTypeList);
        cbCourseType.setBounds(126, 209, 186, 28);
        panel.add(cbCourseType);
        cbCourseType.setSelectedItem("Course Onsite");

        JLabel lbUrl = new JLabel("Url");
        lbUrl.setForeground(Color.BLACK);
        lbUrl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbUrl.setBounds(25, 266, 91, 28);
        panel.add(lbUrl);

        tfUrl = new JTextField();
        tfUrl.setColumns(10);
        tfUrl.setBounds(126, 268, 186, 28);
        panel.add(tfUrl);

        // default disable url (property of course online)
        lbUrl.setVisible(false);
        tfUrl.setVisible(false);

        JLabel lbLocation = new JLabel("Location");
        lbLocation.setForeground(Color.BLACK);
        lbLocation.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbLocation.setBounds(25, 266, 91, 28);
        panel.add(lbLocation);

        tfLocation = new JTextField();
        tfLocation.setColumns(10);
        tfLocation.setBounds(126, 268, 186, 28);
        panel.add(tfLocation);

        JLabel lbDay = new JLabel("Day");
        lbDay.setForeground(Color.BLACK);
        lbDay.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbDay.setBounds(25, 329, 91, 28);
        panel.add(lbDay);

        tfDay = new JTextField();
        tfDay.setColumns(10);
        tfDay.setBounds(126, 331, 186, 28);
        panel.add(tfDay);

        JLabel lbTime = new JLabel("Time");
        lbTime.setForeground(Color.BLACK);
        lbTime.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbTime.setBounds(25, 386, 91, 28);
        panel.add(lbTime);

        tfTime = new JTextField();
        tfTime.setColumns(10);
        tfTime.setBounds(126, 388, 186, 28);
        panel.add(tfTime);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(324, 79, 1036, 632);
        contentPane.add(scrollPane);

        cbCourseType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseType = cbCourseType.getSelectedItem().toString();
                if (courseType.equals("Course Onsite")) {
                    // disabled property of online course
                    lbUrl.setVisible(false);
                    tfUrl.setVisible(false);
                    // enable property of onsite course
                    lbLocation.setVisible(true);
                    tfLocation.setVisible(true);
                    lbDay.setVisible(true);
                    tfDay.setVisible(true);
                    lbTime.setVisible(true);
                    tfTime.setVisible(true);

                } else {
                    // disable property of onsite course
                    lbLocation.setVisible(false);
                    tfLocation.setVisible(false);
                    lbDay.setVisible(false);
                    tfDay.setVisible(false);
                    lbTime.setVisible(false);
                    tfTime.setVisible(false);
                    // enable property of onsite course
                    lbUrl.setVisible(true);
                    tfUrl.setVisible(true);
                }
            }
        });

        // table onsite course
        table = new JTable();
        table.setModel(model);
        model.addColumn("No");
        model.addColumn("CourseID");
        model.addColumn("Title");
        model.addColumn("Credits");
        model.addColumn("DepartmentID");
        model.addColumn("Department");
        model.addColumn("Location");
        model.addColumn("Days");
        model.addColumn("Time");

        // table online course
        tableOnlineCourse = new JTable();
        tableOnlineCourse.setModel(modelOnline);
        modelOnline.addColumn("No");
        modelOnline.addColumn("CourseID");
        modelOnline.addColumn("Title");
        modelOnline.addColumn("Credits");
        modelOnline.addColumn("DepartmentID");
        modelOnline.addColumn("Department");
        modelOnline.addColumn("Url");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTableMouseClickedOnsite(e);
            }
        });

        tableOnlineCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTableMouseClickedOnline(e);
            }
        });
        scrollPane.setViewportView(table);

        Panel panel1 = new Panel();
        panel1.setBackground(new Color(50, 205, 50));
        panel1.setBounds(0, 0, 1364, 78);
        contentPane.add(panel1);
        panel1.setLayout(null);

        JLabel lbTitle = new JLabel("Danh Sách Khóa Học");
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        lbTitle.setBounds(301, 6, 387, 36);
        panel1.add(lbTitle);

        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBounds(1249, 6, 90, 38);
        panel1.add(btnSearch);
        tfSearch = new JTextField();
        tfSearch.setBounds(1003, 6, 243, 38);
        panel1.add(tfSearch);
        tfSearch.setColumns(10);

        JButton btnReturn = new JButton("Trở lại");
        btnReturn.setBounds(22, 13, 90, 38);
        panel1.add(btnReturn);

        cbFilter = new JComboBox(courseTypeList);
        cbFilter.setBounds(828, 6, 165, 38);
        panel1.add(cbFilter);

        cbFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String courseType = cbFilter.getSelectedItem().toString();
                if (courseType.equals("Course Onsite")) {
                    // display onsite list and table onsite course
                    scrollPane.setViewportView(table);

                    // disabled property of online course
                    lbUrl.setVisible(false);
                    tfUrl.setVisible(false);
                    // enable property of onsite course
                    lbLocation.setVisible(true);
                    tfLocation.setVisible(true);
                    lbDay.setVisible(true);
                    tfDay.setVisible(true);
                    lbTime.setVisible(true);
                    tfTime.setVisible(true);
                } else {
                    // display online list
                    scrollPane.setViewportView(tableOnlineCourse);

                    // disable property of onsite course
                    lbLocation.setVisible(false);
                    tfLocation.setVisible(false);
                    lbDay.setVisible(false);
                    tfDay.setVisible(false);
                    lbTime.setVisible(false);
                    tfTime.setVisible(false);
                    // enable property of onsite course
                    lbUrl.setVisible(true);
                    tfUrl.setVisible(true);

                }
                displayList(cbFilter.getSelectedItem().toString());
            }
        });

        JLabel lbSearch = new JLabel("Tìm kiếm theo tiêu đề khóa học");
        lbSearch.setBounds(1023, 41, 280, 29);
        panel1.add(lbSearch);
        lbSearch.setForeground(Color.WHITE);
        lbSearch.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lbFilter = new JLabel("Lọc theo");
        lbFilter.setForeground(Color.WHITE);
        lbFilter.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbFilter.setBounds(756, 6, 72, 29);
        panel1.add(lbFilter);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSearchActionPerformed(e);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                adminGUI p = new adminGUI();
                p.f.setVisible(true);
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        f.add(contentPane);
        f.setSize(1366, 740);
        f.setVisible(true);
    }

    // click in the table
    private void jTableMouseClickedOnsite(MouseEvent e) {
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex >= 0) {
            courseId = Integer.parseInt(String.valueOf(model.getValueAt(selectedIndex, 1)));
            // onsite course selected
            tfCourseTitle.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
            tfCredits.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));

            cbDepartmentID
                    .setSelectedItem(model.getValueAt(selectedIndex, 4) + "_" +
                            model.getValueAt(selectedIndex, 5));

            cbCourseType.setSelectedItem(cbFilter.getSelectedItem().toString());
            tfLocation.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
            tfDay.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
            tfTime.setText(String.valueOf(model.getValueAt(selectedIndex, 8)));
        }
    }

    private void jTableMouseClickedOnline(MouseEvent e) {
        int selectedIndex = tableOnlineCourse.getSelectedRow();
        courseId = Integer.parseInt(String.valueOf(modelOnline.getValueAt(selectedIndex, 1)));
        // online course selected
        tfCourseTitle.setText(String.valueOf(modelOnline.getValueAt(selectedIndex, 2)));
        tfCredits.setText(String.valueOf(modelOnline.getValueAt(selectedIndex, 3)));

        cbDepartmentID
                .setSelectedItem(
                        modelOnline.getValueAt(selectedIndex, 4) + "_" +
                                modelOnline.getValueAt(selectedIndex, 5));

        cbCourseType.setSelectedItem(cbFilter.getSelectedItem().toString());
        tfUrl.setText(String.valueOf(modelOnline.getValueAt(selectedIndex, 6)));
    }

    // add course
    private void btnAddActionPerformed(ActionEvent e) {
        if (!tfCourseTitle.getText().trim().equals("") && !tfCredits.getText().trim().equals("")) {
            // info of course
            int courseId = 0; // init course id
            String title = tfCourseTitle.getText().trim();
            String credits = tfCredits.getText().trim();
            int departmentID = Integer.parseInt(cbDepartmentID.getSelectedItem().toString().split("_")[0]);
            // add course onsite
            if (cbCourseType.getSelectedItem().toString().equals("Course Onsite")
                    && !tfLocation.getText().trim().equals("") && !tfDay.getText().trim().equals("")
                    && !tfTime.getText().trim().equals("")) {
                String location = tfLocation.getText().trim();
                String day = tfDay.getText().trim();
                String time = tfTime.getText().trim();

                onsiteCourse = new OnsiteCourse(courseId, title, credits, departmentID, location, day,
                        time);
                JOptionPane.showMessageDialog(null, courseBLL.addCourse("onsite", onsiteCourse, onlineCourse));
                displayList(cbFilter.getSelectedItem().toString());
            }
            // add course online
            else if (cbCourseType.getSelectedItem().toString().equals("Course Online")
                    && !tfUrl.getText().trim().equals("")) {
                String url = tfUrl.getText().trim();

                onlineCourse = new OnlineCourse(courseId, title, credits, departmentID, url);
                JOptionPane.showMessageDialog(null, courseBLL.addCourse("online", onsiteCourse, onlineCourse));
                displayList(cbFilter.getSelectedItem().toString());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please fill all property of " + cbCourseType.getSelectedItem().toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng điền tất cả các trường của khóa học");
        }
    }

    // edit course
    private void btnEditActionPerformed(ActionEvent e) {
        if (!tfCourseTitle.getText().trim().equals("") && !tfCredits.getText().trim().equals("")) {
            String title = tfCourseTitle.getText().trim();
            String credits = tfCredits.getText().trim();
            int departmentID = Integer.parseInt(cbDepartmentID.getSelectedItem().toString().split("_")[0]);

            String location = tfLocation.getText().trim();
            String day = tfDay.getText().trim();
            String time = tfTime.getText().trim();
            String url = tfUrl.getText().trim();
            if (cbFilter.getSelectedItem().toString().equals(cbCourseType.getSelectedItem().toString())) {
                // update onsiteCourse
                if (cbCourseType.getSelectedItem().equals("Course Onsite") && !tfLocation.getText().trim().equals("")
                        && !tfDay.getText().trim().equals("")
                        && !tfTime.getText().trim().equals("")) {
                    location = tfLocation.getText().trim();
                    day = tfDay.getText().trim();
                    time = tfTime.getText().trim();

                    onsiteCourse = new OnsiteCourse(courseId, title, credits, departmentID, location, day,
                            time);
                    JOptionPane.showMessageDialog(null, courseBLL.editCourse("onsite", onsiteCourse, onlineCourse));
                    displayList(cbFilter.getSelectedItem().toString());

                }
                // update online course
                else if (cbCourseType.getSelectedItem().equals("Course Online")
                        && !tfUrl.getText().trim().equals("")) {
                    url = tfUrl.getText().trim();

                    onlineCourse = new OnlineCourse(courseId, title, credits, departmentID, url);
                    JOptionPane.showMessageDialog(null, courseBLL.editCourse("online", onsiteCourse, onlineCourse));
                    displayList(cbFilter.getSelectedItem().toString());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Vui lòng điền đầy đủ thông tin của khóa học " + cbCourseType.getSelectedItem().toString());
                }
            } else { // change type of course
                // change from course online to course onsite
                if (cbCourseType.getSelectedItem().equals("Course Onsite")) {
                    location = tfLocation.getText().trim();
                    day = tfDay.getText().trim();
                    time = tfTime.getText().trim();

                    onsiteCourse = new OnsiteCourse(courseId, title, credits, departmentID, location, day,
                            time);
                    onlineCourse = new OnlineCourse(courseId, title, credits, departmentID, url);
                    JOptionPane.showMessageDialog(null,
                            courseBLL.editCourse("changeToOnsiteCourse", onsiteCourse, onlineCourse));
                    displayList(cbFilter.getSelectedItem().toString());
                }
                // change from course onsite to course online
                else if (cbCourseType.getSelectedItem().equals("Course Online")) {
                    url = tfUrl.getText().trim();

                    onsiteCourse = new OnsiteCourse(courseId, title, credits, departmentID, location, day,
                            time);
                    onlineCourse = new OnlineCourse(courseId, title, credits, departmentID, url);
                    JOptionPane.showMessageDialog(null,
                            courseBLL.editCourse("changeToOnlineCourse", onsiteCourse, onlineCourse));
                    displayList(cbFilter.getSelectedItem().toString());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Vui lòng điền đầy đủ thông tin của khóa học "
                                    + cbCourseType.getSelectedItem().toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin của khóa học");

        }
    }

    // delete course
    private void btnRemoveActionPerformed(ActionEvent e) {
        if (!tfCourseTitle.getText().trim().equals("")) {
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khóa học này?", "Question",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khóa học này?",
                        "Question", JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION) {
                    if (cbFilter.getSelectedItem().toString().equals("Course Onsite")) {
                        JOptionPane.showMessageDialog(null, courseBLL.deleteCourse("onsite", courseId));
                    } else {
                        JOptionPane.showMessageDialog(null, courseBLL.deleteCourse("online", courseId));
                    }
                    displayList(cbFilter.getSelectedItem().toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khóa học để xóa");
        }
    }

    private void btnSearchActionPerformed(ActionEvent e) {
        String title = String.valueOf(tfSearch.getText());
        if (!title.equals("")) {
            if (cbFilter.getSelectedItem().toString().equals("Course Onsite")) {
                courseOnsiteList = courseBLL.findOnsiteCourse(title);
            } else {
                courseOnlineList = courseBLL.findOnlineCourse(title);
            }
            if (courseOnsiteList.size() == 0) {
                JOptionPane.showMessageDialog(null, "Không có khóa học cần tìm kiếm");
                displayList(cbFilter.getSelectedItem().toString());
            } else {
                if (cbFilter.getSelectedItem().toString().equals("Course Onsite")) {
                    model.setRowCount(0);
                    int i = 0;
                    while (i < courseOnsiteList.size()) {
                        OnsiteCourse course = courseOnsiteList.get(i);
                        model.addRow(new Object[] {
                                model.getRowCount() + 1, course.getCourseID(), course.getTitle(), course.getCredits(),
                                course.getDepartmentID(), courseBLL.getDepartmentName(course.getDepartmentID()),
                                course.getLocation(), course.getDate(), course.getTime()
                        });
                        i++;
                    }
                } else {
                    modelOnline.setRowCount(0);
                    int i = 0;
                    while (i < courseOnlineList.size()) {
                        OnlineCourse course = courseOnlineList.get(i);
                        modelOnline.addRow(new Object[] {
                                modelOnline.getRowCount() + 1, course.getCourseID(), course.getTitle(),
                                course.getCredits(),
                                course.getDepartmentID(), courseBLL.getDepartmentName(course.getDepartmentID()),
                                course.getUrl()
                        });
                        i++;
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tiêu đề của khóa học để tìm kiếm");
        }
    }

    // display list onsite course or online course
    public void displayList(String typeOfCourse) {
        int i = 0;
        if (typeOfCourse.equals("Course Onsite")) {
            model.setRowCount(0);
            courseOnsiteList = courseBLL.getAllOnsiteCourse();
            while (i < courseOnsiteList.size()) {
                OnsiteCourse course = courseOnsiteList.get(i);
                model.addRow(new Object[6]);
                model.setValueAt(model.getRowCount(), i, 0);
                model.setValueAt(course.getCourseID(), i, 1);
                model.setValueAt(course.getTitle(), i, 2);
                model.setValueAt(course.getCredits(), i, 3);
                model.setValueAt(course.getDepartmentID(), i, 4);
                model.setValueAt(courseBLL.getDepartmentName(course.getDepartmentID()), i, 5);
                model.setValueAt(course.getLocation(), i, 6);
                model.setValueAt(course.getDate(), i, 7);
                model.setValueAt(course.getTime(), i, 8);
                i++;
            }
        } else {
            courseOnlineList = courseBLL.getAllOnlineCourse();
            modelOnline.setRowCount(0);
            while (i < courseOnlineList.size()) {
                OnlineCourse course = courseOnlineList.get(i);
                modelOnline.addRow(new Object[6]);
                modelOnline.setValueAt(modelOnline.getRowCount(), i, 0);
                modelOnline.setValueAt(course.getCourseID(), i, 1);
                modelOnline.setValueAt(course.getTitle(), i, 2);
                modelOnline.setValueAt(course.getCredits(), i, 3);
                modelOnline.setValueAt(course.getDepartmentID(), i, 4);
                modelOnline.setValueAt(courseBLL.getDepartmentName(course.getDepartmentID()), i, 5);
                modelOnline.setValueAt(course.getUrl(), i, 6);
                i++;
            }
        }
    }
}
