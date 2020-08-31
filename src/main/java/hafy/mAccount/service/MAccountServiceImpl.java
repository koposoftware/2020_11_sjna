package hafy.mAccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hafy.mAccount.dao.MAccountDAO;
import hafy.mAccount.vo.MAccountVO;

@Service
public class MAccountServiceImpl implements MAccountService{
	
	@Autowired
	private MAccountDAO mAccountDAO; 
	
	@Override
	public void insertMAccount(MAccountVO mAccount) {
		// TODO Auto-generated method stub
		
		mAccountDAO.insertMAccount(mAccount);
		
	}
	
	

}
