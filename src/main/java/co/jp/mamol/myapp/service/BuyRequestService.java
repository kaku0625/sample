package co.jp.mamol.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.mamol.myapp.dao.BuyRequestDao;
import co.jp.mamol.myapp.dto.CategoryDto;
import co.jp.mamol.myapp.dto.SizaiDto;

@Service
public class BuyRequestService {
	@Autowired
	BuyRequestDao buyRequestDao;
	
	//ユーザ別購入依頼一覧取得
	public List<SizaiDto> getUserRequsetList(String startDate,String endDate,String userId){

		startDate = startDate +" "+ "00:00:00";
		endDate = endDate +" "+"23:59:59";

		List<SizaiDto> sizaiList = buyRequestDao.getUserRequsetList(startDate, endDate,userId);

		return sizaiList;
	}
	
	//購入依頼撤回
	public boolean deleteRequest(int id) {
		boolean res=buyRequestDao.deleteById(id);
		return res;
	}
	
	//購入依頼登録
	public void requestRegist(SizaiDto sizaiDto){
		buyRequestDao.requestRegist(sizaiDto);
	}
	
	//カテゴリ取得
	public List<CategoryDto> getCategory(){
		List<CategoryDto> categoryList=buyRequestDao.getCategory();
		return categoryList;
	}
	
	//購入依頼一件取得
	public SizaiDto getRequest(int id) {
		SizaiDto sizaiDto=
		buyRequestDao.getRequestById(id);
		return sizaiDto;
	}
	
	//BL1005購入依頼変更
	public boolean modifyRequest(SizaiDto sizaiDto){
		
		boolean res =buyRequestDao.modifyRequest(sizaiDto);
		return res;
	}
	


}
