package dev.harsh.product_service.controllers;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.dtos.SearchRequestDto;
import dev.harsh.product_service.dtos.SortHelperDto;
import dev.harsh.product_service.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @PostMapping
    public Page<GenericProductDto> searchProduct(@RequestBody SearchRequestDto searchRequestDto){
        List<SortHelperDto> sortByParameters = searchRequestDto.getSorter().stream().toList();
        Sort sort = Sort.by(sortByParameters.get(0).getDirection(), sortByParameters.get(0).getSortBy().toLowerCase());
        sortByParameters.forEach(sorter->{
            sort.and(Sort.by(sorter.getDirection(),sortByParameters.get(0).getSortBy().toLowerCase()));
        });
        return searchService.searchProduct(searchRequestDto.getQuery(),PageRequest.of(searchRequestDto.getPageNumber(),searchRequestDto.getPageSize()));
    }
}
