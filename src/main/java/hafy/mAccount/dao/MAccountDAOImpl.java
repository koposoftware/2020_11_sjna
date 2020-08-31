package hafy.mAccount.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hafy.mAccount.vo.MAccountVO;

@Repository
public class MAccountDAOImpl implements MAccountDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertMAccount(MAccountVO mAccountVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("mAccount.dao.MAccountDAO.insert",mAccountVO);
		
		
	}
	
	
	

}
