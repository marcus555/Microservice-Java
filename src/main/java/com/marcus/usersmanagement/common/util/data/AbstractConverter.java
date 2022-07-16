package com.marcus.usersmanagement.common.util.data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {

    public abstract E dto2Entity(D dto);

    public abstract D entity2DTO(E entity);

    public List<E> dto2Entity(List<D> dtos) {
        if (dtos == null){
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::dto2Entity)
                .collect(Collectors.toList());
    }

    public List<D> entity2DTO(List<E> entities) {
        if (entities == null){
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::entity2DTO)
                .collect(Collectors.toList());
    }
}
