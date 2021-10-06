/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Eduardo
 */
public class ControladorMouse extends MouseAdapter {

    private int x;
    private int y;
    private boolean disparado = false;
    private boolean recarregado = false;
    
    @Override
    public void mousePressed(MouseEvent me) {
        
        if(me.getButton() == MouseEvent.BUTTON1) {
            disparado = true;
        } else if(me.getButton() == MouseEvent.BUTTON3) {
            recarregado = true;
        }
        
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        disparado = false;
        recarregado = false;
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        mouseMoved(me);
        disparado = false;
        recarregado = false;
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.x = me.getX();
        this.y = me.getY();        
    }

    public void setDisparado(boolean disparado) {
        this.disparado = disparado;
    }

    public boolean isDisparado() {
        return disparado;
    }

    public void setRecarregado(boolean recarregado) {
        this.recarregado = recarregado;
    }

    public boolean isRecarregado() {
        return recarregado;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
    
}
