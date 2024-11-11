package gui;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algorithm.A5;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FormA5 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	JTextArea txtPlainText = new JTextArea();
	JTextArea txtCipher = new JTextArea();
	JButton btnNewButton = new JButton("Random Key");
	JTextArea txtKeyInput = new JTextArea();
	JButton btnGenerateKey = new JButton("Generate Key");
	JTextArea txtKey = new JTextArea();
	
	JButton btnEncrypt;
	JButton btnDecrypt;
	
	private A5 a5 = new A5();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormA5 frame = new FormA5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormA5() {
		
		setTitle("Form A5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null); 
		setContentPane(contentPane);
		
		txtPlainText = new JTextArea();
		txtPlainText.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtPlainText.setBounds(20, 49, 333, 135);
		contentPane.add(txtPlainText);
		
		JLabel lblNewLabel = new JLabel("Plain Text");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 21, 79, 27);
		contentPane.add(lblNewLabel);
		
		txtCipher = new JTextArea();
		txtCipher.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtCipher.setBounds(20, 259, 333, 135);
		contentPane.add(txtCipher);
		
		JLabel lblCipherText = new JLabel("Cipher Text");
		lblCipherText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCipherText.setBounds(20, 229, 94, 27);
		contentPane.add(lblCipherText);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKey.setBounds(416, 176, 79, 27);
		contentPane.add(lblKey);
		
		btnNewButton = new JButton("Random Key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = a5.randomKey();
				txtKey.setText(key);
				a5.setKey(key);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(497, 258, 118, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNhpKey = new JLabel("Nhập key");
		lblNhpKey.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhpKey.setBounds(416, 54, 79, 27);
		contentPane.add(lblNhpKey);
		
		txtKeyInput = new JTextArea();
		txtKeyInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtKeyInput.setBounds(417, 81, 198, 52);
		contentPane.add(txtKeyInput);
		
		txtKey = new JTextArea();
		txtKey.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtKey.setBounds(417, 204, 198, 52);
		txtKey.setLineWrap(true); // Bật tính năng tự động xuống dòng
		txtKey.setWrapStyleWord(true); // Xuống dòng tại ranh giới từ
		contentPane.add(txtKey);

		
		btnGenerateKey = new JButton("Generate Key");
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtKeyInput.getText().isBlank()) {
					a5.setKey(txtKeyInput.getText());
					txtKey.setText(a5.generateKey());
				}
				else {
					JOptionPane.showMessageDialog(null, "Chưa nhập gì cả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnGenerateKey.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerateKey.setBounds(497, 140, 118, 27);
		contentPane.add(btnGenerateKey);
		
		btnEncrypt = new JButton("Mã hóa");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (txtPlainText.getText().isBlank()) {
		            JOptionPane.showMessageDialog(null, "Chưa nhập gì ở plain text", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else if (txtKeyInput.getText().isBlank() && txtKey.getText().isBlank()) {
		            JOptionPane.showMessageDialog(null, "Nhập key vào, hoặc cũng có thể bấm random key", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		        	
		        	String plaintext = txtPlainText.getText();
	                String key = txtKey.getText();

	                A5 temp = new A5();
	                temp.setText(plaintext);
	                temp.setKey(key);
	                temp.keyToRegister();
	                temp.sinhKey(temp.getText());
	                String encryptedText = temp.Encypt();

	                txtCipher.setText(encryptedText);
		        }
		    }
		});
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEncrypt.setBounds(416, 319, 94, 57);
		contentPane.add(btnEncrypt);
		
		btnDecrypt = new JButton("Giải mã");
		btnDecrypt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String cipherText = txtCipher.getText();
                String key = txtKey.getText();

                if (cipherText.isEmpty() || key.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Chưa nhập cipher text hoặc key", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                A5 temp = new A5();
                temp.setText(cipherText);
                temp.setKey(key);
                temp.keyToRegister();
                temp.sinhKey(temp.getCipher());
                String decryptedText = temp.Decrypt();

                txtPlainText.setText(decryptedText);
		    }
		});
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDecrypt.setBounds(521, 319, 94, 57);
		contentPane.add(btnDecrypt);
		
		
		
	}
	
}
