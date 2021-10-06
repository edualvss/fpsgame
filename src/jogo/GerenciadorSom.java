/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Eduardo
 */
public class GerenciadorSom {
    
    public static void tocarSom(String nomeSom) {
        tocarSom(nomeSom,false);
    }

    public static void tocarSom(String nomeSom, boolean loop) {
        try {
            Clip clip = AudioSystem.getClip();
            URL urlSom = GerenciadorSom.class.getResource(nomeSom);
            clip.open( AudioSystem.getAudioInputStream(urlSom) );
            if( loop ) {
                clip.loop( Clip.LOOP_CONTINUOUSLY );
            }
            clip.start();
        } catch (Exception ex) {
            Logger.getLogger(GerenciadorSom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void tocarSom(Clip clip) {
        clip.start();
    }
}
