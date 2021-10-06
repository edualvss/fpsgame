/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Eduardo
 */
public class ContadorFPS extends GameObject {

    private int quadroAtual = 0;
    private AcumuladorTempo acumulador = new AcumuladorTempo(1000);
    private int fps = 0;
    
    @Override
    public void update(long tempoDoFrame) {
        quadroAtual++;
        acumulador.update(tempoDoFrame);
        if( acumulador.expirouProgressivo() ) {
            acumulador.reseta();
            fps = quadroAtual;
            quadroAtual = 0;
        }
    }

    public int getFps() {
        return fps;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setTransform( new AffineTransform() );
        g2d.setColor(Color.RED);
        g2d.drawString("FPS: "+fps,(int)super.x, (int)super.y);
    }
    
}
