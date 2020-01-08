package com.johnbryce.CouponSystem.Config;

import com.johnbryce.CouponSystem.enums.CouponType;

public class CouponTypeConverter {

	private static CouponType couponType;
	
	public static CouponType convertStringToType(String typeName) {
		
		switch (typeName) {
		case "Restaurants":
			couponType = CouponType.RESTAURANT;
			break;
		case "Health":
			couponType = CouponType.FOOD;
			break;
		case "Sports":
			couponType = CouponType.VACATION;
			break;
		case "Traveling":
			couponType = CouponType.ELECTRICITY;
			break;
		default:
			break;
		}
		
		return couponType;
		
	}
}
