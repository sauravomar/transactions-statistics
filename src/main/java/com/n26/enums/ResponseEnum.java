package com.n26.enums;

public enum ResponseEnum {

	CREATED("created"), NO_CONTENT("older"), NOT_PARSEABLE("not_parseable");

	private String status;

	private ResponseEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static ResponseEnum getConstants(String status) {
		if (null == status)
			return null;
		for (ResponseEnum sts : ResponseEnum.values()) {
			if (status.equalsIgnoreCase(sts.status)) {
				return sts;
			}
		}
		return null;
	}
}
