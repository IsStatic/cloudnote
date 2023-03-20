package com.lmx.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @RequestMapping("/home")
    public String toNoteHome(){
        return "noteHome";
    }
}
