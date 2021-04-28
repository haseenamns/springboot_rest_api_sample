package com.marksandspencer.foodshub.common.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * The Tile Structure
 */

@Data
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TileList {
	
	private String heading;
	private String description;
	private String image;
	private String imageType;
	private String navigationLink;
	private boolean disabled;
	@JsonProperty("isOpenNewTab")
	private boolean isOpenNewTab;
	@JsonProperty("isDownloadable")
	private boolean isDownloadable;
}
