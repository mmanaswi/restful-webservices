package com.cognizant.truyum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.TruyumApplication;
import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.Cart;

@Service("cartService")
public class CartService {
	private static Logger LOGGER = LoggerFactory.getLogger(TruyumApplication.class);

	@Autowired
	private CartDao cartDao;

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public void addCartItem(long userId, long menuItemId) {
		LOGGER.info("START");
		LOGGER.info("END");
		cartDao.addCartItem(userId, menuItemId);
	}

	public Cart getAllCartItems(long userId) throws CartEmptyException {
		LOGGER.info("START");
		LOGGER.info("END");
		return (Cart) cartDao.getAllCartItems(userId);

	}

	public void removeCartItem(long userId, long menuItemId) throws CartEmptyException {
		LOGGER.info("START");
		LOGGER.info("END");
		cartDao.removeCartItem(userId, menuItemId);
	}
}