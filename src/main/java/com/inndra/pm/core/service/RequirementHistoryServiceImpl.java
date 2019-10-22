package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.RequirementHistoryDao;
import com.inndra.pm.core.domain.model.RequirementHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edwin.rubio on 14/07/2014.
 */
@Service("requirementHistoryService")
public class RequirementHistoryServiceImpl implements RequirementHistoryService {

    @Autowired
    private RequirementHistoryDao requirementHistoryDao;


    @Override
    public void insertRH(RequirementHistory rh) {
        this.requirementHistoryDao.insertRH(rh);
    }

    @Override
    public void updateRH(RequirementHistory rh) {
        this.requirementHistoryDao.updateRH(rh);
    }

    @Override
    public void deleteRH(Integer id) {
        this.requirementHistoryDao.deleteRH(id);
    }

    @Override
    public RequirementHistory getRHById(Integer id) {
        return this.requirementHistoryDao.getRHById(id);
    }

    @Override
    public List<RequirementHistory> getRHByText(String text) {
        return this.requirementHistoryDao.getRHByText(text);
    }

    @Override
    public List<RequirementHistory> getRHByRequirementId(Integer id) {
        return this.requirementHistoryDao.getRHByRequirementId(id);
    }

    @Override
    public List<RequirementHistory> getRHByStatus(String status) {
        return this.requirementHistoryDao.getRHByStatus(status);
    }

    public void setRequirementHistoryDao(RequirementHistoryDao requirementHistoryDao) {
        this.requirementHistoryDao = requirementHistoryDao;
    }
}
