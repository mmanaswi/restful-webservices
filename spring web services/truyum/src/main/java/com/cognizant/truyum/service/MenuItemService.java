package com.cognizant.truyum.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.*;

@Service
public class MenuItemService
{
	@Autowired
	@Qualifier("menuItemDao")
	private MenuItemDao menuItemDao;
	
	public List<MenuItem> getAllItemsAdmin()
	{
		return menuItemDao.getMenuItemListAdmin();
	}

	public List<MenuItem> getMenuItemsCustomer() {
		// TODO Auto-generated method stub
		return menuItemDao.getMenuItemListCustomer();
	}

	public MenuItem getMenuItem(long id) {
		// TODO Auto-generated method stub
		return menuItemDao.getMenuItem(id);
	}
	public MenuItem modifyMenuItem(MenuItem item)
	{
		MenuItem menuItem=menuItemDao.modifyMenuItem(item);
		return menuItem;
	}
	
}
