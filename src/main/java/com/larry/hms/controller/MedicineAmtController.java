package com.larry.hms.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.larry.hms.common.ResponseInfo;
import com.larry.hms.entity.MedicineAmt;
import com.larry.hms.entity.MedicineTransferRecord;
import com.larry.hms.service.MedicineAmtService;
import com.larry.hms.service.MedicineTransferRecordService;

@Controller
@RequestMapping("/medicineAmt")
public class MedicineAmtController {
	private static final Log log = LogFactory.getLog(MedicineAmtController.class);
	@Autowired
	private MedicineAmtService medicineAmtService;
	@Autowired
	private MedicineTransferRecordService medicineTransferRecordService;
	@RequestMapping("/hello")
    public @ResponseBody ResponseInfo<List<MedicineAmt>> test() throws ParseException {
		log.info("testing...");
		ResponseInfo<List<MedicineAmt>> rs = new ResponseInfo<List<MedicineAmt>>();
		rs.setRtnCode("000");
		rs.setRtnMessage("success");
		rs.setIsPagination("N");
		List<MedicineAmt> data = new ArrayList<MedicineAmt>();
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
		data.add(mediAmt);
		rs.setData(data);
		return rs;
    }

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<String> addMedicineAmt(@RequestBody Map<String, Object> requestMap){
		log.info("receiving request message"+requestMap);
		ResponseInfo<String> response = new ResponseInfo<String>();
		try{
			String jsonStr = JSONObject.toJSONString(requestMap);
			MedicineAmt medicineAmt = JSONObject.parseObject(jsonStr, MedicineAmt.class);
			log.info("medicine amount entity :"+medicineAmt);
			medicineAmtService.addMedicineAmt(medicineAmt);
			response.setRtnCode("000");
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/list", method= RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<List<MedicineAmt>> listMedicineAmts(@RequestBody Map<String,Object> requestMap){
		log.info("Received request message:"+requestMap);
		ResponseInfo<List<MedicineAmt>> response  = new ResponseInfo<List<MedicineAmt>>();
		try{
			String jsonStr = JSONObject.toJSONString(requestMap);
			MedicineAmt medicineAmt = JSONObject.parseObject(jsonStr, MedicineAmt.class);
			int totalCount = medicineAmtService.countAll(medicineAmt);
			if(requestMap.containsKey("isPagination") && requestMap.get("isPagination").equals("Y")){
				int currentPage = Integer.parseInt(requestMap.get("currentPage") == null ? "1" : requestMap.get("currentPage").toString());
				int numPerPage = Integer.parseInt(requestMap.get("numPerPage") == null ? "5" : requestMap.get("numPerPage").toString());
				List<MedicineAmt> medicineAmts = medicineAmtService.listByPagination(currentPage,numPerPage,medicineAmt);
				response.setIsPagination(requestMap.get("isPagination").toString());
				response.setCurrentPage(currentPage);
				response.setNumPerPage(numPerPage);
				response.setData(medicineAmts);
				response.setTotalCount(totalCount);
				response.setRtnCode("000");
			}else{
				List<MedicineAmt> medicineAmts = medicineAmtService.listAll();
				response.setIsPagination("N");
				response.setTotalCount(totalCount);
				response.setData(medicineAmts);
				response.setRtnCode("000");
			}
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		
		return response;
	}
	
	@RequestMapping(value="/getById", method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<MedicineAmt> getMedicineAmtById(@RequestBody Map<String,Object> requestMap){
		log.info("==/getById== receiving the request :"+requestMap);
		ResponseInfo<MedicineAmt> response = new ResponseInfo<MedicineAmt>();
		try{
			int medId = Integer.parseInt(requestMap.get("medId").toString());
			MedicineAmt medAmt = medicineAmtService.getMedicineAmtById(medId);
			response.setRtnCode("000");
			response.setData(medAmt);
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		return response;
	}
	@RequestMapping(value="/export",method=RequestMethod.GET)
	public ModelAndView getDocuments(ModelMap model){
		List<MedicineAmt> medicineAmts = medicineAmtService.listAll();
		model.put("medicineAmts", medicineAmts);
		return new ModelAndView("excelView",model);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<String> updateMedicineAmt(@RequestBody Map<String,Object> requestMap){
		log.info("=/medicineAmt/update= receiving the request:"+requestMap);
		ResponseInfo<String> response = new ResponseInfo<String>();
		try{
			String jsonStr = JSONObject.toJSONString(requestMap);
			MedicineAmt medicineAmt = JSONObject.parseObject(jsonStr, MedicineAmt.class);
			medicineAmtService.update(medicineAmt);
			response.setRtnCode("000");
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<String> deleteMedicineAmt(@RequestBody Map<String,Object> requestMap){
		log.info("=/medicineAmt/deleteById= receiving the request:"+requestMap);
		ResponseInfo<String> response = new ResponseInfo<String>();
		try{
			if(requestMap.get("medId") == null){
				throw new Exception("medId cannot be null");
			}
			int medId = Integer.parseInt(requestMap.get("medId").toString());
			medicineAmtService.deleteById(medId);
			response.setRtnCode("000");
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		return response;
	}
	@RequestMapping(value="/transfer",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo<MedicineAmt> transfer(@RequestBody Map<String,Object> requestMap){
		log.info("/transfer receiving the request:"+requestMap);
		ResponseInfo<MedicineAmt> response = new ResponseInfo<MedicineAmt>();
		try{
			String jsonStr = JSONObject.toJSONString(requestMap);
			MedicineTransferRecord record = JSONObject.parseObject(jsonStr, MedicineTransferRecord.class);
			record.setDate(new Date());
			MedicineAmt medcineAmt= medicineAmtService.transfer(record);
			response.setData(medcineAmt);
			response.setRtnCode("000");
		}catch(Exception exception){
			log.error(exception.getMessage(),exception);
			response.setRtnCode("999");
			response.setRtnMessage(exception.getMessage());
		}
		return response;
	}
	@RequestMapping(value="/transferRecordList",method=RequestMethod.GET)
	public ModelAndView transferRecordList(ModelMap model, HttpServletRequest request){
		log.info("/list the transfer record by parameters:"+request);
		ResponseInfo<List<MedicineTransferRecord>> response = new ResponseInfo<List<MedicineTransferRecord>>();
		
		String sdTime = request.getParameter("sd_time");
		String edTime = request.getParameter("ed_time");
		String destination = request.getParameter("destination");
		log.info("sdTime:"+sdTime+",edTime:"+edTime+",destination:"+destination);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sdTime", sdTime);
		params.put("edTime", edTime);
		params.put("destination", destination);
		List<Map<String,Object>> result = medicineTransferRecordService.wareHouseTrsRdList(params);
		model.put("transferRecordList", result);
		log.info("result:"+result);
		return new ModelAndView("transferRecordExcelView",model);
	}
}
