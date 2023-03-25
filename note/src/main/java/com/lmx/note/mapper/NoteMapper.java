package com.lmx.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.note.entity.Note;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author liang
 * @date 2023-03-20
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
