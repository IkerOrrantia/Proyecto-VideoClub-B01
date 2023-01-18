package ventanas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaAdmin extends JFrame{
	// Creamos los componentes de la ventana
    private JLabel lblTitle = new JLabel("Panel de administrador");
    private JButton btnUsers = new JButton("Gestionar usuarios");
    private JButton btnProducts = new JButton("Gestionar productos");
    private JButton btnOrders = new JButton("Gestionar pedidos");
    private JButton btnLogout = new JButton("Cerrar sesi�n");
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin ventanainiciosesion = new VentanaAdmin();
					ventanainiciosesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public VentanaAdmin() throws SQLException{
        // Establecemos el t�tulo de la ventana
        setTitle("Administrador");
        // Establecemos el tama�o de la ventana
        setBounds(650, 350, 600, 300); // (Posicion x, Posicion Y, Anchura, Altura)
        
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Establecemos el layout de la ventana
        setLayout(new BorderLayout());

        // Creamos un panel para colocar los botones en la parte inferior de la ventana
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        // A�adimos los botones al panel
        panelButtons.add(btnUsers);
        panelButtons.add(btnProducts);
        panelButtons.add(btnOrders);
        panelButtons.add(btnLogout);

        // A�adimos los componentes a la ventana
        add(lblTitle, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.SOUTH);

        // Establecemos la acci�n para el bot�n de cerrar sesi�n
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana y vuelve a la pantalla de inicio de sesi�n
                dispose();
                new VentanaInicioSesion().setVisible(true);
            }
        });

        // Establecemos la acci�n para los dem�s botones (en este caso simplemente mostramos un mensaje)
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaGestionUsuarios ventana;
				try {
					ventana = new VentanaGestionUsuarios();
					ventana.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
        });
        btnProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaGestionProductos ventana = new VentanaGestionProductos();
            	ventana.setVisible(true);
            }
        });
        btnOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	VentanaGestionPedidos ventana = new VentanaGestionPedidos();
            	ventana.setVisible(true);
            }
        });
    }
}
