package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;



    @Builder
    public DepartmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
