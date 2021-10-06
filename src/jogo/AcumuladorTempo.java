/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author Eduardo
 */
public class AcumuladorTempo implements Atualizavel {

    private long tempoAcumulado;
    private long tempoExpiracao;

    public AcumuladorTempo(long tempoExpirado) {
        this.tempoAcumulado = 0;
        this.tempoExpiracao = tempoExpirado;
    }
    
    @Override
    public void update(long tempoFrame) {
        tempoAcumulado += tempoFrame;
    }
    
    
    public boolean expirouProgressivo() {
        return tempoAcumulado >= tempoExpiracao;
    }
    
    public boolean expirouRegressivo() {
        return tempoAcumulado <= tempoExpiracao+1;
    }
    
    public void reseta() {
        tempoAcumulado %= tempoExpiracao;
    }
    
    public long getTempoAcumulado() {
        return this.tempoAcumulado;
    }
    
    public void setTempoAcumulado(long tempoAcumulado) {
        this.tempoAcumulado = tempoAcumulado;
    }
    
}
