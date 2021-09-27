package com.example.demo006.service;

import com.example.demo006.pojo.FileInfo;
import com.example.demo006.utils.WebResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author by zhangyuhang
 * @Classname UploadService
 * @Description TODO
 * @Date 2021/9/27 13:58
 */
@Service
public class UploadService {

    @Value(value = "${spring.imagePath}")
    private String imagePath;

    @Value(value = "${spring.excelPath}")
    private String excelPath;

    @Value(value = "${spring.jsonPath}")
    private String jsonPath;

    public WebResponse saveImage(MultipartFile multipartFile ){
        WebResponse webResponse = new WebResponse();
        try {
            // 保存图片,使用UUID防止重名
            String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            File file = new File(imagePath + fileName);
            multipartFile.transferTo(file);
            String url = "http://localhost:9999/"+fileName;
            FileInfo fileInfo = new FileInfo(fileName,url);
            webResponse.setData(fileInfo);
            webResponse.setMsg("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            webResponse.setMsg("上传失败");
            return webResponse;
        }
        webResponse.setSuccess(true);
        return webResponse;
    }

    public WebResponse saveExcel(MultipartFile multipartFile ){
        WebResponse webResponse = new WebResponse();
        try {
            // 保存图片,使用UUID防止重名
            String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            File file = new File(excelPath + fileName);
            multipartFile.transferTo(file);
            String url = "http://localhost:9999/"+fileName;
            FileInfo fileInfo = new FileInfo(fileName,url);
            webResponse.setData(fileInfo);
            webResponse.setMsg("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            webResponse.setMsg("上传失败");
            return webResponse;
        }
        webResponse.setSuccess(true);
        return webResponse;
    }

    public void saveJson(MultipartFile multipartFile ){
        try {
            // 保存图片
            File file = new File(jsonPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
