package co.jp.mamol.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.mamol.myapp.dto.SizaiDto;
import myapp.BuyApprovalDao;

@Service
public class BuyApprovalService {
	@Autowired
	BuyApprovalDao buyApprovalDao;
	
	//BL2001部門別購入依頼一覧取得
	public List<SizaiDto>getDeptRequsetList(String startDate,String endDate,String depid) {
		
		startDate = startDate +" "+ "00:00:00";
		endDate = endDate +" "+"23:59:59";
		
		List<SizaiDto> sizaiList=
		buyApprovalDao.getDeptRequsetList(startDate, endDate, depid);
		
		return sizaiList;
	}
	
	//BL2003承認実行
	public boolean approval(int id) {
		
		boolean res=buyApprovalDao.approval(id);
		
		return res;
	}
	
	//BL2004却下実行
     public boolean regect(int id) {
		
		boolean res=buyApprovalDao.regect(id);
		
		return res;
	} 
	
     //BL2002購入依頼一件取得
     public SizaiDto getRequest(int id) {
    	 
    	 SizaiDto sizaiDto=
    	 buyApprovalDao.getRequestById(id);
    	 return sizaiDto;
     }
     

}
