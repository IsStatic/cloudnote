package com.lmx.common.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteListResponse implements Serializable {
    private long code;
    private String message;
    private long cnt;
    private LinkedList<NoteEntity> data;

    public static NoteListResponse error(){
        return new NoteListResponse(40000,"错误请求",0,null);
    }

    public static NoteListResponse success(LinkedList<NoteEntity> data){
        return new NoteListResponse(0,"成功",data.size(),data);
    }
}
