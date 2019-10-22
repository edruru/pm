package com.inndra.pm.core.mapper;

import com.inndra.pm.core.domain.model.Requirement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by edwin.rubio on 04/06/2014.
 */
public interface RequirementMapper {

    void insertRequirement(Requirement requirement);

    void updateRequirement(Requirement requirement);

    void deleteRequirement(Integer id);

    Requirement getRequirementById(Integer id);

    List<Requirement> getRequirementByUser(String name);

    List<Requirement> getRequirementByText(String text);

    List<Requirement> getAllRequirements();

    List<Requirement> getRequirementByBeginDate(@Param("firstDate") String firstDate, @Param("secondDate") String secondDate);

    List<Requirement> getRequirementByStatus(String type);

    List<Requirement> getRequirementByProject(String name);

    List<Requirement> getLastRequirements(String date);
}
