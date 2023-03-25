package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteInfo {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String title;
    private String context;

    public String getCreatedAtStr() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(createdAt);
    }

    public String getUpdatedAtStr(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(updatedAt);
    }
}
