package com.larry.hms.service;

import java.util.List;

import com.larry.hms.entity.MedicineAmt;
import com.larry.hms.entity.MedicineTransferRecord;

public interface MedicineAmtService {
	public void addMedicineAmt(MedicineAmt medAmt);

	public List<MedicineAmt> listByPagination(int currentPage, int numPerPage,MedicineAmt medAmt);

	public List<MedicineAmt> listAll();
	
	public int countAll(MedicineAmt medAmt);

	public MedicineAmt getMedicineAmtById(int medId);

	public void update(MedicineAmt medicineAmt);

	public void deleteById(int medId);

	public MedicineAmt transfer(MedicineTransferRecord record) throws Exception;
}
