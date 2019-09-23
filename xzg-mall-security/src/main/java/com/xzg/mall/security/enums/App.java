package com.xzg.mall.security.enums;

public enum App {

	/**
	 * 小程序
	 */
	MINI(1),

	/**
	 * 微信公众号
	 */
	MP(2)
	;
	
	private Integer num;
	
	public Integer value() {
		return num;
	}
	
	App(Integer num){
		this.num = num;
	}
	
	public static App instance(Integer value) {
		App[] enums = values();
		for (App statusEnum : enums) {
			if (statusEnum.value().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}
}
