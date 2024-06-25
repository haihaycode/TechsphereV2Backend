package com.Techsphere.TechsphereV2Backend.Service.Image;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {
    private final String UPLOAD_DIR = "uploads/products/";
    private final String UPLOAD_DIR_profile = "uploads/profile/";


    //lưu hình đường dẫn sản phẩm
    public void storeImage(MultipartFile file,String image) throws IOException {
        image=image+".png";
        if (file.isEmpty()) {
            System.out.println("file is empty");
           return;
        }

        String filename = file.getOriginalFilename();
        if (filename == null) {
            System.out.println("không có hình");
        }

        // Create the directory if it does not exist
        File uploadPath = new File(UPLOAD_DIR);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Save the file to the upload directory
        Path path = Paths.get(UPLOAD_DIR + image);
        Files.write(path, file.getBytes());
    }
//  xóa hình đường dẫn sản phẩm
    public void deleteImage(String image) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + image);
        if (Files.exists(path)) {
            Files.delete(path);
        } else {
            System.out.println("file khong tồn tại");
        }
    }
    //lưu hình profile
    public void storeImageProfile(MultipartFile file,String image) throws IOException {
        image=image+".png";
        if (file.isEmpty()) {
            System.out.println("file is empty");
            return;
        }

        String filename = file.getOriginalFilename();
        if (filename == null) {
            System.out.println("không có hình");
        }

        // Create the directory if it does not exist
        File uploadPath = new File(UPLOAD_DIR_profile);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Save the file to the upload directory
        Path path = Paths.get(UPLOAD_DIR_profile + image);
        Files.write(path, file.getBytes());
    }
    //xóa hình profile
    public void deleteImageProfile(String image) throws IOException {
        Path path = Paths.get(UPLOAD_DIR_profile + image);
        if (Files.exists(path)) {
            Files.delete(path);
        } else {
            System.out.println("file khong tồn tại");
        }
    }

//get hình profile
    public byte[] loadImage(String imageName) throws IOException {
        Path imagePath = Paths.get(ResourceUtils.getFile(UPLOAD_DIR_profile + imageName).toURI());
        return Files.readAllBytes(imagePath);
    }
}
