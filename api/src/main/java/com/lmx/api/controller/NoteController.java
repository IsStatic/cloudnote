package com.lmx.api.controller;

import com.lmx.api.rpc.NoteRPC;
import com.lmx.common.entitys.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Resource
    private NoteRPC noteRPC;


    @RequestMapping("/home")
    public String toNoteHome(){
        return "noteHome";
    }

    @RequestMapping("/create")
    public String toCreate(UserInfoEntity userInfoEntity, Model model){
        if (userInfoEntity ==null) return "login";
        model.addAttribute("user",userInfoEntity);
        return "createNote";
    }
    @RequestMapping("/toNote")
    public String toMyNote(UserInfoEntity userInfoEntity, Model model){
        if (userInfoEntity ==null) return "login";
        model.addAttribute("user",userInfoEntity);
        return "myNoteOfNote";
    }


    @RequestMapping("/toUpLoad")
    public String toUpLoad(UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return "login";
        return "noteUpLoad";
    }


    @PostMapping("/create")
    @ResponseBody
    public RespBean create(@Valid NoteCreateEntity noteCreate){
        noteCreate.setTitle(noteCreate.getTitle() +".txt");
        return noteRPC.create(noteCreate);
    }

    @PostMapping("/upLoad")
    @ResponseBody
    public RespBean upLoad(@RequestParam(value = "file") MultipartFile file,UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        if (file == null) return RespBean.error(RespBeanEnum.HDFS_FILENOTNULL_ERROR);
        UpLoadEntity entity = new UpLoadEntity();
        entity.setFile(file);
        entity.setUserID(userInfoEntity.getId());
        return noteRPC.upLoad(entity.getFile(), entity.getUserID());
    }

    @GetMapping("/getNoteList")
    @ResponseBody
    public NoteListResponse getNoteList(UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return NoteListResponse.error();
        NoteListResponse noteList = noteRPC.getNoteList(userInfoEntity.getId());
        return noteList;
    }

    @RequestMapping("/toNoteInfo")
    public String  getNote(Model model , UserInfoEntity userInfoEntity,@RequestParam(value = "noteID") Long noteID){
        if (userInfoEntity == null) return "login";
        model.addAttribute("note",noteRPC.check(noteID));
        return "noteInfo";
    }
    @RequestMapping("/toNoteInfoOfNote")
    public String  getNoteOfNote(Model model , UserInfoEntity userInfoEntity,@RequestParam(value = "noteID") Long noteID){
        if (userInfoEntity == null) return "login";
        model.addAttribute("note",noteRPC.check(noteID));
        return "noteInfoOfNote";
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,UserInfoEntity userInfoEntity,@RequestParam(value = "noteID") Long noteID){
        if (userInfoEntity ==null) return "login";
        model.addAttribute("note",noteRPC.check(noteID));
        return "noteUpdate";
    }
    @RequestMapping("/toUpdateOfNote")
    public String toUpdateOfNote(Model model,UserInfoEntity userInfoEntity,@RequestParam(value = "noteID") Long noteID){
        if (userInfoEntity ==null) return "login";
        model.addAttribute("note",noteRPC.check(noteID));
        return "noteUpdateOfNote";
    }

    @PostMapping("/update")
    @ResponseBody
    public RespBean update(UserInfoEntity userInfoEntity,@Valid NoteUpdateEntity noteUpdateEntity){
        if (userInfoEntity == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        return noteRPC.update(noteUpdateEntity);
    }

    @PostMapping("/delete")
    @ResponseBody
    public RespBean delete(UserInfoEntity userInfoEntity,@RequestParam(value = "noteID") Long noteID){
        if (userInfoEntity == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        return noteRPC.delete(userInfoEntity.getId(),noteID);
    }
}
