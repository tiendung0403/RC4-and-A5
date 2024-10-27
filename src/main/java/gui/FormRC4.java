package gui;

import javax.swing.*;

import algorithm.RC4;

import java.awt.event.*;
public class FormRC4 extends JFrame {
    private JPanel rc4JPanel;

    private JButton btnDecrypt; // Khai báo nút btnDecrypt.
    private JButton btnEncrypt; // Khai báo nút btnEncrypt.
    private JButton btnExit; // Khai báo nút btnExit.

    private JLabel jLabelCipher; // Khai báo nhãn jLabelCipher.
    private JLabel jLabelDecrypt; // Khai báo nhãn jLabelDecrypt.
    private JLabel jLabelPlain; // Khai báo nhãn jLabelPlain.
    private JLabel jLabelKey; // Khai báo nhãn jLabelKey.
    
    private JScrollPane jScrollDecrypt; // Khai báo JScrollPane cho txtDecrypt.
    private JScrollPane jScrollPlain; // Khai báo JScrollPane cho txtPlain.
    private JScrollPane jScrollCipher; // Khai báo JScrollPane cho txtCipher.
    private JScrollPane jScrollKey; // Khai báo JScrollPane cho txtKey.

    private JTextArea txtKey; // Khai báo JTextArea txtKey.
    private JTextArea txtCipher; // Khai báo JTextArea txtCipher.
    private JTextArea txtDecrypt; // Khai báo JTextArea txtDecrypt.
    private JTextArea txtPlain; // Khai báo JTextArea txtPlain.

