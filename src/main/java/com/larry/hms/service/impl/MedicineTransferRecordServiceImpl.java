package com.larry.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.hms.entity.MedicineTransferRecord;
import com.larry.hms.mapper.MedicineTransferRecordMapper;
import com.larry.hms.service.MedicineTransferRecordService;

@Service("MedicineTransferRecordService")
public class MedicineTransferRecordServiceImpl implements MedicineTransferRecordService {
	@Autowired
	private MedicineTransferRecordMapper medicineTransferRecordMapper;

	@Override
	public void addMedicineTransferRecord(MedicineTransferRecord record) {
		medicineTransferRecordMapper.insertMedicineTransferRecord(record);

	}

	@Override
	public List<Map<String, Object>> wareHouseTrsRdList(Map<String, Object> params) {
		return medicineTransferRecordMapper.transferRecordList(params);
	}

}
