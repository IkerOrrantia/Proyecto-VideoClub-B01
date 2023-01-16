package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;

public class VentanaGestionProductos extends JFrame implements ActionListener {

	private JButton botonPeli, botonSerie;

	public VentanaGestionProductos() {
		setLayout(new FlowLayout());

		botonPeli = new JButton("Peliculas");
		add(botonPeli);
		botonPeli.setSize(400,400);
		botonPeli.addActionListener(this);

		botonSerie = new JButton("Series");
		add(botonSerie);
		botonSerie.setSize(400,400);
		botonSerie.addActionListener(this);


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

				VentanaGestionSeries ventana = new VentanaGestionSeries();
				ventana.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		VentanaGestionProductos gui = new VentanaGestionProductos();
		gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gui.pack();
		gui.setTitle("Ventana Gestion Productos");
		gui.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}