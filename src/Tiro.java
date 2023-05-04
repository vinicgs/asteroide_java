import java.awt.*;

public class Tiro {

    private final int x;
    private int y;
    private final int velocidade;

    // construtor do tiro
    public Tiro(int x, int y, int velocidade) {
        this.x = x;
        this.y = y;
        this.velocidade = 15;
    }

    public void mover() {
        this.y -= this.velocidade;
    }
    public void desenhar(Graphics g) {
        g.setColor(Color.YELLOW); // cor do tiro
        g.fillRect(this.x, this.y, 2, 10); // desenha o tiro
    }
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 2, 10);
    }

    // verifica se houve colisao entre o tiro e o asteroide
    public boolean colisao(Asteroide asteroide) {
        Rectangle retanguloTiro = this.getBounds();
        Rectangle retanguloAsteroide = asteroide.pegarLimites();
        // retorna true se houve colisao entre o tiro e o asteroide
        return retanguloTiro.intersects(retanguloAsteroide);
    }

    public int getY() {
        return y;
    }
}
