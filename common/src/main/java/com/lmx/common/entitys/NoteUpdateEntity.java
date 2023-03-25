package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateEntity implements Serializable {
    @NotNull
    private Long noteID;
    @NotNull
    private String context;
}
