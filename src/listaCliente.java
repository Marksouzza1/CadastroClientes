import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;

public class listaCliente extends JFrame{
    private JTable tblListarCliente;
    private JButton btnVoltar;
    private JPanel pnlListarCliente;


    final String URL = "jdbc:mysql://localhost:3306/cadCliente";
    final String USER = "root";
    final String PASSWORD = "root99";
    final String INSERIR = "INSERT INTO clientes(codCliente, nome, sobrenome, profissao, telefone) VALUES (?,?,?,?,?)";
    final String CONSULTA = "select * from clientes";




    public listaCliente(){
        AddListener();
        iniciarComponentes();

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadClientes cadCli = new cadClientes();
            }
        });
    }


    public void iniciarComponentes() {
        setTitle("Cadastro de Clientes");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pnlListarCliente);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void AddListener(){

        DefaultTableModel clientes = new DefaultTableModel();
        clientes.addColumn("codCliente");
        clientes.addColumn("nome");
        clientes.addColumn("sobrenome");
        clientes.addColumn("profissao");
        clientes.addColumn("telefone");

        tblListarCliente.setModel(clientes);

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = null;
            stmt = connection.createStatement();

            ResultSet rs = null;

            rs = stmt.executeQuery(CONSULTA);

            while (rs.next()){
                Object[] row = new Object[5];
                    row[0] = rs.getObject(1);
                    row[1] = rs.getObject(2);
                    row[2] = rs.getObject(3);
                    row[3] = rs.getObject(4);
                    row[4] = rs.getObject(5);
                    clientes.addRow(row);

            }

        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    public static void main(String[] args) {
        listaCliente list = new listaCliente();
    }


}