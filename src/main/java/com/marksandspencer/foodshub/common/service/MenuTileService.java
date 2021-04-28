/**
 * Menu & Tile Service Interface
 */
package com.marksandspencer.foodshub.common.service;

import java.util.List;

import com.marksandspencer.foodshub.common.transfer.MenuList;
import com.marksandspencer.foodshub.common.transfer.TileList;

/**
 *  Menu & Tile Service
 *
 */
public interface MenuTileService {
	
	/**
	 * To get the list of tiles based on the page
	 * 
	 * @param page
	 * @return List of Tiles
	 */
	List<TileList> getTilesList(String page);
	
	/**
	 * To get the top menus List
	 * @return List of TopMenus
	 */
	List<MenuList> getTopMenus();

}
