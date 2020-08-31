package hafy.aucGoods.dao;

import java.util.List;
import java.util.Map;

import hafy.aucGoods.vo.AucGoodsVO;
import hafy.aucGoods.vo.GoodsPhotoVO;

public interface AucGoodsDAO {
	
	void insertAucGoods(AucGoodsVO aucGoodsVO);
	int genAucNo();
	void insertGoodsPhoto(GoodsPhotoVO goodsPhotoVO);
	AucGoodsVO selectAucGoodsByNo(int aucNo);
	List<AucGoodsVO> selectAllAucContents();
//	List<GoodsPhotoVO> selectPhotoListByAucNo(int aucNo);
	String selectFirstPhotoByAucNo(int aucNo);

}
