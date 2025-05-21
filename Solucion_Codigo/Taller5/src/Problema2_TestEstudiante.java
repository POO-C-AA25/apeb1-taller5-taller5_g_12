public class Problema2_TestEstudiante {
    public static void main(String[] args) {
        Estudiante estudiante1 = new Estudiante("Juan Pérez", 20);
        Materia materia1 = new Materia("Matemáticas", 3.0, 3.0, 2.5);
        estudiante1.vincularMateria(materia1);
        System.out.println(estudiante1);
        System.out.println("Resultado: " + estudiante1.verificarAprobacion());

        Estudiante estudiante2 = new Estudiante("Ana Gómez", 22);
        Materia materia2 = new Materia("Física", 2.0, 2.0, 1.5);
        estudiante2.vincularMateria(materia2);
        System.out.println(estudiante2);
        System.out.println("Resultado: " + estudiante2.verificarAprobacion());
    }
}

class Materia {
    private String nombre;
    private double acd;
    private double ape;
    private double aa;

    public Materia() {
    }

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public double getAcd() {
        return acd;
    }

    public double getApe() {
        return ape;
    }

    public double getAa() {
        return aa;
    }

    @Override
    public String toString() {
        return "Materia{nombre='" + nombre + "', acd=" + acd + ", ape=" + ape + ", aa=" + aa + "}";
    }
}

class Estudiante {
    private String nombre;
    private int edad;
    private Materia materia;

    public Estudiante() {
    }

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
    }

    public void vincularMateria(Materia materia) {
        this.materia = materia;
    }

    public String verificarAprobacion() {
        if (materia == null) return "No hay materia vinculada";
        double total = materia.getAcd() + materia.getApe() + materia.getAa();
        return total >= 7.0 ? "Aprobado con " + total : "No aprobado con " + total + ". Requiere recuperación";
    }

    @Override
    public String toString() {
        String materiaInfo = (materia != null) ? materia.toString() : "Sin materia";
        return "Estudiante{nombre='" + nombre + "', edad=" + edad + ", " + materiaInfo + "}";
    }
}