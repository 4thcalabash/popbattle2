package test;

import dal.FileHelper;
import vo.PlayerVo;

public class FileHelperTestDrive {
	public static void main(String[] args){
		FileHelper fh = new FileHelper();
		PlayerVo read= fh.loadData(0);
		fh.saveData(read, 2);
	}
}

