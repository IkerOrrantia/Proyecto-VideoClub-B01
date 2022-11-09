package ventanas;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.BorderLayout;

public class Registro extends JFrame {

//	 public static void main(String[] args) {
//	 Registro reg = new Registro();
//	 }

	public Registro() {

		// Creamos un nuevo frame y definimos su tamaño, posicion, nombre y cuando ha de cerrarse

		setBounds(100, 100, 350, 470); // (Posicion x, Posicion Y, Anchura, Altura)

		getContentPane().setLayout(new BorderLayout());

		setLocationRelativeTo(null);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setTitle("Registro de Usuario");

		//Creamos Todos los Paneles, botones, etc que queremos anyadirle
		JPanel panel_arriba = new JPanel(new FlowLayout());
		
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

		JTextField  TextContrasenya = new JTextField(20);
		
		JLabel Direccion = new JLabel("Direccion: ");

		JTextField  TextDireccion = new JTextField(20);
		
		JLabel Telefono = new JLabel("Telefono: ");

		JTextField  TextTelefono = new JTextField(20);
		
		JButton Registrarse = new JButton("Registrarse");
		
		JButton Atras = new JButton("Atras");
		
		
		// Se anyaden botones y textos a su correspondiente panel
				panel_arriba.add(Cabezera);
				panel_centro1.add(Usuario);
				panel_centro1.add(TextUsuario);
				panel_centro2.add(Nombre);
				panel_centro2.add(TextNombre);
				panel_centro3.add(Apellidos);
				panel_centro3.add(TextApellidos);
				panel_centro4.add(DNI);
				panel_centro4.add(TextDNI);
				panel_centro5.add(Correo);
				panel_centro5.add(TextCorreo);
				panel_centro6.add(Contrasenya);
				panel_centro6.add(TextContrasenya);
				panel_centro7.add(Direccion);
				panel_centro7.add(TextDireccion);
				panel_centro8.add(Telefono);
				panel_centro8.add(TextTelefono);
				panel_abajo.add(Atras);
				panel_abajo.add(Registrarse);
				
			//Anyadimos todos los sub paneles a los paneles Principales
				panel_centro.add(panel_centro1);
				panel_centro.add(panel_centro2);
				panel_centro.add(panel_centro3);
				panel_centro.add(panel_centro4);
				panel_centro.add(panel_centro5);
				panel_centro.add(panel_centro6);
				panel_centro.add(panel_centro7);
				panel_centro.add(panel_centro8);
				
			//Snyadimos cada panel a su correspondiente posicion en el frame
				add(panel_abajo,BorderLayout.SOUTH);
				
				add(panel_centro,BorderLayout.CENTER);


				add(panel_arriba,BorderLayout.NORTH);
				
				//Estabelecemos el frame como visible
				pack();
				setVisible(true);
		
	}

}
