package com.cognizant.truyum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@RestController
public class MeuItemController 
{
	@Autowired
	MenuItemService menuItemService;
	@GetMapping("/menu-items")
	public List<MenuItem> getItemCustomer()
	{
		List<MenuItem> list=menuItemService.getMenuItemsCustomer();
		
		return list; 
	}
	
	@GetMapping("/{id}")
	public MenuItem getMenuItem(@RequestParam long id)
	{
		MenuItem item=menuItemService.getMenuItem(id);
		
		return item;
	}
	@PutMapping("/")
	public MenuItem modifyMenuItem(@RequestBody MenuItem item)
	{
		MenuItem item1=menuItemService.modifyMenuItem(item);
		
		return item;
	}

}
