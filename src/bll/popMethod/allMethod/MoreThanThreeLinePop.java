package bll.popMethod.allMethod;

import java.util.ArrayList;

import bll.matrix.Matrix;
import po.DotPo;

public class MoreThanThreeLinePop extends PopMethod {
	// ֱ������������������ͬ
	@Override
	public boolean popChcek(Matrix chessboard, DotPo dot1, DotPo dot2) {
		// TODO Auto-generated method stub
		// System.out.println("Matrix For Check:");
		// for (int i=0;i<Matrix.TOTALLINE;i++){
		// for (int j=0;j<Matrix.TOTALROW;j++){
		// System.out.print(chessboard.getMatrix()[i][j].getColor()+" ");
		// }
		// System.out.println();
		// }
		if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() != Matrix.NORMAL
				&& chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() != Matrix.NORMAL) {
			return true;
		}
		if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS
				|| chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
			return true;
		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				int count = 0;
				int temp = j;
				int color = chessboard.getMatrix()[i][j].getColor();
				// ����
				while (++temp < Matrix.TOTALROW && color == chessboard.getMatrix()[i][temp].getColor()) {
					count++;
				}
				if (count >= 2)
					return true;
				count = 0;
				temp = i;
				// ����
				while (++temp < Matrix.TOTALLINE && color == chessboard.getMatrix()[temp][j].getColor()) {
					count++;
				}
				if (count >= 2)
					return true;
			}
		}
		return false;
	}

	@Override
	public void pop(Matrix chessboard, DotPo dot1, DotPo dot2) {
		// TODO Auto-generated method stub

		// ��ʼ��
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				chessboard.getIsPop()[i][j] = 0;
			}
		}
		int[] popNum1 = chessboard.getNum();
		// ������
