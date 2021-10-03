package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDTO<DTO> {
	
	private int pageNumber;
	
	private int size;
	
	private int totalPages;
	
	private int numberOfElements;
	
	private List<DTO> content;

}
