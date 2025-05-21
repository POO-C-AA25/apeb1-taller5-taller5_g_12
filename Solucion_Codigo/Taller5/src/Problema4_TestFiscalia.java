
public class Problema4_TestFiscalia {
    public static void main(String[] args) {

        CasoCorrupcionBase caso = new CasoCorrupcionBase("Caso Coimas", "2023-05-10", "Corrupción en contratos públicos", 3);

        PersonaImplicadaBase persona1 = new PersonaImplicadaBase("Carlos López", 50, "Funcionario", "acusado");
        persona1.setSentencia(0.5);
        persona1.colaborar();
        persona1.calcularFianza(2000000); 

        PersonaImplicadaBase persona2 = new PersonaImplicadaBase("María Ruiz", 35, "Contadora", "testigo");
        PersonaImplicadaBase persona3 = new PersonaImplicadaBase("Pedro Sánchez", 40, "Empresario", "acusado");
        persona3.setSentencia(2.0);

        caso.agregarPersona(persona1, 0);
        caso.agregarPersona(persona2, 1);
        caso.agregarPersona(persona3, 2);

        caso.setDiasTranscurridos(15); 
 
        System.out.println(caso.toString());
    }
}

class PersonaImplicadaBase {
    private String nombre;
    private int edad;
    private String ocupacion;
    private String nivelImplicacion;
    private boolean colabora;
    private double sentencia;
    private double fianza;

    public PersonaImplicadaBase() {
    }
    
    public PersonaImplicadaBase(String nombre, int edad, String ocupacion, String nivelImplicacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.colabora = false;
        this.sentencia = 0.0;
        this.fianza = 0.0;
    }

    public void colaborar() {
        if ("acusado".equalsIgnoreCase(nivelImplicacion)) {
            this.colabora = true;
        }
    }

    public void calcularFianza(double danoEconomico) {
        if ("acusado".equalsIgnoreCase(nivelImplicacion) && colabora && sentencia < 1.0) {
            this.fianza = danoEconomico * 0.5;
        }
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) {
         this.nombre = nombre; 
    }

    public int getEdad() {
         return edad; 
    }

    public void setEdad(int edad) {
         this.edad = edad; 
    }

    public String getOcupacion() {
         return ocupacion; 
    }

    public void setOcupacion(String ocupacion) {
         this.ocupacion = ocupacion; 
    }

    public String getNivelImplicacion() {
         return nivelImplicacion; 
    }

    public void setNivelImplicacion(String nivelImplicacion) {
         this.nivelImplicacion = nivelImplicacion; 
    }

    public boolean isColabora() {
         return colabora; 
    }

    public void setColabora(boolean colabora) { 
        this.colabora = colabora; 
    }

    public double getSentencia() {
         return sentencia; 
    }

    public void setSentencia(double sentencia) {
         this.sentencia = sentencia; 
    }

    public double getFianza() {
         return fianza; 
    }

    public void setFianza(double fianza) { 
        this.fianza = fianza; 
    }

    @Override
    public String toString() {
        return "PersonaImplicada{nombre='" + nombre + "', edad=" + edad + ", ocupacion='" + ocupacion +
               "', nivelImplicacion='" + nivelImplicacion + "', colabora=" + colabora +
               ", sentencia=" + sentencia + ", fianza=" + fianza + "}";
    }
}

class CasoCorrupcionBase {
    private String nombre;
    private String fechaInicio;
    private String estado;
    private String detalles;
    private PersonaImplicadaBase[] personasImplicadas;
    private int diasTranscurridos;

    public CasoCorrupcionBase() {
    }    

    public CasoCorrupcionBase(String nombre, String fechaInicio, String detalles, int numPersonas) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.detalles = detalles;
        this.estado = "Iniciado";
        this.personasImplicadas = new PersonaImplicadaBase[numPersonas];
        this.diasTranscurridos = 0;
    }

    public void agregarPersona(PersonaImplicadaBase p, int index) {
        if (index >= 0 && index < personasImplicadas.length) {
            personasImplicadas[index] = p;
        }
    }

    public void setDiasTranscurridos(int dias) {
        this.diasTranscurridos = dias;
        actualizarEstado();
    }

    public void actualizarEstado() {
        if (diasTranscurridos > 14) {
            this.estado = "Urgente";
        } else if (diasTranscurridos > 7) {
            this.estado = "Alerta";
        } else {
            this.estado = "Iniciado";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CasoCorrupcion{nombre='").append(nombre).append("', fechaInicio='").append(fechaInicio)
          .append("', estado='").append(estado).append("', detalles='").append(detalles)
          .append("', personasImplicadas=[\n");
        for (PersonaImplicadaBase p : personasImplicadas) {
            if (p != null) {
                sb.append("  ").append(p.toString()).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}