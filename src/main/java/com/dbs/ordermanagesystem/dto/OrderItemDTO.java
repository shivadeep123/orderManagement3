package com.dbs.ordermanagesystem.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrderItemDTO {

	@NotEmpty
	@Pattern(regexp="^([0-9 ]|)+$")
	private long orderItemNumber;
	@NotEmpty
	@Pattern(regexp="^([A-Za-z0-9 ]|)+$")
	private String productCode;
	@NotEmpty
	@Pattern(regexp="^([A-Za-z0-9 ]|)+$")
	private String productName;
	@NotEmpty
	@Pattern(regexp="^([0-9 ]|)+$")
	private String quantity;
}