    public FormRC4() {
        initComponents();
        JOptionPane.showMessageDialog(this, "Không được nhập các kí tự có giấu vào Plaintext VD: á à ạ ã.", "Chú ý", JOptionPane.ERROR_MESSAGE);

    }
    public JPanel initComponents() {

        this.rc4JPanel = new JPanel(null);

        btnEncrypt = new JButton("Encrypt"); // Tạo nút btnEncrypt dùng cho chức năng mã hóa.
        btnEncrypt.setText("Encrypt"); // Đặt tên cho nút btnEncrypt là "Encrypt".
        btnEncrypt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                btnEncryptActionPerformed(evt);
            }
        });

        btnDecrypt = new JButton("Decrypt"); // Tạo nút btnDecrypt dùng cho chức năng giải mã.
        btnDecrypt.addActionListener(new ActionListener() { // Thêm ActionListener cho nút để xử lý sự kiện khi nút được nhấn.
            public void actionPerformed(ActionEvent evt) {
                btnDecryptActionPerformed(evt); // Gọi phương thức btnDecryptActionPerformed khi nhấn nút.
            }
        });

        btnExit = new JButton("Exit"); // Tạo nút btnExit để đóng ứng dụng.
        btnExit.setText("Exit"); // Đặt tên cho nút btnExit là "Exit".
        btnExit.addActionListener(e -> System.exit(0)); //Thêm sử lí sự kiện cho nút Exit 

        jLabelPlain = new JLabel("Plain text"); // Tạo nhãn jLabelPlain để mô tả trường "Plain text" cho văn bản gốc.
        txtPlain = new JTextArea(); // Tạo JTextArea txtPlain để nhập văn bản gốc trước khi mã hóa.
        txtPlain.setColumns(20); // Đặt số cột mặc định cho JTextArea txtPlain.
        txtPlain.setRows(5); // Đặt số hàng mặc định cho JTextArea txtPlain.
        jScrollPlain = new JScrollPane(txtPlain); // Cuộn cho JTextArea txtPlain.

        jLabelCipher = new JLabel("Cipher text"); // Tạo nhãn jLabelCipher để mô tả trường "Cipher text" cho văn bản đã mã hóa.
        txtCipher = new JTextArea(); // Tạo JTextArea txtCipher để hiện thị văn bản sau khi mã hóa.
        txtCipher.setColumns(20); // Đặt số cột mặc định cho JTextArea txtCipher.
        txtCipher.setRows(5); // Đặt số hàng mặc định cho JTextArea txtCipher.
        jScrollCipher = new JScrollPane(txtCipher); // Cuộn cho JTextArea txtCipher.

        jLabelDecrypt = new JLabel("Decrypt text"); // Tạo nhãn jLabelDecrypt để mô tả trường "Decrypt text".
        txtDecrypt = new JTextArea(); // Tạo JTextArea txtDecrypt để hiện thị văn bản sau khi giải mã.
        txtDecrypt.setColumns(20); // Đặt số cột mặc định cho JTextArea txtDecrypt.
        txtDecrypt.setRows(5); // Đặt số hàng mặc định cho JTextArea txtDecrypt.
        jScrollDecrypt = new JScrollPane(txtDecrypt); // Cuộn cho JTextArea txtDecrypt để hiện thị văn bản được giải mã.

        jLabelKey = new JLabel("Key text"); // Tạo nhãn jLabelKey để mô tả trường "Key text".
        txtKey = new JTextArea(); // Tạo JTextArea txtKey để nhập khóa mã hóa/giải mã.
        txtKey.setColumns(20); // Đặt số cột mặc định cho JTextArea txtKey.
        txtKey.setRows(5); // Đặt số hàng mặc định cho JTextArea txtKey.
        jScrollKey = new JScrollPane(txtKey); // Cuộn cho JTextArea txtKey.

        // Đặt tọa độ cho từng thành phần
        jLabelPlain.setBounds(70, 11, 55, 50); // Tọa độ nhãn Plain text
        jScrollPlain.setBounds(70, 50, 320, 84); // Tọa độ JTextArea Plain text
        btnEncrypt.setBounds(400, 50, 80, 25); // Tọa độ nút Encrypt
        jLabelCipher.setBounds(70, 150, 70, 50); // Tọa độ nhãn Cipher text
        jScrollCipher.setBounds(70, 190, 320, 84); // Tọa độ JTextArea Cipher text
        btnDecrypt.setBounds(400, 190, 80, 25); // Tọa độ nút Decrypt
        jLabelDecrypt.setBounds(70, 290, 70, 50); // Tọa độ nhãn Decrypt text
        jScrollDecrypt.setBounds(70, 330, 320, 84); // Tọa độ JTextArea Decrypt text
        jLabelKey.setBounds(550, 100, 55, 50); // Tọa độ nhãn Key text
        jScrollKey.setBounds(550, 140, 220, 100); // Tọa độ JTextArea Key text
        btnExit.setBounds(550, 300, 80, 25); // Tọa độ nút Exit

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Thiết lập để thoát ứng dụng khi đóng cửa sổ JFrame.

        // Thêm các thành phần vào Jpanel
        rc4JPanel.add(btnExit);
        rc4JPanel.add(jLabelPlain);
        rc4JPanel.add(jScrollPlain);
        rc4JPanel.add(btnEncrypt);
        rc4JPanel.add(jLabelCipher);
        rc4JPanel.add(jScrollCipher);
        rc4JPanel.add(btnDecrypt);
        rc4JPanel.add(jLabelDecrypt);
        rc4JPanel.add(jScrollDecrypt);
        rc4JPanel.add(jLabelKey);
        rc4JPanel.add(jScrollKey);


        
        // Thêm rc4JPanel vào JFrame
        add(rc4JPanel);

        // Thiết lập JFrame
        setTitle("RC4");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        return rc4JPanel;
    } 
    private void btnDecryptActionPerformed(ActionEvent evt) {         
        if (txtKey.getText().isEmpty() || txtCipher.getText().isEmpty()) 
            JOptionPane.showMessageDialog(this, "Không được để trống key và Cipher.", "Error", JOptionPane.ERROR_MESSAGE);
        else txtDecrypt.setText(RC4.decrypt(txtKey.getText(), txtCipher.getText())); 
        // Gọi phương thức giải mã RC4 và hiện thị kết quả trong txtDecrypt.
    }
    private void btnEncryptActionPerformed(ActionEvent evt){
        if (txtKey.getText().isEmpty() || txtPlain.getText().isEmpty()) 
            JOptionPane.showMessageDialog(this, "Không được để trống key và Plaintext.", "Error", JOptionPane.ERROR_MESSAGE);
        else txtCipher.setText(RC4.encrypt(txtKey.getText(), txtPlain.getText()));
    }   
    private static boolean errorChecking(String strings){

        return false;
    }

}
