package hafy.aucGoods.vo;

public class GoodsPhotoVO {
	
	private int no;
	private String oriName;
	private String saveName;
	private int fileSize;	
	private int aucNo;
	
	public GoodsPhotoVO(String oriName, String saveName, int fileSize, int aucNo) {
		super();
		this.oriName = oriName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.aucNo = aucNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getAucNo() {
		return aucNo;
	}
	public void setAucNo(int aucNo) {
		this.aucNo = aucNo;
	}
	@Override
	public String toString() {
		return "GoodsPhotoVO [no=" + no + ", oriName=" + oriName + ", saveName=" + saveName + ", fileSize=" + fileSize
				+ ", aucNo=" + aucNo + "]";
	}
	

}
