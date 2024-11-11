package algorithm;


public class A5 {
	
	// Atribute
	private String m_text;
	private String m_key;
	
	private String m_cipher;
	
	int[] re_1 = new int[19];
	int[] re_2 = new int[22];
	int[] re_3 = new int[23];
	
	String cipherBinaryText = "";
	String messageBinaryText = "";
	int[] keyBits;
	
	// Properties
	public void setText(String text) {
		m_text = text;
	}
	public String getText() {
		return m_text;
	}
	
	public void setCipher(String text) {
		m_cipher = text;
	}
	public String getCipher() {
		return m_cipher;
	}
	
	public void setKey(String key) {
		m_key = key;
	}
	public String getKey() {
		return m_key;
	}
	
	// Constructor
	public A5() {
		m_text = "";
		m_key = "";
		
		re_1 = new int[19];
		re_2 = new int[22];
		re_3 = new int[23];
	}
	
	public A5(String text, String key) {
		m_text = text;
		m_key = key;
		
	}
	
	// Methods
	
	
	public String textToBinary(String text) {
        String binary = "";
        
        for (char character : text.toCharArray()) {
        	
        		// Lấy mã ASCII của ký tự và chuyển đổi thủ công thành nhị phân
                int ascii = (int) character;
                String binaryString = "";
                
                // Chia liên tiếp để tạo ra chuỗi nhị phân
                while (ascii > 0) {
                    binaryString = (ascii % 2) + binaryString;
                    ascii = ascii / 2;
                }

                // Đảm bảo đủ 8 bit cho mỗi ký tự
                while (binaryString.length() < 8) {
                    binaryString = "0" + binaryString;
                }

                // Thêm chuỗi nhị phân của ký tự vào kết quả cuối
                binary += binaryString;
             
        }
        
        return binary;
	}
		
	
	// Kiểm tra key đã là nhị phân chưa
	private boolean isBinaryKey() {
		for (char i : m_key.toCharArray()) {
			if (i != '0' && i != '1') {
				return false;
			}
		}
		return true;
	}
	
	// Tạo key khi người dùng tạo có khóa thô (text hoặc số)
	public String generateKey() {
		
		// Khởi tạo chuỗi nhị phân ban đầu
        String binaryMessage = "";
        
        if (isBinaryKey()) {
        	return m_key;
        }
        
        binaryMessage = textToBinary(m_key);
        
        // Lấy 64 bit đầu tiên
        String key64Bit;
        if (binaryMessage.length() >= 64) {
            key64Bit = binaryMessage.substring(0, 64); // Lấy 64 bit đầu
        } else {
            // Nếu chuỗi ngắn hơn 64 bit, bổ sung '0' cho đủ 64 bit
            key64Bit = String.format("%-64s", binaryMessage).replace(' ', '0');
        }
        
        return key64Bit;
        
	}
	
	public String randomKey() {
		String key = "";

        for (int i = 0; i < 64; i++) {
            // Sinh bit ngẫu nhiên (0 hoặc 1)
            int bit = (int ) (Math.random() * 2);
            key += bit; // Cho bit vào chuỗi
        }
        
        return key;
	}

	private void addNumber(int[] arr, int newNumber) {
        // Dịch chuyển các phần tử sang phải
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        // Đặt số mới vào vị trí đầu tiên
        arr[0] = newNumber;
    }
	public void keyToRegister() {
		String key = generateKey();
		
		for (char c : key.toCharArray()) {
			
			int temp;
			if (c == '0') {
				temp = 0;
			}
			else {
				temp = 1;
			}
			
			// Register 1
			int tempRe1 = (re_1[13] ^ re_1[16] ^ re_1[17] ^ re_1[18]) ^ temp;
			addNumber(re_1, tempRe1);
			
			// Register 2
			int tempRe2 = (re_2[20] ^ re_2[21]) ^ temp;
			addNumber(re_2, tempRe2);
			
			// Register 3
			int tempRe3 = (re_3[7] ^ re_3[20] ^ re_3[21] ^ re_3[22]) ^ temp;
			addNumber(re_3, tempRe3);
			
		}
	}
	
	
	private static void leftToRight(int[] arr) {
        // Lưu phần tử cuối cùng
        int last = arr[arr.length - 1];

        // Dịch chuyển các phần tử sang phải
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        // Đặt phần tử cuối cùng lên đầu mảng
        arr[0] = last;
    }
	public void sinhKey(String text) {
		//keyToRegister();
		
		int n = textToBinary(m_text).length();
		keyBits = new int[n];
		
		
		int index = 0;
		
		int ans0 = 0;
		int ans1 = 0;
		int temp = 0;
		
		for (int i = 0; i < n; i++) {
			
			temp += (re_1[8] == 0) ? ans0++ : ans1++;
			temp += (re_2[10] == 0) ? ans0++ : ans1++; 
			temp += (re_3[10] == 0) ? ans0++ : ans1++; 
			
			// Nếu số 0 chiếm đa số thì di chuyển các thanh ghi = 0
			if (ans0 > ans1) {
				if (re_1[8] == 0) {
					leftToRight(re_1);
				}
				if (re_2[10] == 0) {
					leftToRight(re_2);
				}
				if (re_3[10] == 0) {
					leftToRight(re_3);
				}
			}
			// Nếu số 1 chiếm đa số thì di chuyển các thanh ghi = 1
			else {
				if (re_1[8] == 1) {
					leftToRight(re_1);
				}
				if (re_2[10] == 1) {
					leftToRight(re_2);
				}
				if (re_3[10] == 1) {
					leftToRight(re_3);
				}
			}
			
			keyBits[index++] = re_1[18] ^ re_2[21] ^ re_3[22];
			
		}
		
	}
	
	public String binaryToText(String binaryString) {
        StringBuilder result = new StringBuilder();
        
        int index = 0;
        
        while (true) {
        	
        	String binary = binaryString.substring(index, index+=8);
        	
            int charCode = Integer.parseInt(binary, 2); // Chuyển từ nhị phân sang số nguyên
//            if ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122) || charCode == 32) { // Kiểm tra có nằm trong khoảng chữ cái in hoa A-Z không
//                char character = (char) charCode; // Chuyển từ số nguyên sang ký tự
//                result.append(character);
//            } else {
//                return "Mã nhị phân không hợp lệ: " + binary;
//            }
            char character = (char) charCode; // Chuyển từ số nguyên sang ký tự
            result.append(character);
            
            if (index+8 > (binaryString.length() + 1)) {
            	break;
            }
        }

        return result.toString(); // Trả về chuỗi kết quả
	}
	
	
	public String Encypt() {
		cipherBinaryText = "";
		String binaryText = textToBinary(m_text);
		int n = binaryText.length();
		
		for (int i = 0; i < n; i++) {
			int temp;
			if (binaryText.charAt(i) == '0') {
				temp = 0;
			}
			else {
				temp = 1;
			}
			cipherBinaryText +=  temp ^ keyBits[i];
		}
		return binaryToText(cipherBinaryText);
	}
	
	
	public String Decrypt() {
		cipherBinaryText = "";
		String binaryText = textToBinary(m_text);
		int n = binaryText.length();
		
		for (int i = 0; i < n; i++) {
			int temp;
			if (binaryText.charAt(i) == '0') {
				temp = 0;
			}
			else {
				temp = 1;
			}
			messageBinaryText +=  temp ^ keyBits[i];
		}
		return binaryToText(messageBinaryText);
		
	}
}
