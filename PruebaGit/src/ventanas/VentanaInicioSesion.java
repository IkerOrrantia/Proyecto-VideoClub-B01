package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import javax.swing.*;

import bd.BD;
import clases.*;
import ventanas.*;
import means.*;

public class VentanaInicioSesion extends JFrame {
	private static Logger logger = Logger.getLogger(VentanaInicioSesion.class.getName());
	private BD BD;
	private List<Cuenta> cuentas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion ventanainiciosesion = new VentanaInicioSesion();
					ventanainiciosesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicioSesion() {
		ControladorDeTxt ctxt = new ControladorDeTxt();
		// Metemos el controlador en el Arraylist de clientes
		ArrayList<Cliente> clientes = ctxt.importarClientes();

		// Creamos un nuevo frame y definimos su tamaño, posicion, nombre y cuando ha de
		// cerrarse

		setBounds(100, 100, 220, 224); // (Posicion x, Posicion Y, Anchura, Altura)

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setLocationRelativeTo(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("Incio de Sesion");

		// Creamos Todos los Paneles, botones etc que queremos anyadirle

		JPanel panel_izquierda0 = new JPanel(new FlowLayout());
		panel_izquierda0.setBackground(Color.DARK_GRAY);

		JPanel panel_izquierda1 = new JPanel(new FlowLayout());

		JPanel panel_izquierda2 = new JPanel(new FlowLayout());

		JPanel panel_abajo = new JPanel(new FlowLayout());

		JPanel panel_abajo_centro = new JPanel(new FlowLayout());

		JLabel Cabezera = new JLabel("Incio de Sesion: ");
		Cabezera.setForeground(Color.WHITE);

		JLabel Usuario = new JLabel("Nombre de Usuario:");

		JTextField TextUsuario = new JTextField(8);

		JLabel Contrasenya = new JLabel("Contrasenya:");

		JPasswordField TextContrasenya = new JPasswordField(8); // Posible extra: Anyadir boton (Mostrar Contrasenya)
		// ????

		// JComboBox<> Guardar = new JComboBox<GuardarDispositivo>();

		// Anyadir Boton o Texto Clickable para rediriguir a la ventana registro en caso
		// de no contar con una cuenta

		JButton Registrarse = new JButton("Registrate");
		Registrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaRegistro reg = new VentanaRegistro();
				reg.setVisible(true);
				setVisible(false);
			}
		});
		JButton IniciarSesion = new JButton("Acceder"); // Posibles cambios: Entrar, Logg in, Iniciar.... Por decidir
		IniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BD = new BD();

				try {
					BD.connect();
					String usuario = TextUsuario.getText();
					String contrasenya = String.valueOf(TextContrasenya.getPassword());
					BD.loggin(usuario, contrasenya);

				} catch (SQLException | ClassNotFoundException e1) {
					logger.log(Level.SEVERE, "Error al conectarse a la base de datos", e1);
				}

				;

				boolean acceso = false;
				
				

				for (Cliente c : clientes) {

					// Compobar si la contraseña y usuario coinciden con lo q esta guardado en el
					// txt
//					if (c.getUsuario().equals(usuario) && c.getContrasenya().equals(myPass)
//							|| c.getCorreo().equals(usuario) && c.getContrasenya().equals(myPass)) {
						acceso = true;
						c.setConexion("En linea");

						// clientes.add(c);
						// ctxt.exportarClientes(clientes);

//					}
				}
				// Ventana de mensaje java
				// 0=Error 1=Informacion 2=warning 3=Question 4=Message
				// Asi es true !acceso seria false
				if (acceso) {
					JOptionPane.showMessageDialog(null, "Inicio de sesión válido.", "Confirmacion", 1, null);

					VentanaPrincipal ventanaprincipal = new VentanaPrincipal();
					ventanaprincipal.setVisible(true);
					setVisible(false);
				} else {

					JOptionPane.showMessageDialog(null, "No se encuentra el usuario o la contraseña es incorrecta.",
							"Error", 0, null);
				}
			}
		});

		// Se anyaden botones y textos a su correspondiente panel && anyadimos cada
		// panel a su correspondiente posicion en el frame
		panel_izquierda0.add(Cabezera);
		add(panel_izquierda0, BorderLayout.EAST);
		panel_izquierda1.add(Usuario);
		add(panel_izquierda1, BorderLayout.EAST);
		add(TextUsuario);
		panel_izquierda2.add(Contrasenya);
		add(panel_izquierda2, BorderLayout.EAST);
		add(TextContrasenya);
		panel_abajo_centro.add(IniciarSesion);
		panel_abajo_centro.add(Registrarse);
		add(panel_abajo, BorderLayout.SOUTH);
		panel_abajo.add(panel_abajo_centro, BorderLayout.CENTER);

		// Estabelecemos el frame compacto y no maximizable
		setResizable(false);
		pack();

	}
}
