package test;

import bll.matrix.Matrix;
import bll.platform.Battle;
import bllservice.BattlePlatform;
import po.DotPo;
import po.PopPo;

import java.util.Scanner;
public class MatrixTestDrive {
	static Scanner reader = new Scanner (System.in);
	public static void main(String args[]){
		BattlePlatform testplatform = new Battle ();
		testplatform.adfasdasdassdfasdf();
		while (true){
			
			int d1x,d1y,d2x,d2y;
			d1x=reader.nextInt();
			d1y=reader.nextInt();
			d2x=reader.nextInt();
			d2y=reader.nextInt();
			boolean flag=testplatform.move(new DotPo (d1x,d1y), new DotPo(d2x,d2y));
			System.out.println("��Ĳ����ĺϷ��ԣ�"+flag);
			if (flag){
				System.out.println("������");
				testplatform.adfasdasdassdfasdf();
				PopPo poppo=testplatform.pop(1, new DotPo(d1x,d1y), new DotPo(d2x,d2y));
				
				while (poppo.hasAnyPop()){
					PopPo poppoo=testplatform.pop(1);
					
					if (!poppoo.hasAnyPop()){
						System.out.println("����������������ı���");
						break;
					}
					
					
				}
				System.out.println("������");
				testplatform.adfasdasdassdfasdf();
				if (!testplatform.hasLegalMove()){
					testplatform.remake();
					System.out.println("���ɽ����ˣ������º�");
					testplatform.adfasdasdassdfasdf();
				}
			}
		}
	}
	
}
