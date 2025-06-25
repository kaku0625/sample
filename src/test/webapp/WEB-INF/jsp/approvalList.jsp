<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="menu.jsp" %>

<html>
<head>
<title>購入承認一覧</title>
</head> 
<body>
<h2>購入承認一覧</h2>
<form name="approvalListform" action="/sizaikanri/approvalList/search" method="post">
<div>
<label>From:</label><input type="date" name="aListForm.startDate" value="<s:property value="aListForm.startDate"/>"/>
<label>To:</label><input type="date" name="aListForm.endDate" value="<s:property value="aListForm.endDate"/>"/> 
<input type="submit" value="検索"/>
</div>
</form><br/>

<s:if test="aListForm.sizaiList.size!=0 && aListForm.sizaiList!=null">

<table class="outputtable">
<tr>
  <th>資材ID</th><th>資材名</th><th>数量</th><th>カテゴリ</th><th>購入依頼者</th><th>購入依頼部門</th>
  <th>状態</th><th>依頼日</th><th>承認</th><th>却下</th>
</tr>
<s:iterator value="aListForm.sizaiList">
<tr>
    <td><s:property value="id"/></td>
    <td><a target="under" href="/sizaikanri//approvalDetail/init?detailForm.id=<s:property value='id'/>">
        <s:property value="name"/></a></td>
    <td><s:property value="num"/></td>
    <td><s:property value="category_name"/></td>
    <td><s:property value="request_user_name"/></td>
    <td><s:property value="request_dept_name"/></td>
    <td><s:property value="status_name"/></td>
    <td><s:property value="request_date"/></td>
    <td><s:if test="status==1||status==3"><a href="/sizaikanri/approvalList/approval?aListForm.id=<s:property value="id"/>">承認</a>
    </s:if></td>
    <td><s:if test="status==1"><a href="/sizaikanri/approvalList/regect?aListForm.id=<s:property value="id"/>">却下</a>
    </s:if></td>
</tr>
</s:iterator>
</table>
</s:if>
<br/>
<iframe  name="under" width="100%" height="400" frameborder="no" >
</iframe>

</body>
</html>
