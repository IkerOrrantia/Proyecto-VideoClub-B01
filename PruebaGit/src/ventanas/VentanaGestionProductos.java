package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;

public class VentanaGestionProductos extends JFrame implements ActionListener {

	private JButton botonPeli, botonSerie;

	public VentanaGestionProductos() {
		setLayout(new FlowLayout());
        setBounds(800, 350, 300, 150); // (Posicion x, Posicion Y, Anchura, Altura)
		botonPeli = new JButton("Peliculas");
		add(botonPeli);
		botonPeli.setSize(400,400);
		botonPeli.addActionListener(this);

		botonSerie = new JButton("Series");
		add(botonSerie);
		botonSerie.setSize(400,400);
		botonSerie.addActionListener(this);

		JPanel panelInferior = new JPanel();
		JButton botonVolver = new JButton("Volver");
		panelInferior.add(botonVolver);

		botonPeli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaGestionPeliculas ventana;
				try {
					ventana = new VentanaGestionPeliculas();
					ventana.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		botonSerie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				VentanaGestionSeries ventana;
				try {
					ventana = new VentanaGestionSeries();
					ventana.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		botonVolver.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
				try {
					VentanaAdmin ventana = new VentanaAdmin(); 
					ventana.setVisible(true);
					setVisible(false); // Oculta la ventana actual
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       
		    }
		});
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints gbcP = new GridBagConstraints();
		gbcP.gridx = 0;
		gbcP.gridy = 0;
		gbcP.fill = GridBagConstraints.HORIZONTAL;
		gbcP.insets = new Insets(10, 10, 10, 10);
		gbcP.weightx = 1;
		gbcP.weighty = 1;
		gbcP.anchor = GridBagConstraints.CENTER;
		
		GridBagConstraints gbcS = new GridBagConstraints();
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		gbcS.fill = GridBagConstraints.HORIZONTAL;
		gbcS.insets = new Insets(10, 10, 10, 10);
		gbcS.weightx = 1;
		gbcS.weighty = 1;
		gbcS.anchor = GridBagConstraints.CENTER;
		
		panelCentral.add(botonPeli, gbcP);
		panelCentral.add(botonSerie, gbcS);
		add(panelCentral, BorderLayout.CENTER);
		
		add(panelInferior, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		VentanaGestionProductos gui = new VentanaGestionProductos();
		gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gui.setTitle("Ventana Gestion Productos");
		gui.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}