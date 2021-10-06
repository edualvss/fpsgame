/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.image.BufferedImage;
import tela.Tela;

/**
 *
 * @author Anderson
 */
public class ControladorTela {
    
    
    private double controlePosicaoTelaX;
    private BufferedImage buffer;
    private ControladorMouse mouse;
    private Tela tela;
    
    private double posicaoRelativaMouse;
    private double posicaoAbsolutaMouseNaTelaX;
    private double posicaoAbsolutaMouseNoBuffer;
    
    public ControladorTela(Tela tela,BufferedImage buffer,ControladorMouse mouse) {
        this.buffer = buffer;
        this.mouse = mouse;
        this.tela = tela;
        controlePosicaoTelaX = mouse.getX();
        
    }

    public void movimentaTela(){
        
        this.posicaoAbsolutaMouseNaTelaX = mouse.getX();
        
        this.posicaoRelativaMouse = posicaoAbsolutaMouseNaTelaX / tela.getWidth();
        
        this.posicaoAbsolutaMouseNoBuffer =  (posicaoRelativaMouse * buffer.getWidth());

        controlePosicaoTelaX = -(posicaoAbsolutaMouseNoBuffer - posicaoAbsolutaMouseNaTelaX);

}
    
    public double getControlePosicaoTelaX() {
        return controlePosicaoTelaX;
    }
    
}
