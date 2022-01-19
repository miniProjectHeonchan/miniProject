package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.UserInfoDAO;
import Model.UserInfoVO;
import Model.WordListDAO;
import Model.WordListVO;

public class ViewMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserInfoDAO userDAO = new UserInfoDAO();
		WordListDAO wordDAO = new WordListDAO();

		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆년도별 유행어 맞추기 게임!!!☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println();
		System.out.println("신규 유저는 회원가입을 진행 후 로그인해!!^^");

		while (true) {
			System.out.print("1번 회원가입 2번 로그인 3번 종료 번호 입력 >>>> ");
			int choice = sc.nextInt();

			if (choice == 1) { // 회원가입
				System.out.println("☆★☆★회원가입 진행 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String pw = sc.next();

				boolean check = userDAO.insertUser(id, pw);

				if (check) {
					System.out.println("아이디 생성 성공!!");
				} else {
					System.out.println("아이디 생성 실패!!");
				}
				System.out.println();
			} else if (choice == 2) { // 로그인
				System.out.println("☆★☆★로그인 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String password = sc.next();

				boolean check = userDAO.login(id, password);
				System.out.println();

				if (check) {
					System.out.println("로그인 성공!!");
					System.out.println();
					
					int resultGameScore = 0;// 2번 누적결과
					
					while (true) {
						System.out.println("☆★☆★☆★1번 게임하기☆★☆★☆★");
						System.out.println("☆★☆★☆★2번 누적결과☆★☆★☆★");
						System.out.println("☆★☆★☆★3번 랭킹확인☆★☆★☆★");
						System.out.println("☆★☆★☆★4번 로그아웃☆★☆★☆★");
						System.out.print("번호 선택 >>>>>>>>>>>>>>>>>>>>> ");
						int num = sc.nextInt();

						if (num == 1) {// 게임하기
							System.out.print("1번 2000년대, 2번 2010년대, 3번 2020년대 선택 >>> ");
							int yearNum = sc.nextInt();

							System.out.println("게임을 시작!!!!");
							resultGameScore = 0;
							
							if (yearNum == 1) {// 2000년대
								// 2000년대

								yearNum = 2000;
								ArrayList<WordListVO> vo = wordDAO.wordList(yearNum);
								int score = 0; // 문제 점수

								
								int[] meanLength = new int[vo.size()];
								for(int i = 0;i < vo.size();i++) {
									meanLength[i] = i;
								}
								
								
								
								
								for (int i = 0; i < 3; i++) {

									// 문제출력
									System.out.println(vo.get(i).getMean());
									
									//힌트점수누적
									int hintScore = 0;
									// 힌트 본 횟수
									int hintCnt = 0;

									while (true) {
										// 힌트보기 선택지 주기? 1번 정답입력 2번 힌트보기
										System.out.print("1번 정답입력 2번 힌트보기 >>> ");
										int num3 = sc.nextInt();

										if (num3 == 1) {
											// 답 입력
											System.out.print("정답 입력! >>> ");
											String str = sc.next();

											if (vo.get(i).getWord().equals(str)) {
												
												// 점수추가
												score += 10 - hintScore;
												resultGameScore = 10 - hintScore;
												System.out.println("정답입니다!");
												System.out.println();
												break;

											} else {
												System.out.println("정답은 : " + vo.get(i).getWord() + " 입니다.");
												System.out.println();
												break;
											}

										} else if (num3 == 2) {
											if (hintCnt == 0) {
												// 힌트 1번 출력
												System.out.println("첫번째 힌트 입니다!!");
												System.out.println(vo.get(i).getHint1());
												hintCnt++;
												hintScore += 3;
											} else if (hintCnt == 1) {
												System.out.println("두번째 힌트 입니다!! 모든힌트를 다 보셨습니다!!");
												System.out.println(vo.get(i).getHint2());
												hintCnt++;
												hintScore += 2;
											} else {
												System.out.println("힌트를 다보셨습니다!");
											}
										} else {
											System.out.println("잘못 입력!");
										}
									}
								} // end of for문
								int totalScore = userDAO.getScore(id);
								userDAO.updateScore(totalScore + score, id);
								System.out.println(score + "점 입니다");

							} else if (yearNum == 2) {// 2010년대
								// 2010년대

							} else if (yearNum == 3) {// 2020년대
								// 2020년대

							} else {
								System.out.println("잘못 입력!!");
							}

						} else if (num == 2) {// 누적결과보기
							System.out.println(resultGameScore + "점 입니다.!");

							if (resultGameScore > 80) {
								System.out.println("당신은 상등급 입니다!");
							} else if (resultGameScore > 60) {
								System.out.println("당신은 중등급 입니다!");
							} else if (resultGameScore > 40) {
								System.out.println("당신은 하등급 입니다!");
							} else {
								System.out.println("당신은 최하등급 입니다!");
							}
							System.out.println();
						} else if (num == 3) {// 랭킹확인
							//1.모든 사용자 출력 점수에 따라 정렬 후 출력
							System.out.println("=========랭킹 확인=========");
							/*
							int abc = 1;
							for(UserInfoVO a:userDAO.rank()) {
								System.out.print(abc+"위 아이디 : " + a.getID());
								System.out.print("\t점수" + a.getSCORE());
								abc++;
							}*/
							ArrayList<UserInfoVO> rank = userDAO.rank();
							for(int i = 0;i < 10;i++) {
								System.out.print((i+1)+"위 아이디 : "+rank.get(i).getID());
								System.out.print("\t\t점수 : "+rank.get(i).getSCORE());
								System.out.println();
							}
							System.out.println();

						} else if (num == 4) {// 로그아웃
							System.out.println("로그아웃!!");
							break;
						} else {
							System.out.println("잘못입력!!");
						}
					}

				} else {
					System.out.println("로그인 실패!!");
				}

			} else if (choice == 3) { // 종료
				System.out.println("종료!!");
				break;
			} else { // 잘못입력
				System.out.println("잘못 입력!!");
			}

		} // end of while
	}// end of main
}// end of class
