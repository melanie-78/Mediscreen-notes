package com.openclassrooms.mediscreennotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note {
    @Id
    private String id;
    @NotBlank(message = "patId is mandatory")
    private Integer patId;
    @NotBlank(message = "patName is mandatory")
    private String patName;
    @NotBlank(message = "observation is mandatory")
    private String observation;
}
