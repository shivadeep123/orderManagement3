package com.dbs.ordermanagesystem.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.dbs.ordermanagesystem.util.DateAndTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrderDTO {
	@NotEmpty
	@Pattern(regexp="^([A-Za-z0-9 ]|)+$")
	private String customerName;
	@NotEmpty
	@DateAndTimeFormat(pattern="yyyy-MM-dd")
	private String orderDate;

	@NotEmpty
	@Pattern(regexp="^([0-9])+$")
	private Integer orderItemNumber;
	@NotEmpty
	@Pattern(regexp="^([A-Za-z0-9 |.,();:@#$%&]|)+$")
	private String shippingAddress;
	@NotEmpty
	@Pattern(regexp="^([0-9|.]|)+$")
	private String Total;
}
