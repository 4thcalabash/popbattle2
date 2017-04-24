package test;

import dal.FileHelper;
import vo.PlayerVo;

public class FileHelperTestDrive {
	public static void main(String[] args){
		FileHelper fh = new FileHelper();
		PlayerVo read= fh.loadData(2);
		fh.saveData(read, 1);
		//
	}
}

