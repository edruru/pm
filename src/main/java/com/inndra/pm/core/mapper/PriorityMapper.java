package com.inndra.pm.core.mapper;

import com.inndra.pm.core.domain.model.Priority;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public interface PriorityMapper {
    public Priority getPriorityById(Integer id);

    public List<Priority> getAllPriorities();
}
