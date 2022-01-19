package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.UserInfoDAO;
import Model.WordListDAO;
import Model.WordListVO;

public class GameStartController {
	

	
	public static void start(int yearNum, String id) {
		Scanner sc = new Scanner(System.in);
		UserInfoDAO userDAO = new UserInfoDAO();
		WordListDAO wordDAO = new WordListDAO();
		
		// 2000년대

		ArrayList<WordListVO> vo = wordDAO.wordList(yearNum);
		int score = 0; // 문제 점수

		// vo객체(데이터베이스의 단어뜻) 인덱스값 랜덤을 정렬시킴
		Collections.shuffle(vo);

		for (int i = 0; i < 5; i++) {

			// 문제출력
			System.out.println(vo.get(i).getMean());

			// 힌트점수누적
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
		System.out.println();

		
	}
	
}
