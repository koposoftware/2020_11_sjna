package hafy.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import hafy.SMSUtil.Coolsms;
import hafy.aucGoods.service.AucGoodsService;
import hafy.member.service.MemberService;
import hafy.member.vo.MemberVO;
import hafy.member.vo.NoticeSettingVO;
//import net.nurigo.java_sdk.Coolsms;

@SessionAttributes({ "memberVO" }) // addObject했을때 loginVO객체를 session공유영역에 등록 (여러개 들어올수있음...배열형태{}) 하지만 이방식은
// invalidate로 세션 삭제 안됨
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private AucGoodsService aucGoodsService;

	@ResponseBody
	@PostMapping("/submitAuthNo")
	public boolean submitAuthNo(HttpServletRequest request, HttpSession session) {

		System.out.println("인증번호 입력 거치기");

		String inputAuthNo = request.getParameter("inputAuthNo");
		String authNo = (String) session.getAttribute("authNo");

		System.out.println("inputAuthNo " + inputAuthNo);
		System.out.println("authNo " + authNo);

		boolean authResult = false;

		if (inputAuthNo.equals(authNo)) {
			authResult = true;
			session.removeAttribute("authNo");
		}

		return authResult;
	}

	@ResponseBody
	@PostMapping("/getAuthNo")
	public void getAuthNo(HttpServletRequest request, HttpSession session) {

		Random rand = new Random();
		String authNo = ""; // 난수가 저장될 변수

		// n자리 인증번호
		int len = 6;
		// 중복허용(1) 불허(2)
		int dupCd = 1;
		for (int i = 0; i < len; i++) {

			// 0~9 까지 난수 생성
			String ran = Integer.toString(rand.nextInt(10));

			// 중복을 허용하지 않을시 중복된 값이 있는지 검사한다
			if (dupCd == 1) {
				// 중복 허용시 numStr에 append
				authNo += ran;
			} else if (dupCd == 2) {
				// 중복을 허용하지 않을시 중복된 값이 있는지 검사한다
				if (!authNo.contains(ran)) {
					// 중복된 값이 없으면 numStr에 append
					authNo += ran;
				} else {
					// 생성된 난수가 중복되면 루틴을 다시 실행한다
					i -= 1;
				}
			}
		}
		
		// 진희 coolsms api
		String api_key = "NCSD3ETDGM25G7Q5";
		String api_secret = "X5G4CT9UPJVKE2UDTJDENLTSUVF5CGVP";
		
		// 내 coolsms api
//		String api_key = "NCSQAZX77TZAVZAU";
//		String api_secret = "XCQPPAFV9NZR1V0XLJZ7UXHNBU7FKBJN";
		String receivePhone = request.getParameter("receivePhone");

		session.setAttribute("authNo", authNo);
		String sendMsg = "[HAFY] 본인확인 인증번호는 [" + authNo + "] 입니다.";

		Coolsms coolsms = new Coolsms(api_key, api_secret);

		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", receivePhone); // 수신번호

		set.put("from", "01042115171"); // 발신번호
		set.put("text", sendMsg); // 문자내용
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
	}

	@GetMapping("/noticeSetting")
	public String noticeSetting(HttpSession session, Model model) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		String nickname = memberVO.getNickname();

		NoticeSettingVO noticeSettingVO = memberService.selectNoticeSettingVOByNick(nickname);
//		System.out.println(nickname + "의 " + noticeSettingVO);
		model.addAttribute("noticeSettingVO", noticeSettingVO);

		return "/myPage/noticeSetting";
	}

	@ResponseBody
	@PostMapping("/noticeSettingProcess")
	public void noticeSettingProcess(HttpSession session, NoticeSettingVO noticeSettingVO) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		String nickname = memberVO.getNickname();
		noticeSettingVO.setMemberNick(nickname);

//		System.out.println("noticeSettingVO" + noticeSettingVO);
		memberService.updateNoticeSetting(noticeSettingVO);

	}

	@PostMapping("/changePwdSuccess")
	public String changePwdSuccess(@RequestParam("tranzPwd") String tranzPwd, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		String nickname = memberVO.getNickname();
//		System.out.println("닉넴:"+nickname +", 비번: " + tranzPwd);

		Map<String, String> pwdMap = new HashMap<String, String>();
		pwdMap.put("nickname", nickname);
		pwdMap.put("tranzPwd", tranzPwd);
		memberService.registerPwd(pwdMap);

		memberVO.setTranzPwd(tranzPwd);
		session.removeAttribute("memberVO");
		session.setAttribute("memberVO", memberVO);

		return "/myPage/changePwdSuccess";
	}

	@GetMapping("/changePwdForm")
	public String changePwdForm() {
		return "/myPage/changePwdForm";
	}

	@GetMapping("/changePwd")
	public String changePwd() {
		return "/myPage/changePwd";
	}

	@GetMapping("/confirmSignOut")
	@ResponseBody
	public void confirmSignOut(HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		memberService.deleteMember(memberVO);
		session.removeAttribute("memberVO");
	}

	@PostMapping("/checkPwd")
	@ResponseBody
	public String checkPwd(HttpServletRequest request, HttpSession session) {

		String inputPwd = request.getParameter("inputPwd");
//		System.out.println("회원탈퇴시 입력받은 패스워드: " + inputPwd);

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		String memberPwd = memberVO.getTranzPwd();

//		System.out.println(inputPwd.equals(memberPwd));

		if (inputPwd.equals(memberPwd)) {
			return "correct";
		} else {
			return "incorrect";
		}

	}

