package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Priority;
import com.inndra.pm.core.mapper.PriorityMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by edwin.rubio on 6/3/2014.
 */

@Transactional
@Repository("priorityDao")
public class PriorityDaoImpl implements PriorityDao {

    @Resource
    private PriorityMapper priorityMapper;

    public Priority getPriorityById(Integer id) {
        return priorityMapper.getPriorityById(id);
    }

    public List<Priority> getAllPriorities() {
        return priorityMapper.getAllPriorities();
    }

    public void setPriorityMapper(PriorityMapper priorityMapper) {
        this.priorityMapper = priorityMapper;
    }
}
