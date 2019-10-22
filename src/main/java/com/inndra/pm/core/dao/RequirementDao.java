package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.Requirement;

import java.util.List;

/**
 * RequirementService.
 *
 * @author moises.villa
 * @version 1.0
 * @since 2014-05-20
 */
public interface RequirementDao {
    void insertRequirement(Requirement requirement);

    void updateRequirement(Requirement requirement);

    void deleteRequirement(Integer id);

    Requirement getRequirementById(Integer id);

    List<Requirement> getRequirementByUser(String name);

    List<Requirement> getRequirementByText(String text);

    List<Requirement> getAllRequirements();

    List<Requirement> getRequirementByBeginDate(String firstDate, String secondDate);

    List<Requirement> getRequirementByStatus(String type);

    List<Requirement> getRequirementByProject(String name);

    List<Requirement> getLastRequirements(String date);
}
