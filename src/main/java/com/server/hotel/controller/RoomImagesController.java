package com.server.hotel.controller;


import com.server.hotel.common.Result;
import com.server.hotel.entry.RoomImages;
import com.server.hotel.service.impl.RoomImagesServiceImpl;
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
@RequestMapping("room/image")
public class RoomImagesController {
    @Autowired
    RoomImagesServiceImpl roomImagesService;


    @PostMapping("upload")
    public Result<?> imageUpload(@RequestParam("imageList") List<MultipartFile> fileList, @RequestParam("roomId") String roomId) throws IOException {
        for (MultipartFile file : fileList) {
            String originName = file.getOriginalFilename();
            if (StringUtils.isBlank(originName)) {
                return Result.fail("文件为空");
            }
            if (!(originName.endsWith(".png") || originName.endsWith(".jpg"))) {
                return Result.fail("图片格式不正确");
            }

            String newName = UUID.randomUUID().toString() + originName.substring(originName.lastIndexOf("."));
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            if (UploadUtils.saveFile(file, newName, applicationHome)) {
                roomImagesService.upload(newName, roomId);
            } else {
                return Result.fail("上传失败");
            }


        }

        return Result.success("图片上传成功", null);

    }

    @GetMapping("list")
    public Result<?> list(@RequestParam("roomId") String roomId) {
        List<RoomImages> RoomImages = roomImagesService.getList(roomId);
        for (RoomImages item : RoomImages) {
            item.setName(item.getImageUrl());
            item.setUrl(ServletUtils.getImageUrl(item.getImageUrl()));

        }
        return Result.success("查询成功", RoomImages);
    }

    @PostMapping("/delete")
    public Result<?> deleteFile(@RequestBody Map<String, Object> request) {
        String fileName = (String) request.get("name");


        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        boolean res = UploadUtils.removeFile(fileName, applicationHome);
        System.out.println(res);
        if (res) {
            if (roomImagesService.deleteImage(fileName)) {
                return Result.success("删除成功", null);
            }
        }

        return Result.fail("删除失败");
    }
}