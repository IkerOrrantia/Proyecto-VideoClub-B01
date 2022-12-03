package ventanas;

import java.util.ArrayList;
import java.util.Collections;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import clases.*;
import ventanas.*;


public class VentanaPedido extends JFrame {
	protected JTextField textoCliente;
	protected JComboBox<Producto> comboProductos;
	protected JButton botonAnyadirPelicula;
	protected DefaultListModel<Pagable> modeloPagables;
	protected JList<Pagable> listaPagables;
	protected JButton botonEliminarPagable;
	protected JButton botonGuardar;

	public VentanaPedido(Pedido pedido, VideoClub datos, boolean editar, VentanaGestionPedidos ventanaGestionPedidos) {
		Container cp = this.getContentPane();

		JPanel centro = new JPanel();
		centro.setLayout(new BorderLayout());

		JPanel centroArriba = new JPanel();
		centroArriba.setLayout(new GridLayout(1, 2));

		textoCliente = new JTextField(pedido.getCliente().toString());	
		centroArriba.add(new JLabel("Cliente: "));
		centroArriba.add(textoCliente);

		JPanel centroMedio = new JPanel();
		centroMedio.setLayout(new GridLayout(1, 2));

		JPanel centroMedioIzda = new JPanel();
		centroMedioIzda.setLayout(new BorderLayout());

		JPanel pagables = new JPanel();
		pagables.setLayout(new GridLayout(2,2));

		comboProductos = new JComboBox<Producto>();

		for (Producto producto: datos.getProductos()) {
			comboProductos.addItem(producto);
		}
		botonAnyadirPelicula = new JButton("Anyadir Pelicula");

		botonAnyadirPelicula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pagable seleccionado = (Pagable) comboProductos.getSelectedItem();			
				modeloPagables.addElement(seleccionado);
			}
		});

	

		pagables.add(comboProductos);
		pagables.add(botonAnyadirPelicula);

		centroMedioIzda.add(pagables, BorderLayout.NORTH);

		JPanel pagados = new JPanel();
		pagados.setLayout(new BorderLayout());

		modeloPagables = new DefaultListModel<Pagable>();

		for (Pagable pagable: pedido.getElementos()) {
			modeloPagables.addElement(pagable);
		}

		listaPagables = new JList(modeloPagables);
		listaPagables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPagables = new JScrollPane(listaPagables);
		botonEliminarPagable = new JButton("Eliminar");

		botonEliminarPagable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pagable seleccionado = listaPagables.getSelectedValue();			
				modeloPagables.removeElement(seleccionado);
			}
		});

		JPanel panelEliminar = new JPanel();

		panelEliminar.add(botonEliminarPagable);

		pagados.add(scrollPagables, BorderLayout.CENTER);
		pagados.add(panelEliminar, BorderLayout.SOUTH);

		centroMedio.add(centroMedioIzda);
		centroMedio.add(pagados);

		centro.add(centroArriba, BorderLayout.NORTH);
		centro.add(centroMedio, BorderLayout.CENTER);

		JPanel abajo = new JPanel();

		botonGuardar = new JButton("Guardar pedido");

		botonGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Guardamos los datos
				pedido.setCliente((textoCliente.getText()));
				pedido.setElementos(Collections.list(modeloPagables.elements()));
				pedido.setEstado(Estado.PREPARACION);

				// El pedido ha cambiado, repintamos la ventana de gestión
				ventanaGestionPedidos.repaint();

				// Cerramos la ventana
				dispose();
			}
		});

		abajo.add(botonGuardar);

		cp.add(centro, BorderLayout.CENTER);
		cp.add(abajo, BorderLayout.SOUTH);

		if (editar) {
			this.setTitle("Editar pedido");
		} else {
			this.setTitle("Nuevo pedido");
		}
		this.setSize(800, 400);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
	}
}
