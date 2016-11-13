package com.larry.hms.mapper;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.larry.hms.entity.MedicineAmt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring-mybatis.xml"})
public class MedicineAmtMapperTest {
	
	@Autowired
	MedicineAmtMapper medicineAmtMapper;
	@Autowired
	SqlSessionTemplate sqlSession;
	@Test
	public void validate(){
		assertTrue(medicineAmtMapper != null);
	}
	@Test
	public void insertTest() throws ParseException{
		String dateFormat = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		MedicineAmt mediAmt = new MedicineAmt();
		mediAmt.setName("999¸ÐÃ°Áé¿ÅÁ£");
		mediAmt.setAmountWarehouse(4);
		mediAmt.setAmountStorehouse(0);
		mediAmt.setExpiryDate(sdf.parse("20170701"));
		mediAmt.setPlace("G1¡¢D6");
		mediAmt.setType("drug");
		mediAmt.setPrice(new BigDecimal("10.64567"));
		mediAmt.setUnit("ºÐ");
		medicineAmtMapper.insertMedicineAmount(mediAmt);
		System.out.println("insert result "+mediAmt);
		assertTrue(mediAmt.getMedId() > 0);
	}
	@Test
	public void updateTest() throws ParseException{
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
		medicineAmtMapper.updateMedicineAmount(mediAmt);
		System.out.println(mediAmt);
	}
	@Test
	public void deleteTest(){
		medicineAmtMapper.deleteMedicineAmountById(6);
	}
	@Test
	public void findAllTest(){
		List<MedicineAmt> result = medicineAmtMapper.selectAll();
		System.out.println(result);
	}
	@Test
	public void pagination(){
		List<MedicineAmt> rs = sqlSession.selectList("com.larry.hms.mapper.MedicineAmtMapper.selectAll", null, new RowBounds(0,3));
		System.out.println(rs);
	}
	@Test
	public void searchTest(){
		Map<String, String> params = new HashMap<>();
		params.put("name", "999");
		params.put("type", "drug");
		params.put("place", "G1");
		List<MedicineAmt> result = medicineAmtMapper.search(params);
		System.out.println(result);
	}
	@Test
	public void selectByEntityTest() throws ParseException{
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
		List<MedicineAmt> rs = medicineAmtMapper.selectByEntity(mediAmt);
		System.out.println(rs);
	}
	@Test
	public void selectAllByPageTest(){
		Map<String, Object> params = new HashMap<>();
		params.put("offset", 2);
		params.put("rows", 1);
		List<MedicineAmt> result = medicineAmtMapper.selectAllByPage(params);
		System.out.println(result);
	}
	@Test
	public void match(){
		String REGEX = "^\\s*[Ss][Ee][Ll][Ee][Cc][Tt].*$";
		String sql = "select\n\t\tmed_id,name,type,amount_warehouse,amount_storehouse,unit,expiry_date,place,price\n\tfrom med_amt".trim();
		sql = sql.replaceAll("\r|\n", "");
		System.out.println(sql);
		assertTrue(sql.matches(REGEX));
	}
}
