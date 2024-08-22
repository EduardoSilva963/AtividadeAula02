import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame quadro = new JFrame("Atividade02");
        quadro.setContentPane(new Atividade02().FrmPrincipal);
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.pack();
        quadro.setVisible(true);
    }
}