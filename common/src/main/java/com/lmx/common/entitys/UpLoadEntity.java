package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpLoadEntity implements Serializable {
    @NotNull
    private Long userID;
    @NotNull
    private MultipartFile file;
}
