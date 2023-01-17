package ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import bd.BD;
import clases.Cuenta;

public class VentanaGestionUsuarios extends JFrame {

	private JTable tablaUsuarios;
	private DefaultTableModel modeloTabla;
	private JButton btnEliminar;
	private List<Cuenta> listaUsuarios;
	private BD bd;
	private Cuenta cuentaEliminar;
	// FALTA TRAER LOS DATOS DE USUARIOS DE LA BASE DE DATOS
	public VentanaGestionUsuarios() throws SQLException{
		// Configura la ventana
		setTitle("Ventana Gestión de Usuarios");
		setBounds(525, 250, 900, 600); // (Posicion x, Posicion Y, Anchura, Altura)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Crea el modelo de la tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Usuario");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Apellidos");
		modeloTabla.addColumn("DNI");
		modeloTabla.addColumn("Correo");
		modeloTabla.addColumn("Telefono");
		modeloTabla.addColumn("Direccion");
		modeloTabla.addColumn("Conexion");
		modeloTabla.addColumn("Peliculas Alquiladas");

		// Crea la tabla
		tablaUsuarios = new JTable(modeloTabla);

		// Agrega la tabla a un scroll pane y lo agrega a la ventana
		JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
		add(scrollPane);
		
		JPanel panelInferior = new JPanel();
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new EliminarUsuarioListener());
		panelInferior.add(btnEliminar);
		add(panelInferior, BorderLayout.SOUTH);
		
		
		JButton botonVolver = new JButton("Volver");
		panelInferior.add(botonVolver);
		add(panelInferior, BorderLayout.SOUTH);
		
		// Carga los datos de la base de datos en la tabla

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
		
		setVisible(true);
		bd = new BD();
		try {
			bd.connect();
			listaUsuarios = bd.importarCuentaFromDataBase();
			for (Cuenta c : listaUsuarios) {
				modeloTabla.addRow(new Object[] {
						c.getId(),
						c.getUsuario(),
						c.getNombre(),
						c.getApellidos(),
						c.getDNI(),
						c.getCorreo(),
						c.getTelefono(),
						c.getDireccion(),
						c.getConexion(),
						c.getPeliculasAlquiladas()
				});
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Agrega cada fila de resultados al modelo de la tabla


	}




	private class EliminarUsuarioListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Obtiene la fila seleccionada en la tabla
			int filaSeleccionada = tablaUsuarios.getSelectedRow();

			// Verifica que una fila haya sido seleccionada
			if (filaSeleccionada != -1) {
				// Obtiene el ID del usuario seleccionado
				for (Cuenta c : listaUsuarios) {
					String usuario = (String) tablaUsuarios.getValueAt(filaSeleccionada, 1);
					if (c.getUsuario() == usuario) {
						cuentaEliminar = c;
					}
				}
				

				// Elimina el usuario de la base de datos
				try {
					eliminarUsuarioBD(cuentaEliminar);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Elimina la fila seleccionada del modelo de la tabla
				modeloTabla.removeRow(filaSeleccionada);
			} else {
				// Muestra un mensaje de error si no se ha seleccionado ninguna fila
				JOptionPane.showMessageDialog(VentanaGestionUsuarios.this, "Por favor seleccione un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		private void eliminarUsuarioBD(Cuenta cuenta) throws SQLException, ClassNotFoundException{
			// Inicia la conexión con la base de datos
			
			try {
				bd = new BD();
				bd.connect();
				bd.borrarCuentaExportada(cuenta);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}


		
	}
}