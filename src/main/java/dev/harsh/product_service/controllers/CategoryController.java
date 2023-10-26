package dev.harsh.product_service.controllers;

import dev.harsh.product_service.dtos.CategoryDto;
import dev.harsh.product_service.dtos.GetProductTitlesRequestDto;
import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/{uuid}")
    public CategoryDto getCategory(@PathVariable("uuid") String uuid){
        return categoryService.getCategory(uuid);
    }

    @GetMapping("/titles")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto getProductTitlesRequestDto){
        return categoryService.getProductTitlesSelf(getProductTitlesRequestDto.getUuids());
    }

}
