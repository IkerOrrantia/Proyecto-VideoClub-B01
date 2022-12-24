package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin extends JFrame{
	// Creamos los componentes de la ventana
    private JLabel lblTitle = new JLabel("Panel de administrador");
    private JButton btnUsers = new JButton("Gestionar usuarios");
    private JButton btnProducts = new JButton("Gestionar productos");
    private JButton btnOrders = new JButton("Gestionar pedidos");
    private JButton btnLogout = new JButton("Cerrar sesi�n");
    
    public VentanaAdmin() {
        // Establecemos el t�tulo de la ventana
        setTitle("Administrador");
        // Establecemos el tama�o de la ventana
        setSize(400, 300);
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
                JOptionPane.showMessageDialog(VentanaAdmin.this, "Gesti�n de usuarios");
            }
        });
        btnProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaAdmin.this, "Gesti�n de productos");
            }
        });
        btnOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaAdmin.this, "Gesti�n de pedidos");
            }
        });
    }
}
