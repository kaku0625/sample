package myapp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.jp.mamol.myapp.dto.SizaiDto;

public interface BuyApprovalDao {
	
	//DA2001部門別購入依頼一覧
	public List<SizaiDto> getDeptRequsetList(@Param("startDate") String startDate,
			@Param("endDate") String endDate,@Param("depid") String depid);
	
	//DA2003承認実行
	public boolean approval(@Param("id") int id);
	
	//DA2004却下実行
	public boolean regect(@Param("id") int id);
	
	//DA2002購入依頼一件取得
	public SizaiDto getRequestById(@Param("id") int id);

}
