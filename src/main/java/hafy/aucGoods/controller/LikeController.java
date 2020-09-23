package hafy.aucGoods.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import hafy.aucGoods.service.AucGoodsService;
import hafy.aucGoods.vo.AucGoodsVO;
import hafy.aucGoods.vo.LikeVO;
import hafy.bid.service.BidService;
import hafy.bid.vo.NoticeVO;
import hafy.member.vo.MemberVO;

@RestController
public class LikeController {
	
	@Autowired
	private AucGoodsService aucGoodsService;
	@Autowired
	private BidService bidService;
	
	@PutMapping("incrementLike/{aucNo}")
	public void incrementLikeCnt(@PathVariable("aucNo")int aucNo, HttpSession session) {
//		System.out.println("읽긴하겠지?");
		
		// 로그인한 멤버 닉네임 가져오기
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		String memberNick = memberVO.getNickname();
		
		LikeVO likeVO = new LikeVO(memberNick, aucNo);

		aucGoodsService.insertLike(likeVO);
		aucGoodsService.incrementLikeCnt(aucNo);
		
		// 알림에 추가하기
		AucGoodsVO aucGoodsVO = aucGoodsService.selectAucGoodsByNo(aucNo);
		String notiMsg = memberNick + " 님이 회원님의 '" + aucGoodsVO.getName() + "' 경매(번호: " + aucNo + ")를 좋아합니다.";
		
		NoticeVO noticeVO = new NoticeVO(aucGoodsVO.getMemberNick(), "goodsDetail", aucNo, notiMsg);
		bidService.insertNoti(noticeVO);
		
		
	}
	
	@PutMapping("decrementLike/{aucNo}")
	public void decrementLikeCnt(@PathVariable("aucNo")int aucNo, HttpSession session) {
//		System.out.println("읽긴하겠지?");
		
		// 로그인한 멤버 닉네임 가져오기
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		String memberNick = memberVO.getNickname();
		
		LikeVO likeVO = new LikeVO(memberNick, aucNo);

		aucGoodsService.deleteLike(likeVO);
		aucGoodsService.decrementLikeCnt(aucNo);
		
	}
	
	

}
