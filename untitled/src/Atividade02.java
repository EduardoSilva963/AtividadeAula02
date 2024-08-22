import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Atividade02 {
   public JPanel FrmPrincipal;
   public JLabel LblNome;
   public JLabel LblCPF;
   public JTextField TxtNome;
   public JTextField TxtCPF;
   public JRadioButton RdbMasculino;
   public JRadioButton RdbFeminino;
   public JFormattedTextField TxtDataNascimento;
   public JComboBox CmbEstadoCivil;
   public JTextField TxtProfissao;
   public JButton BtnCadastrar;
   public JLabel LblSexo;
   public JLabel LblDataDeNascimento;
   public JLabel LblEstadoCivil;
   public JLabel LblProfissao;

   private static final String CPF_REGEX = "^(?:\\d{3}\\.){2}\\d{3}-\\d{2}$";

    public Atividade02() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);

       BtnCadastrar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            String nome = TxtNome.getText();
            String cpf = TxtCPF.getText();
            String profissao = TxtProfissao.getText();
            String dataNascimentoStr = TxtDataNascimento.getText();
            String estadoCivil = (String) CmbEstadoCivil.getSelectedItem();
            String sexo = RdbMasculino.isSelected() ? "Masculino" : (RdbFeminino.isSelected() ? "Feminino" : "Não especificado");

            if (profissao.trim().isEmpty()) {
                   TxtProfissao.setText("Desempregado(a)");
            }

            if(nome.isEmpty()) {
                JOptionPane.showMessageDialog(FrmPrincipal,"Nome não pode estar vazio!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else if(CmbEstadoCivil.getSelectedItem().equals("<Selecione>")) {
                JOptionPane.showMessageDialog(FrmPrincipal,"Selecione o Estado Civil!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else if(!isCPFValido(cpf)) {
                JOptionPane.showMessageDialog(FrmPrincipal, "CPF inválido!", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else if(!isDateValid(dataNascimentoStr)) {
                JOptionPane.showMessageDialog(FrmPrincipal, "Data de nascimento inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else {
                // Calcula a idade
                LocalDate dataNascimento = parseDate(dataNascimentoStr);
                int idade = calculateAge(dataNascimento);

                // Prepara a mensagem com as informações
                String mensagem = String.format(
                        "Nome Completo: %s\nIdade: %d\nSexo: %s\nEstado Civil: %s\nProfissão: %s\n",
                        nome, idade, sexo, estadoCivil, profissao
                );

                // Adiciona a mensagem adicional para certas profissões
                if (profissao.equals("Engenheiro") || profissao.equals("Analista de Sistemas")) {
                    mensagem += "Vagas disponíveis na área";
                }

                // Exibe as informações
                JOptionPane.showMessageDialog(FrmPrincipal, mensagem, "Informações do Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
           }
       });
    }

    private boolean isDateValid(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isCPFValido(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }
        return true;
    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }

}
