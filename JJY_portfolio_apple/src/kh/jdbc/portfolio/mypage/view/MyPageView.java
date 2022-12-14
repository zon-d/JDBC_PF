package kh.jdbc.portfolio.mypage.view;

import java.util.Scanner;

import kh.jdbc.portfolio.member.view.UserView;
import kh.jdbc.portfolio.member.vo.User;
import kh.jdbc.portfolio.mypage.model.service.MyPageService;

public class MyPageView {

	Scanner sc = new Scanner(System.in);

	private MyPageService mpService = new MyPageService();

	private User insertUser = null;

	private int input = -1;

	public void myPage(User insertUser) {

		this.insertUser = insertUser;

		do {

			try {

				System.out.println("\n***** 사용자 기능 *****\n");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 내 정보 수정(이름, 전화번호)");
				System.out.println("3. 내 정보 삭제");
				System.out.println("0. 메인메뉴");

				System.out.print("\n메뉴 선택 : ");

				input = sc.nextInt();
				sc.nextLine();

				System.out.println();
				switch (input) {
				case 1:
					selectMyInfo();
					break;
				case 2:
					updateUser();
					break;
				case 3:
					deleteUser();
					break;
				case 0:
					return;
				default:
				}
				System.out.println();

			} catch (Exception e) {

				e.printStackTrace();
			}
		} while (input != 0);
	}

	/**
	 * 사용자 정보 삭제
	 */
	private void deleteUser() {
		try {
			System.out.print("이름 입력 : ");
			String userName = sc.next();

			System.out.println("전화번호 입력 : ");
			String userPhone = sc.next();

			while (true) {
				System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {

					int result = mpService.deleteUser(userName, userPhone, UserView.insertUser.getUserNo());

					if (result > 0) {
						System.out.println("\n[삭제 되었습니다...]\n");

						input = 0;
						UserView.insertUser = null;

					} else {
						System.out.println("\n[이름/전화번호가 일치하지 않습니다.]\n");

					}

					break; // while문 종료

				} else if (ch == 'N') {
					System.out.println("\n[취소되었습니다.]\n");
					break;

				} else {
					System.out.println("\n[Y 또는 N만 입력 해주세요.]\n");
				}
			}
		} catch (Exception e) {
			System.out.println("\n<<사용자 정보 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}

	/**
	 * 내 정보 수정
	 */
	private void updateUser() {
		try {
			System.out.println("\n[회원 정보 수정]\n");

			System.out.print("변경할 이름 : ");
			String userName = sc.next();

			System.out.print("변경할 전화번호 : ");
			String userPhone = sc.next();

			User user = new User();

			user.setUserNo(insertUser.getUserNo());
			user.setUserName(userName);
			user.setUserPhone(userPhone);

			int result = mpService.updateUser(user);

			if (result > 0) {

				insertUser.setUserName(userName);
				insertUser.setUserPhone(userPhone);

				System.out.println("\n[사용자 정보가 수정되었습니다.]\n");
			} else {
				System.out.println("\n[수정 실패]\n");
			}

		} catch (Exception e) {
			System.out.println("\n<<사용자 정보 수정 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}

	/**
	 * 내 정보 조회
	 */
	private void selectMyInfo() {
		System.out.println("\n[내 정보 조회]\n");

		System.out.println("이름 : " + UserView.insertUser.getUserName());
		System.out.println("전화번호 : " + UserView.insertUser.getUserPhone());

	}

}
