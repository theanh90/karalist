package com.bui.karalist.view;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Demo extends JFrame{
	
    private JTable tblStudent;
    private DefaultTableModel model;
    
    public Demo() {
        // TODO Auto-generated constructor stub
        /* START - Nếu dùng Netbeans thì không cần quan tâm đến đoạn này làm gì */
        tblStudent = new JTable();
        tblStudent.setPreferredSize(new Dimension(400,400));
        this.add(new JScrollPane(tblStudent));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
        this.setVisible(true);
        this.setSize(500, 500);
      /* END - Nếu dùng Netbeans thì không cần quan tâm đến đoạn này làm gì */

        loadDataIntoJTable();  //đưa column name và data row lên JTable
    }

    private void loadDataIntoJTable(){
        model = new DefaultTableModel();
        //Set Column Title
        Vector column = new Vector();
        column.add("Student ID");
        column.add("Student Name");
        column.add("Age");
        column.add("Address");
        model.setColumnIdentifiers(column);
       /* List<Student> list = getStudentsFromDB();
        for (int i = 0; i < list.size(); i++) {
            Student student = (Student)list.get(i);
            Vector row = new Vector();
            row.add(student.getStudentID());
            row.add(student.getStudentName());
            row.add(student.getAge());
            row.add(student.getAddress());
            model.addRow(row);
        }*/

        tblStudent.setModel(model);
    }

//    private List<Student> getStudentsFromDB(){
//        try {
//            OracleDataSource source = new OracleDataSource();
//            source.setURL("jdbc:oracle:thin:@localhost:1521/XE");
//            Connection con = source.getConnection("sa", "123456");
//            String sql = "SELECT StudentID, StudentName, Age, Address FROM Student";
//            PreparedStatement pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            List<Student> list = new ArrayList<Student>();
//            while (rs.next()) {
//                Student student = new Student(rs.getString("StudentID"), rs.getString("StudentName"), rs.getString("Age"), rs.getString("Address"));
//                list.add(student);
//            }
//            return list;
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static void main(String[] args) {
        new Demo();
    }
}