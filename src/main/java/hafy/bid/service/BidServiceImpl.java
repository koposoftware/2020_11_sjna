package hafy.bid.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.json.simple.JSONObject;
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import hafy.SMSUtil.Coolsms;
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
import hafy.aucGoods.dao.AucGoodsDAO;
import hafy.aucGoods.vo.AucGoodsVO;
import hafy.bid.dao.BidDAO;
import hafy.bid.vo.AAccountVO;
import hafy.bid.vo.ATranzVO;
import hafy.bid.vo.NoticeVO;
import hafy.mAccount.dao.MAccountDAO;
import hafy.mAccount.vo.MAccountVO;
import hafy.member.dao.MemberDAO;
<<<<<<< HEAD
import hafy.member.vo.MemberVO;
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
import hafy.member.vo.NoticeSettingVO;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private BidDAO bidDAO;
	@Autowired
	private AucGoodsDAO aucGoodsDAO;
	@Autowired
	private MAccountDAO mAccountDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void insertNoti(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		bidDAO.insertNoti(noticeVO);
	}

	@Override
	public AAccountVO isBidding(AAccountVO aAccountVO) {
		// TODO Auto-generated method stub
		AAccountVO isBid = bidDAO.isBidding(aAccountVO);
		return isBid;
	}

	
	
	
	@Override
	public void autoPurchaseConfirm(int confirmDay) {
		// TODO Auto-generated method stub
		
		// 낙찰된지 15일 지난 경매 리스트 가져오기
		List<AucGoodsVO> aucList = aucGoodsDAO.selectNotPurchaseConfirmList(confirmDay);
		
		for(AucGoodsVO auc : aucList) {
			// 경매번호
			int aucNo = auc.getNo();
			// 출품자 닉네임
			String sellerNick = auc.getMemberNick();
			// 출품자의 첫번째 계좌
			List<MAccountVO> mAccountList = mAccountDAO.selectMAccountList(sellerNick);
			String sellerAccount = mAccountList.get(0).getAccountNo();
			// 낙찰액
			double winBidMoney = auc.getWinningBid();
			// 낙찰자 닉네임
			List<ATranzVO> aTranzVOs = bidDAO.selectBidResult(aucNo);
			String winner = aTranzVOs.get(0).getTranzMemberNick();
			
			// 입출금 내역 추가하기 (낙찰액만큼 출금)
			ATranzVO withdrawTranzVO = new ATranzVO(aucNo,
					sellerAccount, -winBidMoney,
					winner, "출금");
			bidDAO.insertBidTranz(withdrawTranzVO);

			// 경매모입통장에서 낙찰자의 낙찰액 빠져나가기
			AAccountVO withdrawAaccountVO = new AAccountVO(aucNo,
					winner, winBidMoney);
			bidDAO.withdrawAAccount(withdrawAaccountVO);

			// 낙찰액 판매자 계좌로 입금되기
			Map<String, Object> depositInfo = new HashMap<String, Object>();
			depositInfo.put("mAccountNo", sellerAccount);
			depositInfo.put("winBidMoney", winBidMoney);
			mAccountDAO.depositMAccount(depositInfo);
			
			// 매입확정 정보 알림 테이블에 넣기
			NoticeSettingVO noticeSettingVO = memberDAO.selectNoticeSettingVOByNick(sellerNick);
			String sellerPurchaseConfirmNotice = noticeSettingVO.getSellerPurchaseConfirmNotice();

			if (sellerPurchaseConfirmNotice.equals("true")) {
				String notiMsg = auc.getName() + "' 경매" + "(번호: " + aucNo + ")의 매입이 확정되었습니다.";
				NoticeVO noticeVO = new NoticeVO(sellerNick, "goodsDetail", aucNo, notiMsg);
				bidDAO.insertNoti(noticeVO);

			}
			System.out.println("경매번호:" + aucNo + " 자동 매입확정 처리완료");
		}
		
	}

	@Transactional
	@Override
	public void returnBidMoney(AAccountVO aAccountVO, Map<String, Object> depositInfo, ATranzVO aTranzVO) {
		// TODO Auto-generated method stub
		
		// 모임계좌에서 돈 빠져나가기
		bidDAO.withdrawAAccount(aAccountVO);
		
		// 본래 낙찰자에게 돈 되돌려주기
		mAccountDAO.depositMAccount(depositInfo);
		
		// 입출금 기록 남기기
		bidDAO.insertBidTranz(aTranzVO);
	}

	@Transactional
	@Override
	public void noticeImminentAucs() {
		// TODO Auto-generated method stub

		// 마감 안된 경매 목록 가져오기
		List<AucGoodsVO> openAucs = new ArrayList<AucGoodsVO>();
		openAucs = aucGoodsDAO.selectOpenAucs();

		if (openAucs.size() > 0) {
			for (AucGoodsVO auc : openAucs) {

				int aucNo = auc.getNo();
//				System.out.println("마감안된 경매 " + aucNo);

				// 마감 안된 경매의 입찰자 리스트 가져오기
				List<AAccountVO> aaList = bidDAO.selectAAccount(aucNo);

				for (AAccountVO a : aaList) {

					// 해당 경매의 입찰자닉 하나하나씩 가져오기
					String memberNick = a.getBidderNick();

					// 그 멤버의 알림 세팅값 가져오기
					NoticeSettingVO notiSettingVO = memberDAO.selectNoticeSettingVOByNick(memberNick);
					String bidderImminentNotice = notiSettingVO.getBidderImminentNotice();

					if (bidderImminentNotice.equals("true")) {

						// 그 멤버의 마감임박 알림시간 설정값 가져오기
						int setMin = notiSettingVO.getBidderImminentTime();

						// 해당 경매번호가 마감임박시간이 도래한 건이면 그 vo 가져오기
						Map<String, Object> setMinMap = new HashMap<String, Object>();
						setMinMap.put("aucNo", aucNo);
						setMinMap.put("setMin", setMin);
						AucGoodsVO aucGoodsVO = aucGoodsDAO.isImminentAucByMin(setMinMap);

						if (aucGoodsVO != null) {
							String notiBidderMsg = "회원님이 입찰하신 '" + auc.getName() + "' 경매" + "(번호: " + aucNo + ")가 마감 "
									+ setMin + "분 전입니다.";
							NoticeVO bidderNoticeVO = new NoticeVO(memberNick, "goodsDetail", aucNo, notiBidderMsg);
							// 입찰자들에게 경매 임박 알림메세지 삽입
							bidDAO.insertNoti(bidderNoticeVO);
//							System.out.println(memberNick + notiBidderMsg);
							System.out.println("경매번호:" + aucNo + " 경매 마감임박 알림 메세지 전송 완료");
						}
					}
				}
				
			}
		}
	}

	@Transactional
	@Override
	public void noticeClosedBid() {
		// TODO Auto-generated method stub

		// 환급절차 진행할 경매조건 : 마감된 + 미환급된 + 한번이라도 입찰이 진행된(winningBid가 0이 아닌)
		List<Integer> notRefundAucList = new ArrayList<Integer>();
		notRefundAucList = bidDAO.selectNotRefundAucList();

		if (notRefundAucList.size() > 0) {
			for (Integer aucNo : notRefundAucList) {
<<<<<<< HEAD
				
				// 해당 경매의 경매결과 (멤버닉, 멤버의 최종입찰액) 가져오기
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
				List<ATranzVO> bidResult = new ArrayList<ATranzVO>();
				bidResult = bidDAO.selectBidResult(aucNo);

				// 해당 경매 내용 가져오기
				AucGoodsVO aucGoodsVO = aucGoodsDAO.selectAucGoodsByNo(aucNo);

				DecimalFormat Commas = new DecimalFormat("#,###");
				String rfWinningMoney = (String) Commas.format(bidResult.get(0).getMemberBalance());

				// 출품자에게 경매 낙찰 알림
				NoticeSettingVO sellerNoticeSettingVO = memberDAO
						.selectNoticeSettingVOByNick(aucGoodsVO.getMemberNick());
				String sellerClosedNotice = sellerNoticeSettingVO.getSellerClosedNotice();
<<<<<<< HEAD
				
=======

>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
				if (sellerClosedNotice.equals("true")) {
					String notiSellerMsg = "회원님이 출품하신 '" + aucGoodsVO.getName() + "' 경매" + "(번호: " + aucNo + ")가 "
							+ rfWinningMoney + " 원에 낙찰되었습니다!";
					NoticeVO sellerNoticeVO = new NoticeVO(aucGoodsVO.getMemberNick(), "bidHistory", aucNo,
							notiSellerMsg);
<<<<<<< HEAD
					System.out.println(notiSellerMsg);
					bidDAO.insertNoti(sellerNoticeVO);
				}
				
				//sms api 키
				
				
				// 출품자에게 경매 마감 알림 (sms)
				String SMSNotice = sellerNoticeSettingVO.getSMSNotice();
				if (SMSNotice.equals("true")) {
					
					String api_key = "NCSD3ETDGM25G7Q5";
					String api_secret = "X5G4CT9UPJVKE2UDTJDENLTSUVF5CGVP";
					Coolsms coolsms = new Coolsms(api_key, api_secret);
					
					// 문자보내기
					String notiSellerSMS = "[HAFY] 회원님이 출품하신 " + aucNo + "번 경매가 마감되었습니다.";
					
					MemberVO tempSellerVO = new MemberVO();
					tempSellerVO.setNickname(aucGoodsVO.getMemberNick());
					MemberVO sellerVO = memberDAO.selectMember(tempSellerVO);
					String receivePhone = sellerVO.getPhone();

					HashMap<String, String> set = new HashMap<String, String>();
					set.put("to", receivePhone); // 수신번호

					set.put("from", "01042115171"); // 발신번호
					set.put("text", notiSellerSMS); // 문자내용
					set.put("type", "sms"); // 문자 타입

					System.out.println(set);

					JSONObject result = coolsms.send(set); // 보내기&전송결과받기

					if ((boolean) result.get("status") == true) {
						// 메시지 보내기 성공 및 전송결과 출력
						System.out.println("성공");
						System.out.println(result.get("group_id")); // 그룹아이디
						System.out.println(result.get("result_code")); // 결과코드
						System.out.println(result.get("result_message")); // 결과 메시지
						System.out.println(result.get("success_count")); // 메시지아이디
						System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
					} else {
						// 메시지 보내기 실패
						System.out.println("실패");
						System.out.println(result.get("code")); // REST API 에러코드
						System.out.println(result.get("message")); // 에러메시지
					}
					
					// notice 테이블에 문자 보낸 흔적 남기기
					NoticeVO SMSNoticeVO = new NoticeVO(aucGoodsVO.getMemberNick(), "goodsDetail", aucNo,
							notiSellerSMS, "sms");
					System.out.println(notiSellerSMS);
					bidDAO.insertNoti(SMSNoticeVO);
				}

				
				// 입찰자들에게 낙찰 성공 / 실패 알림 
				for (int i = 0; i < bidResult.size(); i++) {
					
					NoticeSettingVO bidderNoticeSettingVO = memberDAO.selectNoticeSettingVOByNick(bidResult.get(i).getTranzMemberNick());

					// 출품자에게 경매 마감 알림 (sms)
					String SMSBidderNotice = bidderNoticeSettingVO.getSMSNotice();
					if (SMSBidderNotice.equals("true")) {
						// 문자보내기
						
						String api_key = "NCSD3ETDGM25G7Q5";
						String api_secret = "X5G4CT9UPJVKE2UDTJDENLTSUVF5CGVP";
						Coolsms coolsms = new Coolsms(api_key, api_secret);
						String notiBidderSMS = "[HAFY] 회원님이 입찰하신 " + aucNo + "번 경매가 마감되었습니다.";
						
						MemberVO tempBidderVO = new MemberVO();
						tempBidderVO.setNickname(bidResult.get(i).getTranzMemberNick());
						MemberVO bidderVO = memberDAO.selectMember(tempBidderVO);
						String receivePhone = bidderVO.getPhone();
						
						System.out.println("seller receivePhone: " + receivePhone);
						

						HashMap<String, String> set = new HashMap<String, String>();
						set.put("to", receivePhone); // 수신번호

						set.put("from", "01042115171"); // 발신번호
						set.put("text", notiBidderSMS); // 문자내용
						set.put("type", "sms"); // 문자 타입

						System.out.println(set);

						JSONObject result = coolsms.send(set); // 보내기&전송결과받기

						if ((boolean) result.get("status") == true) {
							// 메시지 보내기 성공 및 전송결과 출력
							System.out.println("성공");
							System.out.println(result.get("group_id")); // 그룹아이디
							System.out.println(result.get("result_code")); // 결과코드
							System.out.println(result.get("result_message")); // 결과 메시지
							System.out.println(result.get("success_count")); // 메시지아이디
							System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
						} else {
							// 메시지 보내기 실패
							System.out.println("실패");
							System.out.println(result.get("code")); // REST API 에러코드
							System.out.println(result.get("message")); // 에러메시지
						}
						// notice 테이블에 문자 보낸 흔적 남기기
						NoticeVO SMSNoticeVO = new NoticeVO(aucGoodsVO.getMemberNick(), "goodsDetail", aucNo,
								notiBidderSMS, "sms");
						System.out.println(notiBidderSMS);
						bidDAO.insertNoti(SMSNoticeVO);
					}
					
					// 앱에 알림메세지 남기기
					String bidderClosedNotice = bidderNoticeSettingVO.getBidderClosedNotice();
					if (bidderClosedNotice.equals("true")) {
						if (i == 0) {
=======
					bidDAO.insertNoti(sellerNoticeVO);
				}

				for (int i = 0; i < bidResult.size(); i++) {

					NoticeSettingVO bidderNoticeSettingVO = memberDAO.selectNoticeSettingVOByNick(bidResult.get(i).getTranzMemberNick());
					String bidderClosedNotice = bidderNoticeSettingVO.getBidderClosedNotice();

					if (bidderClosedNotice.equals("true")) {
						if (i == 0) {

>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
							// 낙찰자에게 낙찰 알림
							String notiWinnerMsg = "축하합니다! '" + aucGoodsVO.getName() + "' 경매" + "(번호: " + aucNo + ")의 "
									+ " 낙찰자로 선정되셨습니다!";
							NoticeVO winnerNoticeVO = new NoticeVO(bidResult.get(0).getTranzMemberNick(), "bidHistory",
									aucNo, notiWinnerMsg);
<<<<<<< HEAD
							System.out.println(notiWinnerMsg);
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
							bidDAO.insertNoti(winnerNoticeVO);

						} else {
							// 낙찰실패자들에게 실패 알림
							String notiLoserMsg = "아쉽습니다... '" + aucGoodsVO.getName() + "' 경매" + "(번호: " + aucNo + ")의 "
									+ " 낙찰자로 선정되지 못했습니다.";
							NoticeVO loserNoticeVO = new NoticeVO(bidResult.get(i).getTranzMemberNick(), "bidHistory",
									aucNo, notiLoserMsg);
<<<<<<< HEAD
							System.out.println(notiLoserMsg);
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
							bidDAO.insertNoti(loserNoticeVO);
						}
					}

				}
				System.out.println("경매번호:" + aucNo + " 경매 마감 알림메세지 전송 완료");
			}
		}

	}

	@Transactional
	@Override
	public void refundBidMoney() {
		// TODO Auto-generated method stub

		// 환급절차 진행할 경매조건 : 마감된 + 미환급된 + 한번이라도 입찰이 진행된(winningBid가 0이 아닌)
		List<Integer> notRefundAucList = new ArrayList<Integer>();
		notRefundAucList = bidDAO.selectNotRefundAucList();

		if (notRefundAucList.size() > 0) {
			for (Integer aucNo : notRefundAucList) {

				List<ATranzVO> bidResult = new ArrayList<ATranzVO>();
				bidResult = bidDAO.selectBidResult(aucNo);

				// 입찰자가 2명 이상이면 아래 코드를 run / 1명 이하이면 낙찰자 돈밖에 없거나 아예 입찰기록이 없기에 아래 코드 run하면 안됨
				if (bidResult.size() >= 2) {

					// 최고입찰가(낙찰가)를 제외한 금액을 환급 (그래서 i=1 부터)
					for (int i = 1; i < bidResult.size(); i++) {

						// 입찰자 닉네임 구하기
						String bidderNick = bidResult.get(i).getTranzMemberNick();
						// 입찰자가 어플에 등록한 계좌 중 첫번째 계좌 구하기
						String bidderAccountNo = mAccountDAO.selectMAccountList(bidderNick).get(0).getAccountNo();
						// 환급할 액수
						int refundMoney = bidResult.get(i).getMemberBalance();

						ATranzVO aTranzVO = new ATranzVO(aucNo, bidderAccountNo, -refundMoney, bidderNick, "출금");

						AAccountVO withdrawAAccountInfo = new AAccountVO();
						withdrawAAccountInfo.setAucNo(aucNo);
						withdrawAAccountInfo.setBidderNick(bidderNick);
						withdrawAAccountInfo.setBidMoney(refundMoney);

						Map<String, Object> depositInfo = new HashMap<String, Object>();
						depositInfo.put("winBidMoney", refundMoney);
						depositInfo.put("mAccountNo", bidderAccountNo);

						// 낙찰액 판매자 계좌로 입금되기
//						Map<String, Object> depositInfo = new HashMap<String, Object>();
//						depositInfo.put("mAccountNo",(String)transferMap.get("sellerAccount"));
//						depositInfo.put("winBidMoney",(Integer)transferMap.get("winBidMoney"));
//						mAccountDAO.depositMAccount(depositInfo);

						// 입출금내역에 출금 추가
						bidDAO.insertBidTranz(aTranzVO);
						// 낙찰액 제외 입찰액 모임계좌에서 빠져나가기
						bidDAO.withdrawAAccount(withdrawAAccountInfo);

						// 낙찰자 제외 입찰자의 회원계좌로 입금
						System.out.println("환급돈(Integer)depositInfo.get(\"winBidMoney\")"
								+ (Integer) depositInfo.get("winBidMoney"));
						System.out.println("환급돈 refundMoney" + refundMoney);
						mAccountDAO.depositMAccount(depositInfo);
					}
				}
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String nowTime = now.format(formatter);

				// 해당경매 환급상태 컬럼 '환급완료'로 바꾸기
				aucGoodsDAO.updateRefundStatus(aucNo);
				System.out.println(nowTime + "에 " + aucNo + "번 경매 환급완료");

			}
		} else {
			System.out.println("환급할 내역이 없습니다.");
		}

	}

	@Override
	public List<ATranzVO> selectBidResult(int aucNo) {
		// TODO Auto-generated method stub
		List<ATranzVO> bidResult = new ArrayList<ATranzVO>();
		bidResult = bidDAO.selectBidResult(aucNo);
		return bidResult;
	}

	@Override
	public List<ATranzVO> selectATranzLazyLoadByAucNo(Map<String, Object> loadInfo) {
		// TODO Auto-generated method stub
		List<ATranzVO> aTranzList = new ArrayList<ATranzVO>();
		aTranzList = bidDAO.selectATranzLazyLoadByAucNo(loadInfo);

		return aTranzList;
	}

	@Override
	public List<ATranzVO> selectATranzByAucNo(int aucNo) {
		// TODO Auto-generated method stub
		List<ATranzVO> aTranzList = new ArrayList<ATranzVO>();
		aTranzList = bidDAO.selectATranzByAucNo(aucNo);

		return aTranzList;
	}

	@Override
	public List<AAccountVO> selectAAccount(int aucNo) {
		// TODO Auto-generated method stub
		return bidDAO.selectAAccount(aucNo);
	}

	@Transactional
	@Override
	public void bidding(AAccountVO aAccountVO) {
		// TODO Auto-generated method stub

		AAccountVO isAccountVO = bidDAO.isBidding(aAccountVO);

		// 첫 입찰이면 insert, 두번 이상 입찰이면 update
		if (isAccountVO == null) {
			bidDAO.insertAAccountBid(aAccountVO);
		} else {
			bidDAO.addBidMoney(aAccountVO);
		}

		// 최고 입찰가 구하기
		//// 입찰목록 불러오기
		List<AAccountVO> bidderList = new ArrayList<AAccountVO>();
		bidderList = bidDAO.selectAAccount(aAccountVO.getAucNo());

		double highestBid = bidderList.get(0).getBidMoney();
		for (int i = 1; i < bidderList.size(); i++) {
			if (bidderList.get(i).getBidMoney() >= highestBid) {

				highestBid = bidderList.get(i).getBidMoney();
			}
		}

//		System.out.println("비드서비스에서 최고입찰가:" + highestBid);

		AAccountVO tempVO = new AAccountVO();
		tempVO.setBidMoney(highestBid);
		tempVO.setAucNo(aAccountVO.getAucNo());
		// hf_auc_goods의 winningBid 컬럼 => 최고 입찰가로 업데이트
		bidDAO.updateWinningBid(tempVO);
	}

	@Transactional
	@Override
	public void insertBidTranz(ATranzVO aTranzVO) {
		// TODO Auto-generated method stub

		AAccountVO aAccountVO = new AAccountVO(aTranzVO.getAucNo(), aTranzVO.getTranzMemberNick());
		AAccountVO isBid = bidDAO.isBidding(aAccountVO);

		// 입찰 기록이 있으면 'memberBalance = 해당 모임계좌 bidMoney + tranzMoney'
		// 없으면 'memberBalance = tranzMoney'
		if (isBid == null) {
			bidDAO.insertBidTranzNoBalance(aTranzVO);
		} else {

			// 수정한부분
			double pastBidMoney = isBid.getBidMoney();
			double nowBidMoney = aTranzVO.getTranzMoney();
			// 지금 입찰액과 과거 입찰액 차액만큼 입금액 추가
			aTranzVO.setTranzMoney(nowBidMoney - pastBidMoney);
			// 여기까지해서

			bidDAO.insertBidTranz(aTranzVO);
		}

	}

}
