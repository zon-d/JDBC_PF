package kh.jdbc.portfolio.cart.view;

//import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.service.CartService;
import kh.jdbc.portfolio.cart.model.vo.Cart;
//import kh.jdbc.portfolio.member.view.UserView;
//import kh.jdbc.portfolio.member.vo.User;

public class CartView {

	private static CartService cService = new CartService();
	Scanner sc = new Scanner(System.in);

	/**
	 * 장바구니
	 * 
	 * @throws Exception
	 */
	public void cartMenu() {

		int input = -1;

		try {

			List<Cart> cartList = cService.cartList();

//			int userNo = UserView.insertUser.getUserNo();
//
//			Cart cart = cService.myCart(userNo);

			if (cartList.isEmpty()) {
				System.out.println("장바구니에 상품이 없습니다.\n");

			} else {

				System.out.printf("장바구니에 %d 개의 상품이 있습니다.\n\n", cService.cart());

			}

			do {

				System.out.println("***** 장바구니 *****");
				System.out.println("1. 장바구니 상세 조회");
				System.out.println("2. 장바구니 선택 주문");
				System.out.println("3. 장바구니 전체 주문");
				System.out.println("4. 장바구니 선택 삭제");
				System.out.println("5. 장바구니 전체 삭제");
				System.out.println("0. 메인메뉴");

				System.out.print("\n메뉴 선택 : ");

				input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1:
					cartList();
					break;
				case 2:

					break;
				case 3:

					break;
				case 4:
					deleteCart();
					break;
				case 5:
					deleteAll();
					break;
				case 0:
					System.out.println("\n<<메인메뉴로 돌아갑니다.>>\n");
					return;
				default:
					System.out.println("메뉴에 작성된 번호를 입력해주세요.");

				}
			} while (cartList != null);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 장바구니 상세 조회
	 */
	public void cartList() {
		System.out.println("\n[장바구니 조회]\n");

		try {
			List<Cart> cartList = cService.cartList();

			if (cartList.isEmpty()) {
				System.out.println("장바구니에 상품이 없습니다.");
			} else {
				for (Cart c : cartList) {

					// 번호, 모델명, 색상, 용량, 가격
					System.out.printf("No. %d | %s | %s | %s \n상품 가격 : %d원\n\n", c.getCartInNo(), c.getProductModel(),
							c.getProductCorlor(), c.getProductMemory(), c.getProductPrice());

				}
				System.out.printf("상품 가격 합계 : %d 원\n\n", cService.priceSum());
			}

		} catch (Exception e) {
			System.out.println("\n<<게시글 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

	/**
	 * 장바구니 선택 삭제
	 */
	public void deleteCart() {

		try {

			List<Cart> cartList = cService.cartList();

			for (Cart c : cartList) {

				System.out.println("\n[상품 삭제]\n");
				System.out.print("삭제할 상품 번호 입력 : ");
				int cartInNo = sc.nextInt();
				sc.nextLine();

				if (c.getCartInNo() == cartInNo) { // 상품 번호 일치

					System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
					char ch = sc.next().toUpperCase().charAt(0);

					if (ch == 'Y') {
						int result = cService.deleteCart(cartInNo);

						if (result > 0) {
							System.out.println("\n[상품 삭제 성공]\n");
						} else {
							System.out.println("\n[상품 삭제 실패...]\n");
						}

					} else {
						System.out.println("\n[취소 되었습니다.]\n");
					}

				} else {
					System.out.println("\n[장바구니에 있는 상품만 삭제할 수 있습니다.]\n");
				}

				break; // 더 이상의 검사 불필요
			}

		} catch (Exception e) {
			System.out.println("\n<<상품 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}

	/**
	 * 장바구니 전체 삭제
	 */
	public void deleteAll() {

		try {

			List<Cart> cartList = cService.cartList();

			if (cartList.isEmpty()) {
				System.out.println("장바구니가 비어있습니다.");
			} else {

				System.out.println("장바구니 삭제를 하시겠습니까?(Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {
					int result = cService.deleteAllCart();

					if (result > 0) {
						System.out.println("\n[상품 삭제 성공]\n");
					} else {
						System.out.println("\n[상품 삭제 실패...]\n");
					}

				}
			}

		} catch (Exception e) {
			System.out.println("\n<<장바구니 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

}
