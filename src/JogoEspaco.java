import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoEspaco extends JFrame implements ActionListener {

    private Nave nave;
    private Asteroide asteroide;

    public JogoEspaco() {
        // Configura a janela
        setTitle("Jogo do espaço");
        int larguraJanela = 800;
        int alturaJanela = 600;
        setSize(larguraJanela, alturaJanela);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        // Configura a nave
        nave = new Nave(larguraJanela / 2, alturaJanela - 100);
        // Configura o asteroide
        asteroide = new Asteroide( larguraJanela / 2, 100 + (int) (Math.random() * 100), 20, 2 , Color.RED);
        // Adiciona o painel do jogo à janela
        add(new TelaJogo(nave, asteroide ));

        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new JogoEspaco();
    }}