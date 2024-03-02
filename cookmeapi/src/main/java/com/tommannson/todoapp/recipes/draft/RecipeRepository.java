package com.tommannson.todoapp.recipes.draft;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
interface RecipeRepository extends JpaRepository<RecipeDbo, Long> {

}
