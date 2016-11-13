package com.larry.hms.mapper;

import java.util.List;
import java.util.Map;

import com.larry.hms.entity.MedicineTransferRecord;

public interface MedicineTransferRecordMapper {
	public void insertMedicineTransferRecord(MedicineTransferRecord record);
	public List<Map<String,Object>> transferRecordList(Map<String,Object> params);
}
