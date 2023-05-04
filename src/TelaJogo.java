import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class TelaJogo extends JPanel implements ActionListener {

    private Nave nave;
    private final ArrayList<Asteroide> asteroides;
    private final ArrayList<Tiro> tiros;
    private final Timer timer; // Timer para atualizar a tela
    private int pontuacao;
    private boolean jogoAtivo;

    public TelaJogo() {
        // Configuração do painel
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        // Configuração dos objetos do jogo
        nave = new Nave(400, 500); // nave na posição (400, 500)
        asteroides = new ArrayList<>();
        tiros = new ArrayList<>();
        timer = new Timer(20,  this);
        pontuacao = 0;
        jogoAtivo = true;

        addKeyListener(new KeyAdapter() {
            // Movimentação da nave  e disparo de tiros
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> tiros.add(new Tiro(nave.getX() + 10, nave.getY(), 15));
                    case KeyEvent.VK_LEFT -> nave.moverEsquerda(-15);
                    case KeyEvent.VK_RIGHT -> nave.moverDireita(15);
                    case KeyEvent.VK_UP -> nave.moverCima(-15);
                    case KeyEvent.VK_DOWN -> nave.moverBaixo(15);
                }

            }
            @Override
            public void keyTyped(KeyEvent e) {
                // Pausa o jogo
                if (e.getKeyChar() == 'p') {
                    if (timer.isRunning()) {
                        timer.stop();
                        JPanel painel = new JPanel();
                        painel.add(new JLabel("Pausado"));
                        JOptionPane.showMessageDialog(null, painel, "Pausado", JOptionPane.PLAIN_MESSAGE);
                        timer.start();

                    } else {
                        timer.start();
                    }
                }
            }


        });

        timer.start();// Inicia o timer para atualizar a tela do jogo
    }

    public TelaJogo(Nave nave, Asteroide asteroide) {
        // Chamando o construtor padrão
        this();
        this.nave = nave;
        asteroides.add(asteroide);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoAtivo) {
            // Atualiza a posição da nave
            nave.mover();

            Iterator<Asteroide> astIterator = asteroides.iterator();
            while (astIterator.hasNext()) {
                // Atualiza a posição dos asteroides
                Asteroide asteroide = astIterator.next();
                asteroide.mover();
                if (nave.colisao(asteroide)) {
                    jogoAtivo = false;
                }
                Iterator<Tiro> tiroIterator = tiros.iterator();
                //hasNext(): returns true if there are more elements in the collection.
                while (tiroIterator.hasNext()) {
                    // Verifica se o tiro colidiu com o asteroide
                    Tiro tiro = tiroIterator.next(); //next(): returns the next element in the collection and advances the iterator.
                    if (tiro.colisao(asteroide)) {
                        pontuacao += 10;
                        tiroIterator.remove(); //remove(): removes the last element returned by the iterator from the collection.
                        astIterator.remove();
                        break;
                    }
                }
            }

            Iterator<Tiro> tiroIterator = tiros.iterator();
            while (tiroIterator.hasNext()) {
                // Atualiza a posição dos tiros
                Tiro tiro = tiroIterator.next();
                tiro.mover();
            }

            if (Math.random() < 0.02) {
                // Adiciona um novo asteroide
                asteroides.add(new Asteroide((int) (Math.random() * 800), -50 - (int) (Math.random() * 300), 20, 5, Color.RED));
            }

            repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        // Chamando o método paintComponent da classe pai (JPanel)
        super.paintComponent(g);
        // Desenha a nave
        nave.desenhar((Graphics2D) g);

        // Desenha os asteroides
        for (Asteroide asteroide : asteroides) {
            asteroide.desenhar(g);
        }
        // Desenha os tiros
        for (Tiro tiro : tiros) {
            tiro.desenhar(g);
        }
        // Desenha a pontuação
        g.setColor(Color.WHITE);
        g.drawString("Pontuação: " + pontuacao, 20, 20);

        // Desenha a mensagem de fim de jogo
        if (!jogoAtivo) {
            g.setColor(Color.WHITE);
            g.drawString("Fim de jogo!", 400, 300);

        }

    }

}