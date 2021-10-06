/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Eduardo
 */
public abstract class GameObject implements Atualizavel {

    protected double x;
    protected double y;
    protected Sprite sprite;
    protected double velocidade = 1;
    protected double angulo = 0;
    
    @Override
    public void update(long tempoFrame) {
        double anguloRadiano = Math.toRadians(angulo);
        x += ( velocidade * tempoFrame / 1000 * Math.cos(anguloRadiano) );
        y += ( velocidade * tempoFrame / 1000 * Math.sin(anguloRadiano) );
        
        if( sprite != null ) {
            sprite.update(tempoFrame);
        }
    }

    public void draw(Graphics2D g2d) {
        draw(g2d, 0, 0);
    }
    
    public void draw(Graphics2D g2d,int quadroX,int quadroY) {
        int lx = sprite.getLarguraQuadros();
        int hy = sprite.getAlturaQuadros();

        g2d.drawImage( this.sprite.getImagem(),
                (int)this.x,(int)this.y,(int)this.x+lx, (int)this.y+hy,
                quadroX, quadroY, quadroX+lx, quadroY+hy, null);
       

    }


    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.sprite.getLarguraQuadros(),this.sprite.getAlturaQuadros());
    }
    
    public boolean colidiuCom(GameObject obj1) {
        return ( this.getBounds().intersects(obj1.getBounds()) );
    }
    
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return the velocidade
     */
    public double getVelocidade() {
        return velocidade;
    }

    /**
     * @param velocidade the velocidade to set
     */
    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getAngulo() {
        return angulo;
    }

    
 }