package com.marksandspencer.foodshub.common.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
/**
 * Type of Each menu Item
 */
public class MenuItem {
	
	private String name;
	private String navigationLink;
	private MenuItemFlag flags;
	private List<MenuItem> menuItems;
}
