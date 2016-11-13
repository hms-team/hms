package com.larry.hms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.larry.hms.entity.MedicineAmt;
import com.larry.hms.entity.MedicineTransferRecord;
import com.larry.hms.mapper.MedicineAmtMapper;
import com.larry.hms.service.MedicineAmtService;
import com.larry.hms.service.MedicineTransferRecordService;
@Service("medicineAmtService")
public class MedicineAmtServiceImpl implements MedicineAmtService{
	@Autowired
	MedicineAmtMapper medicineAmtMapper;
	@Autowired
	MedicineTransferRecordService medicineTransferRecordService;
	@Override
	public void addMedicineAmt(MedicineAmt medAmt) {
		medicineAmtMapper.insertMedicineAmount(medAmt);
	}

	@Override
	public List<MedicineAmt> listByPagination(int currentPage, int numPerPage,MedicineAmt medicineAmt) {
		int offset = (currentPage-1)*numPerPage;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("rows", numPerPage);
		params.put("medicineAmt",medicineAmt);
		return medicineAmtMapper.selectAllByPage(params);
	}

	@Override
	public List<MedicineAmt> listAll() {
		return medicineAmtMapper.selectAll();
	}

	@Override
	public int countAll(MedicineAmt medicineAmt) {
		return medicineAmtMapper.countAll(medicineAmt);
	}

	@Override
	public MedicineAmt getMedicineAmtById(int medId) {
		return medicineAmtMapper.selectById(medId);
	}

	@Override
	public void update(MedicineAmt medicineAmt) {
		medicineAmtMapper.updateMedicineAmount(medicineAmt);
		
	}

	@Override
	public void deleteById(int medId) {
		medicineAmtMapper.deleteMedicineAmountById(medId);
	}

	@Override
	@Transactional
	public MedicineAmt transfer(MedicineTransferRecord record) throws Exception {
		int medId = record.getMedId();
		MedicineAmt medicineAmt = this.getMedicineAmtById(medId);
		if(medicineAmt == null){
			throw new Exception("找不到该药品");
		}
		int amtInWareHouse = medicineAmt.getAmountWarehouse();
		int amtInStoreHouse = medicineAmt.getAmountStorehouse();
		if("WARE_HS".equalsIgnoreCase(record.getDestination())){
			medicineAmt.setAmountWarehouse(amtInWareHouse+record.getAmount());
		}else if("STORE_HS".equalsIgnoreCase(record.getDestination()) && "WARE_HS".equalsIgnoreCase(record.getSource())){
			medicineAmt.setAmountStorehouse(amtInStoreHouse+record.getAmount());
			int newAmtInWareHouse = amtInWareHouse - record.getAmount();
			if( newAmtInWareHouse < 0 ){
				throw new Exception("库存不足");
			}
			medicineAmt.setAmountWarehouse(newAmtInWareHouse);
		}else if("STORE_HS".equalsIgnoreCase(record.getSource()) && "CONSUME".equalsIgnoreCase(record.getDestination())){
			if(amtInStoreHouse < record.getAmount()){
				throw new Exception("药房库存不足");
			}
			medicineAmt.setAmountStorehouse(amtInStoreHouse - record.getAmount());
		}
		this.update(medicineAmt);
		medicineTransferRecordService.addMedicineTransferRecord(record);
		return medicineAmt;
	}
	
}
