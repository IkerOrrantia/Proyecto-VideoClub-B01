package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class VentanaGestionSeries extends JFrame {
    private JTable tablaSeries;
    private DefaultTableModel modeloDatos;

    public VentanaGestionSeries() {
        // Configuración de la ventana
        this.setTitle("Gestión de Series");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación de la tabla
        Vector<String> cabecera = new Vector<>(Arrays.asList("ID", "NOMBRE", "CREADOR", "TEMPORADAS", "ID GENERO", "PRECIO", "CANTIDAD", "DESCRIPCION"));
        this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
        this.tablaSeries = new JTable(modeloDatos);

        // Añadir la tabla a un panel con scroll
        JPanel panelTabla = new JPanel();
        panelTabla.add(new JScrollPane(tablaSeries));

        // Añadir el panel a la ventana
        this.add(panelTabla, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        VentanaGestionSeries vgs = new VentanaGestionSeries();
        vgs.setVisible(true);
    }
}
