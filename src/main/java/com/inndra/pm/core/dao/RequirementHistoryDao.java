package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.RequirementHistory;

import java.util.List;

/**
 * Created by edwin.rubio on 14/07/2014.
 */
public interface RequirementHistoryDao {
    void insertRH(RequirementHistory rh);

    void updateRH(RequirementHistory rh);

    void deleteRH(Integer id);

    RequirementHistory getRHById(Integer id);

    List<RequirementHistory> getRHByText(String text);

    List<RequirementHistory> getRHByRequirementId(Integer id);

    List<RequirementHistory> getRHByStatus(String status);
}
