package com.inndra.pm.core.service;

import com.inndra.pm.core.domain.model.RequirementHistory;

import java.util.List;

/**
 * RequirementService.
 *
 * @author edwin.rubio
 * @version 1.0
 * @since 2014-07-14
 */

public interface RequirementHistoryService {
    void insertRH(RequirementHistory rh);

    void updateRH(RequirementHistory rh);

    void deleteRH(Integer id);

    RequirementHistory getRHById(Integer id);

    List<RequirementHistory> getRHByText(String text);

    List<RequirementHistory> getRHByRequirementId(Integer id);

    List<RequirementHistory> getRHByStatus(String type);
}
