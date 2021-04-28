package com.marksandspencer.foodshub.common.service;

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

import com.marksandspencer.foodshub.common.constant.ErrorCode;
import com.marksandspencer.foodshub.common.domain.Flag;
import com.marksandspencer.foodshub.common.domain.MenuAndTile;
import com.marksandspencer.foodshub.common.domain.MenuItem;
import com.marksandspencer.foodshub.common.exception.CommonServiceException;
import com.marksandspencer.foodshub.common.repository.MenusAndTilesRepository;
import com.marksandspencer.foodshub.common.service.impl.MenuTileServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MenuTileServiceTest {

	@InjectMocks
	MenuTileService menuTileService = new MenuTileServiceImpl();

	@Mock
	private MenusAndTilesRepository menusAndTilesRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void beforeTest() {
		Assert.assertEquals(true, Boolean.TRUE);
	}
	
	@Test
	public void getTilesListSuccessScenario() {
		List<MenuAndTile> tilesList = new ArrayList<>();
		MenuAndTile tile = new MenuAndTile();
		tile.setName("Test Name");
		tile.setDescription("Test Description");
		tilesList.add(tile);
		Flag flags=new Flag();
		tile.setFlags(flags);
		when(menusAndTilesRepository.findByParent(Mockito.anyString())).thenReturn(tilesList);
		Assert.assertEquals("Test Name",
				menuTileService.getTilesList("home").stream().findFirst().get().getHeading());
	}
	
	@Test
	public void getTilesListNoDataScenario() {
		expectedException.expect(CommonServiceException.class);
		expectedException.expectMessage(ErrorCode.NO_DATA.getErrorMessage());
		when(menusAndTilesRepository.findByParent(Mockito.anyString())).thenReturn(null);
		menuTileService.getTilesList("abcd");
	}
	
	@Test
	public void getTopMenusSuccessScenario() {
		List<MenuAndTile> topMenus = new ArrayList<>();
		MenuAndTile topMenu = new MenuAndTile();
		topMenu.setName("Test Name");
		topMenu.setDescription("Test Description");
		topMenus.add(topMenu);
		Flag flags=new Flag();
		topMenu.setFlags(flags);
		MenuItem menuItem=new MenuItem();
		List<MenuItem> menuItems=new ArrayList<>();
		menuItems.add(menuItem);
		topMenu.setMenuItems(menuItems);
		when(menusAndTilesRepository.findByMenuItemsNotNull()).thenReturn(topMenus);
		Assert.assertEquals("Test Name",
				menuTileService.getTopMenus().stream().findFirst().get().getLabel());
	}
	
	@Test
	public void getTopMenusNoDataScenario() {
		expectedException.expect(CommonServiceException.class);
		expectedException.expectMessage(ErrorCode.NO_DATA.getErrorMessage());
		when(menusAndTilesRepository.findByMenuItemsNotNull()).thenReturn(null);
		menuTileService.getTopMenus();
	}

}