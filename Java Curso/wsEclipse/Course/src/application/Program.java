
package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Rectangle;

public class Program {

	public static void main(String[] args) {
	//// INICIO PROGRAMA PARA CALCULAR AREA TRIANGULO

	/////// SOLUÇÃO SEM ORIENTAÇÃO `A OBJETOS
		/*
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		double xA, xB, xC, yA, yB, yC;
		
		System.out.println("Entre com as medidas do triângulo X:");
		xA = sc.nextDouble();
		xB = sc.nextDouble();
		xC = sc.nextDouble();
		
		System.out.println("Entre com as medidas do triângulo Y:");
		yA = sc.nextDouble();
		yB = sc.nextDouble();
		yC = sc.nextDouble();

		double p = (xA + xB + xC) / 2.0;
		double areaX = Math.sqrt(p * (p-xA) * (p-xB) * (p-xC));

		p = (yA + yB + yC) / 2.0;
		double areaY = Math.sqrt(p * (p-yA) * (p-yB) * (p-yC));

		System.out.printf("O Triângulo X tem area igual a: %4f%n", areaX);
		System.out.printf("O Triângulo Y tem area igual a: %4f%n", areaY);
		
		if (areaX > areaY) {
			System.out.println("O triângulo X é o maior");
		} else {
			System.out.println("O triângulo Y é o maior");
		}
		
		sc.close();
		
		
		/////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);

		Triangle x,y;
		
		x = new Triangle();
		y = new Triangle();
		
		System.out.println("Entre com as medidas do triângulo X:");
		x.a = sc.nextDouble();
		x.b = sc.nextDouble();
		x.c = sc.nextDouble();
		
		System.out.println("Entre com as medidas do triângulo Y:");
		y.a = sc.nextDouble();
		y.b = sc.nextDouble();
		y.c = sc.nextDouble();

		double areaX = x.area();
		double areaY = y.area();
        


		System.out.printf("O Triângulo X tem area igual a: %4f%n", areaX);
		System.out.printf("O Triângulo Y tem area igual a: %4f%n", areaY);
		
		if (areaX > areaY) {
			System.out.println("O triângulo X é o maior");
		} else {
			System.out.println("O triângulo Y é o maior");
		}
		
		sc.close();
		
		//// FIM PROGRAMA PARA CALCULAR AREA TRIANGULO
		
	
		
		//// INICIO PROGRAMA ESTOQUE - ENTRADA E SAIDA		
		/////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);

		Product product = new Product();
		System.out.println("Entre com dados do produto: ");
		System.out.print("Nome: ");
		product.name = sc.nextLine();
		
		System.out.print("Preço: ");
		product.price = sc.nextDouble();
		
		System.out.print("Quantidade em estoque: ");
		product.quantity = sc.nextInt();
		
		//System.out.println(product.name + ", " + product.price + ", " + product.quantity);
		
		System.out.println("Dados do Produto: " + product);
		
		System.out.println();
		System.out.print("Entre com o número de produtos a serem adicionados no estoque: ");
		int quantity = sc.nextInt();
		product.addProducts(quantity);
		
		System.out.println();
		System.out.println("Updates data: " + product);
		
		System.out.println();
		System.out.print("Entre com o número de produtos a serem removidos do estoque: ");
		quantity = sc.nextInt();
		product.removeProducts(quantity);
		
		System.out.println();
		System.out.println("Updates data: " + product);
		
		
		sc.close();
		
	//// FIM PROGRAMA ESTOQUE - ENTRADA E SAIDA	
	 
	 */
		
	//// INICIO PROGRAMA AREA RETANGULA		
	/////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
	
	Locale.setDefault(Locale.US);
		
	Scanner sc = new Scanner(System.in);
	
	Rectangle rectangle = new Rectangle();
	
	System.out.println("Entre com dados a area: ");

	System.out.print("Largura: ");
	rectangle.largura = sc.nextDouble();
	
	System.out.print("Altura: ");
	rectangle.altura = sc.nextDouble();

	System.out.println();
	System.out.println("Área: " + rectangle.area());
	
	System.out.println();
	System.out.println("Perímetro: " + rectangle.perimeter());
	
	System.out.println();
	System.out.println("Diagonal: " + rectangle.diagonal());
	
	sc.close();	
	}

}
