<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    

<!DOCTYPE html>
 <c:forEach items="${bidderList}" var="bidder" varStatus="i">
				<tr>
					<th scope="row">${i.count }</th>
					<td style="width: 40%; text-align: center;">
						<div>${bidder.bidderNick }</div>
					</td>
					<td>
						<div style="color:rgb(39, 178, 165);"><fmt:formatNumber value="${bidder.bidMoney }" pattern="#,###"/> 원</div>
					</td>
				</tr>
	</c:forEach>