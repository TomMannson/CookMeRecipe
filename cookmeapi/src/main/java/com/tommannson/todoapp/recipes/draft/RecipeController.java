package com.tommannson.todoapp.recipes.draft;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/repices")
@RestController
public class RecipeController {

    private final RecipeRepository recipeReposotory;

    public RecipeController(RecipeRepository recipeDraftRepo) {
        this.recipeReposotory = recipeDraftRepo;
    }

    @GetMapping
    ResponseEntity<Page<RecipeDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                recipeReposotory.findAll(pageable)
                        .map(this::convertToDto)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<RecipeDto> getById(
            @PathVariable("id")
            @Pattern(regexp = "\\d{9}", message = "Id should be number")
            String draftId
    ) {
        return ResponseEntity.of(
                recipeReposotory.findById(Long.getLong(draftId))
                        .map(this::convertToDto)
        );
    }

    @PostMapping
    ResponseEntity<Void> createDraft(
            @Valid @RequestBody RecipeDto data
    ) {
        var dbObject = new RecipeDbo(
                data.getName(),
                data.getOriginalExtractedText(),
                data.getRecipeText(),
                data.getIngredientsText(),
                data.getToolsInfo(),
                data.getPreparationTime()
        );

        recipeReposotory.save(dbObject);
        return ResponseEntity.created(URI.create("" + dbObject.getId())).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateDraft(
            @PathVariable("id") @NotBlank String draftId,
            @Valid @RequestBody RecipeDto newData
    ) {
        var dto = recipeReposotory.findById(Long.parseLong(draftId))
                .get();


        dto.setRecipeName(newData.getName());
        dto.setOriginalExtractedText(newData.getOriginalExtractedText());
        dto.setRecipeGeneratedText(newData.getRecipeText());
        dto.setIngredientsGeneratedText(newData.getIngredientsText());
        dto.setToolsTextGeneratedText(newData.getToolsInfo());
        dto.setPreparationTime(newData.getPreparationTime());

        recipeReposotory.save(dto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> updateDraft(
            @PathVariable("id") @NotBlank String draftId
    ) {
        recipeReposotory.deleteById(Long.parseLong(draftId));
        return ResponseEntity.noContent().build();
    }

    private RecipeDto convertToDto(RecipeDbo dbObject) {
        RecipeDto dto = new RecipeDto();
        dto.setId(dbObject.getId().toString());
        dto.setName(dbObject.getRecipeName());
        dto.setOriginalExtractedText(dbObject.getOriginalExtractedText());
        return dto;
    }
}
