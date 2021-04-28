/**
 * Menu & Tile Service Implementation
 */
package com.marksandspencer.foodshub.common.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.marksandspencer.foodshub.common.constant.ErrorCode;
import com.marksandspencer.foodshub.common.domain.MenuAndTile;
import com.marksandspencer.foodshub.common.domain.MenuItem;
import com.marksandspencer.foodshub.common.exception.CommonServiceException;
import com.marksandspencer.foodshub.common.repository.MenusAndTilesRepository;
import com.marksandspencer.foodshub.common.service.MenuTileService;
import com.marksandspencer.foodshub.common.transfer.MenuList;
import com.marksandspencer.foodshub.common.transfer.TileList;

/**
 * Menu & Tile Service Implementation
 *
 */
@Service
public class MenuTileServiceImpl implements MenuTileService{
	
	@Autowired
	MenusAndTilesRepository menusAndTilesRespository;
	
	/**
	 * To get the list of tiles based on the page
	 * 
	 * @param page
	 * @return List of Tiles
	 */
	@Override
	public List<TileList> getTilesList(String page) {
		List<MenuAndTile> tilesList = menusAndTilesRespository.findByParent(page);
		if (CollectionUtils.isEmpty(tilesList))
			throw new CommonServiceException(ErrorCode.NO_DATA);
		return tilesList.stream().map(getTileListConverter()).collect(Collectors.toList()); 
	}
	
	/**
	 * to convert the Tile List which contains heading and required info
	 * 
	 * @return Function<MenuAndTile, TileList>
	 */
	private Function<MenuAndTile, TileList> getTileListConverter() {
		return (MenuAndTile menuAndTile) -> TileList.builder()
				.heading(menuAndTile.getName())
				.description(menuAndTile.getDescription())
				.image(menuAndTile.getImage())
				.imageType(menuAndTile.getImageType())
				.navigationLink(menuAndTile.getNavigationLink())
				.disabled(menuAndTile.getFlags().isDisabled())
				.isOpenNewTab(menuAndTile.getFlags().isOpenNewTab())
				.isDownloadable(menuAndTile.getFlags().isDownloadable())
				.build();
	}
	
	/**
	 * To get the top menus with menu list, quick links
	 * 
	 * @return the list of top menus
	 */
	@Override
	public List<MenuList> getTopMenus() {
		List<MenuAndTile> topMenus = menusAndTilesRespository.findByMenuItemsNotNull();
		if (CollectionUtils.isEmpty(topMenus))
			throw new CommonServiceException(ErrorCode.NO_DATA);
		return topMenus.stream().map(getTopMenuListConverter()).collect(Collectors.toList()); 
	}
	
	/**
	 * to convert the Menu List which contains label and required info
	 * 
	 * @return Function<MenuAndTile, MenuList>
	 */
	private Function<MenuAndTile, MenuList> getTopMenuListConverter() {
		return (MenuAndTile menuAndTile) -> MenuList.builder()
				.label(menuAndTile.getName())
				.url("")
				.disabled(menuAndTile.getFlags().isDisabled())
				.items(menuAndTile.getMenuItems().stream().map(getMenuItemListConverter()).collect(Collectors.toList()))
				.build();
	}
	
	/**
	 * to convert the Sub Menu List which contains label and required info
	 * 
	 * @return Function<MenuItem, MenuList>
	 */
	private Function<MenuItem, MenuList> getMenuItemListConverter() {
		return (MenuItem menuItem) -> MenuList.builder()
				.label(menuItem.getName())
				.url(menuItem.getNavigationLink())
				.items(
						!ObjectUtils.isEmpty(menuItem.getMenuItems())
						? menuItem.getMenuItems().stream().map(getMenuItemListConverter()).collect(Collectors.toList())
						: null
					 )
				.build();
				
	}

}
