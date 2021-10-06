/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;

/**
 *
 * @author Eduardo
 */
public class Sprite implements Atualizavel {
    private Image imagem;
    private int numImagens;
    private int larguraQuadros;
    private int alturaQuadros;
    private int quadroAtual;
    private int fps;
    private AcumuladorTempo timer;

    public Sprite(Image imagem, int numImagens, int larguraQuadros, int alturaQuadros, int fps) {
        this.imagem = imagem;
        this.numImagens = numImagens - 1;
        this.larguraQuadros = larguraQuadros;
        this.alturaQuadros = alturaQuadros;
        this.quadroAtual = 0;
        this.fps = fps;
        this.timer = new AcumuladorTempo(1000 / fps);
    }

    @Override
    public void update(long tempoFrame) {
        this.timer.update(tempoFrame);
        if( timer.expirouProgressivo() ) {
            timer.reseta();
            if(this.quadroAtual >= numImagens) {
                this.quadroAtual = 0;
            } else {
               this.quadroAtual++;    
            }
        }
    }

    /**
     * @return the imagem
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the numImagens
     */
    public int getNumImagens() {
        return numImagens;
    }

    /**
     * @param numImagens the numImagens to set
     */
    public void setNumImagens(int numImagens) {
        this.numImagens = numImagens;
    }

    /**
     * @return the larguraQuadros
     */
    public int getLarguraQuadros() {
        return larguraQuadros;
    }

    /**
     * @param larguraQuadros the larguraQuadros to set
     */
    public void setLarguraQuadros(int larguraQuadros) {
        this.larguraQuadros = larguraQuadros;
    }

    /**
     * @return the alturaQuadros
     */
    public int getAlturaQuadros() {
        return alturaQuadros;
    }

    /**
     * @param alturaQuadros the alturaQuadros to set
     */
    public void setAlturaQuadros(int alturaQuadros) {
        this.alturaQuadros = alturaQuadros;
    }

    /**
     * @return the quadroAtual
     */
    public int getQuadroAtual() {
        return quadroAtual;
    }

    /**
     * @param quadroAtual the quadroAtual to set
     */
    public void setQuadroAtual(int quadroAtual) {
        this.quadroAtual = quadroAtual;
    }

    /**
     * @return the fps
     */
    public int getFps() {
        return fps;
    }

    /**
     * @param fps the fps to set
     */
    public void setFps(int fps) {
        this.fps = fps;
    }

    
    
    
    
}
