package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<DTO> {

    private int pageNumber;

    private int size;

    private int totalPages;

    private int numberOfElements;

    private boolean isLast;

    private List<DTO> content;

}
