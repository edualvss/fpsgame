/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author Eduardo
 */
public class ControleSprites {
    
    
    public static final int[] NUMERO_IMAGENS_SPRITE_VILOES = {6,4,4,6,4,4,6,6,4,6};
    public static final int[] NUMERO_IMAGENS_SPRITE_MOCINHOS =  {4,6,10,5,14,6,8,6,5,6};
    
    private static String escolherSprite(int i) {
        if(i < 0 || i > 9) {
            return null;
        } else {
            return ("sprite"+i+".png");
        }
    }
    
    /**
     * Retorna uma string com a referência de local do sprite desejado
     * @param mocinho Verdadeiro se for para imagem de mocinho falso caso contrário
     * @param frente Verdadeire se for para imagem de sprite de frente e falso caso for de costas
     * @param sprite Número do sprite desejado
     * @return String contendo a localização do sprite desejado
     */
    public static String escolherSprite(boolean mocinho,boolean frente, int sprite) {
        String op = escolherSprite(sprite);
        if( op == null ) {
            return null;
        }
        String spriteEscolhido = "/imagem/sprites/";
        if( mocinho ) {
            spriteEscolhido += "mocinhos/";
        } else {
            spriteEscolhido += "viloes/";
        }
        if( frente ) {
            spriteEscolhido += "frente/";
        } else {
            spriteEscolhido += "costa/";
        }
        spriteEscolhido += op;
        
        return spriteEscolhido;
    }
    
}
