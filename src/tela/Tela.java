/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import controle.ControladorMouse;
import controle.ControladorTela;
import controle.ObjetosDoJogo;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import jogo.ContadorFPS;
import jogo.Personagem;
import jogo.Pontuacao;
/**
 *
 * @author Eduardo
 */
public class Tela extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private BufferedImage buffer;
    private Image fundo;
    private ObjetosDoJogo objetos;
    
    private ControladorTela controleTela;
    private ControladorMouse cm;

    private ContadorFPS fps;
    private long timerJogo = 0;
    
    private Pontuacao pontuacaoPartida;
    
    private boolean pontoPerdido = false;
    
    public static final int[] POSICOES_DESENHO_TELA = {450,500,550,600,650};
    
    private int scrollSize = 800;
    
    public Tela() {
        super("Sniper Animated");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        setLocationRelativeTo(null);
        addKeyListener( new ControladorTeclado() );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        Cursor c = new Cursor(Cursor.CROSSHAIR_CURSOR);
        this.setCursor(c);
        
        URL url = getClass().getResource("/imagem/fundo.jpg");
        fundo = new ImageIcon(url).getImage();
    }

    public void setControleTela(ControladorTela controleTela) {
        this.controleTela = controleTela;
    }

    public void setTimerJogo(long timerJogo) {
        this.timerJogo = timerJogo;
    }

    public void setPontuacaoPartida(Pontuacao pontuacaoPartida) {
        this.pontuacaoPartida = pontuacaoPartida;
    }
    
    public void setFPS(ContadorFPS fps) {
        this.fps = fps;
    }
    
    public void setObjetos(ObjetosDoJogo obj) {
        this.objetos = obj;
    }
    
    public void setPontoPerdido(boolean pontoPerdido) {
        this.pontoPerdido = pontoPerdido;
    }

    public void criarBuffer() {
//        this.buffer = new BufferedImage(fundo.getWidth(this), fundo.getHeight(this), BufferedImage.TYPE_INT_ARGB);
        this.buffer = new BufferedImage(getWidth()+scrollSize, getHeight(), BufferedImage.TYPE_INT_ARGB);
    }
    
    public BufferedImage getBuffer() {
        return buffer;
    }

    
    @Override
    public void paint(Graphics tela) {
        Graphics2D g2d = (Graphics2D) buffer.getGraphics();

        int larguraTela = getWidth();
        int alturaTela = getHeight();
        int posicaoTela = (int)controleTela.getControlePosicaoTelaX();
        // Desenha tudo no buffer
        g2d.drawImage(fundo,0, 0, larguraTela+scrollSize,alturaTela, null);
        
        // Desenha viloes
        for(int i = 0; i < this.objetos.viloes.size(); i++) {
            Personagem vilao = this.objetos.viloes.get(i);
            vilao.draw(g2d);
        }
        
        //Desenha mocinhos
        for(int i = 0; i < this.objetos.mocinhos.size(); i++) {
            Personagem mocinho = this.objetos.mocinhos.get(i);
            mocinho.draw(g2d);
        }
        
        Image bala = this.objetos.mira.getBala();
        int larguraBala = bala.getWidth(this);
        int alturaBala = bala.getHeight(this);
        
        for(int i = 0; i < this.objetos.mira.getBalasPente(); i++) {
            g2d.drawImage(bala, ((larguraTela - 50)- posicaoTela) - (i*larguraBala) ,
                    (alturaTela-alturaBala-10),null);
        }
        this.objetos.mira.draw(g2d);
        
        // Ajusta os itens: Tempo, pontuação e FPS, no canto superior direito da tela
        this.fps.setX( ((larguraTela-50) - posicaoTela) );
        this.fps.draw(g2d);
        g2d.drawString( ("Tempo: "+timerJogo/1000),((larguraTela-65) - posicaoTela), 40);
        g2d.drawString("Pontos: "+pontuacaoPartida.getPontos(), (larguraTela-75)-posicaoTela, 60);

        
        // Pinta a tela de vermelho a cada ponto que usuário perde
        if( pontoPerdido ) {
            g2d.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
            pontoPerdido = false;
        }
        
        // Desenha o buffer na tela
        tela.drawImage(buffer,posicaoTela, 0, null);    
    }
    
    // Classe criada simplesmente para o usuário poder sair do jogo
    // a qualquer momento através da tecla esc
    private class ControladorTeclado extends KeyAdapter {

        /**
         * Sair do jogo
         * @param ke Tecla tipada que disparou o evento
         */
        @Override
        public void keyReleased(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
        
    }
    
}
