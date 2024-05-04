package com.server.hotel.controller;

import com.server.hotel.common.Result;
import com.server.hotel.entry.HotelImages;
import com.server.hotel.service.impl.HotelImagesServiceImpl;
import com.server.hotel.utils.ServletUtils;
import com.server.hotel.utils.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("hotel/image")
public class HotelImageController {
    @Autowired
    HotelImagesServiceImpl hotelImagesService;


    @PostMapping("upload")
    public Result<?> imageUpload(@RequestParam("imageList") List<MultipartFile> fileList) throws IOException {
        for (MultipartFile file : fileList) {
            String originName = file.getOriginalFilename();
            if (StringUtils.isBlank(originName)) {
                return Result.fail("文件为空");
            }
            if (!(originName.endsWith(".png") || originName.endsWith(".jpg")||originName.endsWith(".PNG")||originName.endsWith(".jpeg")||originName.endsWith(".JPG"))) {
                return Result.fail("图片格式不正确");
            }

            String newName = UUID.randomUUID().toString() + originName.substring(originName.lastIndexOf("."));
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            if (UploadUtils.saveFile(file, newName, applicationHome)) {
                hotelImagesService.upload(newName);
            } else {
                return Result.fail("上传失败");
            }


        }

        return Result.success("图片上传成功", null);

    }

    @GetMapping("list")
    public Result<?> list() {
        List<HotelImages> hotelImages = hotelImagesService.getList();
        for (HotelImages item : hotelImages) {
            item.setName(item.getUrl());
            item.setUrl(ServletUtils.getImageUrl(item.getUrl()));

        }
        return Result.success("查询成功", hotelImages);
    }

    @PostMapping("/delete")
    public Result<?> deleteFile(@RequestBody Map<String, Object> request) {
        String fileName = (String) request.get("name");


        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        boolean res = UploadUtils.removeFile(fileName, applicationHome);
        System.out.println(res);
        if (res) {
            if (hotelImagesService.deleteImage(fileName)) {
                return Result.success("删除成功", null);
            }
        }

        return Result.fail("删除失败");
    }


}