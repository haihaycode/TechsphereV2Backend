package com.Techsphere.TechsphereV2Backend.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtils {
    public static String generateImage() {
        // Lấy thời gian hiện tại
        Date now = new Date();
        // Định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(now);

        // Tạo một chuỗi số ngẫu nhiên
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // Số ngẫu nhiên từ 1000 đến 9999

        // Kết hợp thời gian và số ngẫu nhiên để tạo mã đơn hàng
        String imgName = "IM" + timestamp + randomNumber;

        return imgName;
    }
}
