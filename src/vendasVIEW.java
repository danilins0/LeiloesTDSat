
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de Vendas: exibe apenas os produtos com status "Vendido".
 */
public class vendasVIEW extends JFrame {

    private JTable tabelaVendas;

    public vendasVIEW() {
        montarTela();
        listarVendas();
    }

    private void montarTela() {
        setTitle("Produtos Vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(560, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Produtos Vendidos", SwingConstants.CENTER);
        titulo.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Valor", "Status"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaVendas = new JTable(model);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        painelTabela.add(new JScrollPane(tabelaVendas), BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel();
        rodape.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        rodape.add(btnVoltar);

        add(titulo, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
    }

    private void listarVendas() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setRowCount(0);

            ArrayList<ProdutosDTO> vendidos = produtosdao.listarProdutosVendidos();

            for (int i = 0; i < vendidos.size(); i++) {
                model.addRow(new Object[]{
                    vendidos.get(i).getId(),
                    vendidos.get(i).getNome(),
                    vendidos.get(i).getValor(),
                    vendidos.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar vendas: " + e.getMessage());
        }
    }
}
