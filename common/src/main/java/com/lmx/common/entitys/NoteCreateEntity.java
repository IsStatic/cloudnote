package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreateEntity implements Serializable {
    @NotNull
    private long userID;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @NotNull
    private String title;
    @NotNull
    private String context;
}