//		System.out.println("Matrix For Pop:");
//		for (int i = 0; i < Matrix.TOTALLINE; i++) {
//			for (int j = 0; j < Matrix.TOTALROW; j++) {
//				System.out.print(chessboard.getMatrix()[i][j].getColor() + " ");
//			}
//			System.out.println();
//		}
		ArrayList<DotPo> queue = new ArrayList<DotPo>();
		int[][] in = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		int[][] linecheck = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		int[][] rowcheck = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		boolean[][] dead = new boolean[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS
				&& chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
			// Two Chick
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					dead[i][j] = true;
					chessboard.getIsPop()[i][j]=Matrix.CHICKPOP;
				}
			}
			in[dot1.getX()][dot1.getY()] = in[dot2.getX()][dot2.getY()] = 1234;
			queue.add(new DotPo(dot1.getX(), dot1.getY(), Matrix.NONE, Matrix.DOUBLECHICK));
			queue.add(new DotPo(dot2.getX(), dot2.getY(), Matrix.NONE, Matrix.DOUBLECHICK));

		} else if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS
				|| chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
			if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
				DotPo temp = dot1;
				dot1 = dot2;
				dot2 = temp;
			}
			// dot1 Chick dot2 Normal | LineBonus | RowBonus | BombBonus
			if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
				// dot1 Chick dot2 Normal
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard.getMatrix()[i][j].getColor() == chessboard.getMatrix()[dot2.getX()][dot2.getY()]
								.getColor()) {
							dead[i][j] = true;
							chessboard.getIsPop()[i][j]=Matrix.CHICKPOP;
						}
					}
				}
				dead[dot1.getX()][dot1.getY()] = true;
				in[dot1.getX()][dot1.getY()] = 1234;
				// queue.add(new DotPo
				// (dot1.getX(),dot1.getY(),chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor()*1000+Matrix.NORMAL,Matrix.CHICKBONUS));
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.CHICKITSELFPOP;

				// ���⴦�� ��ͨ������
			} else {
		//		System.out.println("Chick+Bonus Start");
				// dot1 Chick dot2 LineBonus | RowBonus | BombBonus
				int bonus = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard.getMatrix()[i][j].getColor() == chessboard.getMatrix()[dot2.getX()][dot2.getY()]
								.getColor()) {
							chessboard.getMatrix()[i][j].setBonus((bonus == Matrix.BOMBBONUS) ? Matrix.BOMBBONUS
									: (Math.random() > 0.5 ? Matrix.LINEBONUS : Matrix.ROWBONUS));
			//				System.out.println("(" + i + "," + j + ")->"
					//				+ chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus());
							dead[i][j] = true;
						}
					}
				}
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				dead[dot1.getX()][dot1.getY()] = true;
				in[dot1.getX()][dot1.getY()] = 1234;
				chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.CHICKITSELFPOP;
			}
		} else if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() != Matrix.NORMAL
				&& chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() != Matrix.NORMAL) {
			// dot1 dot2 LineBonus | RowBonus | BombBonus
			if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.BOMBBONUS) {
				DotPo temp = dot1;
				dot1 = dot2;
				dot2 = temp;
			}
			if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() != Matrix.BOMBBONUS) {
				// ��ը��
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.BLANK);
				dead[dot1.getX()][dot1.getY()] = true;
				dead[dot2.getX()][dot2.getY()] = true;
				in[dot1.getX()][dot1.getY()] = 1234;
				in[dot2.getX()][dot2.getY()] = 1234;
				queue.add(
						new DotPo(dot1.getX(), dot1.getY(), chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
								chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
				queue.add(
						new DotPo(dot2.getX(), dot2.getY(), chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
								chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
				chessboard.getIsPop()[dot1.getX()][dot1.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()]
						.getBonus();
				chessboard.getIsPop()[dot2.getX()][dot2.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()]
						.getBonus();
			} else if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.LINEBONUS) {
				// dot1 ը�� dot2����Ч
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.BLANK);
				int imin = dot1.getX()<dot2.getX()?dot1.getX():dot2.getX();
				int imax = dot1.getX()>dot2.getX()?dot1.getX():dot2.getX();
				for (int i = imin-1; i <= imax+1; i++) {
					if (i >= 0 && i < Matrix.TOTALLINE) {
						chessboard.getIsPop()[i][dot2.getY()] = Matrix.LINEBONUSPOP;
						for (int j = 0; j < Matrix.TOTALROW; j++) {
							dead[i][j] = true;
						}
					}
				}
				in[dot1.getX()][dot1.getY()] = 1234;
				in[dot2.getX()][dot2.getY()] = 1234;
				// ��װ���

			} else if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.ROWBONUS) {
				// dot1ը�� dot2����Ч
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.BLANK);
				int jmin = dot1.getY()<dot2.getY()?dot1.getY():dot2.getY();
				int jmax = dot1.getY()>dot2.getY()?dot1.getY():dot2.getY();
				for (int j = jmin-1; j <= jmax+1; j++) {
					if (j >= 0 && j < Matrix.TOTALROW) {
						chessboard.getIsPop()[dot2.getX()][j] = Matrix.ROWBONUS;
						for (int i = 0; i < Matrix.TOTALLINE; i++) {
							dead[i][j] = true;
						}
					}
				}
				in[dot1.getX()][dot1.getY()] = 1234;
				in[dot2.getX()][dot2.getY()] = 1234;
			} else if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.BOMBBONUS) {
				// dot1\2����ը��
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.BLANK);
				if (Math.random() > 0.5) {
					DotPo temp = dot1;
					dot1 = dot2;
					dot2 = temp;
				}
				for (int i = dot1.getX() - 3; i <= dot1.getX() + 3; i++) {
					for (int j = dot1.getY() -3+Math.abs(i-dot1.getX()); j <= dot1.getY() +3- Math.abs(i-dot1.getX()); j++) {
						if (i >= 0 && i < Matrix.TOTALLINE && j >= 0 && j < Matrix.TOTALROW) {
							dead[i][j] = true;
						}
					}
				}
				in[dot1.getX()][dot1.getY()] = 1234;
				chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.BLANK);
				chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.BIGBANG;
			}
		} else {
			// at least one Normal
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					int color = chessboard.getMatrix()[i][j].getColor();
					// ץ��
					int tempx = i, tempy = j;
					// ��
					int dx = 1, dy = 0;
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					if (tempx + tempy - i - j >= 4) {
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								rowcheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
						// ����������
						if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
							// dot1������� ���dot1����Ч �������
							if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
								// ���� ����
								chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.TOCHICK;
								chessboard.getMatrix()[dot1.getX()][dot1.getY()].setBonus(Matrix.CHICKBONUS);
								chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.NONE);
								// in[dot1.getX()][dot1.getY()]=1234;
								dead[dot1.getX()][dot1.getY()] = false;
								// ������
								// ��װ�Լ����˶�,��ʵû��
							} else {
								// �˷��������Ч���Ӿ�Ч��
								chessboard.getIsPop()[dot1.getX()][dot1
										.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										Matrix.CHICKBONUS));
								in[dot1.getX()][dot1.getY()] = 1234;

							}
						} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
								&& dot2.getY() <= tempy) {
							// dot2������� ���dot2����Ч �������
							if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
								// ���� ����
								chessboard.getIsPop()[dot2.getX()][dot2.getY()] = Matrix.TOCHICK;
								chessboard.getMatrix()[dot2.getX()][dot2.getY()].setBonus(Matrix.CHICKBONUS);
								chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.NONE);
								// in[dot2.getX()][dot2.getY()]=1234;
								dead[dot2.getX()][dot2.getY()] = false;
								// ������
								// ��װ�Լ����˶�,��ʵû��
							} else {
								// �˷��������Ч���Ӿ�Ч��
								chessboard.getIsPop()[dot2.getX()][dot2
										.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										Matrix.CHICKBONUS));
								in[dot2.getX()][dot2.getY()] = 1234;

							}
						} else {
							// ��NORMAL������ �����
							boolean flag = false;
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
										flag = true;
										chessboard.getIsPop()[ii][jj] = Matrix.TOCHICK;
										chessboard.getMatrix()[ii][jj].setBonus(Matrix.CHICKBONUS);
										chessboard.getMatrix()[ii][jj].setColor(Matrix.NONE);
										// in[ii][jj]=1234;
										dead[ii][jj] = false;
										break;
									}
								}
								if (flag)
									break;
							}
						}
					}
					// ��
					tempx = i;
					tempy = j;
					dx = 0;
					dy = 1;
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					if (tempx + tempy - i - j >= 4) {
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								linecheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
						// ����������
						if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
							// dot1������� ���dot1����Ч �������
							if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
								// ���� ����
								chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.TOCHICK;
								chessboard.getMatrix()[dot1.getX()][dot1.getY()].setBonus(Matrix.CHICKBONUS);
								chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.NONE);
								// in[dot1.getX()][dot2.getY()]=1234;
								dead[dot1.getX()][dot1.getY()] = false;
								// ������
								// ��װ�Լ����˶�,��ʵû��
							} else {
								// �˷��������Ч���Ӿ�Ч��
								chessboard.getIsPop()[dot1.getX()][dot1
										.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										Matrix.CHICKBONUS));
								in[dot1.getX()][dot1.getY()] = 1234;

							}
						} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
								&& dot2.getY() <= tempy) {
							// dot2������� ���dot2����Ч �������
							if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
								// ���� ����
								chessboard.getIsPop()[dot2.getX()][dot2.getY()] = Matrix.TOCHICK;
								chessboard.getMatrix()[dot2.getX()][dot2.getY()].setBonus(Matrix.CHICKBONUS);
								chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.NONE);
								// in[dot2.getX()][dot2.getY()]=1234;
								dead[dot2.getX()][dot2.getY()] = false;
								// ���ֲ�����
								// ��װ�Լ����˶�,��ʵû��
							} else {
								// �˷��������Ч���Ӿ�Ч��
								chessboard.getIsPop()[dot2.getX()][dot2
										.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										Matrix.CHICKBONUS));
								in[dot2.getX()][dot2.getY()] = 1234;

							}
						} else {
							// ��NORMAL������ �����
							boolean flag = false;
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
										flag = true;
										chessboard.getIsPop()[ii][jj] = Matrix.TOCHICK;
										chessboard.getMatrix()[ii][jj].setBonus(Matrix.CHICKBONUS);
										chessboard.getMatrix()[ii][jj].setColor(Matrix.NONE);
										// in[ii][jj]=1234;
										dead[ii][jj] = false;
										break;
									}
								}
								if (flag)
									break;
							}
						}
					}
				}
			}
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					// ץ�ĸ���
					int color = chessboard.getMatrix()[i][j].getColor();
					int tempx = i, tempy = j;
					int dx = 1, dy = 0;
					// ����
					if (rowcheck[i][j] != 1) {
						// ֮ǰû��������������
						while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
								&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
							tempx += dx;
							tempy += dy;
						}
						if (tempx + tempy - i - j == 3) {
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									rowcheck[ii][jj] = 1;
									dead[ii][jj] = true;
								}
							}
							// ����������Ч
							if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
								// dot1����Ч ����� ����Ѿ�����Ч ֱ�����
								if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
									// dot1����Ч �����
									double temp = Math.random();
									chessboard.getIsPop()[dot1.getX()][dot1.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
											: Matrix.TOROWBONUS;
									chessboard.getMatrix()[dot1.getX()][dot1.getY()]
											.setBonus(chessboard.getIsPop()[dot1.getX()][dot1.getY()] / 10);
									// in[dot1.getX()][dot1.getY()]=1234;
									dead[dot1.getX()][dot1.getY()] = false;
								} else {
									// dot1 ֱ�����
									queue.add(new DotPo(dot1.getX(), dot1.getY(),
											chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
											chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
									chessboard.getIsPop()[dot1.getX()][dot1
											.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
									in[dot1.getX()][dot1.getY()] = 1234;
								}
							} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
									&& dot2.getY() <= tempy) {
								// dot2����Ч ����� ����Ѿ�����Ч ֱ�����
								if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
									// dot1����Ч �����
									double temp = Math.random();
									chessboard.getIsPop()[dot2.getX()][dot2.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
											: Matrix.TOROWBONUS;
									chessboard.getMatrix()[dot2.getX()][dot2.getY()]
											.setBonus(chessboard.getIsPop()[dot2.getX()][dot2.getY()] / 10);
									// in[dot1.getX()][dot1.getY()]=1234;
									dead[dot2.getX()][dot2.getY()] = false;
								} else {
									// dot1 ֱ�����
									queue.add(new DotPo(dot2.getX(), dot2.getY(),
											chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
											chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
									chessboard.getIsPop()[dot2.getX()][dot2
											.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
									in[dot2.getX()][dot2.getY()] = 1234;
								}
							} else {
								// ���������Ч �����
								boolean flag = false;
								for (int ii = i; ii <= tempx; ii++) {
									for (int jj = j; jj <= tempy; jj++) {
										if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
											chessboard.getIsPop()[ii][jj] = Math.random() >= 0.5 ? Matrix.TOLINEBONUS
													: Matrix.TOROWBONUS;
											chessboard.getMatrix()[ii][jj].setBonus(chessboard.getIsPop()[ii][jj] / 10);
											dead[ii][jj] = false;
											flag = true;
										}
										if (flag)
											break;
									}
									if (flag)
										break;
								}
							}
						}
					}
					if (linecheck[i][j] != 1) {
						// ����
						// ֮ǰû��������������
						tempx = i;
						tempy = j;
						dx = 0;
						dy = 1;
						while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
								&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
							tempx += dx;
							tempy += dy;
						}
						if (tempx + tempy - i - j == 3) {
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									linecheck[ii][jj] = 1;
									dead[ii][jj] = true;
								}
							}
							// ����������Ч
							if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
								// dot1����Ч ����� ����Ѿ�����Ч ֱ�����
								if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
									// dot1����Ч �����
									double temp = Math.random();
									chessboard.getIsPop()[dot1.getX()][dot1.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
											: Matrix.TOROWBONUS;
									chessboard.getMatrix()[dot1.getX()][dot1.getY()]
											.setBonus(chessboard.getIsPop()[dot1.getX()][dot1.getY()] / 10);
									// in[dot1.getX()][dot1.getY()]=1234;
									dead[dot1.getX()][dot1.getY()] = false;
								} else {
									// dot1 ֱ�����
									queue.add(new DotPo(dot1.getX(), dot1.getY(),
											chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
											chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
									chessboard.getIsPop()[dot1.getX()][dot1
											.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
									in[dot1.getX()][dot1.getY()] = 1234;
								}
							} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
									&& dot2.getY() <= tempy) {
								// dot2����Ч ����� ����Ѿ�����Ч ֱ�����
								if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
									// dot1����Ч �����
									double temp = Math.random();
									chessboard.getIsPop()[dot2.getX()][dot2.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
											: Matrix.TOROWBONUS;
									chessboard.getMatrix()[dot2.getX()][dot2.getY()]
											.setBonus(chessboard.getIsPop()[dot2.getX()][dot2.getY()] / 10);
									// in[dot1.getX()][dot1.getY()]=1234;
									dead[dot2.getX()][dot2.getY()] = false;
								} else {
									// dot1 ֱ�����
									queue.add(new DotPo(dot2.getX(), dot2.getY(),
											chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
											chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
									chessboard.getIsPop()[dot2.getX()][dot2
											.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
									in[dot2.getX()][dot2.getY()] = 1234;
								}
							} else {
								// ���������Ч
								boolean flag = false;
								for (int ii = i; ii <= tempx; ii++) {
									for (int jj = j; jj <= tempy; jj++) {
										if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
											chessboard.getIsPop()[ii][jj] = Math.random() >= 0.5 ? Matrix.TOLINEBONUS
													: Matrix.TOROWBONUS;
											chessboard.getMatrix()[ii][jj].setBonus(chessboard.getIsPop()[ii][jj] / 10);
											dead[ii][jj] = false;
											flag = true;
										}
										if (flag)
											break;
									}
									if (flag)
										break;
								}
							}
						}
					}
				}
			}
			// ��ͨ����������
			// System.out.println("Start Three Test");
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					int color = chessboard.getMatrix()[i][j].getColor();
					int tempx = i, tempy = j;
					int dx = 1, dy = 0;
					if (rowcheck[i][j] != 1) {
						// System.out.println("("+i+","+j+")"+"Three Row Test");
						while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
								&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
							tempx += dx;
							tempy += dy;
						}
						// System.out.println("End At"+"("+tempx+","+tempy+")");
						if (tempx + tempy - i - j == 2) {
					//		System.out.println("Three Row Succ At" + "(" + i + "," + j + ")");
							// ��ʲô����ֱ�Ӵ�������flag
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									rowcheck[ii][jj] = 1;
									dead[ii][jj] = true;
								}
							}
						}
					}
					if (linecheck[i][j] != 1) {
						// System.out.println("("+i+","+j+")"+"Three Line
						// Test");
						tempx = i;
						tempy = j;
						dx = 0;
						dy = 1;
						while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
								&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
							tempx += dx;
							tempy += dy;
						}
						// System.out.println("End At"+"("+tempx+","+tempy+")");
						if (tempx + tempy - i - j == 2) {
					//		System.out.println("Three Line Succ At" + "(" + i + "," + j + ")");
							// ��ʲô����ֱ�Ӵ�������flag
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									linecheck[ii][jj] = 1;
									dead[ii][jj] = true;
								}
							}
						}
					}

				}
			}
		}

		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				if (linecheck[i][j] == 1 && rowcheck[i][j] == 1
						&& chessboard.getMatrix()[i][j].getBonus() == Matrix.NORMAL) {
					// ��ը��Ч �����
					chessboard.getIsPop()[i][j] = Matrix.TOBOMBBONUS;
					chessboard.getMatrix()[i][j].setBonus(Matrix.BOMBBONUS);
					dead[i][j] = false;
				}
			}
		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				// ���δ��ӵ���������
				if (dead[i][j] && (in[i][j] != 1234)) {
					in[i][j] = 1234;
					queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
							chessboard.getMatrix()[i][j].getBonus()));
				}
			}
		}
		//
		for (DotPo temp : queue) {
	//		System.out.println("(" + temp.getX() + "," + temp.getY() + "):" + temp.getColor() + "|" + temp.getBonus());
		}

		while (queue.size() > 0) {
			DotPo head = queue.get(0);
			queue.remove(0);
			chessboard.getMatrix()[head.getX()][head.getY()].setColor(Matrix.BLANK);
//			chessboard.getPopNum()[head.getColor()]++;
//			if (head.getBonus() == Matrix.DOUBLECHICK) {
//				chessboard.getPopNum()[Matrix.CHICKBONUS]++;
//			}
			if (head.getBonus() == Matrix.NORMAL) {

			} else if (head.getBonus() == Matrix.LINEBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.LINEBONUSPOP;
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (!dead[head.getX()][j]) {
						dead[head.getX()][j] = true;
						queue.add(new DotPo(head.getX(), j, chessboard.getMatrix()[head.getX()][j].getColor(),
								chessboard.getMatrix()[head.getX()][j].getBonus()));
					}
				}
			} else if (head.getBonus() == Matrix.ROWBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.ROWBONUSPOP;
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					if (!dead[i][head.getY()]) {
						dead[i][head.getY()] = true;
						queue.add(new DotPo(i, head.getY(), chessboard.getMatrix()[i][head.getY()].getColor(),
								chessboard.getMatrix()[i][head.getY()].getBonus()));
					}
				}
			} else if (head.getBonus() == Matrix.BOMBBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.BOMBBONUSPOP;
				// ʮ���ǵ�ǰ����
				for (int j = head.getY() - 2; j <= head.getY(); j++) {
					for (int i = head.getX() - j - 2 + head.getY(); i <= head.getX() + j + 2 - head.getY(); i++) {
						if (i >= 0 && i < Matrix.TOTALLINE && j >= 0 && j < Matrix.TOTALROW && !dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
					}
				}
				// ʮ���ǵĺ�����
				for (int j = head.getY() + 1; j <= head.getY() + 2; j++) {
					for (int i = head.getX() - head.getY() - 2 + j; i <= head.getX() + head.getY() + 2 - j; i++) {
						if (i >= 0 && i < Matrix.TOTALLINE && j >= 0 && j < Matrix.TOTALROW && !dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
					}
				}
			} else if (head.getBonus() == Matrix.CHICKBONUS) {
				if (head.getColor()==Matrix.NONE){
					head.setColor((int)(Math.random()*Matrix.KIND));
				}
				chessboard.getIsPop()[head.getX()][head.getY()]=Matrix.CHICKITSELFPOP;
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard.getMatrix()[i][j].getColor() == head.getColor()) {
							chessboard.getIsPop()[i][j] = Matrix.CHICKPOP;
						//	System.out.println("<" + i + "," + j + ">");
							if (!dead[i][j]) {
								dead[i][j] = true;
								queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
										chessboard.getMatrix()[i][j].getBonus()));
							}
						}
					}
				}
			} else if (head.getBonus() == Matrix.DOUBLECHICK) {
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (!dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
						if (chessboard.getIsPop()[i][j] == 0) {
							chessboard.getIsPop()[i][j] = Matrix.CHICKPOP;
						}
					}
				}
			}

		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				if (dead[i][j] && chessboard.getIsPop()[i][j] == 0) {
					// System.out.println("("+i+","+j+")");
					// chessboard.getMatrix()[i][j].setColor(Matrix.BLANK);
					chessboard.getIsPop()[i][j] = Matrix.NORMALPOP;
				}
			}
		}
		int[] popNum2 = chessboard.getNum();
		int [] ans = new int [Matrix.NONE+1];
		//popNum������
		for (int i = 0; i < Matrix.NONE + 1; i++) {
			ans[i]= popNum1[i] - popNum2[i];
		}
		chessboard.setPopNum(ans);
