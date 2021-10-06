/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;
import java.awt.Rectangle;


/**
 *
 * @author Eduardo
 */
public class Mira extends GameObject {

    private boolean disparo = false;
    private boolean recarregado = false;
    private int tolerancia = 0;

    private final int quantidadeBalasPente;
    private int balasPente;
    
    private Image bala;
    
    public Mira(Sprite sp,int tolerancia,int balasPente,Image bala) {
        super.setSprite(sp);
        this.tolerancia = tolerancia;
        this.balasPente = balasPente;
        this.quantidadeBalasPente = balasPente;
        this.bala = bala;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x+tolerancia, (int)this.y+tolerancia,
                this.sprite.getLarguraQuadros()-tolerancia*2,this.sprite.getAlturaQuadros()-tolerancia*2);
    }
    
    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }

    public boolean isDisparo() {
        return disparo;
    }

    public void setRecarregado(boolean recarregado) {
        this.recarregado = recarregado;
    }

    public boolean isRecarregado() {
        return recarregado;
    }

    public Image getBala() {
        return bala;
    }

    public int getBalasPente() {
        return balasPente;
    }

    public void update() {
        this.disparo = false;
        if( this.balasPente > 0 ) {
            this.balasPente--;
        } else {
            if( this.recarregado ) {
                this.balasPente = this.quantidadeBalasPente;
                this.recarregado = false;
            }
        }
    }

    
    
}
