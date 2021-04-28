package com.marksandspencer.foodshub.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marksandspencer.foodshub.common.service.MenuTileService;
import com.marksandspencer.foodshub.common.transfer.AppResponse;
import com.marksandspencer.foodshub.common.transfer.MenuList;
import com.marksandspencer.foodshub.common.transfer.TileList;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Menu Tile Controller
 *
 */
@RestController
@RequestMapping("/v1/common")
public class MenuTileController {
	
	@Autowired
	private MenuTileService menuTileService;
	
	/**
	 * Dummy end point for Assembly call
	 * @param page
	 * @return String
	 */
	@PostMapping("/assembly")
	public String assemblyFetch(@RequestBody String page) {
		return page;
	}
	
	/***
	 * Get the tiles list based on the page
	 * @param path (eg: Home)
	 * @return the List of Tiles based on page
	 */
	@GetMapping("/tile/{page}")
	@ApiOperation(value = "get tiles of a page", response = AppResponse.class, tags = "getTilesPerPage")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No data"), @ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<AppResponse<List<TileList>>> getTilesPerPage(@PathVariable String page) {
		return new ResponseEntity<>(new AppResponse<>(menuTileService.getTilesList(page), Boolean.TRUE), HttpStatus.OK);
	}
	
	/**
	 * Get the top menus List
	 * @return List of top menus
	 */
	@GetMapping("/top-menus")
	@ApiOperation(value = "get top menu list", response = AppResponse.class, tags = "getTopMenus")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No data"), @ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<AppResponse<List<MenuList>>> getTopMenus() {
		return new ResponseEntity<>(new AppResponse<>(menuTileService.getTopMenus(), Boolean.TRUE), HttpStatus.OK);
	}
	

}
