package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.TruyumApplication;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
@Component("cartDao")
public class CartDaoCollectionImpl implements CartDao {
	private static Logger LOGGER = LoggerFactory.getLogger(TruyumApplication.class);
	private  HashMap<String, Cart>userCarts;//here long is used for userId and Cart for shopping items
	
	public CartDaoCollectionImpl() {
		if(this.userCarts==null)
		{
			HashMap<String, Cart> cartMap=new HashMap<>();
			List<MenuItem> list=new ArrayList<>();
			list.add(new MenuItem(1,"Sandwich", (float) 99.0, true, DateUtil.convertToDate("15/07/2017"), "Main Course", true));
			list.add(new MenuItem(2,"Burger", (float) 129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			double total=0.0;
			for(MenuItem m:list)
			{
				total+=m.getPrice();
			}
			Cart ob=new Cart(list,total);
			cartMap.put("123L",ob);
			total=0.0;
			ob=null;
			list=new ArrayList<>();
			list.add(new MenuItem(3,"Pizza", (float) 149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			list.add(new MenuItem(4,"French Fries", (float) 57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			list.add(new MenuItem(5,"Chocolate Brownie", (float) 32.0, true, DateUtil.convertToDate("02/11/2022"), "Desert", true));
			for(MenuItem m:list)
			{
				total+=m.getPrice();
			}
			ob=new Cart(list,total);
			cartMap.put("456L",ob);
			
			setUserCarts(cartMap);
		}
	}
	public HashMap<String, Cart> getUserCarts() {
		return this.userCarts;
	}

	public  void setUserCarts(HashMap<String, Cart> userCarts) {
		this.userCarts = userCarts;
	}


	@SuppressWarnings("null")
	@Override
	public void addCartItem(long userId, long menuItemId) {
		LOGGER.info("START");
		Cart cartMap=this.userCarts.get(userId);
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		
		if(cartMap==null)
		{	
			List<MenuItem> list=new ArrayList<>();
					list.add(menuItemDao.getMenuItem(menuItemId));
			double total=menuItemDao.getMenuItem(menuItemId).getPrice();
			cartMap=new Cart(list,total);
			
			this.userCarts.put(String.valueOf(userId),cartMap);
		}
		else
		{
			List<MenuItem> list=cartMap.getMenuItemList();
			list.add(menuItemDao.getMenuItem(menuItemId));
			double total=cartMap.getTotal()+menuItemDao.getMenuItem(menuItemId).getPrice();
			cartMap.setTotal(total);
			cartMap.setMenuItemList(list);
			this.userCarts.put(String.valueOf(userId), cartMap);
		}
		LOGGER.info("END");
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		LOGGER.info("START");
		if(this.userCarts.isEmpty())
		{
			throw new CartEmptyException("Empty List found exception");
		}
		LOGGER.info("END");
		return this.userCarts.get(String.valueOf(userId)).getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId)
	{
		Cart cartMap=this.userCarts.get(userId);
		if(cartMap== null)
		{
			try {
				throw new CartEmptyException("No user found");
			} catch (CartEmptyException e) {
				System.out.println(e.toString());
			}
		}
		List<MenuItem> list=cartMap.getMenuItemList();
		int i=0;
		for(MenuItem m:list)
		{
			if(m.getId()== menuItemId)
			{
				double total=cartMap.getTotal()-(m.getPrice());
				cartMap.setTotal(total);
				list.remove(i);
				break;
			}
			i++;
		}
		cartMap.setMenuItemList(list);
		this.userCarts.put(String.valueOf(userId), cartMap);	
		
	}

}
