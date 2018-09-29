package com.qfedu.controller;

import org.springframework.core.convert.converter.Converter;

public class CustomerIntConvert implements Converter<String, Integer>{

	@Override
	public Integer convert(String info) {
		if (info == null || info.isEmpty()) {
			return 0;
		}
		
		int v = 0;
		try {
			v = Integer.parseInt(info);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return v;
	}

}
