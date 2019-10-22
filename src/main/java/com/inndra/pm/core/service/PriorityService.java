package com.inndra.pm.core.service;

import com.inndra.pm.core.domain.model.Priority;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */

public interface PriorityService {

    Priority getPriorityById(Integer id);

    List<Priority> getAllPriorities();

}