//		chessboard.getPopNum();
		chessboard.renew();

	}

	public void pop(Matrix chessboard) {
		// ������
//		System.out.println("Matrix For Pop:");
//		for (int i = 0; i < Matrix.TOTALLINE; i++) {
//			for (int j = 0; j < Matrix.TOTALROW; j++) {
//				System.out.print(chessboard.getMatrix()[i][j].getColor() + " ");
//			}
//			System.out.println();
//		}

		int[] popNum1 = chessboard.getNum();
		ArrayList<DotPo> queue = new ArrayList<DotPo>();
		int[][] in = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		int[][] linecheck = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		int[][] rowcheck = new int[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		boolean[][] dead = new boolean[Matrix.TOTALLINE + 1][Matrix.TOTALROW];
		DotPo dot1 = new DotPo(10, 0, 0, 0);
		DotPo dot2 = new DotPo(10, 0, 0, 0);
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				chessboard.getIsPop()[i][j] = 0;
			}
		}
		// at least one Normal
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				int color = chessboard.getMatrix()[i][j].getColor();
				// ץ��
				int tempx = i, tempy = j;
				// ��
				int dx = 1, dy = 0;
				while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
						&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
					tempx += dx;
					tempy += dy;
				}
				if (tempx + tempy - i - j >= 4) {
					for (int ii = i; ii <= tempx; ii++) {
						for (int jj = j; jj <= tempy; jj++) {
							rowcheck[ii][jj] = 1;
							dead[ii][jj] = true;
						}
					}
					// ����������
					if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
						// dot1������� ���dot1����Ч �������
						if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
							// ���� ����
							chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.TOCHICK;
							chessboard.getMatrix()[dot1.getX()][dot1.getY()].setBonus(Matrix.CHICKBONUS);
							chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.NONE);
							// in[dot1.getX()][dot1.getY()]=1234;
							dead[dot1.getX()][dot1.getY()] = false;
							// ������
							// ��װ�Լ����˶�,��ʵû��
						} else {
							// �˷��������Ч���Ӿ�Ч��
							chessboard.getIsPop()[dot1.getX()][dot1
									.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
							queue.add(new DotPo(dot1.getX(), dot1.getY(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
							queue.add(new DotPo(dot1.getX(), dot1.getY(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(), Matrix.CHICKBONUS));
							in[dot1.getX()][dot1.getY()] = 1234;

						}
					} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j && dot2.getY() <= tempy) {
						// dot2������� ���dot2����Ч �������
						if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
							// ���� ����
							chessboard.getIsPop()[dot2.getX()][dot2.getY()] = Matrix.TOCHICK;
							chessboard.getMatrix()[dot2.getX()][dot2.getY()].setBonus(Matrix.CHICKBONUS);
							chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.NONE);
							// in[dot2.getX()][dot2.getY()]=1234;
							dead[dot2.getX()][dot2.getY()] = false;
							// ������
							// ��װ�Լ����˶�,��ʵû��
						} else {
							// �˷��������Ч���Ӿ�Ч��
							chessboard.getIsPop()[dot2.getX()][dot2
									.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
							queue.add(new DotPo(dot2.getX(), dot2.getY(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
							queue.add(new DotPo(dot2.getX(), dot2.getY(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(), Matrix.CHICKBONUS));
							in[dot2.getX()][dot2.getY()] = 1234;

						}
					} else {
						// ��NORMAL������ �����
						boolean flag = false;
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
									flag = true;
									chessboard.getIsPop()[ii][jj] = Matrix.TOCHICK;
									chessboard.getMatrix()[ii][jj].setBonus(Matrix.CHICKBONUS);
									chessboard.getMatrix()[ii][jj].setColor(Matrix.NONE);
									// in[ii][jj]=1234;
									dead[ii][jj] = false;
									break;
								}
							}
							if (flag)
								break;
						}
					}
				}
				// ��
				tempx = i;
				tempy = j;
				dx = 0;
				dy = 1;
				while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
						&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
					tempx += dx;
					tempy += dy;
				}
				if (tempx + tempy - i - j >= 4) {
					for (int ii = i; ii <= tempx; ii++) {
						for (int jj = j; jj <= tempy; jj++) {
							linecheck[ii][jj] = 1;
							dead[ii][jj] = true;
						}
					}
					// ����������
					if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
						// dot1������� ���dot1����Ч �������
						if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
							// ���� ����
							chessboard.getIsPop()[dot1.getX()][dot1.getY()] = Matrix.TOCHICK;
							chessboard.getMatrix()[dot1.getX()][dot1.getY()].setBonus(Matrix.CHICKBONUS);
							chessboard.getMatrix()[dot1.getX()][dot1.getY()].setColor(Matrix.NONE);
							// in[dot1.getX()][dot2.getY()]=1234;
							dead[dot1.getX()][dot1.getY()] = false;
							// ������
							// ��װ�Լ����˶�,��ʵû��
						} else {
							// �˷��������Ч���Ӿ�Ч��
							chessboard.getIsPop()[dot1.getX()][dot1
									.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
							queue.add(new DotPo(dot1.getX(), dot1.getY(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
							queue.add(new DotPo(dot1.getX(), dot1.getY(),
									chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(), Matrix.CHICKBONUS));
							in[dot1.getX()][dot1.getY()] = 1234;

						}
					} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j && dot2.getY() <= tempy) {
						// dot2������� ���dot2����Ч �������
						if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
							// ���� ����
							chessboard.getIsPop()[dot2.getX()][dot2.getY()] = Matrix.TOCHICK;
							chessboard.getMatrix()[dot2.getX()][dot2.getY()].setBonus(Matrix.CHICKBONUS);
							chessboard.getMatrix()[dot2.getX()][dot2.getY()].setColor(Matrix.NONE);
							// in[dot2.getX()][dot2.getY()]=1234;
							dead[dot2.getX()][dot2.getY()] = false;
							// ���ֲ�����
							// ��װ�Լ����˶�,��ʵû��
						} else {
							// �˷��������Ч���Ӿ�Ч��
							chessboard.getIsPop()[dot2.getX()][dot2
									.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
							queue.add(new DotPo(dot2.getX(), dot2.getY(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
							queue.add(new DotPo(dot2.getX(), dot2.getY(),
									chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(), Matrix.CHICKBONUS));
							in[dot2.getX()][dot2.getY()] = 1234;

						}
					} else {
						// ��NORMAL������ �����
						boolean flag = false;
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
									flag = true;
									chessboard.getIsPop()[ii][jj] = Matrix.TOCHICK;
									chessboard.getMatrix()[ii][jj].setBonus(Matrix.CHICKBONUS);
									chessboard.getMatrix()[ii][jj].setColor(Matrix.NONE);
									// in[ii][jj]=1234;
									dead[ii][jj] = false;
									break;
								}
							}
							if (flag)
								break;
						}
					}
				}
			}
		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				// ץ�ĸ���
				int color = chessboard.getMatrix()[i][j].getColor();
				int tempx = i, tempy = j;
				int dx = 1, dy = 0;
				// ����
				if (rowcheck[i][j] != 1) {

					// ֮ǰû��������������
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					// System.out.println("Start At "+"("+i+","+j+")"+"End At
					// "+"("+tempx+","+tempy+")");
					if (tempx + tempy - i - j == 3) {
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								rowcheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
						// ����������Ч
						if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
							// dot1����Ч ����� ����Ѿ�����Ч ֱ�����
							if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
								// dot1����Ч �����
								double temp = Math.random();
								chessboard.getIsPop()[dot1.getX()][dot1.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
										: Matrix.TOROWBONUS;
								chessboard.getMatrix()[dot1.getX()][dot1.getY()]
										.setBonus(chessboard.getIsPop()[dot1.getX()][dot1.getY()] / 10);
								// in[dot1.getX()][dot1.getY()]=1234;
								dead[dot1.getX()][dot1.getY()] = false;
							} else {
								// dot1 ֱ�����
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
								chessboard.getIsPop()[dot1.getX()][dot1
										.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
								in[dot1.getX()][dot1.getY()] = 1234;
							}
						} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
								&& dot2.getY() <= tempy) {
							// dot2����Ч ����� ����Ѿ�����Ч ֱ�����
							if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
								// dot1����Ч �����
								double temp = Math.random();
								chessboard.getIsPop()[dot2.getX()][dot2.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
										: Matrix.TOROWBONUS;
								chessboard.getMatrix()[dot2.getX()][dot2.getY()]
										.setBonus(chessboard.getIsPop()[dot2.getX()][dot2.getY()] / 10);
								// in[dot1.getX()][dot1.getY()]=1234;
								dead[dot2.getX()][dot2.getY()] = false;
							} else {
								// dot1 ֱ�����
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
								chessboard.getIsPop()[dot2.getX()][dot2
										.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
								in[dot2.getX()][dot2.getY()] = 1234;
							}
						} else {
							// ���������Ч �����
							boolean flag = false;
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
										chessboard.getIsPop()[ii][jj] = Math.random() >= 0.5 ? Matrix.TOLINEBONUS
												: Matrix.TOROWBONUS;
										chessboard.getMatrix()[ii][jj].setBonus(chessboard.getIsPop()[ii][jj] / 10);
										dead[ii][jj] = false;
										flag = true;
									}
									if (flag)
										break;
								}
								if (flag)
									break;
							}
						}
					}
				}
				if (linecheck[i][j] != 1) {
					// System.out.println("Four Line Test Start");
					// ����
					// ֮ǰû��������������
					tempx = i;
					tempy = j;
					dx = 0;
					dy = 1;
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					// System.out.println("Start At "+"("+i+","+j+")"+"End At
					// "+"("+tempx+","+tempy+")");
					if (tempx + tempy - i - j == 3) {
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								linecheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
						// ����������Ч
						if (dot1.getX() >= i && dot1.getX() <= tempx && dot1.getY() >= j && dot1.getY() <= tempy) {
							// dot1����Ч ����� ����Ѿ�����Ч ֱ�����
							if (chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus() == Matrix.NORMAL) {
								// dot1����Ч �����
								double temp = Math.random();
								chessboard.getIsPop()[dot1.getX()][dot1.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
										: Matrix.TOROWBONUS;
								chessboard.getMatrix()[dot1.getX()][dot1.getY()]
										.setBonus(chessboard.getIsPop()[dot1.getX()][dot1.getY()] / 10);
								// in[dot1.getX()][dot1.getY()]=1234;
								dead[dot1.getX()][dot1.getY()] = false;
							} else {
								// dot1 ֱ�����
								queue.add(new DotPo(dot1.getX(), dot1.getY(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor(),
										chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus()));
								chessboard.getIsPop()[dot1.getX()][dot1
										.getY()] = chessboard.getMatrix()[dot1.getX()][dot1.getY()].getBonus();
								in[dot1.getX()][dot1.getY()] = 1234;
							}
						} else if (dot2.getX() >= i && dot2.getX() <= tempx && dot2.getY() >= j
								&& dot2.getY() <= tempy) {
							// dot2����Ч ����� ����Ѿ�����Ч ֱ�����
							if (chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus() == Matrix.NORMAL) {
								// dot1����Ч �����
								double temp = Math.random();
								chessboard.getIsPop()[dot2.getX()][dot2.getY()] = temp <= 0.5 ? Matrix.TOLINEBONUS
										: Matrix.TOROWBONUS;
								chessboard.getMatrix()[dot2.getX()][dot2.getY()]
										.setBonus(chessboard.getIsPop()[dot2.getX()][dot2.getY()] / 10);
								// in[dot1.getX()][dot1.getY()]=1234;
								dead[dot2.getX()][dot2.getY()] = false;
							} else {
								// dot1 ֱ�����
								queue.add(new DotPo(dot2.getX(), dot2.getY(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getColor(),
										chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus()));
								chessboard.getIsPop()[dot2.getX()][dot2
										.getY()] = chessboard.getMatrix()[dot2.getX()][dot2.getY()].getBonus();
								in[dot2.getX()][dot2.getY()] = 1234;
							}
						} else {
							// ���������Ч
							boolean flag = false;
							for (int ii = i; ii <= tempx; ii++) {
								for (int jj = j; jj <= tempy; jj++) {
									if (chessboard.getMatrix()[ii][jj].getBonus() == Matrix.NORMAL) {
										chessboard.getIsPop()[ii][jj] = Math.random() >= 0.5 ? Matrix.TOLINEBONUS
												: Matrix.TOROWBONUS;
										chessboard.getMatrix()[ii][jj].setBonus(chessboard.getIsPop()[ii][jj] / 10);
										dead[ii][jj] = false;
										flag = true;
									}
									if (flag)
										break;
								}
								if (flag)
									break;
							}
						}
					}
				}
			}
		}
		// ��ͨ����������
		// System.out.println("Start Three Test");
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				int color = chessboard.getMatrix()[i][j].getColor();
				int tempx = i, tempy = j;
				int dx = 1, dy = 0;
				if (rowcheck[i][j] != 1) {
					// System.out.println("("+i+","+j+")"+"Three Row Test");
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					// System.out.println("End At"+"("+tempx+","+tempy+")");
					if (tempx + tempy - i - j == 2) {
				//		System.out.println("Three Row Succ At" + "(" + i + "," + j + ")");
						// ��ʲô����ֱ�Ӵ�������flag
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								rowcheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
					}
				}
				if (linecheck[i][j] != 1) {
					// System.out.println("("+i+","+j+")"+"Three Line Test");
					tempx = i;
					tempy = j;
					dx = 0;
					dy = 1;
					while (tempx + dx < Matrix.TOTALLINE && tempy + dy < Matrix.TOTALROW
							&& chessboard.getMatrix()[tempx + dx][tempy + dy].getColor() == color) {
						tempx += dx;
						tempy += dy;
					}
					// System.out.println("End At"+"("+tempx+","+tempy+")");
					if (tempx + tempy - i - j == 2) {
			//			System.out.println("Three Line Succ At" + "(" + i + "," + j + ")");
						// ��ʲô����ֱ�Ӵ�������flag
						for (int ii = i; ii <= tempx; ii++) {
							for (int jj = j; jj <= tempy; jj++) {
								linecheck[ii][jj] = 1;
								dead[ii][jj] = true;
							}
						}
					}
				}

			}

		}

		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				if (linecheck[i][j] == 1 && rowcheck[i][j] == 1
						&& chessboard.getMatrix()[i][j].getBonus() == Matrix.NORMAL) {
					// ��ը��Ч �����
					chessboard.getIsPop()[i][j] = Matrix.TOBOMBBONUS;
					chessboard.getMatrix()[i][j].setBonus(Matrix.BOMBBONUS);
					dead[i][j] = false;
				}
			}
		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				// ���δ��ӵ���������
				if (dead[i][j] && (in[i][j] != 1234)) {
					in[i][j] = 1234;
					queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
							chessboard.getMatrix()[i][j].getBonus()));
				}
			}
		}
		//
		for (DotPo temp : queue) {
	//		System.out.println("(" + temp.getX() + "," + temp.getY() + "):" + temp.getColor() + "|" + temp.getBonus());
		}

		while (queue.size() > 0) {
			DotPo head = queue.get(0);
			queue.remove(0);
			chessboard.getMatrix()[head.getX()][head.getY()].setColor(Matrix.BLANK);
//			chessboard.getPopNum()[head.getColor()]++;
			if (head.getBonus() == Matrix.DOUBLECHICK) {
				chessboard.getPopNum()[Matrix.CHICKBONUS]++;
			}
			if (head.getBonus() == Matrix.NORMAL) {

			} else if (head.getBonus() == Matrix.LINEBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.LINEBONUSPOP;
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (!dead[head.getX()][j]) {
						dead[head.getX()][j] = true;
						queue.add(new DotPo(head.getX(), j, chessboard.getMatrix()[head.getX()][j].getColor(),
								chessboard.getMatrix()[head.getX()][j].getBonus()));
					}
				}
			} else if (head.getBonus() == Matrix.ROWBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.ROWBONUSPOP;
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					if (!dead[i][head.getY()]) {
						dead[i][head.getY()] = true;
						queue.add(new DotPo(i, head.getY(), chessboard.getMatrix()[i][head.getY()].getColor(),
								chessboard.getMatrix()[i][head.getY()].getBonus()));
					}
				}
			} else if (head.getBonus() == Matrix.BOMBBONUS) {
				chessboard.getIsPop()[head.getX()][head.getY()] = Matrix.BOMBBONUSPOP;
				// ʮ���ǵ�ǰ����
				for (int j = head.getY() - 2; j <= head.getY(); j++) {
					for (int i = head.getX() - j - 2 + head.getY(); i <= head.getX() + j + 2 - head.getY(); i++) {
						if (i >= 0 && i < Matrix.TOTALLINE && j >= 0 && j < Matrix.TOTALROW && !dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
					}
				}
				// ʮ���ǵĺ�����
				for (int j = head.getY() + 1; j <= head.getY() + 2; j++) {
					for (int i = head.getX() - head.getY() - 2 + j; i <= head.getX() + head.getY() + 2 - j; i++) {
						if (i >= 0 && i < Matrix.TOTALLINE && j >= 0 && j < Matrix.TOTALROW && !dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
					}
				}
			} else if (head.getBonus() == Matrix.CHICKBONUS) {
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard.getMatrix()[i][j].getColor() == head.getColor()) {
							chessboard.getIsPop()[i][j] = Matrix.CHICKPOP;
							if (!dead[i][j]) {
								dead[i][j] = true;
								queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
										chessboard.getMatrix()[i][j].getBonus()));
							}
						}
					}
				}
			} else if (head.getBonus() == Matrix.DOUBLECHICK) {
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (!dead[i][j]) {
							dead[i][j] = true;
							queue.add(new DotPo(i, j, chessboard.getMatrix()[i][j].getColor(),
									chessboard.getMatrix()[i][j].getBonus()));
						}
						if (chessboard.getIsPop()[i][j] == 0) {
							chessboard.getIsPop()[i][j] = Matrix.CHICKPOP;
						}
					}
				}
			}

		}
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				if (dead[i][j] && chessboard.getIsPop()[i][j] == 0) {
					// System.out.println("("+i+","+j+")");
					// chessboard.getMatrix()[i][j].setColor(Matrix.BLANK);
					chessboard.getIsPop()[i][j] = Matrix.NORMALPOP;
				}
			}
		}
		
		
		int[] popNum2 = chessboard.getNum();
		int [] ans = new int [Matrix.NONE+1];
		for (int i = 0; i < Matrix.NONE + 1; i++) {
			ans[i]= popNum1[i] - popNum2[i];
		}
		chessboard.setPopNum(ans);
