package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Requirement;
import com.inndra.pm.core.mapper.RequirementMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by edwin.rubio on 04/06/2014.
 */

@Transactional
@Repository("requirementDao")
public class RequirementDaoImpl implements RequirementDao {

    @Resource
    private RequirementMapper requirementMapper;

    @Override
    public void insertRequirement(Requirement requirement) {
        requirementMapper.insertRequirement(requirement);
    }

    @Override
    public void updateRequirement(Requirement requirement) {

        requirementMapper.updateRequirement(requirement);
    }

    @Override
    public void deleteRequirement(Integer id) {

        requirementMapper.deleteRequirement(id);
    }

    @Override
    public Requirement getRequirementById(Integer id) {

        return requirementMapper.getRequirementById(id);
    }

    @Override
    public List<Requirement> getRequirementByUser(String name) {

        return requirementMapper.getRequirementByUser("%" + name + "%");
    }

    @Override
    public List<Requirement> getRequirementByText(String text) {

        return requirementMapper.getRequirementByText("%" + text + "%");
    }

    @Override
    public List<Requirement> getAllRequirements() {

        return requirementMapper.getAllRequirements();
    }

    @Override
    public List<Requirement> getRequirementByBeginDate(String firstDate, String secondDate) {

        return requirementMapper.getRequirementByBeginDate(firstDate, secondDate);
    }

    @Override
    public List<Requirement> getRequirementByStatus(String type) {

        return requirementMapper.getRequirementByStatus("%" + type + "%");
    }

    @Override
    public List<Requirement> getRequirementByProject(String name) {

        return requirementMapper.getRequirementByProject("%" + name + "%");
    }

    @Override
    public List<Requirement> getLastRequirements(String date) {
        return requirementMapper.getLastRequirements(date);
    }

    public void setRequirementMapper(RequirementMapper requirementMapper) {
        this.requirementMapper = requirementMapper;
    }
}
