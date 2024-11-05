package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WelcomeForm extends JFrame {

    public WelcomeForm() {
        initComponents(); // Khởi tạo các thành phần của giao diện
    }

    private JPanel initComponents() {

        // Khởi tạo các JLabel để hiển thị văn bản trên giao diện
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        btnStart = new JButton(); // Khởi tạo nút Start
        welcomfrm = new JPanel(null); // Khởi tạo panel chính để chứa các thành phần giao diện

        // Định dạng jLabel1 và thiết lập văn bản cho thông tin đề tài
        jLabel1.setFont(new Font("Segoe UI", 0, 24)); // Đặt font chữ và kích cỡ
        jLabel1.setForeground(new Color(255, 51, 51)); // Đặt màu chữ
        jLabel1.setText("Đồ án lập trình mã hóa cổ điển"); // Văn bản chính

        // Định dạng và thiết lập văn bản cho môn học
        jLabel2.setFont(new Font("Segoe UI", 0, 18)); 
        jLabel2.setText("Môn: Mã hóa ứng dụng");

        // Định dạng và thiết lập văn bản cho tên thầy giáo
        jLabel3.setFont(new Font("Segoe UI", 0, 18));
        jLabel3.setText("Thầy: Đoàn Trình Dục");

        // Thông báo cho phần danh sách thành viên

        // Định dạng và thiết lập thông tin thành viên 1
        jLabel5.setFont(new Font("Segoe UI", 0, 14));
        jLabel5.setText("Họ tên: Nguyễn Đình Phương ");

        jLabel6.setFont(new Font("Segoe UI", 0, 14));
        jLabel6.setText("MSSV: DH52201277");

        jLabel7.setFont(new Font("Segoe UI", 0, 14));
        jLabel7.setText("Lớp: D22_TH14");

        // Định dạng và thiết lập thông tin thành viên 2
        jLabel8.setFont(new Font("Segoe UI", 0, 14));
        jLabel8.setText("Họ tên: Đặng Tiến Dũng");

        jLabel9.setFont(new Font("Segoe UI", 0, 14));
        jLabel9.setText("MSSV: DH52200530 ");

        jLabel10.setFont(new Font("Segoe UI", 0, 14));
        jLabel10.setText("Lớp: D22_TH15 ");

        // Thiết lập cho nút Start và thêm lắng nghe sự kiện khi nhấn
        btnStart.setText("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStartActionPerformed(evt); // Xử lý sự kiện khi nhấn nút Start
            }
        });

        // Sử dụng GroupLayout để tổ chức bố cục các thành phần
        GroupLayout layout = new GroupLayout(welcomfrm);
        welcomfrm.setLayout(layout);

        // Thiết lập bố cục ngang
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1) // Đặt tiêu đề ở giữa màn hình
                        .addGap(134, 134, 134))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2) // Môn học
                            .addComponent(jLabel3)) // Thầy giáo
                        .addGap(200, 200, 200))))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(120, 120, 120))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Thiết lập bố cục dọc
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        // Thêm các thành phần vào panel chính
        welcomfrm.add(jLabel1);
        welcomfrm.add(jLabel2);
        welcomfrm.add(jLabel3);
        welcomfrm.add(jLabel5);
        welcomfrm.add(jLabel6);
        welcomfrm.add(jLabel7);
        welcomfrm.add(jLabel8);
        welcomfrm.add(jLabel9);
        welcomfrm.add(jLabel10);
        welcomfrm.add(btnStart);


        // Thiết lập các thuộc tính của JFrame
        add(welcomfrm); // Thêm JPanel vào JFrame
        setTitle("Giới Thiệu"); // Đặt tiêu đề cho JFrame
        setSize(597, 494); // Đặt kích thước JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thiết lập khi đóng JFrame
        setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
        setVisible(true); // Hiển thị JFrame
        return welcomfrm; // Trả về JPanel để xử lý thêm nếu cần
    }                        

    // Phương thức xử lý sự kiện khi nhấn nút Start
    private void btnStartActionPerformed(ActionEvent evt) {                                         
        MainScreen mainscr = new MainScreen();
        mainscr.start();
        this.setVisible(false);
    }                                        


    // Khai báo các biến thành phần để tránh sửa đổi
    private JButton btnStart;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel welcomfrm;

    // Kết thúc khai báo các biến thành phần
}
