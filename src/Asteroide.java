import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Asteroide {
    private int x, y; // coordenadas do asteroide
    private int raio; // raio do asteroide
    private int velocidade; // velocidade do asteroide
    private Color cor; // cor do asteroide

    private BufferedImage imagem; // imagem do asteroide

    public Asteroide(int x, int y, int raio, int velocidade, Color cor) {
        // coordenada x aleatoria entre 0 e 800 - raio do asteroide
        // 800 = largura da tela do jogo (pixels)
        // - largura do asteroide (pixels).
        this.x =   Math.abs((int) (Math.random() * (800 - raio * 2)) - raio);
        this.y =  y;
        this.raio = raio;
        this.velocidade = velocidade;
        this.cor = cor;
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("imagens/asteroide.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mover() {
        this.y += this.velocidade;
      }

      // desenha o asteroide
    public void desenhar(Graphics g2d) {
        g2d.drawImage(imagem, x, y, raio, raio, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return 2 * raio;
    }

    public int getAltura() {
        return 2 * raio;
    }

    public Rectangle pegarLimites() {
        return new Rectangle(x - raio, y - raio, 2 * raio, 2 * raio);
    }
    public void atualizar() {
        mover();
    }
}