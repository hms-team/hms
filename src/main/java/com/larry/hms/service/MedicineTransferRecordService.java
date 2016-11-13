package com.larry.hms.service;

import java.util.List;
import java.util.Map;

import com.larry.hms.entity.MedicineTransferRecord;

public interface MedicineTransferRecordService {
	public void addMedicineTransferRecord(MedicineTransferRecord record);
	public List<Map<String,Object>> wareHouseTrsRdList(Map<String,Object> params);
}
