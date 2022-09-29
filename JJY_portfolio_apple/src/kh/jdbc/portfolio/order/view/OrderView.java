package kh.jdbc.portfolio.order.view;

import java.util.List;
import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.member.view.UserView;
import kh.jdbc.portfolio.order.model.service.OrderService;
import kh.jdbc.portfolio.order.model.vo.Order;

public class OrderView {

	public OrderService oService = new OrderService();
	Scanner sc = new Scanner(System.in);

	int input = -1;

	/**
	 * 주문조회
	 */
	public void orderMenu() {
		do {

			System.out.println("\n***** 주문조회 *****\n");

			System.out.println("1) 주문목록");
			System.out.println("2) 주문취소");
			System.out.println("0) 메인메뉴");

			System.out.print("\n메뉴 선택 : ");

			input = sc.nextInt();
			sc.nextLine();
			System.out.println();

			switch (input) {
			case 1:
				viewOrderList();
				break;
			case 2:
				deleteOrder();
				break;
			case 0:
				System.out.println("\n<<메인메뉴로 돌아갑니다.>>\n");
				return;
			default:
				System.out.println("메뉴에 작성된 번호를 입력해주세요.");

			}
		} while (input != 0);

	}

	/**
	 * 주문목록 조회
	 */
	public void viewOrderList() {

		System.out.println("\n[주문목록 조회]\n");

		try {
			List<Order> orderList = oService.orderList(UserView.insertUser.userNo);

			if (orderList.isEmpty()) {
				System.out.println("<<주문내역이 없습니다.>>");

			} else {
				System.out.println(" 주문번호 |       주문날짜       |       상품명       |   상품 가격    ");

				for (Order o : orderList) {
					// 주문번호, 결제일, 상품명, 상품메모리, 상품색상, 결제금액
					System.out.printf(" [%d] %s |  %s %s %s     %d원    \n", o.getOrderNo(), o.getOrderDate(),
							o.getProductModel(), o.getProductMemory(), o.getProductCorlor(), o.getProductPrice());

				}
				System.out.printf("\n주문 금액 합계 : %d 원\n", oService.priceSum());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 주문취소
	 */
	public void deleteOrder() {

		try {
			List<Order> orderList = oService.orderList(UserView.insertUser.userNo);

			if (orderList.isEmpty()) {
				System.out.println("<<주문내역이 없습니다.>>");
			} else {

				System.out.println("[주문취소 메뉴]");
				System.out.println("1) 주문취소");
				System.out.println("2) 전체취소");
				System.out.println("3) 돌아가기");

				System.out.print("메뉴 선택 : ");
				int input = sc.nextInt();

				switch (input) {
				case 1:
					cancleOrder(orderList);
					break;
				case 2:
					deleteOrderList(orderList);
					break;
				case 0:
					return;
				default:
					System.out.println("\n<<메뉴에 있는 번호만 선택해 주세요.>>\n");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 주문내역 전체 취소
	 * 
	 * @param orderList
	 */
	private void deleteOrderList(List<Order> orderList) {
		try {

			if (orderList.isEmpty()) {
				System.out.println("주문내역이 없습니다.");
			} else {

				System.out.print("주문을 취소 하시겠습니까?(Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {
					int result = oService.deleteOrderList();

					if (result > 0) {
						System.out.println("\n[주문 취소 성공]\n");
					} else {
						System.out.println("\n[주문 취소 실패...]\n");
					}

				} else {
					System.out.println("\n[주문 취소를 취소합니다.]\n");
				}
			}

		} catch (Exception e) {
			System.out.println("\n<<주문 취소 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}

	/**
	 * 주문취소
	 */
	private void cancleOrder(List<Order> orderList) {

		try {
			System.out.print("취소할 주문 번호 입력 : ");
			int orderNo = sc.nextInt();

			boolean flag = true;

			for (Order o : orderList) {

				if (o.getOrderNo() == orderNo) {
					flag = false;
					if (o.getUserNo() == UserView.insertUser.userNo) {

						int result = oService.deleteOrder(orderNo);

						if (result > 0) {
							System.out.println("\n[주문 취소 성공]\n");
						} else {
							System.out.println("\n[결제일 3일 이내의 주문내역만 취소 가능합니다.]\n");
						}
					}
					if (flag) {
						System.out.println("\n[번호가 일치하는 주문내역이 없습니다.]\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
