
package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;
import entities.Product;
import entities.Rectangle;
import util.Calculator;

public class Program {

	// public static final double PI = 3.14159;

	public static void main(String[] args) {
		//// INICIO PROGRAMA PARA CALCULAR AREA TRIANGULO

		/////// SOLUÇÃO SEM ORIENTAÇÃO `A OBJETOS
		/*
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in); double xA, xB, xC, yA, yB, yC;
		 * 
		 * System.out.println("Entre com as medidas do triângulo X:"); xA =
		 * sc.nextDouble(); xB = sc.nextDouble(); xC = sc.nextDouble();
		 * 
		 * System.out.println("Entre com as medidas do triângulo Y:"); yA =
		 * sc.nextDouble(); yB = sc.nextDouble(); yC = sc.nextDouble();
		 * 
		 * double p = (xA + xB + xC) / 2.0; double areaX = Math.sqrt(p * (p-xA) * (p-xB)
		 * * (p-xC));
		 * 
		 * p = (yA + yB + yC) / 2.0; double areaY = Math.sqrt(p * (p-yA) * (p-yB) *
		 * (p-yC));
		 * 
		 * System.out.printf("O Triângulo X tem area igual a: %4f%n", areaX);
		 * System.out.printf("O Triângulo Y tem area igual a: %4f%n", areaY);
		 * 
		 * if (areaX > areaY) { System.out.println("O triângulo X é o maior"); } else {
		 * System.out.println("O triângulo Y é o maior"); }
		 * 
		 * sc.close();
		 * 
		 * 
		 * /////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * Triangle x,y;
		 * 
		 * x = new Triangle(); y = new Triangle();
		 * 
		 * System.out.println("Entre com as medidas do triângulo X:"); x.a =
		 * sc.nextDouble(); x.b = sc.nextDouble(); x.c = sc.nextDouble();
		 * 
		 * System.out.println("Entre com as medidas do triângulo Y:"); y.a =
		 * sc.nextDouble(); y.b = sc.nextDouble(); y.c = sc.nextDouble();
		 * 
		 * double areaX = x.area(); double areaY = y.area();
		 * 
		 * 
		 * 
		 * System.out.printf("O Triângulo X tem area igual a: %4f%n", areaX);
		 * System.out.printf("O Triângulo Y tem area igual a: %4f%n", areaY);
		 * 
		 * if (areaX > areaY) { System.out.println("O triângulo X é o maior"); } else {
		 * System.out.println("O triângulo Y é o maior"); }
		 * 
		 * sc.close();
		 * 
		 * //// FIM PROGRAMA PARA CALCULAR AREA TRIANGULO
		 * 
		 * 
		 * 
		 * //// INICIO PROGRAMA ESTOQUE - ENTRADA E SAIDA /////// SOLUÇÃO COM ORIENTAÇÃO
		 * `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * Product product = new Product();
		 * System.out.println("Entre com dados do produto: ");
		 * System.out.print("Nome: "); product.name = sc.nextLine();
		 * 
		 * System.out.print("Preço: "); product.price = sc.nextDouble();
		 * 
		 * System.out.print("Quantidade em estoque: "); product.quantity = sc.nextInt();
		 * 
		 * //System.out.println(product.name + ", " + product.price + ", " +
		 * product.quantity);
		 * 
		 * System.out.println("Dados do Produto: " + product);
		 * 
		 * System.out.println(); System.out.
		 * print("Entre com o número de produtos a serem adicionados no estoque: "); int
		 * quantity = sc.nextInt(); product.addProducts(quantity);
		 * 
		 * System.out.println(); System.out.println("Updates data: " + product);
		 * 
		 * System.out.println(); System.out.
		 * print("Entre com o número de produtos a serem removidos do estoque: ");
		 * quantity = sc.nextInt(); product.removeProducts(quantity);
		 * 
		 * System.out.println(); System.out.println("Updates data: " + product);
		 * 
		 * 
		 * sc.close();
		 * 
		 * //// FIM PROGRAMA ESTOQUE - ENTRADA E SAIDA
		 * 
		 * //// INICIO PROGRAMA AREA RETANGULO /////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * Rectangle rectangle = new Rectangle();
		 * 
		 * System.out.println("Entre com dados a area: ");
		 * 
		 * System.out.print("Largura: "); rectangle.largura = sc.nextDouble();
		 * 
		 * System.out.print("Altura: "); rectangle.altura = sc.nextDouble();
		 * 
		 * System.out.println(); System.out.println("Área: " + rectangle.area());
		 * 
		 * System.out.println(); System.out.println("Perímetro: " +
		 * rectangle.perimeter());
		 * 
		 * System.out.println(); System.out.println("Diagonal: " +
		 * rectangle.diagonal());
		 * 
		 * sc.close();
		 * 
		 * //// INICIO PROGRAMA SALARIO FUNCIONARIO /////// SOLUÇÃO COM ORIENTAÇÃO `A
		 * OBJETOS
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * Funcionario funcionario = new Funcionario();
		 * 
		 * System.out.println("Entre com dados a area: ");
		 * 
		 * System.out.print("Nome: "); funcionario.name = sc.nextLine();
		 * 
		 * System.out.print("Salário Bruto: "); funcionario.grossSalary =
		 * sc.nextDouble();
		 * 
		 * System.out.print("Taxa: "); funcionario.tax = sc.nextDouble();
		 * 
		 * 
		 * System.out.println(); System.out.println("Employee: " + funcionario.name +
		 * ", " + funcionario.grossSalary + ", " + funcionario.netSalary());
		 * 
		 * System.out.println();
		 * System.out.print("Qual percentual para aumentar o salário? "); double
		 * percentage = sc.nextDouble(); funcionario.increaseSalary(percentage);
		 * 
		 * System.out.println(); System.out.println("Update data: " + funcionario);
		 * sc.close();
		 * 
		 * 
		 * 
		 * /// Membros Estáticos Parte 1.1
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * System.out.print("Entre com o raio:"); double raio = sc.nextDouble();
		 * 
		 * double c = cincunferencia(raio); double v = volume(raio);
		 * 
		 * System.out.printf("Circunferência: %.2f%n", c);
		 * System.out.printf("Volume: %.2f%n", v);
		 * System.out.printf("Valor do PI: %.2f%n", PI);
		 * 
		 * sc.close();
		 * 
		 * }
		 * 
		 * public static double cincunferencia(double raio) { return 2.0 * PI * raio; }
		 * 
		 * public static double volume (double raio) { return 4.0 * PI * raio * raio *
		 * raio / 3.0; }
		 * 
		 * 
		 * 
		 * /// Membros Estáticos Parte 1.2
		 * 
		 * Locale.setDefault(Locale.US);
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * // Instanciar o objeto
		 * 
		 * Calculator calc = new Calculator();
		 * 
		 * 
		 * System.out.print("Entre com o raio:"); double raio = sc.nextDouble();
		 * 
		 * double c = calc.cincunferencia(raio); double v = calc.volume(raio);
		 * 
		 * System.out.printf("Circunferência: %.2f%n", c);
		 * System.out.printf("Volume: %.2f%n", v);
		 * System.out.printf("Valor do PI: %.2f%n", calc.PI);
		 * 
		 * sc.close();
		 * 
		 * 
		 * //// INICIO PROGRAMA ESTOQUE - ENTRADA E SAIDA - PROPOSTA DE MELHORIA -
		 * CRIANDO PRIMEIRO CONSTRUTOR /////// SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US); Scanner sc = new Scanner(System.in);
		 * 
		 * System.out.println("Enter product data: "); System.out.print("Name: ");
		 * String name = sc.nextLine();
		 * 
		 * System.out.print("Price: "); double price = sc.nextDouble();
		 * 
		 * System.out.print("Quantity in stock: "); int quantity = sc.nextInt();
		 * 
		 * Product product = new Product(name, price, quantity);
		 * 
		 * 
		 * System.out.println(); System.out.println("Product data: " + product);
		 * System.out.println();
		 * System.out.print("Enter the number of products to be added in stock: ");
		 * quantity = sc.nextInt(); product.addProducts(quantity); System.out.println();
		 * System.out.println("Updated data: " + product); System.out.println();
		 * System.out.print("Enter the number of products to be removed from stock: ");
		 * quantity = sc.nextInt(); product.removeProducts(quantity);
		 * System.out.println(); System.out.println("Updated data: " + product);
		 * sc.close();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //// INICIO PROGRAMA ESTOQUE - ENTRADA E SAIDA - PROPOSTA DE MELHORIA -
		 * CRIANDO SEGUNDO CONSTRUTOR ONDE NÃO PRECISA DE QUANTIDADE /////// SOLUÇÃO COM
		 * ORIENTAÇÃO `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US); Scanner sc = new Scanner(System.in);
		 * 
		 * System.out.println("Enter product data: "); System.out.print("Name: ");
		 * String name = sc.nextLine();
		 * 
		 * System.out.print("Price: "); double price = sc.nextDouble();
		 * 
		 * Product product = new Product(name, price);
		 * 
		 * 
		 * System.out.println(); System.out.println("Product data: " + product);
		 * System.out.println();
		 * System.out.print("Enter the number of products to be added in stock: "); int
		 * quantity = sc.nextInt(); product.addProducts(quantity); System.out.println();
		 * System.out.println("Updated data: " + product); System.out.println();
		 * System.out.print("Enter the number of products to be removed from stock: ");
		 * quantity = sc.nextInt(); product.removeProducts(quantity);
		 * System.out.println(); System.out.println("Updated data: " + product);
		 * sc.close();
		 * 
		 * 
		 * 
		 * 
		 * //// INICIO PROGRAMA ESTOQUE - ENTRADA E SAIDA - ENCAPSULAMENTO ///////
		 * SOLUÇÃO COM ORIENTAÇÃO `A OBJETOS
		 * 
		 * Locale.setDefault(Locale.US); Scanner sc = new Scanner(System.in);
		 * 
		 * System.out.println("Enter product data: "); System.out.print("Name: ");
		 * String name = sc.nextLine();
		 * 
		 * System.out.print("Price: "); double price = sc.nextDouble();
		 * 
		 * Product product = new Product(name, price);
		 * 
		 * product.setName("Computer"); System.out.println("Updated Name: " +
		 * product.getName());
		 * 
		 * product.setPrice(1200.00); System.out.println("Updated Price: " +
		 * product.getPrice());
		 * 
		 * System.out.println(); System.out.println("Product data: " + product);
		 * System.out.println();
		 * System.out.print("Enter the number of products to be added in stock: "); int
		 * quantity = sc.nextInt(); product.addProducts(quantity); System.out.println();
		 * System.out.println("Updated data: " + product); System.out.println();
		 * System.out.print("Enter the number of products to be removed from stock: ");
		 * quantity = sc.nextInt(); product.removeProducts(quantity);
		 * System.out.println(); System.out.println("Updated data: " + product);
		 * sc.close();
		 * 
		 * 
		 * 
		 * 
		 * //// VETOR
		 * 
		 * Locale.setDefault(Locale.US); Scanner sc = new Scanner(System.in);
		 * 
		 * int n = sc.nextInt(); double [] vect = new double[n];
		 * 
		 * for (int i=0; i<n; i++) { vect[i] = sc.nextDouble(); }
		 * 
		 * double sum = 0.0; for (int i = 0; i<n; i++) { sum += vect[i]; }
		 * 
		 * double avg = sum / n;
		 * 
		 * System.out.println("A média da altura é: " + avg); sc.close();
		 * 
		 * 
		 * //// VETOR
		 * 
		 * Locale.setDefault(Locale.US); Scanner sc = new Scanner(System.in);
		 * 
		 * int n = sc.nextInt();
		 * 
		 * Product[] vect = new Product[n];
		 * 
		 * for (int i=0; i < vect.length; i++) { sc.nextLine(); String name =
		 * sc.nextLine(); double price = sc.nextDouble(); vect[i] = new Product(name,
		 * price); }
		 * 
		 * double sum = 0.0; for (int i=0; i < vect.length; i++) { sum +=
		 * vect[i].getPrice(); }
		 * 
		 * double avg = sum / n;
		 * 
		 * System.out.printf("A média de preço é: %.2f%n", avg);
		 * 
		 * 
		 * 
		 * sc.close();
		 * 
		 * //// BOXING, UNBOXING E WRAPPER CLASSES
		 * 
		 * int x = 20; Object obj = x; // boxing System.out.println(obj);
		 * 
		 * int y = (int) obj; // umboxing
		 * 
		 * System.out.println(y);
		 * 
		 * 
		 * ////WRAPPER CLASSES int x = 20;
		 * 
		 * Integer obj = x; // WRAPPING
		 * 
		 * System.out.println(obj);
		 * 
		 * int y = obj; // umboxing
		 * 
		 * System.out.println(y);
		 * 
		 * 
		 * 
		 * ////Laço for each
		 * 
		 * String [] vect = new String[] {"Maria", "Bob", "Alex"};
		 * 
		 * for (int i=0;i<vect.length; i++) { System.out.println(vect[i]); }
		 * 
		 * System.out.println("---------------------------");
		 * 
		 * for (String obj : vect ) { // para cada objeto obj contido em vect
		 * System.out.println(obj); }
		 * 
		 */

		//// Matrizes

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] mat = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = sc.nextInt();
			}
		}

		sc.close();

	}

}
