package com.lmx.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.common.entitys.*;
import com.lmx.hdfsmodel.util.HdfsService;
import com.lmx.note.entity.Note;
import com.lmx.note.mapper.NoteMapper;
import com.lmx.note.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 * @author liang
 * @date 2023-03-20
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

    private final static String pathAdds = "/myNote/v1/";



    @Autowired
    private NoteMapper noteMapper;

    @Override
    public RespBean create(NoteCreateEntity noteCreate) {
        Note note = noteMapper.selectOne(new QueryWrapper<Note>().eq("user_id", noteCreate.getUserID()).eq("title", noteCreate.getTitle()));
        if (null != note ){
            return RespBean.error(RespBeanEnum.NOTE_ISEXIST_ERROR);
        }
        String path = pathAdds + String.valueOf(noteCreate.getUserID());
        String fileName = noteCreate.getTitle();
        try {
            HdfsService.createFileForString(path,fileName,noteCreate.getContext());
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.HDFS_ERROR,e);
        }
        try {
            note = setNoteOfNoteCreateEntity(new Note(),noteCreate);
            note.setAddress(path);
            noteMapper.insert(note);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.MAPPER_ERROR);
        }
        return RespBean.success();
    }

    @Override
    public RespBean upLoad(UpLoadEntity entity) {
        MultipartFile file = entity.getFile();
        Note note = noteMapper.selectOne(new QueryWrapper<Note>().eq("user_id",entity.getUserID()).eq("title",file.getOriginalFilename()));
        if (null != note ){
            return RespBean.error(RespBeanEnum.NOTE_ISEXIST_ERROR);
        }
        System.out.println(2);
        String path = pathAdds + String.valueOf(entity.getUserID());
        try {
            HdfsService.createFileForStream(path,file.getOriginalFilename(),file.getInputStream());
        } catch (Exception e) {
            return RespBean.error(RespBeanEnum.HDFS_ERROR,e);
        }
        System.out.println(3);
        note = new Note();
        note.setUserId(entity.getUserID());
        note.setTitle(file.getOriginalFilename());
        note.setAddress(path);
        note.setCreatedAt(new Date());
        try {
            noteMapper.insert(note);
        }catch (Exception e){
            return RespBean.error(RespBeanEnum.MAPPER_ERROR,e);
        }
        return RespBean.success();
    }

    @Override
    public NoteListResponse getList(Long userID) {
        if (userID == null) return NoteListResponse.error();
        List<Note> noteList = noteMapper.selectList(new QueryWrapper<Note>().eq("user_id", userID));
        return NoteListResponse.success(noteListToNoteEntityList(noteList));
    }

    @Override
    public NoteInfo check(Long id) {
        if (id == null) return null;
        Note note = noteMapper.selectById(id);
        if (note == null) return null;
        NoteInfo info = new NoteInfo();
        info.setId(note.getId());
        info.setCreatedAt(note.getCreatedAt());
        info.setUpdatedAt(new Date());
        info.setTitle(note.getTitle());
        String path = note.getAddress() +"/"+ note.getTitle();
        System.out.println("path:"+path);
        String file;
        try {
            file = HdfsService.readFile(path);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        info.setContext(file);
        return info;
    }

    @Override
    public RespBean update(NoteUpdateEntity entity) {
        Note note = noteMapper.selectById(entity.getNoteID());
        if (note == null) return RespBean.error(RespBeanEnum.NOTE_NOT_ERROR);
        note.setUpdatedAt(new Date());

        try {
            HdfsService.createFileForString(note.getAddress(),note.getTitle(),entity.getContext());
        } catch (Exception e) {
            System.out.println(e);
            return RespBean.error(RespBeanEnum.HDFS_ERROR);
        }
        noteMapper.update(note,new QueryWrapper<Note>().eq("id",entity.getNoteID()));
        return RespBean.success();
    }

    @Override
    public RespBean delete(Long userID, Long noteID) {
        Note note = noteMapper.selectOne(new QueryWrapper<Note>().eq("user_id",userID).eq("id",noteID));
        if (null == note) return RespBean.error(RespBeanEnum.NOTE_NOT_ERROR);
        String path = note.getAddress() +"/" +note.getTitle();
        try {
            HdfsService.deleteFile(path);
        } catch (Exception e) {
            System.out.println(e);
            return RespBean.error(RespBeanEnum.HDFS_ERROR,e);
        }
        noteMapper.deleteById(note);
        return RespBean.success();
    }

    public static LinkedList<NoteEntity> noteListToNoteEntityList(List<Note> notes){
        LinkedList<NoteEntity> noteEntities = new LinkedList<>();
        for (Note note : notes) {
            NoteEntity noteEntitie = new NoteEntity();
            noteEntitie.setId(note.getId());
            noteEntitie.setCreatedAt(note.getCreatedAt());
            noteEntitie.setUserId(note.getUserId());
            noteEntitie.setUpdatedAt(note.getUpdatedAt());
            noteEntitie.setTitle(note.getTitle());
            noteEntitie.setDeletedAt(note.getDeletedAt());
            noteEntitie.setAddress(note.getAddress());
            noteEntities.add(noteEntitie);
        }
        return noteEntities;
    }

    public static Note setNoteOfNoteCreateEntity(Note note,NoteCreateEntity noteCreate){
        note.setCreatedAt(noteCreate.getCreatedAt());
        note.setUserId(noteCreate.getUserID());
        note.setTitle(noteCreate.getTitle());
        return note;
    }
}
