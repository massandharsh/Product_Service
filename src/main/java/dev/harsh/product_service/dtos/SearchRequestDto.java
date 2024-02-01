package dev.harsh.product_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
    private String query;
    private Integer pageNumber;
    private Integer pageSize;
    private Set<SortHelperDto> sorter = new HashSet<>();
}
