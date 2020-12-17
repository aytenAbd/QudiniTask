package com.qudini.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Customer implements Serializable {
    @NotNull(message = "ID field must not be null")
    @Positive(message = "ID field must be positive integer value")
    private Integer id;
    @NotNull(message = "Name field must not be null")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXXXX")
    @NotNull(message = "Duetime field must not be null")
    private ZonedDateTime duetime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXXXX")
    @NotNull(message = "Jointime field must not be null")
    private ZonedDateTime jointime;
}
