package com.mayankmadhav.demo.app.mobileappws.transformer;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface GenericTransformer<ENTITY, DTO> {

    public ENTITY fromDTO(DTO dto);

    public DTO toDTO(ENTITY entity);

    default List<ENTITY> fromListDTO(List<DTO> dtos) {
        List<ENTITY> entities = new ArrayList<ENTITY>();
        for (DTO dto : dtos) {
            entities.add(fromDTO(dto));
        }
        return entities;
    }

    default List<DTO> toListDTO(List<ENTITY> entites) {
        List<DTO> dtos = new ArrayList<DTO>();
        for (ENTITY entity : entites) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    default List<DTO> setContent(Page<ENTITY> page) {
        List<DTO> dtos = new ArrayList<>();
        List<ENTITY> content = page.getContent();
        for (ENTITY e : content) {
            dtos.add(toDTO(e));
        }
        return dtos;
    }

}
