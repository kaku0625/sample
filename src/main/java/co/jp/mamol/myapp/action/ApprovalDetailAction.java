package co.jp.mamol.myapp.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import co.jp.mamol.myapp.dto.SizaiDto;
import co.jp.mamol.myapp.form.ApprovalDetailForm;
import co.jp.mamol.myapp.service.BuyApprovalService;

@Results({
	@Result(name="approvalDetail",location="/WEB-INF/jsp/approvalDetail.jsp")
})

public class ApprovalDetailAction extends BaseAction{

	private ApprovalDetailForm detailForm = new ApprovalDetailForm();
	
	@Autowired
	private BuyApprovalService aService;
	
	@Action("/approvalDetail/init")
	public String init() {
		SizaiDto s=
		aService.getRequest(detailForm.getId());
		
		detailForm.setSizaiDto(s);
		
		return "approvalDetail";
		
	}

	public ApprovalDetailForm getDetailForm() {
		return detailForm;
	}

	public void setDetailForm(ApprovalDetailForm detailForm) {
		this.detailForm = detailForm;
	}
	
	
	
}
