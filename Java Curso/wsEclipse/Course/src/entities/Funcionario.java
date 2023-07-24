package entities;

public class Funcionario {

	public String name;
	public double grossSalary;
	public double tax;

	public double netSalary() {
		return grossSalary - tax;
	}

	public void increaseSalary(double percentage) {
		grossSalary += grossSalary * (percentage/100);
	}
	
	public String toString() { // criando a minha versão de toString
		return 
			name + ", $ " + String.format("%.2f", netSalary());

	}
}
