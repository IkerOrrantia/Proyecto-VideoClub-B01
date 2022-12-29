package ventanas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.BD;
import clases.*;
import ventanas.*;
import means.*;

import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;

public class VentanaRegistro extends JFrame {
	private static Logger logger = Logger.getLogger(VentanaRegistro.class.getName());
	private BD BD;
	public ArrayList<Cliente> clientes;

//	 public static void main(String[] args) {
//	 Registro reg = new Registro();
//	 }

	public VentanaRegistro() {
		
		
	
		// Creamos un nuevo frame y definimos su tamaño, posicion, nombre y cuando ha de cerrarse

		setBounds(100, 100, 220, 524); // (Posicion x, Posicion Y, Anchura, Altura)

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setLocationRelativeTo(null);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setTitle("Registro de Usuario");

		//Creamos Todos los Paneles, botones, etc que queremos anyadirle
		JPanel panel_arriba = new JPanel(new FlowLayout());
		panel_arriba.setBackground(Color.DARK_GRAY);

		JPanel panel_centro = new JPanel(new FlowLayout());

		JPanel panel_centro1 = new JPanel(new FlowLayout());
		JPanel panel_centro2 = new JPanel(new FlowLayout());
		JPanel panel_centro3 = new JPanel(new FlowLayout());
		JPanel panel_centro4 = new JPanel(new FlowLayout());
		JPanel panel_centro5 = new JPanel(new FlowLayout());
		JPanel panel_centro6 = new JPanel(new FlowLayout());
		JPanel panel_centro7 = new JPanel(new FlowLayout());
		JPanel panel_centro8 = new JPanel(new FlowLayout());


		JPanel panel_abajo = new JPanel(new FlowLayout());

		JLabel Cabezera = new JLabel("Registro de Usuario: ");
		Cabezera.setForeground(Color.WHITE);	

		JLabel Usuario = new JLabel("Nombre de Usuario: ");
		
		JTextField TextUsuario = new JTextField(15);	
		
		JLabel Nombre = new JLabel("Nombre: ");

		JTextField  TextNombre = new JTextField(20);

		JLabel Apellidos = new JLabel("Apellidos: ");

		JLabel DNI = new JLabel("DNI: ");

		JTextField  TextDNI = new JTextField(20);

		JLabel Correo = new JLabel("Correo: ");

		JTextField  TextCorreo = new JTextField(20);

		JTextField  TextApellidos = new JTextField(20);
		
		JLabel Contrasenya = new JLabel("Contrasenya: ");

		JPasswordField  TextContrasenya = new JPasswordField(20);;
		
		JLabel Direccion = new JLabel("Direccion: ");

		JTextField  TextDireccion = new JTextField(20);
		
		JLabel Telefono = new JLabel("Telefono: ");

		JTextField  TextTelefono = new JTextField(20);
		
		JLabel Espacio = new JLabel(" ");
		
		JButton Registrarse = new JButton("Registrarse");
		
		JButton Atras = new JButton("Atras");
		
		Registrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BD = new BD();
				
				try {
					BD.connect();
					
				}catch (SQLException | ClassNotFoundException e1) {
					logger.log(Level.SEVERE, "Error al conectarse a la base de datos", e1);
				}
			      
				// TODO Auto-generated method stub
				//Variables
				String usuario= TextUsuario.getText();
				String nombre= TextNombre.getText();
				String apellidos= TextApellidos.getText();
				String dni= TextDNI.getText();
				String correo= TextCorreo.getText();
				String direccion= TextDireccion.getText();
				int telefono= Integer.parseInt(TextTelefono.getText());
				String contrasenya= String.valueOf(TextContrasenya.getPassword());
				String conexion = "Desconectado";
				int peliculasAlquiladas = 0;
				int rol = 2;
					//Si aca en esas terminaciones el email
					if (TextCorreo.getText().contains("@gmail.com") || TextCorreo.getText().contains("@yahoo.es") ||  TextCorreo.getText().contains("@hotmail.com") || TextCorreo.getText().contains("@opendeusto.es") || TextCorreo.getText().contains("@deusto.es")) {
						
						Cuenta cuenta = new Cuenta(0,usuario,nombre,apellidos,dni,correo,contrasenya,telefono,direccion, conexion, peliculasAlquiladas, rol);
						Cliente cliente = new Cliente(0, usuario, 0);
						 try{
							BD.exportCuentaToDataBase(cuenta);
							BD.exportarClienteToDataBase(cliente);
							BD.disconnect();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						VentanaInicioSesion ventanainiciosesion = new VentanaInicioSesion();
						//Para que aparezca
						ventanainiciosesion.setVisible(true);
						//Para que deje de verse
						setVisible(false);
						
					}
					else {
						//Mostrar un mensaje 
						JOptionPane.showMessageDialog(null, "Email no valido", "Error", 0, null);
					}
			}
		});
		
		Atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaInicioSesion ventanainiciosesion = new VentanaInicioSesion();
				ventanainiciosesion.setVisible(true);
				setVisible(false);
			}
		});
		
		
		// Se anyaden botones y textos a su correspondiente panel && Snyadimos cada panel a su correspondiente posicion en el frame

				panel_arriba.add(Cabezera);
				add(panel_arriba,BorderLayout.NORTH);
				panel_centro1.add(Usuario);
				add(panel_centro1,BorderLayout.WEST);
				add(TextUsuario);
				panel_centro2.add(Nombre);
				add(panel_centro2,BorderLayout.WEST);
				add(TextNombre);
				panel_centro3.add(Apellidos);
				add(panel_centro3,BorderLayout.WEST);
				add(TextApellidos);
				panel_centro4.add(DNI);
				add(panel_centro4,BorderLayout.WEST);
				add(TextDNI);
				panel_centro5.add(Correo);
				add(panel_centro5,BorderLayout.WEST);
				add(TextCorreo);
				panel_centro6.add(Contrasenya);
				add(panel_centro6,BorderLayout.WEST);
				add(TextContrasenya);
				panel_centro7.add(Direccion);
				add(panel_centro7,BorderLayout.WEST);
				add(TextDireccion);
				panel_centro8.add(Telefono);
				add(panel_centro8,BorderLayout.WEST);
				add(TextTelefono);
				add(Espacio);
				panel_abajo.add(Atras);
				panel_abajo.add(Registrarse);
				add(panel_abajo);
			
				
				
				// Estabelecemos el frame compacto y no maximizable
				setResizable(false);
				pack();
				setVisible(false);
		
	}

}
