package br.com.bytebank.bank.modelo;

import java.io.Serializable;

/**
 * Classe que representa a moldura de uma conta
 * 
 *@author Guilherme de Aquino
 *@version 0.1
 */

public abstract class Conta extends Object implements Comparable<Conta>,Serializable {

	// double saldo => package private = visível somente dentro da classe e do pacote
	protected double saldo;
	private int agencia;
	private int numero;
	private Cliente titular;
	private static int total = 0;
	
	/**
	 * Construtor para inicializar o objeto Conta a partir da agencia e numero.
	 * 
	 * @param agencia
	 * @param numero
	 */

	// Construtor
	public Conta(int agencia, int numero) {
		Conta.total++;
		// System.out.println("O total de contas abertas é: " + Conta.total);
		if(agencia < 1) {
			throw new IllegalArgumentException("Agencia invalida");
		} this.agencia = agencia;
		if(numero < 1) {
			throw new IllegalArgumentException("Numero da conta invlido");
		} this.numero = numero;
		// System.out.println("estou criando uma conta " + this.numero +" Na Agencia: "+
		// this.agencia);
	}

	// Métodos
	public abstract void deposita(double valor);
	
	/**
	 * Valor precisa ser maior que saldo.
	 * 
	 * @param valor
	 * @throws SaldoInsuficienteException
	 */

	public void saca(double valor) throws SaldoInsuficienteException {
		if (this.saldo < valor) {
			throw new SaldoInsuficienteException("Saldo: " + this.saldo + ", valor: " + valor);
		}

		this.saldo -= valor;

	}

	public void transfere(double valor, Conta destino) throws SaldoInsuficienteException {

		this.saca(valor);
		destino.deposita(valor);
	}

	public double getSaldo() {
		return this.saldo;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		if (numero <= 0) {
			System.out.println("Não pode valor menor igual a 0");
			return;
		}
		this.numero = numero;
	}

	public int getAgencia() {
		return this.agencia;
	}

	public void setAgencia(int agencia) {
		if (agencia <= 0) {
			System.out.println("Não pode valor menor igual a 0");
			return;
		}
		this.agencia = agencia;
	}
	
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public Cliente getTitular() {
		return this.titular;
	}

	// Método da classe
	public static int getTotal() {
		return Conta.total;
	}
	
	@Override
	public boolean equals(Object ref) {
		
		Conta outra = (Conta) ref;
		
		if(this.agencia != outra.agencia) {
			return false;
		}
		
		if(this.numero != outra.numero) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Conta outra) {
		return Double.compare(this.saldo, outra.saldo);
	}
	
	@Override
	public String toString() {
		
		return "Numero: " + this.numero + ", Agencia: " + this.agencia + " Saldo: " + this.saldo;
	}

}