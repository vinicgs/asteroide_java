import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Nave {

    private int x, y;
    private int largura = 40, altura = 40;
    private int velocidade = 10;
    private BufferedImage imagem;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("imagens/nave.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // atualiza a posicao da nave
    public void atualizar() {
        mover();

    }
    // retorna um retangulo que representa os limites da nave
    public boolean colisao(Asteroide asteroide) {
        Rectangle retanguloNave = new Rectangle(x, y, largura, altura);
        Rectangle retanguloAsteroide = new Rectangle(asteroide.getX(), asteroide.getY(), asteroide.getLargura(), asteroide.getAltura());

        return retanguloNave.intersects(retanguloAsteroide);
    }

    public void desenhar(Graphics2D g2d) {
        g2d.drawImage(imagem, x, y, largura, altura, null);
    }
    // limita a movimentacao da nave dentro da tela
    public void mover() {
        if (x < 0) {
            x = 0;
        }
        if (x > 780 - largura) {
            x = 780 - largura;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 550 - altura) {
            y = 550 - altura;
        }
    }
    public void moverEsquerda(int i) {
        x -= velocidade;
    }

    public void moverDireita(int i) {
        x += velocidade;
    }

    public void moverCima(int i) {
        y -= velocidade;

    }

    public void moverBaixo(int i) {
        y += velocidade;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}