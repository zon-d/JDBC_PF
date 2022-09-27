package kh.jdbc.portfolio.main.view;

import java.util.InputMismatchException;

import java.util.Scanner;

import kh.jdbc.portfolio.cart.model.service.CartService;
import kh.jdbc.portfolio.cart.model.vo.Cart;
import kh.jdbc.portfolio.cart.view.CartView;
import kh.jdbc.portfolio.main.model.service.MainService;
import kh.jdbc.portfolio.member.vo.User;
import kh.jdbc.portfolio.product.vo.Product;

public class MainView {

	public CartService cService = new CartService();
	public static CartView cartView = new CartView();
	public Cart cart = new Cart();
	Scanner sc = new Scanner(System.in);

	MainService service = new MainService();

	public User insertUser = null;

	public void productList(User insertUser) {

		int productType = -1;

		this.insertUser = insertUser;
		try {

			int userNo = insertUser.getUserNo();
			do {
				System.out.println("***** 상품타입 *****");
				System.out.println("1. 아이폰");
				System.out.println("2. 아이패드");
				System.out.println("3. 애플워치");
				System.out.println("4. 에어팟");
				System.out.println("0. 메인메뉴");

				System.out.print("\n상품타입 입력 : ");
				productType = sc.nextInt();

				System.out.println();

				switch (productType) {
				case 1:
					iphone(userNo);
					break;
				case 2:
					ipad(userNo);
					break;
				case 3:
					applewatch(userNo);
					break;
				case 4:
					airpods(userNo);
					break;
				case 0:
					System.out.println("\n<<메인메뉴로 돌아갑니다.>>\n");
					return;
				default:
					System.out.println("\n<<메뉴에 작성된 상품 타입만 입력 해주세요.>>\n");
				}
			} while (productType != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void iphone(int userNo) throws Exception {

		int input = 0;
		String productModel = null;
		String productMemory = null;
		String productCorlor = null;

		do {

			System.out.println("***** 아이폰 모델 ******");
			System.out.println("1. 아이폰14");
			System.out.println("2. 아이폰14플러스");
			System.out.println("3. 아이폰14프로");
			System.out.println("4. 아이폰14프로맥스");
			System.out.println("0. 뒤로가기");

			System.out.print("\n모델명 선택 : ");
			input = sc.nextInt();

			switch (input) {
			case 0:
				System.out.println("\n<<상품리스트로 돌아갑니다.>>\n");
				return;
			}

			if (input == 1) {
				productModel = "아이폰14";
				break;
			} else if (input == 2) {
				productModel = "아이폰14플러스";
				break;
			} else if (input == 3) {
				productModel = "아이폰14프로";
				break;
			} else if (input == 4) {
				productModel = "아이폰14프로맥스";
				break;
			} else {
				System.out.println("\n<<메뉴에 나와있는 번호를 입력해주세요.>>\n");
			}
		} while (input != -1);

		do {

			System.out.println("***** 저장용량 *****");
			System.out.println("1. 128G");
			System.out.println("2. 256G");
			System.out.println("3. 512G");

			System.out.print("\n저장용량 선택 : ");
			input = sc.nextInt();

			if (input == 1) {
				productMemory = "128G";
				break;
			} else if (input == 2) {
				productMemory = "256G";
				break;
			} else if (input == 3) {
				productMemory = "512G";
				break;
			} else {
				System.out.println("\n<<메뉴에 있는 번호만 입력해주세요.>>\n");
			}
		} while (input != -1);

		do {

			System.out.println("***** 색상 *****");
			System.out.println("1. 블루");
			System.out.println("2. 퍼플");
			System.out.println("3. 미드나이트");
			System.out.println("4. 스타라이트");
			System.out.println("5. 레드");

			System.out.print("\n색상 선택 : ");
			input = sc.nextInt();

			if (input == 1) {
				productCorlor = "블루";
				break;
			} else if (input == 2) {
				productCorlor = "퍼플";
				break;
			} else if (input == 3) {
				productCorlor = "미드나이트";
				break;
			} else if (input == 4) {
				productCorlor = "스타라이트";
				break;
			} else if (input == 5) {
				productCorlor = "레드";
				break;
			} else {
				System.out.println("\n<<메뉴에 있는 번호만 입력해주세요.>>\n");
			}
		} while (input != -1);

		Product product = new Product(productModel, productMemory, productCorlor);

		int result = service.cartIn(product, userNo);

		if (result > 0) {
			System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
			System.out.printf("모델명 : %s \n저장용량 : %s \n색상 : %s \n\n", productModel, productMemory, productCorlor);

		} else {
			System.out.println("<<상품 선택 실패>>");
		}

		try {

			System.out.println("1. 장바구니 담기");
			System.out.println("2. 취소");
			System.out.print("\n메뉴 선택 : ");
			int input1 = sc.nextInt();

			System.out.println();

			if (input1 == 1) {
				System.out.println("\n<<장바구니에 담았습니다>>\n");
			} else if (input1 == 2) {
				System.out.println("\n<<입력하신 상품을 취소했습니다>>\n");
			}

			System.out.println("1. 쇼핑 계속하기");
			System.out.println("2. 장바구니");
			System.out.println("0. 메인화면");

			System.out.println();

			System.out.print("메뉴 선택 : ");
			int input2 = sc.nextInt();

			do {

				switch (input2) {
				case 1:
					System.out.println("\n<<상품리스트로 돌아갑니다>>\n");
					return;
				case 2:
					System.out.println();
					cartView.cartMenu();
					break;
				case 0:
					System.out.println("\n<<메인화면으로 돌아갑니다.>>\n");
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");

				}
				System.out.println();

			} while (input2 != 0);

		} catch (InputMismatchException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 아이패드
	 * 
	 * @throws Exception
	 */
	public void ipad(int userNo) throws Exception {

		int input = 0;
		String productModel = null;
		String productMemory = null;
		String productCorlor = null;

		while (true) {

			System.out.println("***** 아이패드 모델 ******");
			System.out.println("1. 아이패드");
			System.out.println("2. 아이패드 미니");
			System.out.println("3. 아이패드 에어");
			System.out.println("4. 아이패드 프로 11");
			System.out.println("5. 아이패드 프로 12.9");
			System.out.println("0. 뒤로가기");

			System.out.print("\n모델명 선택 : ");
			input = sc.nextInt();

			switch (input) {
			case 0:
				System.out.println("\n<<상품리스트로 돌아갑니다.>>\n");
				return;
				
			}

			if (input == 1) {
				productModel = "아이패드";

				do {
					System.out.println();
					System.out.println("***** 저장용량 *****");
					System.out.println("1. 64G");
					System.out.println("2. 256G");

					System.out.print("\n저장용량 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "64G";
					} else if (input == 2) {
						productMemory = "256G";
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 실버");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
					} else if (input == 2) {
						productCorlor = "실버";
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 2) {
				productModel = "아이패드 미니";

				do {
					System.out.println();
					System.out.println("***** 저장용량 *****");
					System.out.println("1. 64G");
					System.out.println("2. 256G");

					System.out.print("\n저장용량 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "64G";
						break;
					} else if (input == 2) {
						productMemory = "256G";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 핑크");
					System.out.println("3. 퍼플");
					System.out.println("4. 스타라이트");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
						break;
					} else if (input == 2) {
						productCorlor = "핑크";
						break;
					} else if (input == 3) {
						productCorlor = "퍼플";
						break;
					} else if (input == 4) {
						productCorlor = "스타라이트";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 3) {
				productModel = "아이패드 에어";

				do {
					System.out.println();
					System.out.println("***** 저장용량 *****");
					System.out.println("1. 64G");
					System.out.println("2. 256G");

					System.out.print("\n저장용량 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "64G";
						break;
					} else if (input == 2) {
						productMemory = "256G";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 핑크");
					System.out.println("3. 블루");
					System.out.println("4. 퍼플");
					System.out.println("5. 스타라이트");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
						break;
					} else if (input == 2) {
						productCorlor = "핑크";
						break;
					} else if (input == 3) {
						productCorlor = "블루";
						break;
					} else if (input == 4) {
						productCorlor = "퍼플";
						break;
					} else if (input == 5) {
						productCorlor = "스타라이트";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 4) {
				productModel = "아이패드 프로 11";

				do {
					System.out.println();
					System.out.println("***** 저장용량 *****");
					System.out.println("1. 128G");
					System.out.println("2. 256G");
					System.out.println("3. 512G");
					System.out.println("4. 1TB");

					System.out.print("\n저장용량 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "128G";
						break;
					} else if (input == 2) {
						productMemory = "256G";
						break;
					} else if (input == 3) {
						productMemory = "512G";
						break;
					} else if (input == 4) {
						productMemory = "1TB";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 실버");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
						break;
					} else if (input == 2) {
						productCorlor = "실버";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 5) {
				productModel = "아이패드 프로 12.9";

				do {
					System.out.println();
					System.out.println("***** 저장용량 *****");
					System.out.println("1. 128G");
					System.out.println("2. 256G");
					System.out.println("3. 512G");
					System.out.println("4. 1TB");
					System.out.print("\n저장용량 선택 : ");
					input = sc.nextInt();
					if (input == 1) {
						productMemory = "128G";
						break;
					} else if (input == 2) {
						productMemory = "256G";
						break;
					} else if (input == 3) {
						productMemory = "512G";
						break;
					} else if (input == 4) {
						productMemory = "1TB";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 실버");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
						break;
					} else if (input == 2) {
						productCorlor = "실버";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);
			} else {
				System.out.println("\n<<모델명에 해당하는 번호를 입력해주세요.>>\n");
			}

			Product product = new Product(productModel, productMemory, productCorlor);

			int result = service.cartIn(product, userNo);

			System.out.println();
			if (result > 0) {
				System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
				System.out.printf("모델명 : %s \n저장용량 : %s \n색상 : %s \n\n", productModel, productMemory, productCorlor);

			} else {
				System.out.println("<<상품 선택 실패>>");
			}

			try {
				System.out.println("1. 장바구니 담기");
				System.out.println("2. 취소");
				System.out.print("\n메뉴 선택 : ");
				int input1 = sc.nextInt();

				System.out.println();

				if (input1 == 1) {
					System.out.println("\n<<장바구니에 담았습니다>>\n");
				} else if (input1 == 2) {
					System.out.println("\n<<입력하신 상품을 취소했습니다>>\n");
				}

				System.out.println("1. 쇼핑 계속하기");
				System.out.println("2. 장바구니");
				System.out.println("0. 메인화면");

				System.out.println();

				System.out.print("메뉴 선택 : ");
				int input2 = sc.nextInt();

				do {

					switch (input2) {
					case 1:
						System.out.println("\n<<상품리스트로 돌아갑니다>>\n");
						return;
					case 2:
						System.out.println();
						cartView.cartMenu();
						break;
					case 0:
						System.out.println("\n<<메인화면으로 돌아갑니다.>>\n");
						return;
					default:
						System.out.println("잘못 입력하셨습니다.");

					}
					System.out.println();

				} while (input2 != 0);

			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}

	}

	public void applewatch(int userNo) throws Exception {

		int input = 0;
		String productModel = null;
		String productMemory = null;
		String productCorlor = null;

		while (true) {

			System.out.println("***** 애플워치 모델 ******");
			System.out.println("1. 애플워치8 알루미늄");
			System.out.println("2. 애플워치8 스테인리스");
			System.out.println("3. 애플워치SE2");
			System.out.println("0. 뒤로가기");

			System.out.print("\n모델명 선택 : ");
			input = sc.nextInt();

			switch (input) {
			case 0:
				System.out.println("\n<<상품리스트로 돌아갑니다.>>\n");
				return;
			}

			if (input == 1) {
				productModel = "애플워치8 알루미늄";

				do {
					System.out.println();
					System.out.println("***** 크기 *****");
					System.out.println("1. 41mm");
					System.out.println("2. 45mm");

					System.out.print("\n크기 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "41mm";
					} else if (input == 2) {
						productMemory = "45mm";
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 미드나이트");
					System.out.println("2. 스타라이트");
					System.out.println("3. 실버");
					System.out.println("4. 골드");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "미드나이트";
					} else if (input == 2) {
						productCorlor = "스타라이트";
					} else if (input == 3) {
						productCorlor = "실버";
					} else if (input == 4) {
						productCorlor = "골드";
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 2) {
				productModel = "애플워치8 스테인리스";

				do {
					System.out.println();
					System.out.println("***** 크기 *****");
					System.out.println("1. 41mm");
					System.out.println("2. 45mm");

					System.out.print("\n크기 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "41mm";
						break;
					} else if (input == 2) {
						productMemory = "45mm";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 실버");
					System.out.println("2. 그래파이트");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "실버";
						break;
					} else if (input == 2) {
						productCorlor = "그래파이트";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			} else if (input == 3) {
				productModel = "애플워치SE2";

				do {
					System.out.println();
					System.out.println("***** 크기 *****");
					System.out.println("1. 40mm");
					System.out.println("2. 44mm");

					System.out.print("\n크기 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productMemory = "40mm";
						break;
					} else if (input == 2) {
						productMemory = "44mm";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 미드나이트");
					System.out.println("2. 스타라이트");
					System.out.println("3. 실버");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "미드나이트";
						break;
					} else if (input == 2) {
						productCorlor = "스타라이트";
						break;
					} else if (input == 3) {
						productCorlor = "실버";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

			}

			Product product = new Product(productModel, productMemory, productCorlor);

			int result = service.cartIn(product, userNo);

			System.out.println();
			if (result > 0) {
				System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
				System.out.printf("모델명 : %s \n저장용량 : %s \n색상 : %s \n\n", productModel, productMemory, productCorlor);

			} else {
				System.out.println("<<상품 선택 실패>>");
			}

			try {
				System.out.println("1. 장바구니 담기");
				System.out.println("2. 취소");
				System.out.print("\n메뉴 선택 : ");
				int input1 = sc.nextInt();

				System.out.println();

				if (input1 == 1) {
					System.out.println("\n<<장바구니에 담았습니다>>\n");
				} else if (input1 == 2) {
					System.out.println("\n<<입력하신 상품을 취소했습니다>>\n");
				}

				System.out.println("1. 쇼핑 계속하기");
				System.out.println("2. 장바구니");
				System.out.println("0. 메인화면");

				System.out.println();

				System.out.print("메뉴 선택 : ");
				int input2 = sc.nextInt();

				do {

					switch (input2) {
					case 1:
						System.out.println("\n<<상품리스트로 돌아갑니다>>\n");
						return;
					case 2:
						System.out.println();
						cartView.cartMenu();
						break;
					case 0:
						System.out.println("\n<<메인화면으로 돌아갑니다.>>\n");
						return;
					default:
						System.out.println("잘못 입력하셨습니다.");

					}
					System.out.println();

				} while (input2 != 0);

			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}

	}

	public void airpods(int userNo) throws Exception {

		int input = 0;
		String productModel = null;
		String productCorlor = null;

		while (true) {

			System.out.println("***** 에어팟 모델 ******");
			System.out.println("1. 에어팟 2세대");
			System.out.println("2. 에어팟 3세대");
			System.out.println("3. 에어팟 3세대 맥세이프");
			System.out.println("4. 에어팟 프로 2세대");
			System.out.println("5. 에어팟 맥스");
			System.out.println("0. 뒤로가기");

			System.out.print("\n모델명 선택 : ");
			input = sc.nextInt();

			switch (input) {
			case 0:
				System.out.println("\n<<상품리스트로 돌아갑니다.>>\n");
				return;
			}

			if (input == 1) {
				productModel = "에어팟 2세대";

			} else if (input == 2) {
				productModel = "에어팟 3세대";

			} else if (input == 3) {
				productModel = "에어팟 프로 2세대";

				Product product = new Product(productModel);

				int result = service.cartIn2(product);

				System.out.println();
				if (result > 0) {
					System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
					System.out.printf("모델명 : %s\n", productModel);

				} else {
					System.out.println("<<상품 선택 실패>>");
				}

				try {
					System.out.println("1. 장바구니 담기");
					System.out.println("2. 취소");
					System.out.print("\n메뉴 선택 : ");
					int input1 = sc.nextInt();

					System.out.println();

					if (input1 == 1) {
						System.out.println("\n<<장바구니에 담았습니다>>\n");
					} else if (input1 == 2) {
						System.out.println("\n<<입력하신 상품을 취소했습니다>>\n");
					}

					System.out.println("1. 쇼핑 계속하기");
					System.out.println("2. 장바구니");
					System.out.println("0. 메인화면");

					System.out.println();

					System.out.print("메뉴 선택 : ");
					int input2 = sc.nextInt();

					do {

						switch (input2) {
						case 1:
							System.out.println("\n<<상품리스트로 돌아갑니다>>\n");
							return;
						case 2:
							System.out.println();
							cartView.cartMenu();
							break;
						case 0:
							System.out.println("\n<<메인화면으로 돌아갑니다.>>\n");
							return;
						default:
							System.out.println("잘못 입력하셨습니다.");

						}
						System.out.println();

					} while (input2 != 0);

				} catch (InputMismatchException e) {
					e.printStackTrace();
				}

			} else if (input == 5) {
				productModel = "에어팟 맥스";

				do {
					System.out.println();
					System.out.println("***** 색상 *****");
					System.out.println("1. 스페이스그레이");
					System.out.println("2. 핑크");
					System.out.println("3. 그린");
					System.out.println("4. 실버");
					System.out.println("5. 스카이블루");

					System.out.print("\n색상 선택 : ");
					input = sc.nextInt();

					if (input == 1) {
						productCorlor = "스페이스그레이";
						break;
					} else if (input == 2) {
						productCorlor = "핑크";
						break;
					} else if (input == 3) {
						productCorlor = "그린";
						break;
					} else if (input == 4) {
						productCorlor = "실버";
						break;
					} else if (input == 5) {
						productCorlor = "스카이블루";
						break;
					} else {
						System.out.println("<<메뉴에 있는 번호만 입력해주세요.>>");
					}
				} while (input != -1);

				Product product = new Product(productModel, productCorlor);

				int result = service.cartIn3(product);

				System.out.println();
				if (result > 0) {
					System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
					System.out.printf("모델명 : %s\n색상 : %s\n\n", productModel, productCorlor);

				} else {
					System.out.println("<<상품 선택 실패>>");
				}

				try {
					System.out.println("1. 장바구니 담기");
					System.out.println("2. 취소");
					System.out.print("\n메뉴 선택 : ");
					int input1 = sc.nextInt();

					System.out.println();

					if (input1 == 1) {
						System.out.println("\n<<장바구니에 담았습니다>>\n");
					} else if (input1 == 2) {
						System.out.println("\n<<입력하신 상품을 취소했습니다>>\n");
					}

					System.out.println("1. 쇼핑 계속하기");
					System.out.println("2. 장바구니");
					System.out.println("0. 메인화면");

					System.out.println();

					System.out.print("메뉴 선택 : ");
					int input2 = sc.nextInt();

					do {

						switch (input2) {
						case 1:
							System.out.println("\n<<상품리스트로 돌아갑니다>>\n");
							return;
						case 2:
							System.out.println();
							cartView.cartMenu();
							break;
						case 0:
							System.out.println("\n<<메인화면으로 돌아갑니다.>>\n");
							return;
						default:
							System.out.println("잘못 입력하셨습니다.");

						}
						System.out.println();

					} while (input2 != 0);

				} catch (InputMismatchException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
