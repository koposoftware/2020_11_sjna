package hafy.aucGoods.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hafy.aucGoods.vo.AucGoodsVO;
import hafy.aucGoods.vo.CodeVO;
import hafy.aucGoods.vo.GoodsPhotoVO;
import hafy.aucGoods.vo.LikeVO;
import hafy.bid.vo.AAccountVO;
import hafy.member.vo.MemberVO;

@Repository
public class AucGoodsDAOImpl implements AucGoodsDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	
	@Override
	public void updatePurchaseConfirm(int aucNo) {
		// TODO Auto-generated method stub
		sqlSession.update("auction.dao.AucGoodsDAO.updatePurchaseConfirm",aucNo);
	}



	@Override
	public List<AucGoodsVO> selectHotAucContents() {
		// TODO Auto-generated method stub
		List<AucGoodsVO> hotAucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectHotAucContents");
		return hotAucList;
	}



	@Override
	public CodeVO selectCodeVO(String category) {
		// TODO Auto-generated method stub
		CodeVO codeVO = sqlSession.selectOne("auction.dao.AucGoodsDAO.selectCodeVO",category);
		return codeVO;
	}

	
	
	@Override
	public List<AucGoodsVO> selectAucSearchWord(String searchWord) {
		// TODO Auto-generated method stub
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>();
		aucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectAucSearchWord",searchWord);

		return aucList;
	}


	@Override
	public List<AucGoodsVO> selectSpecificCategory(String category) {
		// TODO Auto-generated method stub
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>();
		aucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectSpecificCategory",category);
		return aucList;
	}

	@Override
	public List<LikeVO> selectLikeList(MemberVO memberVO) {
		// TODO Auto-generated method stub
		List<LikeVO> likeList = new ArrayList<LikeVO>();
		likeList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectLikeList",memberVO);
		return likeList;
	}

	@Override
	public List<CodeVO> selectGoodsCategory(String codeCategory) {
		// TODO Auto-generated method stub
		List<CodeVO> category = sqlSession.selectList("auction.dao.AucGoodsDAO.selectGoodsCategory",codeCategory);
		
		return category;
	}

	@Override
	public LikeVO selectIsLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		LikeVO isLikeVO = sqlSession.selectOne("auction.dao.AucGoodsDAO.selectIsLike",likeVO);
		return isLikeVO;
	}

	@Override
	public void insertLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("auction.dao.AucGoodsDAO.insertLike",likeVO);
	}

	@Override
	public void deleteLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		sqlSession.delete("auction.dao.AucGoodsDAO.deleteLike",likeVO);
	}

	@Override
	public void decrementLikeCnt(int aucNo) {
		// TODO Auto-generated method stub
		sqlSession.update("auction.dao.AucGoodsDAO.decrementLikeCnt",aucNo);
	}

	@Override
	public void incrementLikeCnt(int aucNo) {
		// TODO Auto-generated method stub
		sqlSession.update("auction.dao.AucGoodsDAO.incrementLikeCnt",aucNo);
		
	}

	@Override
	public void incrementViewCnt(int aucNo) {
		// TODO Auto-generated method stub
		sqlSession.update("auction.dao.AucGoodsDAO.incrementViewCnt",aucNo);
		
	}

	@Override
	public List<GoodsPhotoVO> selectPhotoListByAucNo(int aucNo) {
		// TODO Auto-generated method stub
		List<GoodsPhotoVO> goodsPhotoList = new ArrayList<GoodsPhotoVO>();
		goodsPhotoList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectPhotoListByAucNo",aucNo);
		
		return goodsPhotoList;
	}

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
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>();
		aucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectAllAucContents");
		return aucList;	
	}
	
	@Override
	public List<AAccountVO> selectBidList(MemberVO memberVO) {
		// TODO Auto-generated method stub
		List<AAccountVO> aucList = new ArrayList<AAccountVO>();
		aucList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectBidList", memberVO);
		return aucList;
	}
	
	@Override
	public List<AucGoodsVO> selectDisplayList(MemberVO memberVO) {
		// TODO Auto-generated method stub
		List<AucGoodsVO> displayList = new ArrayList<AucGoodsVO>();
		displayList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectDisplayList", memberVO);

		return displayList;
	}

	/**
	 * 경매번호로 상품사진정보 가져오기
	*/
	@Override
//	public List<GoodsPhotoVO> selectPhotoListByAucNo(int aucNo) {
	public List<String> selectPhotoNameByAucNo(int aucNo) {
		// TODO Auto-generated method stub
		List<String> photoList = new ArrayList<String>();
		photoList = sqlSession.selectList("auction.dao.AucGoodsDAO.selectPhotoNameByAucNo",aucNo);
		
		return photoList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
