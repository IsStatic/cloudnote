package com.lmx.note.controller;

import com.lmx.common.entitys.*;
import com.lmx.note.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 * @author liang
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private INoteService noteService;

    @GetMapping("/hello")
    public RespBean hello(){
        return RespBean.success("hello");
    }

    @PostMapping("/create")
    public RespBean create(@RequestBody @Valid NoteCreateEntity noteCreate){
        if (null == noteCreate) return RespBean.error(RespBeanEnum.HDFS_CREATE_NULL_ERROR);
        return noteService.create(noteCreate);
    }

//    @PostMapping("/upload")
//    public RespBean upLoad(@RequestBody @Valid UpLoadEntity entity){
//        System.out.println("上传开始");
//        if (null == entity) return RespBean.error(RespBeanEnum.HDFS_CREATE_NULL_ERROR);
//        System.out.println("service层");
//        return noteService.upLoad(entity);
//    }
    @PostMapping("/upload")
    public RespBean upLoad(@RequestPart("file") MultipartFile file,@RequestParam(value = "userID") Long userID){
        if (file == null) return RespBean.error(RespBeanEnum.HDFS_FILENOTNULL_ERROR);
        if (userID == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        UpLoadEntity entity = new UpLoadEntity();
        entity.setUserID(userID);
        entity.setFile(file);
        return noteService.upLoad(entity);
    }

    @GetMapping("/getNoteList")
    @ResponseBody
    public NoteListResponse getNoteList(@RequestParam(value = "userID") Long userID){
        System.out.println("获取LIST");
        if (userID == null) return null;
        NoteListResponse list = noteService.getList(userID);
        System.out.println(list.toString());
        return list;
    }

    @GetMapping("getNote")
    public NoteInfo check(@RequestParam(value = "id") Long id){
        if (id == null) return null;
        return noteService.check(id);
    }
    @PostMapping("/update")
    public RespBean update(@RequestBody NoteUpdateEntity entity){
        return noteService.update(entity);
    }

    @PostMapping("/delete")
    public RespBean delete(@RequestParam(value = "userID") Long userID,@RequestParam(value = "noteID") Long noteID){
        if (userID == null || noteID == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        return noteService.delete(userID,noteID);
    }
}
