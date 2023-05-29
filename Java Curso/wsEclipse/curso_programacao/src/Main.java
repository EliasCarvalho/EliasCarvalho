import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		System.out.println("Olá Mundo!");
		System.out.println("Bom dia!");
		
		System.out.print("Olá Mundo!");
		System.out.println("Bom dia!");
		
		double x = 10.35784;
		System.out.println(x);
		Locale.setDefault(Locale.US);
		System.out.printf("%.3f%n",x);
		System.out.println("RESULTADO = " + x + " metros");
		System.out.printf("RESULTADO = %.2f%n metros%n",x);

		String nome = "Maria";
		int idade = 31;
		double renda = 4000.00;
		System.out.printf("%s tem %d anos e ganha R $%.2f reais%n", nome, idade, renda);
		
		
		String product1 = "Computer";
		String product2 = "Office desk";
		
		int age = 30;
		int code = 5290;
		char gender = 'F';
		
		double price1 = 2100.0;
		double price2 = 650.50;
		double measure = 53.234567;
		
		System.out.println("Products: \n");
		
		System.out.printf("%s, which price is $%.2f reais%n", product1, price1);
		System.out.printf("%s, which price is $%.2f reais%n", product2, price2);
		
		System.out.printf("\nRecord: %d anos de idade, code %d and gender %s\n", age, code, gender );
		
		System.out.printf("\nMeasure with eight decimal places: $%.8f\n", measure );
		
		System.out.printf("\nRounded (three decimal places: $%.3f\n", measure );
		Locale.setDefault(Locale.ROOT);
		System.out.printf("\nUS decimal point: $%.3f\n", measure );

		
		Scanner sc = new Scanner(System.in);
		
		String x;
		x = sc.next();
		System.out.println("Vc digitou: "+x);
		sc.close();
		
		Scanner sc = new Scanner(System.in);
		int x;
		x = sc.nextInt();
		System.out.println("Vc digitou: "+x);
		sc.close();
		
		Scanner sc = new Scanner(System.in);
		double x;
		x = sc.nextDouble();
		System.out.println("Vc digitou: "+x);
		System.out.printf("Vc digitou: %.2f%n",x);

		sc.close();
		

		
		Scanner sc = new Scanner(System.in);
		char x;
		x = sc.next().charAt(0);
		System.out.println("Vc digitou: "+x);

		sc.close();
		
		
		Scanner sc = new Scanner(System.in);
		String x;
		int y;
		double z;
		
		x = sc.next();
		y = sc.nextInt();
		z = sc.nextDouble();
			
		System.out.println("Dados digitados: ");
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		sc.close();
		
		// Quebra de linha
		Scanner sc = new Scanner(System.in);
		String s1, s2, s3;
		
		s1 = sc.nextLine();	
		s2 = sc.nextLine();
		s3 = sc.nextLine();
		
		System.out.println("Dados digitados: ");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		sc.close();
	}

	// Quebra de linha pendente
		Scanner sc = new Scanner(System.in);
		String s1, s2, s3;
		int x;
		
		x  = sc.nextInt();
		     sc.nextLine();
		s1 = sc.nextLine();	
		s2 = sc.nextLine();
		s3 = sc.nextLine();
		
		System.out.println("Dados digitados: ");
		System.out.println(x);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		sc.close();
		
		
		
		double x = 3.0;
		double y = 4.0;
		double z = -5.0;
		double A,B,C;
		
		A = Math.sqrt(x);
		B = Math.sqrt(y);
		C = Math.sqrt(25);
		
		System.out.println("Raiz quadrada de " + x + " = " + A);
		System.out.println("Raiz quadrada de " + y + " = " + B);
		System.out.println("Raiz quadrada de 25 = " + C);
				
		A = Math.pow(x, y);
		B = Math.pow(x, 2.0);
		C = Math.pow(5.0, 2.0);
		System.out.println(x + " elevado a " + y + " = " + A);
		System.out.println(x + " elevado ao quadrado =  " + B);
		System.out.println("5 elevado ao quadrado =  " + C);
		
		A = Math.abs(y);
		B = Math.abs(z);
		
		System.out.println("Valor absoluto de " + y + " = " + A);
		System.out.println("Valor absoluto de " + z + " = " + B);
	sc.close();
	
	
		Scanner sc = new Scanner(System.in);
		int hora;
		
		System.out.println("Quantas horas? ");
		hora = sc.nextInt();
		
		if (hora < 12) {
			System.out.println("Bom dia ");
		} 
		else if (hora >= 12 && hora < 18) {
				System.out.println("Boa tarde ");
		}
		else
			{
				System.out.println("Boa noite ");
			}
		sc.close();
		
		
		
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		String dia;
		
		if (x==1) {
			dia = "domingo";
		} else if (x==2) {
			dia = "segunda";
		} else if (x==3) {
			dia = "terça";
		}else if (x==4) {
			dia = "quarta";
		}else if (x==5) {
			dia = "quinta";
		}else if (x==6) {
			dia = "sexta";
		}else if (x==7) {
			dia = "sábado";
		} else dia ="Valor invalido!"; 
		
		System.out.println("Dia da semana: " + dia);
		
		sc.close();
		
		
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		String dia;
		
		switch(x) {
		case 1:
			dia = "domingo";
			break;
		case 2:
			dia = "segunda";
			break;
		case 3:
			dia = "terça";
			break;
		case 4:
			dia = "quarta";
			break;
		case 5:
			dia = "quinta";
			break;
		case 6:
			dia = "sexta";
			break;
		case 7:
			dia = "sábado";
			break;
		default:
			dia = "Valor invalido!";
			break;
		} 
		
		System.out.println("Dia da semana: " + dia);
		
		sc.close();
		
		// Condição Ternária
		double preco = 34.6;
		double desconto;
		if (preco < 20.0) {
			desconto = preco * 0.1;
		} else {
			desconto = preco * 0.05;
		}
		System.out.println("Desconto: " + desconto);
	
	
	// Condição Ternária
		double preco = 34.6;
		double desconto = (preco < 20.0 ? preco * 0.1 : preco * 0.05);
		
		System.out.println("Desconto: " + desconto);


   // While
    
    Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int soma = 0;
		
		while (x !=0) {
			soma = soma + x;
			x = sc.nextInt();
		}
		System.out.println(soma);
		sc.close();
    
    
    // for
     
     Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int soma = 0;
				
		for (int i=0; i < N; i=i+1) {
			int x = sc.nextInt();
			soma = soma + x;			
		}
		System.out.println(soma);
		sc.close();
		
	// while
	 	Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		char resp = 's';
		
	    while (resp !='n') {
	    	System.out.println("Digite a temperatura em Celsius:");
			
			double C = sc.nextDouble();
			double F = 9.0 * C / 5.0 + 32.0;
			
			System.out.printf("Equivalente em Fahrenheit: %.2f%n",F);
			System.out.print("\nDeseja repetir (s/n)?");
			resp = sc.next().charAt(0);	
	    }
		
		sc.close();
		
	}
		
		 
*/
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
				
	    do  {
	    	System.out.println("Digite a temperatura em Celsius:");
			
			double C = sc.nextDouble();
			double F = 9.0 * C / 5.0 + 32.0;
			
			System.out.printf("Equivalente em Fahrenheit: %.2f%n",F);
			System.out.print("\nDeseja repetir (s/n)?");
			char resp = sc.next().charAt(0);	
	    } while (resp !='n');
		
		sc.close();
		
	}	
}
