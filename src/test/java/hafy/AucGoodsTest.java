package hafy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hafy.aucGoods.vo.GoodsPhotoVO;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/spring-mvc.xml"})
@ContextConfiguration(locations = {"classpath:config/spring/*.xml"})
public class AucGoodsTest {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private DataSource ds;
	
	@Ignore
	@Test
	public void 테스트해보기() throws Exception{
		System.out.println(ds);
	}
	
//	@Ignore
	@Test
	public void 세션테스트() {
		
//		assertNotNull(sqlSession);
//		assertNull(sqlSession);
		System.out.println(sqlSession);
		
	}
	
	@Ignore
	@Test
	public void 경매번호생성() throws Exception {
		
//		List<ReplyVO> list = sqlSession.selectList("reply.dao.ReplyDAO.selectAll", 7);
		List<Integer> nos = sqlSession.selectList("auction.dao.AucGoodsDAO.genAucNo");
		
		for(Integer no : nos) {
			System.out.println(no);
		}
		
	}
	
//	@Ignore
	@Test
	public void selectPhotoListByAucNo() throws Exception {
		// TODO Auto-generated method stub
		
		List<GoodsPhotoVO> photoList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectPhotoListByAucNo", 120);
		
		
//		String firstPhoto = sqlSession.selectOne("auction.dao.AucGoodsDAO.selectFirstPhotoByAucNo", 120);
		
//		System.out.println("하단에 출력됨");
//		System.out.println("첫번쨰사진: " + firstPhoto);
		
		for(GoodsPhotoVO p : photoList) {
			System.out.println(p);
		}
		
	}

}

//package kr.ac.kopo;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import kr.ac.kopo.reply.vo.ReplyVO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/*.xml"})
//public class ReplyTest {
//	
//	@Autowired
//	private SqlSessionTemplate sqlSession;
//	
//	@Test
//	public void 전체댓글조회() throws Exception {
//		
//		List<ReplyVO> list = sqlSession.selectList("reply.dao.ReplyDAO.selectAll", 7);
//		
//		for(ReplyVO vo : list) {
//			System.out.println(vo);
//		}
//		
//	}
//	
//	public void 댓글수증가Test() throws Exception {
//		
//		sqlSession.update("board.dao.BoardDAO.incrementReplyCnt",7);
//		
//	}
//
//}
