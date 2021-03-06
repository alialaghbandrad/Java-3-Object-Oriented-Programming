/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day10students;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author Teacher
 */
public class Day10students extends javax.swing.JFrame {

    DefaultListModel<Student> studentListModel = new DefaultListModel<>();
    String sortColumn = "id"; // id or name
    Database db;

    Student currentEditedStudent = null; // null when adding, non-null when editing

    /**
     * Creates new form Day10students
     */
    public Day10students() {
        try {
            initComponents();
            fileChooserImage.setFileFilter(new FileNameExtensionFilter("Images (*.gif,*.png,*.jpg,*.jpeg)", "gif", "png", "jpg", "jpeg"));
            fileChooserCsv.setFileFilter(new FileNameExtensionFilter("CSV files (*.csv)", "csv"));
            db = new Database();
            reloadFromDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to connect " + ex.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1); // FATAL ERROR, EXIT PROGRAM
        }
    }

    private void reloadFromDatabase() {
        try {
            ArrayList<Student> list = db.getAllStudents();
            switch (sortColumn) {
                case "id":
                    // Collections.sort(list, Student.compareById);
                    Collections.sort(list, (Student o1, Student o2) -> o1.id - o2.id);
                    break;
                case "name":
                    Collections.sort(list, Student.compareByName);
                    break;
                default: // should never happen
                    JOptionPane.showMessageDialog(this,
                            "Invalid control flow (2134234)",
                            "Internal error",
                            JOptionPane.ERROR_MESSAGE);
            }
            studentListModel.clear(); // remove any items that may be in model
            for (Student student : list) {
                studentListModel.addElement(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to fetch records: " + ex.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgAddEdit = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dlgAddEdit_lblId = new javax.swing.JLabel();
        dlgAddEdit_tfName = new javax.swing.JTextField();
        dlgAddEdit_lblImage = new javax.swing.JLabel();
        dlgAddEdit_btSave = new javax.swing.JButton();
        dlgAddEdit_btCancel = new javax.swing.JButton();
        dlgAddEdit_btRemovePicture = new javax.swing.JButton();
        fileChooserImage = new javax.swing.JFileChooser();
        fileChooserCsv = new javax.swing.JFileChooser();
        buttonGroupSortOrder = new javax.swing.ButtonGroup();
        popupMenu = new javax.swing.JPopupMenu();
        miPopupEdit = new javax.swing.JMenuItem();
        miPopupDelete = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstStudents = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miFileExportSelToCsv = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miFileExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miEditAddStudent = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        miSortById = new javax.swing.JRadioButtonMenuItem();
        miSortByName = new javax.swing.JRadioButtonMenuItem();

        dlgAddEdit.setResizable(false);

        jLabel2.setText("Id:");

        jLabel3.setText("Name:");

        jLabel4.setText("Photo (click to pick):");

        dlgAddEdit_lblId.setText("-");

        dlgAddEdit_lblImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dlgAddEdit_lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dlgAddEdit_lblImageMouseClicked(evt);
            }
        });

        dlgAddEdit_btSave.setText("Save");
        dlgAddEdit_btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgAddEdit_btSaveActionPerformed(evt);
            }
        });

        dlgAddEdit_btCancel.setText("Cancel");
        dlgAddEdit_btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgAddEdit_btCancelActionPerformed(evt);
            }
        });

        dlgAddEdit_btRemovePicture.setText("Remove picture");
        dlgAddEdit_btRemovePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgAddEdit_btRemovePictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgAddEditLayout = new javax.swing.GroupLayout(dlgAddEdit.getContentPane());
        dlgAddEdit.getContentPane().setLayout(dlgAddEditLayout);
        dlgAddEditLayout.setHorizontalGroup(
            dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dlgAddEdit_btRemovePicture))
                .addGap(32, 32, 32)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dlgAddEdit_lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dlgAddEdit_lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dlgAddEdit_tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(dlgAddEditLayout.createSequentialGroup()
                        .addComponent(dlgAddEdit_btSave, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dlgAddEdit_btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dlgAddEditLayout.setVerticalGroup(
            dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dlgAddEdit_lblId))
                .addGap(18, 18, 18)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dlgAddEdit_tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dlgAddEdit_lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(dlgAddEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dlgAddEdit_btSave)
                    .addComponent(dlgAddEdit_btCancel)
                    .addComponent(dlgAddEdit_btRemovePicture))
                .addGap(24, 24, 24))
        );

        miPopupEdit.setText("Edit");
        miPopupEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPopupEditActionPerformed(evt);
            }
        });
        popupMenu.add(miPopupEdit);

        miPopupDelete.setText("Delete");
        miPopupDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPopupDeleteActionPerformed(evt);
            }
        });
        popupMenu.add(miPopupDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(350, 200));

        jLabel1.setText("...");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_END);

        lstStudents.setModel(studentListModel);
        lstStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstStudentsMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstStudentsMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lstStudents);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        miFileExportSelToCsv.setText("Export selected to *.csv ...");
        miFileExportSelToCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFileExportSelToCsvActionPerformed(evt);
            }
        });
        jMenu1.add(miFileExportSelToCsv);
        jMenu1.add(jSeparator1);

        miFileExit.setText("Exit");
        miFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFileExitActionPerformed(evt);
            }
        });
        jMenu1.add(miFileExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        miEditAddStudent.setText("Add student");
        miEditAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditAddStudentActionPerformed(evt);
            }
        });
        jMenu2.add(miEditAddStudent);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sort");

        buttonGroupSortOrder.add(miSortById);
        miSortById.setSelected(true);
        miSortById.setText("By id");
        miSortById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSortByIdActionPerformed(evt);
            }
        });
        jMenu3.add(miSortById);

        buttonGroupSortOrder.add(miSortByName);
        miSortByName.setText("By name");
        miSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSortByNameActionPerformed(evt);
            }
        });
        jMenu3.add(miSortByName);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miEditAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditAddStudentActionPerformed
        // clean inputs
        dlgAddEdit_tfName.setText("");
        dlgAddEdit_lblId.setText("");
        dlgAddEdit_lblImage.setIcon(null);
        currentEditedStudent = null;
        currentBuffImage = null;
        // show dialog
        dlgAddEdit.pack();
        dlgAddEdit.setLocationRelativeTo(this);
        dlgAddEdit.setModal(true);
        dlgAddEdit_btSave.setText("Add student");
        dlgAddEdit.setVisible(true);
    }//GEN-LAST:event_miEditAddStudentActionPerformed

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        // FIXME: keep original width/height ratio
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private BufferedImage byteArrayToBufferedImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        return ImageIO.read(bais);
    }

    byte[] bufferedImageToByteArray(BufferedImage bi) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (!ImageIO.write(bi, "png", baos)) {
            throw new IOException("Error creating png data");
        }
        byte[] imageBytes = baos.toByteArray();
        return imageBytes;
    }

    BufferedImage currentBuffImage;

    private void dlgAddEdit_lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dlgAddEdit_lblImageMouseClicked
        if (fileChooserImage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File chosenFile = fileChooserImage.getSelectedFile();
                currentBuffImage = ImageIO.read(chosenFile);
                // FIXME: use constants or ask JLabel
                currentBuffImage = resize(currentBuffImage, 150, 150);
                Icon icon = new ImageIcon(currentBuffImage);
                dlgAddEdit_lblImage.setIcon(icon);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "File error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_dlgAddEdit_lblImageMouseClicked

    private void dlgAddEdit_btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgAddEdit_btSaveActionPerformed
        try {
            // check for errors and collect them
            ArrayList<String> errorList = new ArrayList<>();
            String name = dlgAddEdit_tfName.getText();
            if (name.length() < 2 || name.length() > 45) {
                errorList.add("Name must be 2-45 characters long");
            }
            //
            if (!errorList.isEmpty()) {
                JOptionPane.showMessageDialog(this, String.join("\n", errorList),
                        "Input error(s)", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // prepare bytes of image for insert
            byte[] imageBytes = null;
            // convert to current image to byte array
            if (currentBuffImage != null) {
                imageBytes = bufferedImageToByteArray(currentBuffImage); // IOException
            }
            if (currentEditedStudent == null) { // add
                Student student = new Student(0, name, imageBytes);
                db.addStudent(student);
            } else { // update
                currentEditedStudent.name = name;
                currentEditedStudent.image = imageBytes;
                db.updateStudent(currentEditedStudent);
            }
            reloadFromDatabase();
            dlgAddEdit.setVisible(false); // dismiss the dialog
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Database error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Data error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dlgAddEdit_btSaveActionPerformed

    private void dlgAddEdit_btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgAddEdit_btCancelActionPerformed
        dlgAddEdit.setVisible(false);
    }//GEN-LAST:event_dlgAddEdit_btCancelActionPerformed

    private void miSortByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSortByIdActionPerformed
        sortColumn = "id";
        reloadFromDatabase();
    }//GEN-LAST:event_miSortByIdActionPerformed

    private void miSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSortByNameActionPerformed
        sortColumn = "name";
        reloadFromDatabase();
    }//GEN-LAST:event_miSortByNameActionPerformed

    private void dlgAddEdit_btRemovePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgAddEdit_btRemovePictureActionPerformed
        dlgAddEdit_lblImage.setIcon(null);
        currentBuffImage = null;
    }//GEN-LAST:event_dlgAddEdit_btRemovePictureActionPerformed

    private void miFileExportSelToCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFileExportSelToCsvActionPerformed
        java.util.List<Student> list = lstStudents.getSelectedValuesList();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select some records first to export them",
                    "Data export", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (fileChooserCsv.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File chosenFile = fileChooserCsv.getSelectedFile();
                String chosenFileStr = chosenFile.getCanonicalPath(); // ex
                // Important: append file extension if none was given
                if (!chosenFileStr.matches(".*\\.[^.]{1,10}")) {
                    chosenFileStr += ".csv";
                    chosenFile = new File(chosenFileStr);
                }
                // perform the export
                try (Writer writer = new FileWriter(chosenFile); CSVWriter csvWriter = new CSVWriter(writer)) {
                    String[] headerRecord = {"Id", "Name"};
                    csvWriter.writeNext(headerRecord);
                    for (Student student : list) { // FIXME: what exception does writeNext throw ??? catch it !
                        csvWriter.writeNext(new String[]{student.id + "", student.name});
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error writing file:\n" + ex.getMessage(),
                        "File error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_miFileExportSelToCsvActionPerformed

    private void miFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFileExitActionPerformed
        dispose();
    }//GEN-LAST:event_miFileExitActionPerformed

    private void miPopupEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPopupEditActionPerformed
        editCurrSelectedStudent();
    }//GEN-LAST:event_miPopupEditActionPerformed

    private void miPopupDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPopupDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miPopupDeleteActionPerformed

    private void lstStudentsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstStudentsMouseReleased
        if (evt.isPopupTrigger()) {
            int index = lstStudents.locationToIndex(evt.getPoint());
            lstStudents.setSelectedIndex(index);
            popupMenu.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_lstStudentsMouseReleased

    private void lstStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstStudentsMouseClicked
        if (evt.getClickCount() == 2) { // double-click
            int index = lstStudents.locationToIndex(evt.getPoint());
            lstStudents.setSelectedIndex(index);
            editCurrSelectedStudent();
        } // do nothing if it's not a double-click
    }//GEN-LAST:event_lstStudentsMouseClicked

    private void editCurrSelectedStudent() {
        Student studentSelected = lstStudents.getSelectedValue();
        if (studentSelected == null) {
            return; // do nothing, no selection
        }
        try {
            // fetch full record, including the blob / image
            currentEditedStudent = db.getStudentById(studentSelected.id);
            dlgAddEdit_lblId.setText(currentEditedStudent.id + "");
            dlgAddEdit_tfName.setText(currentEditedStudent.name);
            // byte[] array to BufferedImage to Icon
            if (currentEditedStudent.image == null) {
                dlgAddEdit_lblImage.setIcon(null);
                currentBuffImage = null;
            } else {
                currentBuffImage = byteArrayToBufferedImage(currentEditedStudent.image);
                // FIXME: use constants or ask JLabel
                currentBuffImage = resize(currentBuffImage, 150, 150);
                Icon icon = new ImageIcon(currentBuffImage);
                dlgAddEdit_lblImage.setIcon(icon);
            }
            // show dialog
            dlgAddEdit.pack();
            dlgAddEdit.setLocationRelativeTo(this);
            dlgAddEdit.setModal(true);
            dlgAddEdit_btSave.setText("Update student");
            dlgAddEdit.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to fetch records: " + ex.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading image: " + ex.getMessage(),
                    "Data error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Day10students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Day10students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Day10students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Day10students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Day10students().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSortOrder;
    private javax.swing.JDialog dlgAddEdit;
    private javax.swing.JButton dlgAddEdit_btCancel;
    private javax.swing.JButton dlgAddEdit_btRemovePicture;
    private javax.swing.JButton dlgAddEdit_btSave;
    private javax.swing.JLabel dlgAddEdit_lblId;
    private javax.swing.JLabel dlgAddEdit_lblImage;
    private javax.swing.JTextField dlgAddEdit_tfName;
    private javax.swing.JFileChooser fileChooserCsv;
    private javax.swing.JFileChooser fileChooserImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JList<Student> lstStudents;
    private javax.swing.JMenuItem miEditAddStudent;
    private javax.swing.JMenuItem miFileExit;
    private javax.swing.JMenuItem miFileExportSelToCsv;
    private javax.swing.JMenuItem miPopupDelete;
    private javax.swing.JMenuItem miPopupEdit;
    private javax.swing.JRadioButtonMenuItem miSortById;
    private javax.swing.JRadioButtonMenuItem miSortByName;
    private javax.swing.JPopupMenu popupMenu;
    // End of variables declaration//GEN-END:variables
}
