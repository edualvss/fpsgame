/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Graphics2D;

/**
 *
 * @author Anderson
 */
public class Personagem extends GameObject {

    public Personagem(Sprite sprite,double velocidade) {
        super.sprite = sprite;
        super.velocidade = velocidade;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Altura == 0, devido a todas os sprites serem somente uma linha de quadros
        super.draw(g2d, super.sprite.getQuadroAtual() * super.sprite.getLarguraQuadros(), 0);
    }

    
    
}
