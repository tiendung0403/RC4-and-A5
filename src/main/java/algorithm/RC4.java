package algorithm;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RC4 {
    private static final int MOD = 256; // Hằng số MOD cho RC4, mảng S sẽ có độ dài 256 phần tử

    // Hàm hoán đổi giá trị của 2 phần tử trong mảng S tại vị trí i và j
    public static int[] swap(int[] s, int i, int j) {
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        return s; 
    }

    // Khởi tạo mảng S dựa trên thuật toán KSA với khóa đầu vào
    public static int[] KSA(String key) {
        int[] S = new int[MOD]; // Mảng S có độ dài 256
        int keyLength = key.length(); // Độ dài của khóa

        // Khởi tạo mảng S với các giá trị từ 0 đến 255
        for (int i = 0; i < MOD; i++) {
            S[i] = i;
        }

        int j = 0;
        // Hoán đổi các phần tử trong S dựa trên khóa
        for (int i = 0; i < MOD; i++) {
            j = (j + S[i] + key.charAt(i % keyLength)) % MOD; // Tính j
            S = swap(S, i, j); //hoán đổi
        }
        return S; // Trả về mảng S 
    }

    // Sinh chuỗi khóa (keystream) dùng thuật toán PRGA dựa trên mảng S đã được tạo từ KSA

    public static List<Integer> PRGA(int[] S, int textLength) {
        List<Integer> keystream = new ArrayList<>(); // Tạo Danh sách chứa keystream
        int i = 0, j = 0;

        // Sinh keystream với độ dài bằng độ dài của văn bản
        for (int k = 0; k < textLength; k++) {
            i = (i + 1) % MOD; // Tăng i theo mod 256
            j = (j + S[i]) % MOD; // Tăng j theo mod 256

            S = swap(S, i, j); // Hoán đổi giá trị trong S 

            int K = S[(S[i] + S[j]) % MOD]; // Lấy giá trị keystream K từ mảng S
            keystream.add(K); // Thêm K vào keystream
        }
        return keystream; 
    }

    //hàm Mã hóa văn bản bằng RC4
    public static String encrypt(String key, String plaintext) {

        int[] S = KSA(key); // Tạo mảng S bằng KSA với khóa đầu vào
        List<Integer> keystream = PRGA(S, plaintext.length()); // Sinh keystream có độ dài bằng plaintext

        StringBuilder ciphertext = new StringBuilder(); // Chuỗi chứa mã hóa

        // XOR từng ký tự của plaintext với ký tự tương ứng trong keystream

        for (int i = 0; i < plaintext.length(); i++) {
            int plaintextChar = plaintext.charAt(i); // Ký tự tại vị trí i trong plaintext
            int keystreamChar = keystream.get(i); // Giá trị keystream tương ứng

            int cipherChar = plaintextChar ^ keystreamChar; // XOR giữa ký tự plaintext và keystream
            ///
            /// %X: Chỉ định rằng cipherChar sẽ được chuyển đổi thành chuỗi dạng hexadecimal (sử dụng chữ in hoa cho các ký tự từ A đến F).
            // 02: Xác định rằng chuỗi hex phải có độ dài tối thiểu là 2 ký tự. 
            /// Nếu giá trị của cipherChar có độ dài ngắn hơn 2 ký tự, nó sẽ được thêm số 0 ở phía trước để đủ 2 ký tự. 
            ///Ví dụ, nếu giá trị là A, kết quả sẽ là 0A
            ///
            ciphertext.append(String.format("%02X", cipherChar)); // Định dạng thành hex và thêm vào ciphertext
        }
        return ciphertext.toString(); // Trả về chuỗi mã hóa
    }

    // Giải mã văn bản bằng RC4
    public static String decrypt(String key, String ciphertext) {

        int[] S = KSA(key); // Tạo mảng S bằng KSA với khóa đầu vào
        List<Integer> keystream = PRGA(S, ciphertext.length() / 2); // Sinh keystream cho ciphertext

        byte[] decodedBytes = new byte[ciphertext.length() / 2]; // Mảng byte để lưu giá trị sau giải mã
        for (int i = 0; i < decodedBytes.length; i++) {
            ///
            /// chuyển đổi hai ký tự hex trong ciphertext thành một số nguyên giúp tái tạo lại dữ liệu gốc
            /// Lấy hai ký tự từ ciphertext bắt đầu từ vị trí i * 2 và kết thúc tại (i * 2) + 2
            /// i * 2 giúp duyệt qua ciphertext theo cặp ký tự (do mỗi byte mã hóa được lưu dưới dạng 2 ký tự hex)
            /// Tham số 16 trong Integer.parseInt cho bieet chuỗi đầu vào đang ở hệ cơ số 16 
            ///
            int cipherChar = Integer.parseInt(ciphertext.substring(i * 2, (i * 2) + 2), 16); // Lấy từng cặp hex
            int keystreamChar = keystream.get(i); // Giá trị keystream tương ứng
            decodedBytes[i] = (byte) (cipherChar ^ keystreamChar); // XOR giữa cipherChar và keystream
        }
        return new String(decodedBytes, StandardCharsets.UTF_8); // Chuyển mảng byte thành chuỗi UTF-8 và trả về
    }

    
    // public static void main(String[] args) {
    //     String key = "Tấn công lúc bình minh"; // Khóa dùng để mã hóa và giải mã
    //     String plaintext = "attack tomorow"; // Chuỗi gốc cần mã hóa

    //     // Mã hóa
    //     String ciphertext = encrypt(key, plaintext);
    //     System.out.println("Plaintext: " + plaintext);
    //     System.out.println("Ciphertext: " + ciphertext);

    //     // Giải mã
    //     String decryptedText = decrypt(key, ciphertext);
    //     System.out.println("Decrypted text: " + decryptedText);
    // }
}
