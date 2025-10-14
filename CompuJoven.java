
package compujoven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompuJoven {

    // Estos son los datos que vamos a usar pero solo para la clase Joven

    // Clase Joven 
    public static class Joven {
        private String nombre;
        private String documentoIdentidad;
        private int edad;
        private double peso;
        private String email;
        private String contrasena;
        private final String ciudad = "Cartagena";
        
    // Constructor
        public Joven(String nombre, String documentoIdentidad, int edad, double peso, String email, String contrasena) {
            this.nombre = nombre;
            this.documentoIdentidad = documentoIdentidad;
            this.edad = edad;
            this.peso = peso;
            this.email = email;
            this.contrasena = contrasena;
        }

        public String getNombre() { return nombre; }
        public String getDocumentoIdentidad() { return documentoIdentidad; }
        public int getEdad() { return edad; }
        public double getPeso() { return peso; }
        public String getEmail() { return email; }
        public String getContrasena() { return contrasena; }
        public String getCiudad() { return ciudad; }

    // Esto es lo que tiene que imprimir    
        @Override
        public String toString() {
            return "\n--- Perfil de Joven ---\n" +
                   "Nombre: " + nombre + "\n" +
                   "Documento ID: " + documentoIdentidad + "\n" +
                   "Edad: " + edad + " anios\n" +
                   "Peso: " + String.format("%.2f", peso) + " kg\n" +
                   "Ciudad: " + ciudad + "\n" +
                   "Email: " + email;
        }
    }

    // Clase Empresa con los datos que vamos a usar 
    public static class Empresa {
        private String nombre;
        private String nit;
        private String contacto;
        private String email;
        private String contrasena;

    // Constructor
        public Empresa(String nombre, String nit, String contacto, String email, String contrasena) {
            this.nombre = nombre;
            this.nit = nit;
            this.contacto = contacto;
            this.email = email;
            this.contrasena = contrasena;
        }

        public String getNombre() { return nombre; }
        public String getNit() { return nit; }
        public String getEmail() { return email; }
        public String getContrasena() { return contrasena; }
        
        @Override
        public String toString() {
            return "\n--- Informacion de la Empresa ---\n" +
                   "Nombre: " + nombre + "\n" +
                   "NIT: " + nit + "\n" +
                   "Contacto: " + contacto + "\n" +
                   "Email: " + email;
        }
    }

    // Clase Vacante con sus datos 
    public static class Vacante {
        private static int contadorVacantes = 0;
        private int id;
        private String titulo;
        private String descripcion;
        private String nombreEmpresa;
        private String requisitosEdad;
        
    // Constructor
        public Vacante(String titulo, String descripcion, String nombreEmpresa, String requisitosEdad) {
            this.id = ++contadorVacantes;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.nombreEmpresa = nombreEmpresa;
            this.requisitosEdad = requisitosEdad;
        }

        public int getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getNombreEmpresa() { return nombreEmpresa; }

        @Override
        public String toString() {
            return "ID Vacante: " + id + "\n" +
                   "Titulo: " + titulo + "\n" +
                   "Empresa: " + nombreEmpresa + "\n" +
                   "Requisitos de Edad: " + requisitosEdad + "\n" +
                   "Descripcion: " + descripcion + "\n" +
                   "---------------------------------";
        }
    }


    // Intento 4 de la logica de este programa (Empiezo a creer que el error soy yo)
    
    // Almacenamiento de datos
    private static List<Joven> listaJovenes = new ArrayList<>();
    private static List<Empresa> listaEmpresas = new ArrayList<>();
    private static List<Vacante> listaVacantes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    // Estado de la sesion
    private static Joven jovenLogueado = null;
    private static Empresa empresaLogueada = null;

    // Metodo Main
    public static void main(String[] args) {
        cargarDatosIniciales();
        
        System.out.println("==============================================");
        System.out.println("  Bienvenido al Portal de Empleos CompuJoven ");
        System.out.println("==============================================");

        while (true) {
            if (jovenLogueado != null) {
                menuJoven();
            } else if (empresaLogueada != null) {
                menuEmpresa();
            } else {
                menuPrincipal();
            }
        }
    }

    // Aqui pondre todos los Menus

    private static void menuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Registrarse (Joven o Empresa)");
        System.out.println("2. Iniciar Sesion");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
        
        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: menuRegistro(); break;
                case 2: iniciarSesion(); break;
                case 3:
                    System.out.println("Gracias por usar CompuJoven. Hasta pronto!");
                    System.out.println("SOMOS LA MONDAAA");
                    System.exit(0);
                    break;
                default: System.out.println("Opcion no es valida. Intente de nuevo :D");
            }
        } else {
            System.out.println("Entrada invalida. Debe ser un numero.");
            scanner.nextLine();
        }
    }

    private static void menuJoven() {
        System.out.println("\n--- PANEL DEL JOVEN (" + jovenLogueado.getNombre() + ") ---");
        System.out.println("1. Ver Vacantes Disponibles");
        System.out.println("2. Ver Mi Perfil");
        System.out.println("3. Aplicar a Vacante (Simulado)");
        System.out.println("4. Cerrar Sesion");
        System.out.print("Seleccione una opcion: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: verVacantes(); break;
                case 2: System.out.println(jovenLogueado); break;
                case 3: aplicarVacante(); break;
                case 4:
                    jovenLogueado = null;
                    System.out.println("Sesion cerrada correctamente.");
                    break;
                default: System.out.println("Opcion no valida.");
            }
        } else {
            System.out.println("Entrada invalida. Debe ser un numero.");
            scanner.nextLine();
        }
    }

    private static void menuEmpresa() {
        System.out.println("\n--- PANEL DE LA EMPRESA (" + empresaLogueada.getNombre() + ") ---");
        System.out.println("1. Publicar Nueva Vacante");
        System.out.println("2. Ver Mis Vacantes Publicadas");
        System.out.println("3. Cerrar Sesion");
        System.out.print("Seleccione una opcion: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: publicarVacante(); break;
                case 2: verMisVacantes(); break;
                case 3:
                    empresaLogueada = null;
                    System.out.println("Sesion cerrada correctamente.");
                    break;
                default: System.out.println("Opcion no valida.");
            }
        } else {
            System.out.println("Entrada invalida. Debe ser un numero.");
            scanner.nextLine();
        }
    }

    // Menu de registro y de login

    private static void menuRegistro() {
        System.out.println("\n--- REGISTRO ---");
        System.out.println("1. Registrar Joven");
        System.out.println("2. Registrar Empresa");
        System.out.print("Seleccione una opcion (1 o 2): ");
        
        if (scanner.hasNextInt()) {
            int tipo = scanner.nextInt();
            scanner.nextLine();
            
            if (tipo == 1) {
                registrarJoven();
            } else if (tipo == 2) {
                registrarEmpresa();
            } else {
                System.out.println("Opcion de registro no valida.");
            }
        } else {
            System.out.println("Entrada invalida.");
            scanner.nextLine();
        }
    }

    private static void registrarJoven() {
        System.out.println("\n--- REGISTRO DE JOVEN ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Documento de Identidad: ");
        String documento = scanner.nextLine();
        
        int edad = -1;
        while (edad < 16 || edad > 22) {
            try {
                System.out.print("Edad (16-22 anios): ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 16 || edad > 22) {
                    System.out.println("🚫 Edad fuera del rango permitido (16-22 anios).");
                }
            } catch (Exception e) {
                System.out.println("🚫 Entrada invalida para la edad.");
                scanner.nextLine();
            }
        }

        double peso = 0;
        while (peso <= 0) {
            try {
                System.out.print("Peso en kg (Requisito): ");
                peso = scanner.nextDouble();
                scanner.nextLine();
                if (peso <= 0) {
                    System.out.println("🚫 El peso debe ser un valor positivo.");
                }
            } catch (Exception e) {
                System.out.println("🚫 Entrada invalida para el peso.");
                scanner.nextLine();
            }
        }

        System.out.print("Email (sera su usuario): ");
        String email = scanner.nextLine();
        
        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();
        
        Joven nuevoJoven = new Joven(nombre, documento, edad, peso, email, contrasena);
        listaJovenes.add(nuevoJoven);
        System.out.println("✅ ¡Registro de Joven exitoso! Bienvenido a CompuJoven.");
    }
    
    private static void registrarEmpresa() {
        System.out.println("\n--- REGISTRO DE EMPRESA ---");
        System.out.print("Nombre de la Empresa: ");
        String nombre = scanner.nextLine();
        
        System.out.print("NIT: ");
        String nit = scanner.nextLine();
        
        System.out.print("Nombre del Contacto: ");
        String contacto = scanner.nextLine();

        System.out.print("Email (sera su usuario): ");
        String email = scanner.nextLine();
        
        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();

        Empresa nuevaEmpresa = new Empresa(nombre, nit, contacto, email, contrasena);
        listaEmpresas.add(nuevaEmpresa);
        System.out.println("✅ ¡Registro de Empresa exitoso! Ya puede publicar vacantes.");
    }
    
    private static void iniciarSesion() {
        System.out.println("\n--- INICIAR SESION ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();

        // 1. Buscar como Joven
        for (Joven j : listaJovenes) {
            if (j.getEmail().equals(email) && j.getContrasena().equals(contrasena)) {
                jovenLogueado = j;
                System.out.println("🎉 ¡Bienvenido, " + j.getNombre() + "! Has iniciado sesion como Joven.");
                return;
            }
        }
        
        // 2. Buscar como Empresa
        for (Empresa e : listaEmpresas) {
            if (e.getEmail().equals(email) && e.getContrasena().equals(contrasena)) {
                empresaLogueada = e;
                System.out.println("🏢 ¡Bienvenido, " + e.getNombre() + "! Has iniciado sesion como Empresa.");
                return;
            }
        }
        
        System.out.println("❌ Error: Email o contrasena incorrectos, o usuario no registrado.");
    }

    // Esto viene siendo la forma de logica que cumplen las vacantes (EMPRESA) ---

    private static void publicarVacante() {
        System.out.println("\n--- PUBLICAR NUEVA VACANTE ---");
        System.out.print("Titulo del Puesto: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Descripcion y Requisitos: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Requisitos de Edad (Ej: 18-20 anios, Flexible): ");
        String requisitosEdad = scanner.nextLine();

        // El nombre de la empresa se toma del usuario logueado
        String nombreEmpresa = empresaLogueada.getNombre();
        
        Vacante nuevaVacante = new Vacante(titulo, descripcion, nombreEmpresa, requisitosEdad);
        listaVacantes.add(nuevaVacante);
        System.out.println("✅ ¡Vacante publicada con exito! ID: " + nuevaVacante.getId());
    }
    
    private static void verMisVacantes() {
        System.out.println("\n--- MIS VACANTES PUBLICADAS (" + empresaLogueada.getNombre() + ") ---");
        int contador = 0;
        for (Vacante v : listaVacantes) {
            if (v.getNombreEmpresa().equals(empresaLogueada.getNombre())) {
                System.out.println(v);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("Aun no ha publicado vacantes.");
        }
    }

    // Lo mismo, logica de vacantes (JOVEN) 

    private static void verVacantes() {
        System.out.println("\n--- VACANTES DISPONIBLES EN CARTAGENA ---");
        if (listaVacantes.isEmpty()) {
            System.out.println("Actualmente no hay vacantes publicadas.");
            return;
        }
        for (Vacante v : listaVacantes) {
            System.out.println(v);
        }
    }

    private static void aplicarVacante() {
        if (listaVacantes.isEmpty()) {
            System.out.println("No hay vacantes para aplicar.");
            return;
        }
        verVacantes();
        
        System.out.print("\nIngrese el ID de la vacante a la que desea aplicar: ");
        if (scanner.hasNextInt()) {
            int idAplicar = scanner.nextInt();
            scanner.nextLine();

            Vacante vacanteSeleccionada = null;
            for (Vacante v : listaVacantes) {
                if (v.getId() == idAplicar) {
                    vacanteSeleccionada = v;
                    break;
                }
            }

            if (vacanteSeleccionada != null) {
                // Simulacion de como seria la postulacion para una vacante
                System.out.println("---------------------------------");
                System.out.println("⭐ Aplicando a: " + vacanteSeleccionada.getTitulo() + " (" + vacanteSeleccionada.getNombreEmpresa() + ")");
                System.out.println("Su perfil (Edad: " + jovenLogueado.getEdad() + ", Peso: " + String.format("%.2f", jovenLogueado.getPeso()) + "kg) ha sido enviado.");
                System.out.println("✅ ¡Aplicacion exitosa! La empresa recibira sus datos.");
                System.out.println("---------------------------------");
            } else {
                System.out.println("❌ ID de vacante no encontrado.");
            }

        } else {
            System.out.println("Entrada invalida. Debe ser un número.");
            scanner.nextLine();
        }
    }
    
    // Estos datos son para probar que funcionen las vacantes
    // pero pero se podrian dejar y solo hariamos 1 o 2 para mostrar como funciona la creacion a los profesores en la presentacion
    
    private static void cargarDatosIniciales() {
        // Joven de prueba
        listaJovenes.add(new Joven("Pedro Perez", "1001123456", 19, 70.5, "pedro@mail.com", "123"));
        listaJovenes.add(new Joven("Ana Torres", "1001987654", 17, 60.0, "ana@mail.com", "123"));
        
        // Empresa de prueba
        listaEmpresas.add(new Empresa("Hotel Cartagena SAS", "800100200-1", "Gerente RRHH", "hotel@mail.com", "456"));
        listaEmpresas.add(new Empresa("Logística Caribe LTDA", "900300400-5", "Director de Operaciones", "logistica@mail.com", "456"));
        
        // Vacantes de prueba
        listaVacantes.add(new Vacante("Auxiliar de Recepcion", "Atencion al cliente y manejo de reservas.", "Hotel Cartagena SAS", "18-22 años"));
        listaVacantes.add(new Vacante("Mensajero en Moto", "Entrega de documentos y paquetes en la ciudad.", "Logística Caribe LTDA", "16-20 años"));
    }
}
