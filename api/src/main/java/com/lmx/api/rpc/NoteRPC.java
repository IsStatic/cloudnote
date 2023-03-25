package com.lmx.api.rpc;

import com.lmx.api.configs.FeignConfig;
import com.lmx.common.entitys.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Component
@FeignClient(value = "NOTE", configuration = FeignConfig.class)
public interface NoteRPC {

    @GetMapping("/note/hello")
    public RespBean hello();

    @PostMapping("/note/create")
    public RespBean create(NoteCreateEntity noteCreate);

//    @PostMapping("/note/upload")
//    public RespBean upLoad(UpLoadEntity entity);

    @PostMapping(value = "/note/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RespBean upLoad(@RequestPart("file") MultipartFile file, @RequestParam(value = "userID") Long userID);

    @GetMapping(value = "/note/getNoteList")
    public NoteListResponse getNoteList(@RequestParam(value = "userID") Long userID);

    @GetMapping("/note/getNote")
    public NoteInfo check(@RequestParam(value = "id") Long id);

    @PostMapping("/note/update")
    public RespBean update(@RequestBody NoteUpdateEntity entity);

    @PostMapping("/note/delete")
    public RespBean delete(@RequestParam(value = "userID") Long userID,@RequestParam(value = "noteID") Long noteID);
}
