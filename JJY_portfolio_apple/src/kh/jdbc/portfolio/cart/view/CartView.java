package kh.jdbc.portfolio.cart.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.service.CartService;
import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.main.view.MainView;

public class CartView {


	private CartService cService = new CartService();

	/**
	 * 장바구니
	 * @throws Exception
	 */
	public static void cartMenu() throws Exception {

		Scanner sc = new Scanner(System.in);
		int input = -1;

		do {
			try {
				System.out.println("***** 장바구니 *****");
				System.out.println("1. 장바구니 조회");
				System.out.println("2. 장바구니 상품 삭제");
				System.out.println("3. 장바구니 전체 삭제");
				System.out.println("4. 장바구니 전체 주문");
				System.out.println("0. 메인메뉴");

				System.out.print("\n메뉴 선택 : ");

				input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1: 
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 0:
					System.out.println("\n<<메인메뉴로 돌아갑니다.>>\n");
					break;
				default:
					System.out.println("메뉴에 작성된 번호를 입력해주세요.");

				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}

		} while (input != 0);

	}
	
	public void cartView() {
		System.out.println("\n[장바구니 조회]\n");
		
		try {
			List<Cart> cartList = cService.viewCart();
			
			if(cartList.isEmpty()) {
				System.out.println("장바구니에 상품이 없습니다.");
			}else {
				for(Cart c : cartList) {
					
					// 번호, 모델명, 색상, 용량, 가격
					System.out.printf("%d | %s | %s | %s | %d\n");
						c.getCartInNo(), c.getProductModel(), c.getProductCorlor(),
						c.getProductMemory(), 
					
				}
			}
			
			
		}
		
	}

}
