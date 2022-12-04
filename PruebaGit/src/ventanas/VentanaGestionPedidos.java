package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import clases.*;
import ventanas.*;

public class VentanaGestionPedidos extends JFrame {
	protected VideoClub datos;
	protected DefaultListModel<Pedido> modeloPedidos;
	protected JList<Pedido> listaPedidos;
	protected JButton botonAnyadir;
	protected JButton botonEditar;
	protected JButton botonBorrar;
	protected VentanaPedido ventanaPedido;

	public VentanaGestionPedidos(VideoClub datos) {
		this.datos = datos;

		Container cp = this.getContentPane();

		modeloPedidos = new DefaultListModel<Pedido>();
		for (Pedido pedido : datos.getPedidos()) {
			modeloPedidos.addElement(pedido);
		}
		listaPedidos = new JList<Pedido>(modeloPedidos);
		listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPedidos = new JScrollPane(listaPedidos);
		cp.add(scrollPedidos, BorderLayout.CENTER);

		JPanel abajo = new JPanel();

		botonAnyadir = new JButton("Nuevo pedido");
		botonEditar = new JButton("Editar pedido");
		botonBorrar = new JButton("Borrar pedido");

		botonAnyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pedido nuevo = new Pedido();
				modeloPedidos.addElement(nuevo);
				datos.anyadirPedido(nuevo);
				new VentanaPedido(nuevo, datos, false, VentanaGestionPedidos.this);
			}
		});

		botonEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pedido seleccionado = listaPedidos.getSelectedValue();

				if (seleccionado != null) {
					new VentanaPedido(seleccionado, datos, true, VentanaGestionPedidos.this);
				} else {
					JOptionPane.showMessageDialog(null, "No hay ningún pedido seleccionado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		botonBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pedido seleccionado = listaPedidos.getSelectedValue();

				if (seleccionado != null) {
					modeloPedidos.removeElement(seleccionado);
					datos.borrarPedido(seleccionado);
				} else {
					JOptionPane.showMessageDialog(null, "No hay ningún pedido seleccionado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		abajo.add(botonAnyadir);
		abajo.add(botonEditar);
		abajo.add(botonBorrar);
		cp.add(abajo, BorderLayout.SOUTH);

		this.setTitle("Gestion de pedidos");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
	}
}
