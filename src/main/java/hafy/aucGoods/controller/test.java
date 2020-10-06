package hafy.aucGoods.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
import java.util.TimeZone;

public class test {

	public static void main(String[] args) throws ParseException {
		
<<<<<<< HEAD
		
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
		
		System.out.println(authNo);
		//데이트포맷
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
//        //한국기준 날짜
//        Calendar calendar = Calendar.getInstance();
//        Date date = new Date(calendar.getTimeInMillis());
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//        String nowTime = sdf.format(date);
//        //오늘 타임스탬프(데이트포맷으로 저장했다고 치고 그걸 타임스탬프로 바꿔보는 작업)
//        long nowTimestamp = sdf.parse(nowTime).getTime();
//
//        //2019년 9월 6일의 타임스탬프
//        String endTime = "2020-10-01 04:47:00";
//        long endTimestamp = sdf.parse(endTime).getTime();
//
//        long difference = (endTimestamp - nowTimestamp );
//        System.out.println("오늘날짜 => "+nowTime);
//        System.out.println("다른날짜 => "+endTime);
////        System.out.println("differentTimestamp 타임스탬프=> "+todayTimestamp);
////        System.out.println("todayTimestamp 타임스탬프=> "+difference);
//        System.out.println("일수차=> "  +  difference/ (24*60*60*1000));
//        System.out.println("시간차=> "  +  difference/ (60*60*1000));
//        System.out.println("분차=> "  +  difference/ (60*1000));
=======
		//데이트포맷
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        //한국기준 날짜
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String nowTime = sdf.format(date);
        //오늘 타임스탬프(데이트포맷으로 저장했다고 치고 그걸 타임스탬프로 바꿔보는 작업)
        long nowTimestamp = sdf.parse(nowTime).getTime();

        //2019년 9월 6일의 타임스탬프
        String endTime = "2020-10-01 04:47:00";
        long endTimestamp = sdf.parse(endTime).getTime();

        long difference = (endTimestamp - nowTimestamp );
        System.out.println("오늘날짜 => "+nowTime);
        System.out.println("다른날짜 => "+endTime);
//        System.out.println("differentTimestamp 타임스탬프=> "+todayTimestamp);
//        System.out.println("todayTimestamp 타임스탬프=> "+difference);
        System.out.println("일수차=> "  +  difference/ (24*60*60*1000));
        System.out.println("시간차=> "  +  difference/ (60*60*1000));
        System.out.println("분차=> "  +  difference/ (60*1000));
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
        
	}
		

}
