package com.mayankmadhav.demo.app.mobileappws.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;

public interface GenericTransformer<ENTITY, DTO> {

	public ENTITY fromDTO(DTO dto);

	public DTO toDTO(ENTITY entity);

	public PageDTO<DTO> toPageDto(Page<ENTITY> page);

	default List<DTO> setContent(Page<ENTITY> page) {
		List<DTO> dtos = new ArrayList<>();
		List<ENTITY> content = page.getContent();
		for (ENTITY e : content) {
			dtos.add(toDTO(e));
		}
		return dtos;
	}

}
