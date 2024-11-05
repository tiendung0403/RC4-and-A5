package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame {
    private JPanel panelContainer;
    private CardLayout cardLayout;
    private JButton rc4Btn;

    public CardLayout getLayout(){
        return this.cardLayout;
    }
    public JPanel getPanelContainer(){
        return this.panelContainer;
    }
    public MainScreen() {
        setTitle("Đồ án Lập trình Mã hóa cổ điển");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();  // Khởi tạo các thành phần
    }
    private void changeScreen(String screen, String title, Boolean haveNote, String note) {
    	// Chuyển sang màn hình Caesar
        cardLayout.show(panelContainer, screen);
    //set Title với tên Caesar Cipher
        setTitle(title);
        setSize(800,600);
        setResizable(false);
        
        if (haveNote == true) {
        	JOptionPane.showMessageDialog(this, note);
        }
    }


    public JPanel initComponents() {
        JPanel mainPanel = new JPanel(null);

        // Nút RC4
        rc4Btn = new JButton("RC4");
        rc4Btn.setBounds(400, 190, 80, 25);  // Vị trí nút RC4
        rc4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rc4BtnActionPerformed(evt);
            }
        });

        // Nút Exit
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(400, 250, 80, 25); // Vị trí nút Exit
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        mainPanel.add(rc4Btn);
        mainPanel.add(btnExit);

        return mainPanel;
    }

    private void rc4BtnActionPerformed(ActionEvent evt) {
        // Chuyển sang màn hình RC4
        changeScreen("RC4Screen", "RC4", true, "Không được nhập các kí tự có giấu vào Plaintext VD: á à ạ ã.");
    }

    private void btnExitActionPerformed(ActionEvent evt) {
        // Thoát chương trình
        System.exit(0);
    }

    public void start() {
        // Tạo CardLayout và `panelContainer`
        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);

        // Tạo các màn hình và thêm vào `panelContainer`
        JPanel frmMainScreen = this.initComponents();
        JPanel frmRC4Screen = new FormRC4().initComponents();

        // Thêm các màn hình vào `panelContainer` với tên
        panelContainer.add(frmMainScreen, "MainScreen");
        panelContainer.add(frmRC4Screen, "RC4Screen");

        // Thiết lập màn hình chính hiển thị đầu tiên
        cardLayout.show(panelContainer, "MainScreen");

        // Thêm `panelContainer` vào JFrame
        add(panelContainer);
        setVisible(true);

    }
}
