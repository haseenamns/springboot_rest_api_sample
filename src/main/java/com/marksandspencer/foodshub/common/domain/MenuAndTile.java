package com.marksandspencer.foodshub.common.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
/**
 * Type of Menu and Tile
 */
@Data
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection="menus_and_tiles")
public class MenuAndTile {
	
	@Id
	private String id;
	private String name;
	private String description;
	private String image;
	private String imageType;
	private String navigationLink;
	private Flag flags;
	private String parent;
	private List<MenuItem> menuItems;
}
