package myapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import co.jp.mamol.myapp.action.BaseAction;
import co.jp.mamol.myapp.dto.SizaiDto;
import co.jp.mamol.myapp.dto.UserDto;
import co.jp.mamol.myapp.service.BuyApprovalService;

@Results({
		@Result(name = "approvalList", location = "/WEB-INF/jsp/approvalList.jsp"),
		@Result(name = "init", location = "/approvalList/init", type = "redirect")
})

public class ApprovalListAction extends BaseAction {

	private ApprovalListForm aListForm = new ApprovalListForm();

	@Autowired
	private BuyApprovalService aService;

	@Action("/approvalList/init")
	public String init() {

		//現在日付を取得する
		LocalDate currentDate = LocalDate.now();
		//"yyyy-MM-dd"の日付フォーマットを定義する。
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//処理2で定義した日付フォーマットで、処理1で取得した現在日付を文字列に変換する。
		String currentDateString = currentDate.format(formatter);
		//処理1で取得した現在日付に基づき、一か月前の日付を取得する。
		LocalDate monthDate = currentDate.minusMonths(1);
		//処理2で定義した日付フォーマットで、処理４で取得した先月日付を文字列に変換する。
		String monthDateString = monthDate.format(formatter);
		//現在日付Stringを画面フォーム.終了日付に設定
		aListForm.setEndDate(currentDateString);
		//「先月日付String」を画面フォーム.開始日付に設定する
		aListForm.setStartDate(monthDateString);
		//共通機能-ログインユーザ情報取得を呼び出し、ユーザDTOを取得する
		UserDto useDto = getLoginUser();

		List<SizaiDto> sizaiList = aService.getDeptRequsetList(monthDateString, currentDateString, useDto.getDepid());

		if (sizaiList == null || sizaiList.size() == 0) {
			setMessage("指定期間の購入依頼が登録されていません", false);
		} else {
			aListForm.setSizaiList(sizaiList);
		}
		return "approvalList";
	}

	@Action("/approvalList/search")
	public String search() {
		UserDto useDto = getLoginUser();

		List<SizaiDto> sizaiList = aService.getDeptRequsetList(aListForm.getStartDate(), aListForm.getEndDate(),
				useDto.getDepid());

		if (sizaiList == null || sizaiList.size() == 0) {
			setMessage("指定期間の購入依頼が登録されていません", false);
		} else {
			aListForm.setSizaiList(sizaiList);
		}
		return "approvalList";
	}

	@Action("/approvalList/approval")
	public String approval() {

		aService.approval(aListForm.getId());

		return "init";
	}

	@Action("/approvalList/regect")
	public String regect() {

		aService.regect(aListForm.getId());

		return "init";
	}

	public ApprovalListForm getaListForm() {
		return aListForm;
	}

	public void setaListForm(ApprovalListForm aListForm) {
		this.aListForm = aListForm;
	}

}
