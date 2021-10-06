/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jogo.*;
import tela.Tela;

/**
 *
 * @author Eduardo
 */
public class Controlador {
    
    private Tela tela;
    private ControladorTela controleTela;
    
    private ObjetosDoJogo objetos;

    private ControladorMouse controleMouse;

    private ContadorFPS fps;

    private AcumuladorTempo timerJogo;
    
    private Pontuacao pontuacaoPartida;
    
    private URL urlImagem;
    private Image imagemObjetos;
    
    private final int BALAS_PENTE = 6;
    
    // Localizaçãos dos sons
    private String somTiro = "/sons/tiro.wav";
    private String somFundo = "/sons/trilha.wav";
    private String somRecarregarArma = "/sons/recarregar_arma.wav";
    
    public Controlador(Tela tela) {
        this.tela = tela;
        this.tela.criarBuffer();

        // Cria controlador do mouse
        controleMouse = new ControladorMouse();
        this.tela.addMouseListener(controleMouse);
        this.tela.addMouseMotionListener(controleMouse);

        this.objetos = new ObjetosDoJogo();
        this.tela.setObjetos(objetos);
        
        this.timerJogo = new AcumuladorTempo(0);
        this.timerJogo.setTempoAcumulado(60000);
        
        this.pontuacaoPartida = new Pontuacao();
        this.tela.setPontuacaoPartida(pontuacaoPartida);
        
        Sprite sprite;
        // Criação Mira
        urlImagem = getClass().getResource("/imagem/mira.png");
        imagemObjetos = new ImageIcon(urlImagem).getImage();
        sprite = new Sprite(imagemObjetos, 1, imagemObjetos.getWidth(tela), imagemObjetos.getHeight(tela), 1);
        urlImagem = getClass().getResource("/imagem/bullet.png");
        imagemObjetos = new ImageIcon(urlImagem).getImage();
        Mira mira = new Mira(sprite,20,BALAS_PENTE,imagemObjetos);
        mira.setX(mira.getX()- (mira.getSprite().getLarguraQuadros()/ 2) );
        mira.setY(mira.getY()- (mira.getSprite().getAlturaQuadros() / 2) );
        this.objetos.mira = mira;

        // Reproduz a trilha do jogo
        GerenciadorSom.tocarSom(somFundo, true);
        
        this.controleTela = new ControladorTela(tela,tela.getBuffer(),controleMouse);
        this.tela.setControleTela(controleTela);
        
        // Criação contador FPS
        fps = new ContadorFPS();
        fps.setX(750); // Posicão eixo x
        fps.setY(20);  // Posição eixo y
        this.tela.setFPS(fps);
        
        this.tela.setVisible(true);
    }
    
