package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.RequirementHistory;
import com.inndra.pm.core.mapper.RequirementHistoryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by edwin.rubio on 14/07/2014.
 */

@Transactional
@Repository("requirementHistoryDao")
public class RequirementHistoryDaoImpl implements RequirementHistoryDao {

    @Resource
    private RequirementHistoryMapper requirementHistoryMapper;

    @Override
    public void insertRH(RequirementHistory rh) {
        requirementHistoryMapper.insertRH(rh);
    }

    @Override
    public void updateRH(RequirementHistory rh) {
        requirementHistoryMapper.updateRH(rh);
    }

    @Override
    public void deleteRH(Integer id) {
        requirementHistoryMapper.deleteRH(id);
    }

    @Override
    public RequirementHistory getRHById(Integer id) {
        return requirementHistoryMapper.getRHById(id);
    }

    @Override
    public List<RequirementHistory> getRHByRequirementId(Integer id) {
        return requirementHistoryMapper.getRHByRequirementId(id);
    }

    @Override
    public List<RequirementHistory> getRHByText(String text) {
        return requirementHistoryMapper.getRHByText("%" + text + "%");
    }


    @Override
    public List<RequirementHistory> getRHByStatus(String status) {
        return requirementHistoryMapper.getRHByStatus(status);
    }

    public void setRequirementHistoryMapper(RequirementHistoryMapper requirementHistoryMapper) {
        this.requirementHistoryMapper = requirementHistoryMapper;
    }
}
