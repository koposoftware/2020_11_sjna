package hafy.mAccount.vo;

public class MAccountVO {
	
	private String bank;
	private String accountNo;
	private String nickname;
	
	
	
	public MAccountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MAccountVO(String bank, String accountNo, String nickname) {
		super();
		this.bank = bank;
		this.accountNo = accountNo;
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "MAccountVO [bank=" + bank + ", accountNo=" + accountNo + ", nickname=" + nickname + "]";
	}
	
	
	

}
