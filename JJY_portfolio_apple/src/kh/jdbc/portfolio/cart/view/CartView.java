package kh.jdbc.portfolio.cart.view;

//import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.service.CartService;
import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.member.view.UserView;
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

			List<Cart> cartList = cService.cartList(UserView.insertUser.userNo);

//			int userNo = UserView.insertUser.getUserNo();
//
//			Cart cart = cService.myCart(userNo);

			if (cartList.isEmpty()) {
				System.out.println("장바구니에 상품이 없습니다.\n");
				return;

			} else {

				System.out.printf("장바구니에 상품이 있습니다.\n");

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
						order(cartList);
						break;
					case 3:
						orderAll();
						break;
					case 4:
						deleteCart(cartList);
						break;
					case 5:
						deleteAll();
						break;
					case 0:
						System.out.println("\n<<메인메뉴로 돌아갑니다.>>\n");
						break;
					default:
						System.out.println("메뉴에 작성된 번호를 입력해주세요.");

					}
				} while (input != 0);
			}

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
			List<Cart> cartList = cService.cartList(UserView.insertUser.userNo);

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
	public void deleteCart(List<Cart> cartList) {

		try {
			System.out.println("\n[상품 삭제]\n");
			System.out.print("삭제할 상품 번호 입력 : ");
			int cartInNo = sc.nextInt();
			sc.nextLine();

			boolean flag = true;

			for (Cart c : cartList) {

				if (c.getCartInNo() == cartInNo) { // 상품 번호 일치
					flag = false;
					if (c.getUserNo() == UserView.insertUser.userNo) {

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

					}
					if (flag) {
						System.out.println("\n[장바구니에 있는 상품만 삭제할 수 있습니다.]\n");
					}
				}

			}

		} catch (

		Exception e) {
			System.out.println("\n<<상품 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}

	/**
	 * 장바구니 전체 삭제
	 */
	public void deleteAll() {

		try {

			List<Cart> cartList = cService.cartList(UserView.insertUser.userNo);

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

				} else {
					System.out.println("\n[삭제를 취소합니다.]\n");
				}
			}

		} catch (Exception e) {
			System.out.println("\n<<장바구니 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

	/**
	 * 상품 선택 주문
	 */
	private void order(List<Cart> cartList) {

		try {

			if (cartList.isEmpty()) {
				System.out.println("장바구니가 비어있습니다.");
			} else {
				System.out.println("\n[상품 주문]\n");
				System.out.print("주문할 상품 번호 입력 : ");
				int cartInNo = sc.nextInt();

				boolean flag = true;

				for (Cart c : cartList) {

					if (c.getCartInNo() == cartInNo) {
						flag = false;
						if (c.getUserNo() == UserView.insertUser.getUserNo()) {

							System.out.printf("%s %s %s 을 주문 하시겠습니까? (Y/N) : ", c.getProductModel(),
									c.getProductCorlor(), c.getProductMemory());
							char ch = sc.next().toUpperCase().charAt(0);
							if (ch == 'Y') {
								System.out.printf("\n[%d원]을 결제하셔야합니다.\n", c.getProductPrice());
								System.out.print("결제 (Y) / 취소 (N) : ");
								char ch2 = sc.next().toUpperCase().charAt(0);

								if (ch2 == 'Y') {
									int result = cService.order(cartInNo);

									if (result > 0) {
										System.out.println("\n[상품 주문 성공]\n");
										int result1 = cService.deleteCart(cartInNo);
										if (result1 > 0) {

										}

									} else {
										System.out.println("\n[상품 주문 실패...]\n");
									}

								} else {
									System.out.println("\n[주문을 취소합니다.]\n");
								}

							} else {
								System.out.println("\n[주문을 취소합니다.]\n");
							}

						} else {
							System.out.println("\n[장바구니에 있는 상품만 주문할 수 있습니다.]\n");
						}
					}
				}
				if (flag) {
					System.out.println("\n[번호가 일치하는 상품이 없습니다.]\n");
				}
			}

		} catch (Exception e) {
			System.out.println("\n<<상품 주문 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

	private void orderAll() {

		try {

			List<Cart> cartList = cService.cartList(UserView.insertUser.userNo);

			if (cartList.isEmpty()) {
				System.out.println("장바구니가 비어있습니다.");
			} else {
				System.out.printf("[합계 : %d 원]\n", cService.priceSum());
				System.out.print("장바구니에 있는 상품 전체를 주문하시겠습니까?(Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {
					System.out.printf("\n[%d 원 결제 완료]", cService.priceSum());

					int result = cService.orderAll();

					if (result > 0) {
						System.out.println("\n[상품 주문 성공]\n");
						int result2 = cService.deleteAllCart();

						if (result2 > 0) {

						}

					} else {
						System.out.println("\n[상품 주문 실패...]\n");
						return;
					}

				} else {
					System.out.println("\n[주문을 취소합니다.]\n");
				}

			}

		} catch (Exception e) {
			System.out.println("\n<<상품 주문 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

}
