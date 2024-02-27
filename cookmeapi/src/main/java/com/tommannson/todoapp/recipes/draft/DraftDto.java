package com.tommannson.todoapp.recipes.draft;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DraftDto {

    @NotNull
    private String name;

    @NotNull
    private String originalExtractedText;

    private String recipeText;
    private String ingredientsText;
    private String toolsInfo;
    private String preparationTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
}