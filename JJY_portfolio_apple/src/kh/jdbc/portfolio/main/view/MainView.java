package kh.jdbc.portfolio.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import kh.jdbc.portfolio.main.model.service.MainService;
import kh.jdbc.portfolio.product.vo.Product;

public class MainView {
	Scanner sc = new Scanner(System.in);

	MainService service = new MainService();

	public void mainMenu() throws Exception {

		int input = -1;

		try {

			System.out.println("****** 환영합니다 *****");
			System.out.println("1. 상품보기");
			System.out.println("2. 장바구니");
			System.out.println("3. 주문하기");
			System.out.println("0. 프로그램 종료");

			System.out.print("\n메뉴 선택 : ");

			input = sc.nextInt();
			sc.nextLine();
			System.out.println();

			switch (input) {
			case 1:
				productList();
				break;
			case 2:
				break;
			case 3:
				break;
			case 0:
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("메뉴에 작성된 번호만 입력해주세요.");

			}
		} catch (InputMismatchException e) {
			System.out.println("\n<<입력 형식이 올바르지 않습니다.>>\n");
			sc.nextLine();

		}

	}

	private void productList() throws Exception {
		
		String productType = null;
		
		System.out.println("***** 상품타입 *****");
		System.out.println("1. 아이폰");
		System.out.println("2. 아이패드");
		System.out.println("3. 맥북");
		System.out.println("4. 맥");
		System.out.println("5. 애플워치");
		System.out.println("6. 이어폰");
		
		System.out.print("\n상품타입 입력 : ");
		productType = sc.next();
		
		System.out.println();
		
		switch(productType) {
		case "아이폰": iphone(); break;
		case "아이패드": break;
		case "맥북": break;
		case "맥": break;
		case "애플워치": break;
		case "이어폰": break;
		default : System.out.println("\n<<잘못 입력 하셨습니다.>>\n");
		}
		
		
		
	}

	public void iphone() throws Exception {
		
		String productModel = null;
		String productMemory = null;
		String productCorlor = null;
		
		System.out.println("***** 아이폰 모델 ******");
		System.out.println("1. 아이폰14");
		System.out.println("2. 아이폰14플러스");
		System.out.println("3. 아이폰14프로");
		System.out.println("4. 아이폰14프로맥스");
		
		System.out.print("\n모델명 입력 : ");
		productModel = sc.next();
		
		if(productModel.equals("아이폰14") || productModel.equals("아이폰14플러스") || productModel.equals("아이폰14프로") || productModel.equals("아이폰14프로맥스")) {
		}else {
			System.out.println("다시");
		}
	
			
			
		System.out.println("***** 저장용량 *****");
		System.out.println("1. 128G");
		System.out.println("2. 256G");
		System.out.println("3. 512G");
		
		
		System.out.print("\n저장용량 입력 (숫자+G) : ");
		productMemory = sc.next();
		
		if(productMemory.equals("128G") || productMemory.equals("256G") || productMemory.equals("512G")) {
		}else {
			System.out.println("다시");
		}
				
		System.out.println("***** 색상 *****");
		System.out.println("1. 블루");
		System.out.println("2. 퍼플");
		System.out.println("3. 미드나이트");
		System.out.println("4. 스타라이트");
		System.out.println("5. 레드");
			
			
		System.out.print("\n색상 입력 : ");
		productCorlor = sc.next();
		
		if(productCorlor.equals("블루") || productCorlor.equals("퍼플") || productCorlor.equals("미드나이트") || productCorlor.equals("스타라이트") || productCorlor.equals("레드")) {
			
		}else {
			System.out.println("[메뉴에 적힌 값만 입력해주세요.]");
		}
		System.out.println();
					
			
		
		
		Product product = new Product(productModel, productMemory, productCorlor);
		
		int result = service.cartIn(product);
		
		System.out.println();
		if(result>0) {
			System.out.println("\n[입력하신 상품이 맞는지 확인해주세요.]\n");
			System.out.printf("모델명 : %s \n저장용량 : %s \n색상 : %s \n\n", productModel, productMemory, productCorlor);
			
		}else {
			System.out.println("<<상품 선택 실패>>");
		}
	
		try {
			System.out.println("1. 장바구니 담기");
			System.out.println("2. 취소");
			System.out.print("\n메뉴 선택 : ");
			int input = sc.nextInt();
			
			
			switch(input) {
			case 1: System.out.println("\n장바구니에 담았습니다.\n메인화면으로 돌아갑니다.\n"); mainMenu();  break;
			case 2: System.out.println("\n취소하셨습니다.\n메인화면으로 돌아갑니다.\n"); mainMenu(); break;
			default : System.out.println("잘못 입력하셨습니다.");
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}
		
		
	
			
		
	}
}
		
		
		
		
		
