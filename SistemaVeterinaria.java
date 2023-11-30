
package TrabajoIntegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

 public class SistemaVeterinaria {
     
    public static void main(String[] args) { 
// Pregunta 1
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Que animal desea atender?");
        String especie = scanner.nextLine();
       
          
// Pregunta 2
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("¿Cuantos años tiene su " + especie + "?");
        int edad = scanner1.nextInt();

// Pregunta 3 + opciones        
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("¿Cual es el motivo de su consulta " + especie + "?\n");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add(" Fiebre");
        opciones.add(" Vomitos");
        opciones.add(" Sin apetito");
        opciones.add(" Se queja de alguna molestia");
        opciones.add(" Vacunacion");
        System.out.println("Selecciona una opción:\n");

        for (int i = 0; i < opciones.size(); i++) 
        {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        Scanner scanner3 = new Scanner(System.in);
        int sintomas = scanner3.nextInt();

        if (sintomas >= 1 && sintomas <= opciones.size()) {
            String opcionSeleccionada = opciones.get(sintomas - 1);
            System.out.println("Seleccionó la opción de: " + opcionSeleccionada +"\n");
        } 
        else 
        {
          System.out.println("Selección no válida. Por favor, elige una opción válida.");
        }
        String opcionSeleccionada = opciones.get(sintomas-1);

// Pregunta 4   
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("¿Es la primera vez que viene por este problema?");
        String PrimeraVez = scanner4.nextLine();
// Pregunta 5 
        Scanner scanner5 = new Scanner(System.in);
        System.out.println("\n¿Como se llama su " + especie + "?");
        String nombre = scanner5.nextLine();
        
// Ficha Tecnica 
        List<String> fichaTecnica = new ArrayList<>();
        {
          fichaTecnica.add("Animal: " + especie);
          fichaTecnica.add("Edad: " + edad);
          fichaTecnica.add("Consulta: " + opcionSeleccionada);
          fichaTecnica.add("Primera vez: " + PrimeraVez);
          fichaTecnica.add("Nombre: " + nombre);
        }
          System.out.print("\nVeterinaria Happy pet(Ficha del paciente)\n");
          for (int i = 0; i < fichaTecnica.size(); i++) 
        {
          System.out.println((i + 1) + ". " + fichaTecnica.get(i));
        }
// Datos de conexion       
        String url = "jdbc:mysql://localhost:3306/conexion";
        String usuario = "root";
        String contraseña = "geemabe2023-2";
          
//Conexión a la base de datos
        try 
        (
          Connection connection = DriverManager.getConnection(url, usuario, contraseña);
          PreparedStatement statement = connection.prepareStatement("INSERT INTO pacientes ( especie, edad, nombre) VALUES (?, ?, ?)")           
        ) 
        {
          statement.setString(1, especie);
          statement.setInt(2,edad);
          statement.setString(3, nombre);

          int filasAfectadas = statement.executeUpdate();
          System.out.println("\nRegistro de paciente guardado");
        } 
          catch (SQLException e) 
        {
          e.printStackTrace();
        }
         
    }   
        
}
 