import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cadAluno  extends JFrame{
    private JPanel telaCadAluno;
    private JTextField txtNome;
    private JTextField txtMatricula;
    private JButton btnInserir;
    private JButton btnLimpar;
    private JButton btnLista;
    private JLabel lblInsiraDados;
    private JLabel lbllogo;
    private JLabel lblNome;
    private JLabel lblMatricula;


    final String URL = "jdbc:mysql://localhost:3306/cadaluno";
    final String USER = "root";
    final String PASSWORD = "root99";
    final String INSERIR = "INSERT INTO aluno(nome, matricula) VALUES (?,?)";
    final String CONSULTA = "selec * from aluno";

    public cadAluno(){
        iniciarComponentes();
        AddListeners();
        Conecta();

    }

    public void AddListeners(){
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                txtMatricula.setText("");
            }
        });
    }


    public void Conecta(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtInserir;
            stmtInserir = connection.prepareStatement(INSERIR);

            btnInserir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = txtNome.getText();
                    String matriculaStr = txtMatricula.getText();

                    try {
                        int matricula = Integer.parseInt(matriculaStr);
                        stmtInserir.setString(1, nome );
                        stmtInserir.setInt(2,matricula);
                        stmtInserir.executeUpdate();
                        System.out.println("Dados inseridos!");
                        JOptionPane.showMessageDialog(btnInserir,"Dados Inseridos");
                        txtNome.setText("");
                        txtMatricula.setText("");
                    } catch (NumberFormatException ex) {
                        System.out.println("A matricula informada não é um número!");

                    }catch (Exception ex){
                            System.out.println("Erro ao inserir dados no banco");
                    }
                }
            });

        }catch (Exception ex){
            System.out.println("Erro ao conectar ao banco de dados!");
        }
    }

    public void iniciarComponentes(){
        setTitle("Cadastro de alunos");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(telaCadAluno);
        setVisible(true);
    }
    public static void main(String[] args) {
        cadAluno cadAluno = new cadAluno();
    }

}

