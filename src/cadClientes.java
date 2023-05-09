import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cadClientes extends JFrame{
    private JPanel telaCadClient;
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JButton btnInserir;
    private JButton btnLimpar;
    private JButton btnLista;
    private JLabel lblInsiraDados;
    private JLabel lbllogo;
    private JLabel lblNome;
    private JLabel lblSobrenome;
    private JTextField txtCodCliente;
    private JLabel lblProfissão;
    private JTextField txtProfissao;
    private JLabel lblTelefone;
    private JTextField txtTelefone;
    private JLabel lblCodCliente;


    final String URL = "jdbc:mysql://localhost:3306/cadCliente";
    final String USER = "root";
    final String PASSWORD = "root99";
    final String INSERIR = "INSERT INTO clientes(codCliente, nome, sobrenome, profissao, telefone) VALUES (?,?,?,?,?)";
    final String CONSULTA = "selec * from clientes";

    public cadClientes(){
        iniciarComponentes();
        AddListeners();
        Conecta();

        btnLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCliente lista = new listaCliente();
            }
        });
    }

    public void AddListeners(){
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                txtSobrenome.setText("");
                txtCodCliente.setText("");
                txtProfissao.setText("");
                txtTelefone.setText("");
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

                    String codClienteStr = txtCodCliente.getText();
                    String nome = txtNome.getText();
                    String Sobrenome = txtSobrenome.getText();
                    String proficao = txtProfissao.getText();
                    String telefone = txtTelefone.getText();

                    try {
                        int codClient = Integer.parseInt(codClienteStr);
                        stmtInserir.setInt(1, codClient );
                        stmtInserir.setString(2,nome);
                        stmtInserir.setString(3, Sobrenome);
                        stmtInserir.setString(4, proficao);
                        stmtInserir.setString(5, telefone);
                        stmtInserir.executeUpdate();
                        System.out.println("Dados inseridos!");
                        JOptionPane.showMessageDialog(btnInserir,

                                "Codigo do Cliente"+codClient+"\n"+
                                "nome="+nome+"\n"+
                                "Sobrenome="+Sobrenome+"\n"+
                                 "Profissão="+proficao+"\n"+
                                        "Telefone="+telefone+"\n"
                        );

                        txtNome.setText("");
                        txtSobrenome.setText("");
                    } catch (NumberFormatException ex) {
                        System.out.println("dados invalidos");

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
        setTitle("Cadastro de Clientes");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(telaCadClient);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        cadClientes cadClientes = new cadClientes();
    }

}