//		chessboard.getPopNum();
		chessboard.renew();
	}

	@Override
	public boolean hasLegalMove(Matrix chessboard) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				int color = chessboard.getMatrix()[i][j].getColor();
				if (chessboard.getMatrix()[i][j].getBonus() == Matrix.CHICKBONUS) {
					return true;
				} else if (chessboard.getMatrix()[i][j].getBonus() != Matrix.NORMAL) {
					if (i + 1 < Matrix.TOTALLINE && chessboard.getMatrix()[i + 1][j].getBonus() != Matrix.NORMAL) {
						return true;
					}
					if (j + 1 < Matrix.TOTALROW && chessboard.getMatrix()[i][j + 1].getBonus() != Matrix.NORMAL) {
						return true;
					}
				} else {
					if (j + 2 < Matrix.TOTALROW) {
						if (chessboard.getMatrix()[i][j + 1].getColor() == color) {
							if (i - 1 >= 0 && chessboard.getMatrix()[i - 1][j + 2].getColor() == color) {
								return true;
							} else if (i + 1 < Matrix.TOTALLINE
									&& chessboard.getMatrix()[i + 1][j + 2].getColor() == color) {
								return true;
							}
						}
						if (chessboard.getMatrix()[i][j + 2].getColor() == color) {
							if (i - 1 >= 0 && chessboard.getMatrix()[i - 1][j + 1].getColor() == color) {
								return true;
							} else if (i + 1 < Matrix.TOTALLINE
									&& chessboard.getMatrix()[i + 1][j + 1].getColor() == color) {
								return true;
							}
						}
					}
					if (i + 2 < Matrix.TOTALLINE) {
						if (chessboard.getMatrix()[i + 1][j].getColor() == color) {
							if (j - 1 >= 0 && chessboard.getMatrix()[i + 2][j - 1].getColor() == color) {
								return true;
							} else if (j + 1 < Matrix.TOTALROW
									&& chessboard.getMatrix()[i + 2][j + 1].getColor() == color) {
								return true;
							}
						}
						if (chessboard.getMatrix()[i + 2][j].getColor() == color) {
							if (j - 1 >= 0 && chessboard.getMatrix()[i + 1][j - 1].getColor() == color) {
								return true;
							} else if (j + 1 < Matrix.TOTALROW
									&& chessboard.getMatrix()[i + 1][j + 1].getColor() == color) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	// ǰ�˻�ȡispop��newmatrix
	// ispop��¼����Щ��������Ȼ���ŵ� ��Щ�����Ǳ�pop�� ��Щ�����Ǳ�chick�� ��Щ������bonuspop�ģ��ȱ����Ч��Ȼ������ű�������
	// ��Щ������chickpop�ģ��ȱ�ɼ���Ȼ������ű�������
	// һ��popָ�ڷ�������֮ǰ������pop��Ϣ
}
