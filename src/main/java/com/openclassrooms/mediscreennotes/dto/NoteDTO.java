package com.openclassrooms.mediscreennotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private String id;
    @NotBlank(message = "patId is mandatory")
    private Integer patId;
    private String patName;
    private String observation;
}
