package kh.jdbc.portfolio.member.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.cart.view.CartView;
import kh.jdbc.portfolio.main.view.MainView;
import kh.jdbc.portfolio.member.model.service.UserService;
import kh.jdbc.portfolio.member.vo.User;
import kh.jdbc.portfolio.mypage.view.MyPageView;

public class UserView {

	public UserService uService = new UserService();
	public MainView mainView = new MainView();
	public CartView cartView = new CartView();
	public MyPageView mypage = new MyPageView();
	public Cart cart = new Cart();

	public static User insertUser = null;
	Scanner sc = new Scanner(System.in);

	public void userMenu() {

		int input = -1;

		do {

			try {

				if (insertUser == null) {
					System.out.println("\n***** 안녕하세요 *****\n");
					System.out.println("1. 사용자 정보 확인");
					System.out.println("2. 사용자 정보 추가");
					System.out.println("0. 프로그램 종료");

					System.out.print("\n메뉴 선택 : ");

					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거
					System.out.println();

					switch (input) {
					case 1:
						login();
						break;
					case 2:
						signUp();
						break;
					case 0:
						System.out.println("프로그램 종료");
						break;
					default:
						System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O

					System.out.println("****** 메인메뉴 *****");
					System.out.println("1. 상품리스트");
					System.out.println("2. 장바구니");
					System.out.println("3. 주문배송");
					System.out.println("4. 마이페이지");
					System.out.println("0. 프로그램 종료");

					System.out.print("\n메뉴 선택 : ");

					input = sc.nextInt();
					sc.nextLine();
					System.out.println();

					switch (input) {
					case 1:
						mainView.productList();
						break;
					case 2:
						cartView.cartMenu();
						break;
					case 3:
//								viewShipping(insertUser);
						break;
					case 4:
						mypage.myPage(insertUser);
						break;
					case 0:
						System.out.println("프로그램 종료");
						break;
					default:
						System.out.println("메뉴에 작성된 번호만 입력해주세요.\n");

					}
				}

			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>\n");
				sc.nextLine();

			}
		} while (input != 0);

	}

	

	public void signUp() {
		System.out.println("[사용자 정보 추가]");

		String userName = null;
		String userPhone = null;

		try {

			while (true) {

				System.out.print("이름 입력 : ");
				userName = sc.next();

				System.out.print("핸드폰 번호 입력 : ");
				userPhone = sc.next();

				int result = uService.userDupCheck(userName, userPhone);

				if (result == 0) {
					System.out.println("[사용자 정보 등록 성공]");
					break;
				} else {
					System.out.println("[이미 등록된 사용자 입니다.]");

				}
				System.out.println();
			}

			User user = new User(userName, userPhone);

			int result = uService.signUp(user);

			// 서비스 처리 결과에 따른 출력 화면 제어
			System.out.println();
			if (result > 0) {
				System.out.println("*****사용자 등록 성공*****");
			} else {
				System.out.println("<<사용자 등록 실패>>");
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("\n<<사용자 등록 중 예외 발생>>");
			e.printStackTrace();
		}

	}

	public void login() {

		System.out.println("[사용자 정보 조회]");

		System.out.print("이름 : ");
		String userName = sc.next();

		System.out.print("전화번호 : ");
		String userPhone = sc.next();

		try {

			insertUser = uService.login(userName, userPhone);

			System.out.println();
			if (insertUser != null) {
				System.out.println(insertUser.getUserName() + "님 환영합니다.");
			} else {
				System.out.println("[이름 또는 전화번호가 일치하지 않습니다.]");
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("\n<<사용자 정보 조회 중 예외 발생>>\n");
		}

	}

}
