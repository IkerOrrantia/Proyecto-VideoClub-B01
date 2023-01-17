package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class VentanaGestionSeries extends JFrame {
    private JTable tablaSeries;
	private DefaultTableModel modeloTabla;
    public VentanaGestionSeries() {
        // Configuraci�n de la ventana
        this.setTitle("Gesti�n de Series");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creaci�n de la tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Director");
		modeloTabla.addColumn("ID Genero");
		modeloTabla.addColumn("Anyo");
		modeloTabla.addColumn("Precio");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Imagen");

        // A�adir la tabla a un panel con scroll
        JPanel panelTabla = new JPanel();
        panelTabla.add(new JScrollPane(tablaSeries));

        // A�adir el panel a la ventana
        this.add(panelTabla, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        VentanaGestionSeries vgs = new VentanaGestionSeries();
        vgs.setVisible(true);
    }
}
