package com.lmx.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.common.entitys.*;
import com.lmx.note.entity.Note;

/**
 * 
 *
 * @author liang
 * @date 2023-03-20
 */
public interface INoteService extends IService<Note> {

    public RespBean create(NoteCreateEntity noteCreate);

    public RespBean upLoad(UpLoadEntity entity);

    public NoteListResponse getList(Long userID);

    public NoteInfo check(Long id);

    public RespBean update(NoteUpdateEntity entity);

    public RespBean delete(Long userID,Long noteID);
}
