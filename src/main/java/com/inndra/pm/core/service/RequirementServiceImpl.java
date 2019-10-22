package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.RequirementDao;
import com.inndra.pm.core.dao.RequirementDaoImpl;
import com.inndra.pm.core.domain.model.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-05-20
 */

@Service("requirementService")
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementDao requirementDao;

    public RequirementServiceImpl() {
        this.requirementDao = new RequirementDaoImpl();
    }


    public void setRequirementDao(RequirementDaoImpl requirementDao) {
        this.requirementDao = requirementDao;
    }

    @Override
    public void insertRequirement(Requirement requirement) {
        requirementDao.insertRequirement(requirement);
    }

    @Override
    public void updateRequirement(Requirement requirement) {
        requirementDao.updateRequirement(requirement);
    }

    @Override
    public void deleteRequirement(Integer id) {
        requirementDao.deleteRequirement(id);
    }

    @Override
    public Requirement getRequirementById(Integer id) {
        return requirementDao.getRequirementById(id);
    }

    @Override
    public List<Requirement> getRequirementByUser(String name) {
        return requirementDao.getRequirementByUser(name);
    }

    @Override
    public List<Requirement> getRequirementByText(String text) {
        return requirementDao.getRequirementByText(text);
    }

    @Override
    public List<Requirement> getAllRequirements() {
        return requirementDao.getAllRequirements();
    }

    @Override
    public List<Requirement> getRequirementByBeginDate(String firstDate, String secondDate) {
        return requirementDao.getRequirementByBeginDate(firstDate, secondDate);
    }

    @Override
    public List<Requirement> getRequirementByStatus(String type) {
        return requirementDao.getRequirementByStatus(type);
    }

    @Override
    public List<Requirement> getRequirementByProject(String name) {
        return requirementDao.getRequirementByProject(name);
    }

    @Override
    public List<Requirement> getLastRequirements(String date) {
        return requirementDao.getLastRequirements(date);
    }
}
