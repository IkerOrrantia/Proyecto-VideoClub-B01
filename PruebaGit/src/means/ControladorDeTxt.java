package means;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import clases.*;
import ventanas.*;

public class ControladorDeTxt {
	//Variables
	public ArrayList<Cliente>importarClientes(){
		
		//Archivo
		File archivo = null;
		//Abrir archivo en modo lectura
		FileReader fr = null;
		//Leer las lineas
        BufferedReader br = null;
        //MAPA
        ArrayList<Cliente>clientes = new ArrayList<Cliente>();
        //RUTA DEL ARCHIVO
        String ruta = "data/Clientes.txt";
        try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			//Salta de linea
			String linea = br.readLine();
			while (linea != null) {
				
				//Array de palabras pq devuelve eso
				//Argumento es q quieres q lo separe
				//Pasar de int a string para q no ponga ""
				String[] palabras = linea.split(";");
				String usuario = palabras[0];
				String nombre = palabras[1];
				String apellidos = palabras[2];
				String dni = palabras[3];
				String correo = palabras[4];
				String contrasenya = palabras[5];
				int telefono = Integer.parseInt(palabras[6]);
				String direccion = palabras[7];				
				String conexion = palabras[8];
			    int peliculas = Integer.parseInt(palabras[9]);
			    
			    
//				Cliente cliente = new Cliente(usuario, nombre, apellidos, dni, correo, contrasenya, telefono, direccion, conexion, peliculas);
//			    clientes.add(cliente);
			    
				linea = br.readLine();
			}
			
			//Para cerrar
			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	return clientes;
	}
	
	//Necesita array para meter la info
	public void exportarClientes(ArrayList<Cliente>clientes) {
		

		//RUTA
		String ruta = "data/Clientes.txt";
		
		File archivo = null;
		archivo = new File(ruta);
		
		try {
			FileWriter fw = new FileWriter(archivo);
			
			//get es para sacar info d algo
			for (Cliente c : clientes) {
				String usuario = c.getUsuario();
				String nombre = c.getNombre();
				String apellidos = c.getApellidos();
				String dni = c.getDNI();
				String correo = c.getCorreo();
				String contrasenya = c.getContrasenya();
				int telefono = c.getTelefono();
				String direccion = c.getDireccion();
				String conexion = "Deconectado";
				int peliculas = c.getPeliculasAlquiladas();
				
				fw.write(usuario+";"+nombre+";"+apellidos+";"+dni+";"+correo+";"+contrasenya+";"+telefono+";"+direccion+";"+conexion+";"+peliculas+"\n");
			}
			//Para cerrar
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public ArrayList<Pelicula>importarPeliculas(){

		
		//Archivo
		File archivo = null;
		//Abrir archivo en modo lectura
		FileReader fr = null;
		//Leer las lineas
        BufferedReader br = null;
        
        ArrayList<Pelicula>peliculas = new ArrayList<Pelicula>();
      
        String ruta = "data/Pelicuilas.db";
        try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			//Salta de linea
			String linea = br.readLine();
			while (linea != null) {
				
				//Array de palabras pq devuelve eso
				//Argumento es q quieres q lo separe
				String[] palabras = linea.split(",");
				int id = Integer.parseInt(palabras[0]);
				String nombre = palabras[1];
				String director = palabras[2];
				Genero genero = Genero.valueOf(palabras[3]);
			    int anyo = Integer.parseInt(palabras[4]);
			    double precio = Double.parseDouble(palabras[5]);
			    
//			    Pelicula pelicula= new Pelicula(id, nombre, director, genero,anyo, precio);
//			    peliculas.add(pelicula);
			    
				linea = br.readLine();
			}
			
			//Para cerrar
			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	return peliculas;
	}
}

