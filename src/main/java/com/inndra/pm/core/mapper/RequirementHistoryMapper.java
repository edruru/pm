package com.inndra.pm.core.mapper;

import com.inndra.pm.core.domain.model.RequirementHistory;

import java.util.List;

/**
 * Created by edwin.rubio on 04/06/2014.
 */
public interface RequirementHistoryMapper {
    void insertRH(RequirementHistory rh);

    void updateRH(RequirementHistory rh);

    void deleteRH(Integer id);

    RequirementHistory getRHById(Integer id);

    List<RequirementHistory> getRHByText(String text);

    List<RequirementHistory> getRHByRequirementId(Integer id);

    List<RequirementHistory> getRHByStatus(String type);
}
