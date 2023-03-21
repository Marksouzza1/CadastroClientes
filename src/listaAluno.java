import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class listaAluno extends JFrame{
    private JTable tblListarAlunos;
    private JButton btnExcluir;
    private JPanel pnlListarAluno;


    final String URL = "jdbc:mysql://localhost:3306/cadaluno";
    final String USER = "root";
    final String PASSWORD = "root99";
    final String INSERIR = "INSERT INTO aluno(nome, matricula) VALUES (?,?)";
    final String CONSULTA = "select * from aluno";
    final String EXCLUIR = "DELETE FROM aluno WHERE matricula =?";




    public listaAluno(){
        AddListener();
        iniciarComponentes();

    }


    public void iniciarComponentes() {
        setTitle("Cadastro de alunos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pnlListarAluno);
        setVisible(true);
    }

    public void AddListener(){

        DefaultTableModel alunos = new DefaultTableModel();
        alunos.addColumn("nome");
        alunos.addColumn("matricula");
        tblListarAlunos.setModel(alunos);

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = null;
            stmt = connection.createStatement();

            ResultSet rs = null;

            rs = stmt.executeQuery(CONSULTA);

            while (rs.next()){
                Object[] row = new Object[2];
                    row[0] = rs.getObject(1);
                    row[1] = rs.getObject(2);
                    alunos.addRow(row);

            }

        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    public static void main(String[] args) {
        listaAluno list = new listaAluno();
    }


}