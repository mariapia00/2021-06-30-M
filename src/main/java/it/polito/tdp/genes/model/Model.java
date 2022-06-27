package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {

	private GenesDao dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private int min;
	private int max;
	private List<Integer> best;
	private double bestLunghezza;
	public Model() {
		dao = new GenesDao();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.getVertici());
		
		for(Collegati c : this.dao.getArchi()) {
			Graphs.addEdgeWithVertices(this.grafo, c.getChromosome1(), c.getChromosome2(),c.getPeso());
		}
	}
	public List<Integer> calcolaCammino(double soglia){
		bestLunghezza  = 0.0;
		
		best = new ArrayList<>();
		
		List<Integer> parziale = new ArrayList<>();
		ricorsione(parziale, 1, soglia);
		return best;
		
	}
	public void ricorsione(List<Integer> parziale, int livello, double soglia) {
		if(lunghezza(parziale)>bestLunghezza) {
			best = new ArrayList<>(parziale);
			bestLunghezza = lunghezza(parziale);
		}
		if(livello == this.grafo.vertexSet().size())
			return;
		for(Integer i : this.grafo.vertexSet()) {
			if(aggiuntaValida(i, parziale, soglia)) {
				parziale.add(i);
				ricorsione(parziale, livello+1, soglia);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	
	
	
	private boolean aggiuntaValida(int i, List<Integer> parziale, double soglia) {
		if(parziale.size()==0) {
			return true;
		}else{
			
			int ultimo = parziale.get(parziale.size()-1);
			if(this.grafo.containsEdge(ultimo, i) && this.grafo.getEdgeWeight(this.grafo.getEdge(ultimo,i))>soglia && !parziale.contains(i)) {
				return true;
			}
			return false;
		}
	}

	private double lunghezza(List<Integer> parziale) {
		double peso = 0.0;
		for(int i = 0; i<parziale.size()-1; i++) {
			if(this.grafo.getEdge(parziale.get(i), parziale.get(i+1))!=null)
				peso+=this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i), parziale.get(i+1)));
		}
		return peso;
	}

	public double getPesoMax() {
		double max=Double.MIN_VALUE;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)> max) {
				max = this.grafo.getEdgeWeight(e);
			}
		}
		return max;
	}
	public double getPesoMin() {
		double min=Double.MAX_VALUE;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)< min) {
				min = this.grafo.getEdgeWeight(e);
			}
		}
		return min;
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}

	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}

	public void contaArchi(double soglia) {
		
		min = 0;
		max = 0;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>soglia)
				max++;
			if(this.grafo.getEdgeWeight(e)<soglia)
				min++;
		}
	}
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
}