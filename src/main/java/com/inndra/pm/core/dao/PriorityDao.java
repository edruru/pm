package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Priority;
import java.util.List;

/**
 * Created by edwin.rubio on 6/03/2014.
 */

public interface PriorityDao {

    Priority getPriorityById(Integer id);
    List<Priority> getAllPriorities();
}