    public void runGame() {
        try {
            // Instrução abaixo feita por falta de menu
            JOptionPane.showMessageDialog(tela, "Mate os vilões.\nDica: Os vilões estão piscando");
            long tempoFrame = 0;
            while (true) {
                long tempoInicial = System.currentTimeMillis();
                processaEntrada(); 
                processaJogo(tempoFrame);
                tela.repaint();
                Thread.sleep(20);
                tempoFrame = System.currentTimeMillis() - tempoInicial;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tela,"Deu pane na bagaça: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void processaEntrada() {
        Sprite mira = this.objetos.mira.getSprite();
        
        this.objetos.mira.setX( (controleMouse.getX() - ( mira.getLarguraQuadros()/2))+(-controleTela.getControlePosicaoTelaX()) );
        this.objetos.mira.setY( controleMouse.getY() - ( mira.getAlturaQuadros() / 2) );
        
        boolean disparado = controleMouse.isDisparado();
        this.objetos.mira.setDisparo(disparado);
        controleMouse.setDisparado(false);
        
        boolean recarregado = controleMouse.isRecarregado();
        this.objetos.mira.setRecarregado(recarregado);
        controleMouse.setRecarregado(false);
        
        controleTela.movimentaTela();

    }
    
    private void processaJogo(long frame) {
        // Atualiza o FPS
        this.fps.update(frame);

        // Atualiza o timer do jogo
        this.timerJogo.update(-frame);
        this.tela.setTimerJogo(this.timerJogo.getTempoAcumulado());
        
        // Condição término do jogo através do timer // devido a falta de menu, não está organizado
        if(timerJogo.expirouRegressivo()) {
            JOptionPane.showMessageDialog(tela, "Tempo esgotado\nPontuação obtida: "+pontuacaoPartida.getPontos());
            System.exit(0);
        }
        
        // Executar Som e atualizar mira quando for feito disparo
        int balasPente = this.objetos.mira.getBalasPente();
        boolean tiroDisparado = this.objetos.mira.isDisparo();
        boolean armaRecarregada = this.objetos.mira.isRecarregado();
        // Processa a mira
        if( balasPente == 0 ) {
            if( armaRecarregada ) {
                GerenciadorSom.tocarSom(somRecarregarArma);
                this.objetos.mira.update();
            } else {
                tiroDisparado = false;
            }
        } else {
            if( tiroDisparado ) {
                GerenciadorSom.tocarSom(somTiro);
                this.objetos.mira.update();
            } else {
                armaRecarregada = false;
            }
        }

        
        // Condições pada adição de personagens na tela
        if( this.objetos.viloes.size() < 4 
                || (this.timerJogo.getTempoAcumulado() % 5000 == 0) ) {
            criarPersonagens();
        }
        
        gerenciarPersonagens( frame,tiroDisparado );
        
                
    }

    private void criarPersonagens() {

        double sorteioExtremidadeTela = Math.random();
        int spriteSorteado = (int)(Math.random() * 10);
        double velocidade = (Math.random() * 50 + 100);
        if( sorteioExtremidadeTela < 0.5 ) {
            // Cria vilao
            criarPersonagem(false,true ,spriteSorteado,velocidade);
            // Cria mocinho
            criarPersonagem(true ,false,spriteSorteado,velocidade);
        } else {
            // Cria vilao
            criarPersonagem(false,false,spriteSorteado,velocidade);
            // Cria mocinho
            criarPersonagem(true ,true ,spriteSorteado,velocidade);
        }
        
    }

    private void criarPersonagem(boolean mocinho,boolean frente,int spriteSorteado,double velocidade) {
        
        urlImagem = getClass().getResource( ControleSprites.escolherSprite(mocinho, frente, spriteSorteado) );
        imagemObjetos = new ImageIcon(urlImagem).getImage();
        Sprite sprite = null;
        if( mocinho ) {
            sprite = new Sprite(imagemObjetos, ControleSprites.NUMERO_IMAGENS_SPRITE_MOCINHOS[spriteSorteado],
                imagemObjetos.getWidth(tela) / ControleSprites.NUMERO_IMAGENS_SPRITE_MOCINHOS[spriteSorteado],
                imagemObjetos.getHeight(tela), (int)velocidade/10);
        } else {
            sprite = new Sprite(imagemObjetos, ControleSprites.NUMERO_IMAGENS_SPRITE_VILOES[spriteSorteado],
                imagemObjetos.getWidth(tela) / ControleSprites.NUMERO_IMAGENS_SPRITE_VILOES[spriteSorteado],
                imagemObjetos.getHeight(tela), (int)velocidade/10);
        }
        Personagem personagem = new Personagem(sprite, velocidade );
        
        if ( frente ) {
            // Começa da esquerda
            personagem.setAngulo(0);
            personagem.setX(1);
        } else {
            // Começa da direita
            personagem.setAngulo(180);
            personagem.setX(this.tela.getBuffer().getWidth()-1);            
        }

        personagem.setY( (Tela.POSICOES_DESENHO_TELA[spriteSorteado/2])-sprite.getAlturaQuadros() );
        // Adicionando o personagem criado na lista correta
        if( mocinho ) {
            this.objetos.mocinhos.add(personagem);
        } else {
            this.objetos.viloes.add(personagem);
        }
        
    }

    private void gerenciarPersonagens(long frame,boolean tiroDisparado) {
        // Verifica a colisão do tiro com os personagens
        for(int i = this.objetos.viloes.size() - 1; i >= 0; i--) {
            Personagem vilao = this.objetos.viloes.get(i);
            Personagem mocinho = this.objetos.mocinhos.get(i);
            if(mocinho.colidiuCom(vilao)) {
                this.pontuacaoPartida.update( Pontuacao.PONTO_PERDIDO_VILAO );
                this.objetos.mocinhos.remove(i);
                this.objetos.viloes.remove(i);
                this.tela.setPontoPerdido(true);
                break;
            }
            if( tiroDisparado ) {
                if( this.objetos.mira.colidiuCom(vilao) ) {
                    this.pontuacaoPartida.update( Pontuacao.PONTO_GANHO );
                    this.objetos.mocinhos.remove(i);
                    this.objetos.viloes.remove(i);
                    break;
                } else if( this.objetos.mira.colidiuCom(mocinho) ) {
                    this.pontuacaoPartida.update( Pontuacao.PONTO_PERDIDO_TIRO );
                    this.tela.setPontoPerdido(true);
                }
            }
            mocinho.update(frame);
            vilao.update(frame);
        }
    }
}
