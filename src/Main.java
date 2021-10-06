
import controle.Controlador;
import tela.Tela;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO Jogo FPS em 2d
        
        Tela tela = new Tela();
        
        Controlador controle = new Controlador(tela);
        controle.runGame();
    }
}
