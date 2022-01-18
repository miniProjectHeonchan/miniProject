package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.UserInfoDAO;
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
		
		while(true) {
			System.out.print("1번 회원가입 2번 로그인 3번 종료 번호 입력 >>>> ");
			int choice = sc.nextInt();
			
			if(choice == 1) { // 회원가입
				System.out.println("☆★☆★회원가입 진행 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String pw = sc.next();
				
				boolean check = userDAO.insertUser(id, pw);
				
				if(check) {
					System.out.println("아이디 생성 성공!!");
				}else {
					System.out.println("아이디 생성 실패!!");					
				}
				System.out.println();
			}else if(choice == 2) { // 로그인
				System.out.println("☆★☆★로그인 중☆★☆★");
				System.out.print("아이디 입력해!! ");
				String id = sc.next();
				System.out.print("비밀번호 입력해!! ");
				String password = sc.next();
				
				boolean check = userDAO.login(id, password);
				System.out.println();
				
				if(check) {
					System.out.println("로그인 성공!!");
					System.out.println();
					
					while(true) {
						System.out.println("☆★☆★☆★1번 게임하기☆★☆★☆★");
						System.out.println("☆★☆★☆★2번 누적결과☆★☆★☆★");
						System.out.println("☆★☆★☆★3번 랭킹확인☆★☆★☆★");
						System.out.println("☆★☆★☆★4번 로그아웃☆★☆★☆★");
						System.out.print("번호 선택 >>>>>>>>>>>>>>>>>>>>> ");
						int num = sc.nextInt();
						
						if(num == 1) {//게임하기
							System.out.print("1번 2000년대, 2번 2010년대, 3번 2020년대 선택 >>> ");
							int yearNum = sc.nextInt();
							
							System.out.println("게임을 시작!!!!");
							
							if(yearNum == 1) {// 2000년대
								// 2000년대
								//0. 반복문으로 묶기
								//1. 문제출력이 되고
								//2. 힌트보기 선택지 주기? 1번 정답입력 2번 힌트보기
								//3. 정답입력
								
								yearNum = 2000;
								ArrayList<WordListVO> vo = wordDAO.wordList(yearNum);
								
								for (int i = 0; i < 1; i++) {
									//1.문제출력
									//2.1번 힌트보기 선택지? 2번 정답입력 
									//3.1번, 2번 선택
									//4.1번 입력시 힌트보여주고 정답입력 또는 다음힌트보기 선택
									//5.두번째 힌트보면 정답입력 후 다음문제 출력
									//6.문제 다풀면 점수안보여주고 끝?
									
									//문제출력
									System.out.println(vo.get(i).getMean());
									
									//힌트횟수
									int hintCnt = 0;
									
									while(true) {
										//힌트보기 선택지 주기? 1번 정답입력 2번 힌트보기
										System.out.print("1번 정답입력 2번 힌트보기 >>> ");
										int num3 = sc.nextInt();
										
										if(num3 == 1) {
											//답 입력
											System.out.print("정답 입력! >>> ");
											String str = sc.next();
											
											if(vo.get(i).getWord().equals(str)) {
												userDAO.insertScore(10, id);
												System.out.println("정답입니다!");
												System.out.println();
												
											}else {
												System.out.println("정답은 : "+vo.get(i).getWord() + " 입니다.");
												System.out.println();
											}
											
										}else if(num3 == 2) {
											if(hintCnt == 0) {
												//힌트 1번 출력
												System.out.println("첫번째 힌트 입니다!!");
												System.out.println(vo.get(i).getHint1());	
												hintCnt++;
											}else if(hintCnt == 1) {
												System.out.println("두번째 힌트 입니다!! 모든힌트를 다 보셨습니다!!");
												System.out.println(vo.get(i).getHint2());
												
												hintCnt = 0;
											}
										}else {
											System.out.println("잘못 입력!");
										}
									}
									
									
									
									
								}// end of for문
								
							}else if(yearNum == 2) {// 2010년대 
								// 2010년대 
								
							}else if(yearNum == 3) {// 2020년대 
								// 2020년대 
								
							}else {
								System.out.println("잘못 입력!!");
							}
							
							
						}else if(num == 2) {//누적결과보기
							 int score = 0;
		                     System.out.println(score + "점 입니다.!");

		                     if (score > 80) {
		                        System.out.println("당신은 상등급 입니다!");

		                     } else if (score > 60) {
		                        System.out.println("당신은 중등급 입니다!");
		                     } else if (score > 40) {
		                        System.out.println("당신은 하등급 입니다!");
		                     } else {
		                        System.out.println("당신은 최하등급 입니다!");
		                     }

						}else if(num == 3) {//랭킹확인
							 System.out.println("=========랭킹 확인=========");
		                     System.out.println(userDAO.rank());

						}else if(num == 4) {//로그아웃
							System.out.println("로그아웃!!");
							break;
						}else {
							System.out.println("잘못입력!!");
						}
					}
					
				}else {
					System.out.println("로그인 실패!!");					
				}
				
				
			}else if(choice == 3) { // 종료
				System.out.println("종료!!");
				break;
			}else { // 잘못입력
				System.out.println("잘못 입력!!");
			}
			
			
		}// end of while
	}// end of main
}// end of class
