package com.larry.hms.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class MedAmtTest {
	@Test
	public void obj2json() throws ParseException{
		String dateFormat = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		MedicineAmt mediAmt = new MedicineAmt();
		mediAmt.setMedId(5);
		mediAmt.setName("999Æ¤Ñ×Æ½");
		mediAmt.setAmountWarehouse(5);
		mediAmt.setAmountStorehouse(0);
		mediAmt.setExpiryDate(sdf.parse("20181201"));
		mediAmt.setPlace("C6");
		mediAmt.setType("drug");
		mediAmt.setPrice(new BigDecimal("8.85"));
		mediAmt.setUnit("ºÐ");
		String jsonStr = JSONObject.toJSONString(mediAmt);
		System.out.println(jsonStr);
	}
	@Test
	public void json2obj(){
		String jsonStr = "{\"amountStorehouse\":0,\"amountWarehouse\":5,\"expiryDate\":1543593600000,\"medId\":5,\"name\":\"999Æ¤Ñ×Æ½\",\"place\":\"C6\",\"price\":8.85,\"type\":\"drug\",\"unit\":\"ºÐ\"}";
		MedicineAmt rs = JSONObject.parseObject(jsonStr, MedicineAmt.class);
		System.out.println(rs);
	}
	@Test
	public void parseInt(){
		int rs = Integer.parseInt("2");
	}
}
