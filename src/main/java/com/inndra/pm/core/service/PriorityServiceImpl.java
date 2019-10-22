package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.PriorityDao;
import com.inndra.pm.core.dao.PriorityDaoImpl;
import com.inndra.pm.core.domain.model.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */

@Service("priorityService")
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityDao priorityDao;

    public PriorityServiceImpl(){
        this.priorityDao = new PriorityDaoImpl();
    }

    public Priority getPriorityById(Integer id){
        //TODO: Handle exception
        return priorityDao.getPriorityById(id);
    }

    public List<Priority> getAllPriorities() {
        //TODO: Handle exception
        return priorityDao.getAllPriorities();
    }

    public void setPriorityDao(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }
}
