package com.larry.hms.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class TransferRecordExcelView extends AbstractXlsView{
	private static final Log log = LogFactory.getLog(TransferRecordExcelView.class);
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		response.setHeader("Content-Disposition", "attachment; filename=\"transfer_record.xls\"");
		List<Map<String,Object>> result = (List<Map<String, Object>>) model.get("transferRecordList");
		log.info("data in the excel is :"+result);
		//create excel xls sheet
		String todayStr = sdf.format(new Date());
		Sheet sheet = workbook.createSheet("transferRecord_"+todayStr);
		//create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ҩƷ����");
		header.createCell(1).setCellValue("ҩƷ����");
		header.createCell(2).setCellValue("ҩ����");
		header.createCell(3).setCellValue("ҩ����");
		header.createCell(4).setCellValue("��λ");
		header.createCell(5).setCellValue("��������");
		header.createCell(6).setCellValue("���λ��");
		header.createCell(7).setCellValue("�۸�");
		header.createCell(8).setCellValue("�����");
		//create data cells
		int rowCount = 1;
		for(Map<String,Object> medicineAmt : result){
			Row medicineAmtRow = sheet.createRow(rowCount++);
			medicineAmtRow.createCell(0).setCellValue(medicineAmt.get("name").toString());
			medicineAmtRow.createCell(1).setCellValue(medicineAmt.get("type").toString());
			medicineAmtRow.createCell(2).setCellValue(medicineAmt.get("amount_warehouse").toString());
			medicineAmtRow.createCell(3).setCellValue(medicineAmt.get("amount_storehouse").toString());
			medicineAmtRow.createCell(4).setCellValue(medicineAmt.get("unit").toString());
			if(medicineAmt.get("expiry_date") != null){
				medicineAmtRow.createCell(5).setCellValue(medicineAmt.get("expiry_date").toString());
			}
			
			medicineAmtRow.createCell(6).setCellValue(medicineAmt.get("place").toString());
			medicineAmtRow.createCell(7).setCellValue(medicineAmt.get("price").toString());
			medicineAmtRow.createCell(8).setCellValue(medicineAmt.get("transferAmt").toString());
		}
			
	}

}
