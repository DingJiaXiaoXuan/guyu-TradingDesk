package com.guyu.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UtilController {

    /**
     * 保存图片（头像）
     * @param file
     * @return 图片新路径
     * @throws IOException
     */
    @RequestMapping("/UserImage")
    public String UserImage(@RequestParam("file") MultipartFile file) throws IOException {
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\user_imges"; // 保存上传的路径
        String oldFileName = file.getOriginalFilename();    // 得到原文件名
        String suffix = FilenameUtils.getExtension(oldFileName);    // 得到原文件的后缀名
        String NewFileName = String.valueOf(System.currentTimeMillis())+'.'+suffix; // 上传成功后的新文件名
        File File =new File(path,NewFileName);// 构建上传路径的文件名
        if (!File.exists()) {   // 判断该文件是否存在
            File.mkdirs();   // 不存在则创建
        }
        file.transferTo(File);  // 上传图片
        return NewFileName;
    }

    /**
     * 保存图片（店铺）
     * @param file
     * @return 图片新路径
     * @throws IOException
     */
    @RequestMapping("/StoreImage")
    public String StoreImage(@RequestParam("file") MultipartFile file) throws IOException {
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\store_imges"; // 保存上传的路径
        String oldFileName = file.getOriginalFilename();    // 得到原文件名
        String suffix = FilenameUtils.getExtension(oldFileName);    // 得到原文件的后缀名
        String NewFileName = String.valueOf(System.currentTimeMillis())+'.'+suffix; // 上传成功后的新文件名
        File File =new File(path,NewFileName);// 构建上传路径的文件名
        if (!File.exists()) {   // 判断该文件是否存在
            File.mkdirs();   // 不存在则创建
        }
        file.transferTo(File);  // 上传图片
        return NewFileName;
    }

    /**
     * 保存图片（商品）
     * @param file
     * @return 图片新路径
     * @throws IOException
     */
    @RequestMapping("/ProImage")
    public String ProImage(@RequestParam("file") MultipartFile file) throws IOException {
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\pro_imges"; // 保存上传的路径
        String oldFileName = file.getOriginalFilename();    // 得到原文件名
        String suffix = FilenameUtils.getExtension(oldFileName);    // 得到原文件的后缀名
        String NewFileName = String.valueOf(System.currentTimeMillis())+'.'+suffix; // 上传成功后的新文件名
        File File =new File(path,NewFileName);// 构建上传路径的文件名
        if (!File.exists()) {   // 判断该文件是否存在
            File.mkdirs();   // 不存在则创建
        }
        file.transferTo(File);  // 上传图片
        return NewFileName;
    }

    /**
     * 删除图片（用户头像）
     * @param image
     * @return
     */
    @PostMapping("/DelUserImage")
    public Boolean DelUserImage(@RequestBody String image){
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\user_imges\\"+image;
        return FileSystemUtils.deleteRecursively(new File(path));
    }

    /**
     * 删除图片（店铺头像）
     * @param image
     * @return
     */
    @PostMapping("/DelStoreImage")
    public Boolean DelStoreImage(@RequestBody String image){
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\store_imges\\"+image;
        return FileSystemUtils.deleteRecursively(new File(path));
    }
    /**
     * 删除图片（商品头像）
     * @param image
     * @return
     */
    @PostMapping("/DelProImage")
    public Boolean DelProImage(@RequestBody String image){
        String path = "F:\\JavaProject\\guyu\\vue\\src\\assets\\images\\pro_imges\\"+image;
        return FileSystemUtils.deleteRecursively(new File(path));
    }

}
