package com.marksandspencer.foodshub.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
/**
 *  Type of Sub Item Flag
 */
@Data
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class MenuItemFlag {

	private boolean isDisabled;
	
}
