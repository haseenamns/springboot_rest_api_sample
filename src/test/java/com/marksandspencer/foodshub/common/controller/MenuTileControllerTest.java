package com.marksandspencer.foodshub.common.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marksandspencer.foodshub.common.service.MenuTileService;
import com.marksandspencer.foodshub.common.transfer.AppResponse;
import com.marksandspencer.foodshub.common.transfer.MenuList;
import com.marksandspencer.foodshub.common.transfer.TileList;

@RunWith(MockitoJUnitRunner.class)
public class MenuTileControllerTest {

	@InjectMocks
	MenuTileController menuTileController = new MenuTileController();
	
	@Mock
	MenuTileService menuTileService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void beforeTest() {
		Assert.assertEquals(true, Boolean.TRUE);
	}
	
	@Test
	public void getTilesPerPage() {
		List<TileList> tilesList=new ArrayList<>();
		TileList tile=new TileList();
		tilesList.add(tile);
		when(menuTileService.getTilesList(Mockito.any())).thenReturn(tilesList);
		ResponseEntity<AppResponse<List<TileList>>> actual = menuTileController.getTilesPerPage("home");
		Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test
	public void getTopMenus() {
		List<MenuList> menusList=new ArrayList<>();
		MenuList menuItem=new MenuList();
		menusList.add(menuItem);
		when(menuTileService.getTopMenus()).thenReturn(menusList);
		ResponseEntity<AppResponse<List<MenuList>>> actual = menuTileController.getTopMenus();
		Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}