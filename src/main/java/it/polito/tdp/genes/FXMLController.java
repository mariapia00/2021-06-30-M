/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnContaArchi"
    private Button btnContaArchi; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSoglia"
    private TextField txtSoglia; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doContaArchi(ActionEvent event) {
    	double soglia = Double.parseDouble(txtSoglia.getText());
    	if(soglia<=this.model.getPesoMax() && soglia >= this.model.getPesoMin()) {
    		this.model.contaArchi(soglia);
    		txtResult.appendText("Soglia: "+soglia+"---> Maggiori: "+this.model.getMax()+", Minori: "+ this.model.getMin()+"\n");
    	}else {
    		txtResult.setText("Il valore di soglia deve essere compreso tra massimo e minimo specificati!\n\n\n");
    	}
    }

    @FXML
    void doRicerca(ActionEvent event) {
    	double soglia = Double.parseDouble(txtSoglia.getText());
    	if(soglia<=this.model.getPesoMax() && soglia >= this.model.getPesoMin()) {

        	txtResult.appendText("Cammino: \n");
    		List<Integer> result = this.model.calcolaCammino(soglia);
    		for(Integer i : result) {
    			txtResult.appendText("Cromosoma "+i+"\n");
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		this.model.creaGrafo();
    	
    	txtResult.appendText("Grafo creato: ");
    	txtResult.appendText(this.model.getNVertici()+" vertici, "+this.model.getNArchi()+" archi.\n\n");
    	txtResult.appendText("Peso massimo: "+this.model.getPesoMax()+".\n\n");
    	txtResult.appendText("Peso minimo: "+ this.model.getPesoMin()+".\n\n");
	}
}
