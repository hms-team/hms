package com.larry.hms.mapper;

import java.util.List;
import java.util.Map;

import com.larry.hms.entity.MedicineAmt;

public interface MedicineAmtMapper {
	public void insertMedicineAmount(MedicineAmt medicineAmt);

	public void updateMedicineAmount(MedicineAmt medicineAmt);

	public void deleteMedicineAmountById(int medId);

	public List<MedicineAmt> selectAll();
	
	public List<MedicineAmt> search(Map<String, String> parameters);
	
	public List<MedicineAmt> selectByEntity(MedicineAmt medicineAmt);
	
	public List<MedicineAmt> selectAllByPage(Map<String, Object> params);
	
	public int countAll(MedicineAmt medicineAmt);

	public MedicineAmt selectById(int medId);
}
