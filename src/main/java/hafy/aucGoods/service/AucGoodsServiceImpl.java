package hafy.aucGoods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hafy.aucGoods.dao.AucGoodsDAO;
import hafy.aucGoods.vo.AucGoodsVO;
import hafy.aucGoods.vo.CodeVO;
import hafy.aucGoods.vo.GoodsPhotoVO;
import hafy.aucGoods.vo.LikeVO;
import hafy.bid.vo.AAccountVO;
import hafy.member.vo.MemberVO;


@Service
public class AucGoodsServiceImpl implements AucGoodsService{
	
	@Autowired
	private AucGoodsDAO aucGoodsDAO;
	
	
	
	@Override
	public List<CodeVO> selectGoodsCategory(String codeCategory) {
		// TODO Auto-generated method stub
		List<CodeVO> category = aucGoodsDAO.selectGoodsCategory(codeCategory);
		return category;
	}

	@Override
	public LikeVO selectIsLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		LikeVO isLikeVO = aucGoodsDAO.selectIsLike(likeVO);
		return isLikeVO;
	}

	@Override
	public void insertLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		aucGoodsDAO.insertLike(likeVO);
		
	}

	@Override
	public void deleteLike(LikeVO likeVO) {
		// TODO Auto-generated method stub
		aucGoodsDAO.deleteLike(likeVO);

	}

	@Override
	public void decrementLikeCnt(int aucNo) {
		// TODO Auto-generated method stub
		aucGoodsDAO.decrementLikeCnt(aucNo);

		
	}

	@Override
	public void incrementLikeCnt(int aucNo) {
		// TODO Auto-generated method stub
		aucGoodsDAO.incrementLikeCnt(aucNo);
		
	}
	
	@Transactional
	@Override
	public Map<AucGoodsVO, List<GoodsPhotoVO>> selectAucByNo(int aucNo) {
		// TODO Auto-generated method stub
		
		aucGoodsDAO.incrementViewCnt(aucNo);
		
		Map<AucGoodsVO, List<GoodsPhotoVO>> aucMap = new HashMap<AucGoodsVO, List<GoodsPhotoVO>>();
		
		AucGoodsVO aucGoodsVO = aucGoodsDAO.selectAucGoodsByNo(aucNo);
		List<GoodsPhotoVO> goodsPhotoList = aucGoodsDAO.selectPhotoListByAucNo(aucNo);
		
		aucMap.put(aucGoodsVO, goodsPhotoList);
		
		return aucMap;
	}

	@Override
	public void insertAucGoods(AucGoodsVO aucGoodsVO) {
		// TODO Auto-generated method stub
		aucGoodsDAO.insertAucGoods(aucGoodsVO);
	}

	@Override
	public int genAucNo() {
		// TODO Auto-generated method stub
		
		int aucNo = aucGoodsDAO.genAucNo();
		
		return aucNo;
	}

	@Override
	public void insertGoodsPhoto(GoodsPhotoVO goodsPhotoVO) {
		// TODO Auto-generated method stub
		aucGoodsDAO.insertGoodsPhoto(goodsPhotoVO);
		
	}

	@Override
	public AucGoodsVO selectAucGoodsByNo(int aucNo) {
		// TODO Auto-generated method stub
		
		AucGoodsVO aucGoodsVO = aucGoodsDAO.selectAucGoodsByNo(aucNo);
		return aucGoodsVO;
	}

	
	/**
	 * 첫번째 사진 파일명과 상품정보가 담긴 Map 생성
	*/
	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectAllAuc() {
		// TODO Auto-generated method stub
		
		Map<String, AucGoodsVO> aucMap = new LinkedHashMap<String, AucGoodsVO>();
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>(); 
		aucList = aucGoodsDAO.selectAllAucContents();
		
		for(AucGoodsVO auc : aucList) {
			
			int aucNo = auc.getNo();
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0); 
			
			aucMap.put(firstPhoto, auc);
			
		}
		return aucMap;
	}
	
	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectBidMap(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
		Map<String, AucGoodsVO> bidMap = new LinkedHashMap<String, AucGoodsVO>();
		List<AAccountVO> bidList = new ArrayList<AAccountVO>();
		bidList = aucGoodsDAO.selectBidList(memberVO);
		
		for(AAccountVO auc : bidList) {
			
			int aucNo = auc.getAucNo();
			AucGoodsVO aucGoodsVO = aucGoodsDAO.selectAucGoodsByNo(aucNo);
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0); 
			
			bidMap.put(firstPhoto, aucGoodsVO);
		}
		return bidMap;
	}

	
	@Override
	public CodeVO selectCodeVO(String category) {
		// TODO Auto-generated method stub
		CodeVO codeVO = aucGoodsDAO.selectCodeVO(category);
		return codeVO;
	}

	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectDisplayMap(MemberVO memberVO) {
		// TODO Auto-generated method stub
		Map<String, AucGoodsVO> displayMap = new LinkedHashMap<String, AucGoodsVO>();
		List<AucGoodsVO> displayList = new ArrayList<AucGoodsVO>();
		displayList = aucGoodsDAO.selectDisplayList(memberVO);
		
		for(AucGoodsVO auc : displayList) {
			
			int aucNo = auc.getNo();
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0); 
			
			displayMap.put(firstPhoto, auc);
		}
		return displayMap;
		
	}
	
	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectSpecificCategory(String category) {
		// TODO Auto-generated method stub
		Map<String, AucGoodsVO> specCategoryMap = new LinkedHashMap<String, AucGoodsVO>();
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>();
		aucList = aucGoodsDAO.selectSpecificCategory(category);
		
		for(AucGoodsVO auc : aucList) {
			
			int aucNo = auc.getNo();
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0); 
			
			specCategoryMap.put(firstPhoto, auc);
		}
		return specCategoryMap;

	}
	
	
	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectAucSearchWord(String searchWord) {
		// TODO Auto-generated method stub
		Map<String, AucGoodsVO> aucSearchMap = new LinkedHashMap<String, AucGoodsVO>();
		List<AucGoodsVO> aucList = new ArrayList<AucGoodsVO>();
		aucList = aucGoodsDAO.selectAucSearchWord(searchWord);
		
		for(AucGoodsVO auc : aucList) {
			
			int aucNo = auc.getNo();
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0); 
			
			aucSearchMap.put(firstPhoto, auc);
		}
		return aucSearchMap;

	}

	@Transactional
	@Override
	public Map<String, AucGoodsVO> selectLikeMap(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
		Map<String, AucGoodsVO> likeMap = new LinkedHashMap<String, AucGoodsVO>();
		List<LikeVO> likeList = new ArrayList<LikeVO>(); 
		likeList = aucGoodsDAO.selectLikeList(memberVO);
		
		for(LikeVO l : likeList) {
			int aucNo = l.getAucNo();
			AucGoodsVO aucGoodsVO = aucGoodsDAO.selectAucGoodsByNo(aucNo);
			List<String> photoList =  aucGoodsDAO.selectPhotoNameByAucNo(aucNo);
			String firstPhoto = photoList.get(0);
			
			likeMap.put(firstPhoto, aucGoodsVO);

		}
		
		return likeMap;
	}
	
	
	
	
	

}
