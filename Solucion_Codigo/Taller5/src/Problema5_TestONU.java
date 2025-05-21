public class Problema5_TestONU{
    public static void main(String[] args) {

        String[] paisesInvolucrados = {"País A", "País B", "País C"};
        String[] paisesPrimerMundo = {"País A", "País D"};
        int totalPaises = 200; 

        ConflictoBase conflicto = new ConflictoBase("Conflicto X", paisesInvolucrados, "2023-01-01", "Conflicto por recursos", 3);

        EventoBase evento1 = new EventoBase("Batalla 1", "2023-02-01", "País A", "Batalla con armas nucleares", "batalla");
        EventoBase evento2 = new EventoBase("Tratado de paz", "2023-03-01", "País B", "Firma de tratado", "tratado de paz");
        EventoBase evento3 = new EventoBase("Batalla 2", "2023-04-01", "País C", "Batalla con 50% o más bajas", "batalla");

        conflicto.agregarEvento(evento1, 0);
        conflicto.agregarEvento(evento2, 1);
        conflicto.agregarEvento(evento3, 2);

        conflicto.evaluarEstado(totalPaises, paisesPrimerMundo);

        System.out.println(conflicto.toString());
    }
}

class EventoBase {
    private String nombre;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private String tipo;

    public EventoBase() {
    }

    public EventoBase(String nombre, String fecha, String ubicacion, String descripcion, String tipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getNombre() {
         return nombre; 
    }

    public void setNombre(String nombre) {
         this.nombre = nombre;
    }

    public String getFecha() { 
        return fecha; 
    }

    public void setFecha(String fecha) {
         this.fecha = fecha; 
    }

    public String getUbicacion() {
         return ubicacion; 
    }

    public void setUbicacion(String ubicacion) {
         this.ubicacion = ubicacion; 
    }

    public String getDescripcion() {
         return descripcion; 
    }

    public void setDescripcion(String descripcion) {
         this.descripcion = descripcion; 
    }

    public String getTipo() {
         return tipo; 
    }

    public void setTipo(String tipo) {
         this.tipo = tipo; 
    }

    @Override
    public String toString() {
        return "Evento{nombre='" + nombre + "', fecha='" + fecha + "', ubicacion='" + ubicacion +
               "', descripcion='" + descripcion + "', tipo='" + tipo + "'}";
    }
}

class ConflictoBase {
    private String nombre;
    private String[] paisesInvolucrados;
    private String fechaInicio;
    private String estadoActual;
    private String detalles;
    private EventoBase[] eventos;

    public ConflictoBase() {
    }
    
    public ConflictoBase(String nombre, String[] paisesInvolucrados, String fechaInicio, String detalles, int numEventos) {
        this.nombre = nombre;
        this.paisesInvolucrados = paisesInvolucrados;
        this.fechaInicio = fechaInicio;
        this.detalles = detalles;
        this.estadoActual = "Activo";
        this.eventos = new EventoBase[numEventos];
    }

    public void agregarEvento(EventoBase e, int index) {
        if (index >= 0 && index < eventos.length) {
            eventos[index] = e;
        }
    }

    public void evaluarEstado(int totalPaises, String[] paisesPrimerMundo) {
        int batallas = 0;
        boolean batallaPrimerMundoNuclear = false;
        boolean altaBajas = false;

        for (EventoBase e : eventos) {
            if (e != null && "batalla".equalsIgnoreCase(e.getTipo())) {
                batallas++;
                if (isPrimerMundo(e.getUbicacion(), paisesPrimerMundo) && e.getDescripcion().contains("nuclear")) {
                    batallaPrimerMundoNuclear = true;
                }
                if (e.getDescripcion().contains("50% o más bajas")) {
                    altaBajas = true;
                }
            }
        }

        double porcentajeBatallas = (double) batallas / totalPaises * 100;
        if (porcentajeBatallas > 50) {
            estadoActual = "Guerra mundial";
        } else if (porcentajeBatallas >= 30) {
            estadoActual = "Reunión urgente ONU";
        }

        if (batallaPrimerMundoNuclear) {
            estadoActual = "Guerra mundial";
        }

        if (altaBajas) {
            estadoActual = "Reunión urgente ONU";
        }
    }

    private boolean isPrimerMundo(String ubicacion, String[] paisesPrimerMundo) {
        for (String pais : paisesPrimerMundo) {
            if (ubicacion.contains(pais)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conflicto{nombre='").append(nombre).append("', paisesInvolucrados=");
        for (String pais : paisesInvolucrados) {
            sb.append(pais).append(", ");
        }
        sb.append("fechaInicio='").append(fechaInicio).append("', estadoActual='").append(estadoActual)
          .append("', detalles='").append(detalles).append("', eventos=[\n");
        for (EventoBase e : eventos) {
            if (e != null) {
                sb.append("  ").append(e.toString()).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}