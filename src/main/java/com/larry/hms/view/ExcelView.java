package com.larry.hms.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.larry.hms.entity.MedicineAmt;

public class ExcelView extends AbstractXlsView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		response.setHeader("Content-Disposition", "attachment; filename=\"medicineAmount.xls\"");
		List<MedicineAmt> medicineAmts = (List<MedicineAmt>) model.get("medicineAmts");
		//create excel xls sheet
		Sheet sheet = workbook.createSheet("medicineAmt");
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
		
		//create data cells
		int rowCount = 1;
		for(MedicineAmt medicineAmt : medicineAmts){
			Row medicineAmtRow = sheet.createRow(rowCount++);
			medicineAmtRow.createCell(0).setCellValue(medicineAmt.getName());
			medicineAmtRow.createCell(1).setCellValue(medicineAmt.getType());
			medicineAmtRow.createCell(2).setCellValue(medicineAmt.getAmountWarehouse());
			medicineAmtRow.createCell(3).setCellValue(medicineAmt.getAmountStorehouse());
			medicineAmtRow.createCell(4).setCellValue(medicineAmt.getUnit());
			if(medicineAmt.getExpiryDate() != null){
				medicineAmtRow.createCell(5).setCellValue(sdf.format(medicineAmt.getExpiryDate()));
			}
			
			medicineAmtRow.createCell(6).setCellValue(medicineAmt.getPlace());
			medicineAmtRow.createCell(7).setCellValue(medicineAmt.getPrice().doubleValue());
		}
			
	}

}
