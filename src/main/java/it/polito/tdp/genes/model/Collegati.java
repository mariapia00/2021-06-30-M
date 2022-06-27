package it.polito.tdp.genes.model;

public class Collegati {

	private int chromosome1;
	private int chromosome2;
	private double peso;
	public Collegati(int chromosome1, int chromosome2, double peso) {
		super();
		this.chromosome1 = chromosome1;
		this.chromosome2 = chromosome2;
		this.peso = peso;
	}
	public int getChromosome1() {
		return chromosome1;
	}
	public int getChromosome2() {
		return chromosome2;
	}
	public double getPeso() {
		return peso;
	}
	
}
