package com.inndra.pm.core.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Sprint.
 * 
 * @author moises.villa
 * @version 1.0
 * @since 2014-05-20
 */
public class Sprint {

	@NotNull
	private int sequence;
	@NotNull
	@Valid
	private Requirement[] requirements;

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Requirement[] getRequirements() {
		return requirements;
	}

	public void setRequirements(Requirement[] requirements) {
		this.requirements = requirements;
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("Sprint[");
		info.append(" sequence:").append(sequence);
		info.append(" requirements:").append(
				requirements != null ? Arrays.toString(requirements) : null);
		return (info.append(" ]")).toString();
	}

}
