package com.tommannson.todoapp.recipes.draft.data;

import com.tommannson.todoapp.recipes.configuration.audit.AuditableDbo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "draft")
@Data
@Getter
@Setter
@NoArgsConstructor
public class DraftDbo extends AuditableDbo {

    String recipeName;
    String originalExtractedText;
    String recipeGeneratedText;
    String ingredientsGeneratedText;
    String toolsTextGeneratedText;
    String preparationTime;

    public DraftDbo(String recipeName, String originalExtractedText, String recipeGeneratedText, String ingredientsGeneratedText, String toolsTextGeneratedText, String preparationTime) {
        this.recipeName = recipeName;
        this.originalExtractedText = originalExtractedText;
        this.recipeGeneratedText = recipeGeneratedText;
        this.ingredientsGeneratedText = ingredientsGeneratedText;
        this.toolsTextGeneratedText = toolsTextGeneratedText;
        this.preparationTime = preparationTime;
    }

    @Id
    @SequenceGenerator(
            name = "draft_pk_generator_seq",
            sequenceName = "draft_id_seqs",
            allocationSize = 100,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "draft_pk_generator_seq"
    )
    Integer id;
}
