package com.marksandspencer.foodshub.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 *  Type of Main Item Flag
 */
@Data
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Flag {
	
	private boolean isDownloadable;
	private boolean isOpenNewTab;
	private boolean isDisabled;
	
}
