package hafy.aucGoods.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hafy.aucGoods.vo.AucGoodsVO;
import hafy.aucGoods.vo.GoodsPhotoVO;

@Repository
public class AucGoodsDAOImpl implements AucGoodsDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertAucGoods(AucGoodsVO aucGoodsVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("auction.dao.AucGoodsDAO.insertAucGoods",aucGoodsVO);
	}

	@Override
	public int genAucNo() {
		// TODO Auto-generated method stub
		int aucNo = sqlSession.selectOne("auction.dao.AucGoodsDAO.genAucNo");
		
		
		return aucNo;
	}

	@Override
	public void insertGoodsPhoto(GoodsPhotoVO goodsPhotoVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("auction.dao.AucGoodsDAO.insertGoodsPhoto",goodsPhotoVO);
		
	}

	@Override
	public AucGoodsVO selectAucGoodsByNo(int aucNo) {
		// TODO Auto-generated method stub
		AucGoodsVO aucGoodsVO = sqlSession.selectOne("auction.dao.AucGoodsDAO.selectAucGoodsByNo", aucNo);
		
		return aucGoodsVO;
	}

	@Override
	public List<AucGoodsVO> selectAllAucContents() {
		// TODO Auto-generated method stub
		List<AucGoodsVO> aucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectAllAucContents");
		return aucList;	
	}

	/**
	 * 경매번호로 상품사진정보 가져오기
	*/
	@Override
//	public List<GoodsPhotoVO> selectPhotoListByAucNo(int aucNo) {
	public String selectFirstPhotoByAucNo(int aucNo) {
		// TODO Auto-generated method stub
		
//		List<GoodsPhotoVO> photoList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectPhotoListByAucNo", aucNo);
		String firstPhoto = sqlSession.selectOne("auction.dao.AucGoodsDAO.selectFirstPhotoByAucNo", aucNo);
		System.out.println("dao에서 다음 포토는 ? " +firstPhoto);
		
//		for(GoodsPhotoVO g : photoList) {
//			System.out.println("dao에서 다음 포토는 ? " +g);
//		}
		
		return firstPhoto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
