package com.example.demo006.controller;

import com.example.demo006.service.UploadService;
import com.example.demo006.utils.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author by zhangyuhang
 * @Classname UploadController
 * @Description TODO
 * @Date 2021/9/27 13:39
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/images")
    public WebResponse uploadImage(MultipartFile image){
        if (image == null){
            return WebResponse.ofError("上传文件为空");
        }
        return uploadService.saveImage(image);
    }

    @RequestMapping("/excel")
    public WebResponse uploadExcel(MultipartFile excel){
        if (excel == null){
            return WebResponse.ofError("上传文件为空");
        }
        return uploadService.saveExcel(excel);
    }

}
