package com.tommannson.todoapp.recipes.draft;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/draft2")
@RestController
public class DraftController {

    private final DraftRepository recipeDraftRepo;

    public DraftController(DraftRepository recipeDraftRepo) {
        this.recipeDraftRepo = recipeDraftRepo;
    }

    @GetMapping
    ResponseEntity<Page<DraftDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                recipeDraftRepo.findAll(pageable)
                        .map(this::convertToDto)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<DraftDto> getById(
            @PathVariable("id")
            @Pattern(regexp = "\\d{9}", message = "Id should be number")
            String draftId
    ) {
        return ResponseEntity.of(
                recipeDraftRepo.findById(Long.getLong(draftId))
                        .map(this::convertToDto)
        );
    }

    @PostMapping
    ResponseEntity<Void> createDraft(
            @Valid @RequestBody DraftDto data
    ) {
        var dbObject = new DraftDbo(
                data.getName(),
                data.getOriginalExtractedText(),
                data.getRecipeText(),
                data.getIngredientsText(),
                data.getToolsInfo(),
                data.getPreparationTime()
        );

        recipeDraftRepo.save(dbObject);
        return ResponseEntity.created(URI.create("" + dbObject.getId())).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateDraft(
            @PathVariable("id") @NotBlank String draftId,
            @Valid @RequestBody DraftDto newData
    ) {
        var dto = recipeDraftRepo.findById(Long.parseLong(draftId))
                .get();


        dto.setRecipeName(newData.getName());
        dto.setOriginalExtractedText(newData.getOriginalExtractedText());
        dto.setRecipeGeneratedText(newData.getRecipeText());
        dto.setIngredientsGeneratedText(newData.getIngredientsText());
        dto.setToolsTextGeneratedText(newData.getToolsInfo());
        dto.setPreparationTime(newData.getPreparationTime());

        recipeDraftRepo.save(dto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> updateDraft(
            @PathVariable("id") @NotBlank String draftId
    ) {
        recipeDraftRepo.deleteById(Long.parseLong(draftId));
        return ResponseEntity.noContent().build();
    }

    private DraftDto convertToDto(DraftDbo dbObject) {
        DraftDto dto = new DraftDto();
        dto.setId(dbObject.getId().toString());
        dto.setName(dbObject.getRecipeName());
        dto.setOriginalExtractedText(dbObject.getOriginalExtractedText());
        return dto;
    }
}