//	@PostMapping("/logout")
//	@ResponseBody
//	public void logout(HttpSession session) {
//
//		session.removeAttribute("memberVO");
//	}

	@PostMapping("/logout")
	@ResponseBody
	public void logout(SessionStatus status) {

//		session.invalidate();

		// session 내 값 지우기
		status.setComplete();
		System.out.println(status.isComplete()); // false면 아직 session에 값이 남아있다는 뜻 / true면 값이 다 사라진것
	}

	@ResponseBody
	@PostMapping("/checkID")
	public boolean checkID(HttpServletRequest request) {

		String inputID = request.getParameter("inputID");
		System.out.println("inputID: " + inputID);

		MemberVO isMember = memberService.checkID(inputID);

		boolean isExist = false;

		if (isMember != null) {
			isExist = true;
		}
		return isExist;

	}

	@RequestMapping("/signOut")
	public String signOut() {

		return "/myPage/signOut";
	}

	@PostMapping("/login")
	public ModelAndView loginProcess(MemberVO inputMemberVO, HttpSession session) {

		MemberVO memberVO = memberService.checkLogin(inputMemberVO);
		ModelAndView mav = new ModelAndView();
		System.out.println("컨트롤러에서 멤버 받아오는지?" + memberVO);

		if (memberVO == null) {
			mav.setViewName("redirect:/login");
		}
//		else {
////			System.out.println("로긴프로세스에서 로긴한애 " + memberVO);
//			session.setAttribute("memberVO", memberVO);
////			MemberVO m = (MemberVO)session.getAttribute("memberVO");
////			System.out.println("방금로긴한애" + m);
//			mav.setViewName("redirect:/hot");
//		}

		// 로그인 인터셉터 쓸경우 주석 풀어줘야
		else {
			// 로그인 성공
//			HttpSession session = request.getSession();
			String dest = (String) session.getAttribute("dest");
			System.out.println("멤버 컨트롤러에서 dest: " + dest);
			if (dest == null) {
				mav.setViewName("redirect:/hot");
			} else {
				mav.setViewName("redirect:" + dest);
				session.removeAttribute("dest");

			}
			mav.addObject("memberVO", memberVO);
		}
		// 여기까지 로그인 인터셉터

//			System.out.println("세션에 등록하나?");
//			session.setAttribute("memberVO", memberVO);
//			return "redirect:/hot";
		return mav;
	}
//	@PostMapping("/loginProcess")
//	public String loginProcess(MemberVO inputMemberVO, HttpSession session) {
//		
//		MemberVO memberVO = memberService.checkLogin(inputMemberVO);
////		ModelAndView mav = new ModelAndView();
////		System.out.println("컨트롤러에서 멤버 받아오는지?"+memberVO);
//
//		if (memberVO == null) {
//			return "redirect:/login";
//		} else {
////			System.out.println("로긴프로세스에서 로긴한애 " + memberVO);
//			session.setAttribute("memberVO", memberVO);
////			MemberVO m = (MemberVO)session.getAttribute("memberVO");
////			System.out.println("방금로긴한애" + m);
//			return "redirect:/hot";
//		}
//	}

	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}

	@RequestMapping("/myPage")
	public String myPage() {

		return "/myPage/myPage";
	}

	@RequestMapping("/myInfo")
	public String myInfo() {
		return "/myPage/myInfo";
	}

	@RequestMapping("/myModify")
	public String myModify() {
		return "/myPage/myModify";
	}

	@PostMapping("/myModifyComplete")
	public String myModifyComplete(HttpSession session, HttpServletRequest request) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		
		String phone = request.getParameter("phone");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		memberVO.setPhone(phone);
		memberVO.setAddress1(address1);
		memberVO.setAddress2(address2);
		
		
//		MemberVO modMemberVO = new MemberVO();
//		modMemberVO.setNickname(memberVO.getNickname());
//		modMemberVO.setPhone(phone);
//		modMemberVO.setAddress1(address1);
//		modMemberVO.setAddress2(address2);
		memberService.updateMember(memberVO);

		session.removeAttribute("memberVO");
		session.setAttribute("memberVO", memberVO);

		return "redirect:/myInfo";
	}

	@RequestMapping("/signUp")
	public String subCover() {
//		System.out.println("거치나?");
		return "signUp/signUp";
	}

	@RequestMapping("/signUpForm")
	public String signUpForm() {
		return "signUp/signUpForm";
	}

	@PostMapping("/signUpSuccess")
	public String signUpSuccess(MemberVO memberVO, HttpSession session) {

		String tranzPwd = "0000t";
		memberVO.setTranzPwd(tranzPwd);

		memberService.insertMember(memberVO);
		memberService.insertNoticeSetting(memberVO);
		session.setAttribute("memberVO", memberVO);

		return "signUp/signUpSuccess";
	}

	@RequestMapping("/pwdSetting")
	public String pwdSetting() {
		return "signUp/pwdSetting";
	}

	@PostMapping("/pwdSettingSuccess")
	public String pwdSettingSuccess(@RequestParam("tranzPwd") String tranzPwd, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		String nickname = memberVO.getNickname();

		Map<String, String> pwdMap = new HashMap<String, String>();
		pwdMap.put("nickname", nickname);
		pwdMap.put("tranzPwd", tranzPwd);
		memberService.registerPwd(pwdMap);

		memberVO.setTranzPwd(tranzPwd);
		session.removeAttribute("memberVO");
		session.setAttribute("memberVO", memberVO);

		return "signUp/pwdSettingSuccess";
	}

}
