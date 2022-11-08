package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.security.DomainCombiner;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InicioSesion extends JFrame {
	
//	public static void main(String[] args) {
//		InicioSesion ini = new InicioSesion();
//	}

	public InicioSesion() {

		// Creamos un nuevo frame y definimos su tamaño, posicion, nombre y cuando ha de cerrarse

		setBounds(100, 100, 220, 224); // (Posicion x, Posicion Y, Anchura, Altura)

		getContentPane().setLayout(new BorderLayout());

		setLocationRelativeTo(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("Incio de Sesion");
		
		//Creamos Todos los Paneles, botones etc que queremos anyadirle
		JPanel panel_arriba = new JPanel(new FlowLayout());

		JPanel panel_centro = new JPanel(new FlowLayout());

		JPanel panel_abajo = new JPanel(new FlowLayout());
		
		JLabel Cabezera = new JLabel("Incio de Sesion: ");
		
		JLabel Usuario = new JLabel("Nombre de Usuario:");
		
		JTextField TextUsuario = new JTextField(12);		
		
		JLabel Contrasenya = new JLabel("Contrasenya:");
		
		JPasswordField  TextContrasenya = new JPasswordField(12);		//Posible extra: Anyadir boton (Mostrar Contrasenya) ????
		
		//Anyadir Boton o Texto Clickable para rediriguir a la ventana registro en caso de no contar con una cuenta
		
		JButton InciarSesion = new JButton("Acceder"); 			//Posibles cambios: Entrar, Logg in, Iniciar.... Por decidir (Cambio menor)
		
		// Se anyaden botones y textos a su correspondiente panel
		panel_arriba.add(Cabezera);
		panel_centro.add(Usuario);
		panel_centro.add(TextUsuario);
		panel_centro.add(Contrasenya);
		panel_centro.add(TextContrasenya);
		panel_abajo.add(InciarSesion);
		
		
		
		
		//anyadimos cada panel a su correspondiente posicion en el frame
		add(panel_abajo,BorderLayout.SOUTH);

		add(panel_centro,BorderLayout.CENTER);

		add(panel_arriba,BorderLayout.NORTH);
		
		//Estabelecemos el frame como visible
		pack();
		setVisible(true);
	}
}
