package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;

public class VentanaGestionProductos extends JFrame implements ActionListener {

	private JButton botonPeli, botonSerie;
	private ImageIcon imageP, imageS;

	public VentanaGestionProductos() {
		setLayout(new FlowLayout());

		botonPeli = new JButton();
		add(botonPeli);
		botonPeli.setSize(400,400);
		imageP = new ImageIcon(getClass().getResource("git/Proyecto-VideoClub-B01/PruebaGit/img/peli.jpg")); // MIRAR PORQUE NO VA LA CARPETA IMG
		botonPeli.setIcon(imageP);
		botonPeli.addActionListener(this);

		botonSerie = new JButton();
		add(botonSerie);
		botonSerie.setSize(400,400);
		imageS = new ImageIcon(getClass().getResource("git/Proyecto-VideoClub-B01/PruebaGit/img/series.jpg")); // MIRAR PORQUE NO VA LA CARPETA IMG
		botonSerie.setIcon(imageS);
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
		gui.setTitle("VentanaGestionProductos");
		gui.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}