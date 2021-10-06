/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author Eduardo
 */
public class Pontuacao {
    
    public static final int PONTO_GANHO = 100;
    public static final int PONTO_PERDIDO_VILAO = -75;
    public static final int PONTO_PERDIDO_TIRO = -50;
    
    private int pontos = 0;
    
    public int getPontos() {
        return pontos;
    }

    public void update(int pontuacao) {
        this.pontos += pontuacao;
        if(this.pontos < 0) {
            this.pontos = 0;
        }
    }

    
}
