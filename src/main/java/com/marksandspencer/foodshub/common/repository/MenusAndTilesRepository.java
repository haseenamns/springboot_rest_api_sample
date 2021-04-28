package com.marksandspencer.foodshub.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marksandspencer.foodshub.common.domain.MenuAndTile;

/**
 * Interface of Menu Repository
 */
@Repository
public interface MenusAndTilesRepository extends MongoRepository<MenuAndTile, String>{
	
	/**
	 * Return the tiles list based on parent
	 * @param parent
	 * @return List of Tiles
	 */
	List<MenuAndTile> findByParent(String parent);
	
	/**
	 * Return the menus list
	 * @return List of Menus
	 */
	List<MenuAndTile> findByMenuItemsNotNull();

}
