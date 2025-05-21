public class Problema3_TestEmpresa{
    public static void main(String[] args) {

        EmpresaBase empresa = new EmpresaBase("TechCorp", "123456789", "Av. Principal 123", 3);

        DepartamentoBase depto1 = new DepartamentoBase("Ventas", 15, 600000);
        DepartamentoBase depto2 = new DepartamentoBase("Producción", 25, 1200000);
        DepartamentoBase depto3 = new DepartamentoBase("Recursos Humanos", 8, 400000);

        empresa.agregarDepartamento(depto1, 0);
        empresa.agregarDepartamento(depto2, 1);
        empresa.agregarDepartamento(depto3, 2);

        System.out.println(empresa.toString());
    }
}

class DepartamentoBase {
    private String nombre;
    private int numEmpleados;
    private double produccionAnual;
    private String categoria;

    public DepartamentoBase(String nombre, int numEmpleados, double produccionAnual) {
        this.nombre = nombre;
        this.numEmpleados = numEmpleados;
        this.produccionAnual = produccionAnual;
        this.categoria = determinarCategoria();
    }

    public String determinarCategoria() {
        if (numEmpleados > 20 && produccionAnual > 1000000) {
            return "A";
        } else if (numEmpleados >= 20 && produccionAnual >= 1000000) {
            return "B";
        } else if (numEmpleados >= 10 && produccionAnual >= 500000) {
            return "C";
        } else {
            return "Sin categoría";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public double getProduccionAnual() {
        return produccionAnual;
    }

    public void setProduccionAnual(double produccionAnual) {
        this.produccionAnual = produccionAnual;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Departamento{nombre='" + nombre + "', numEmpleados=" + numEmpleados + 
               ", produccionAnual=" + produccionAnual + ", categoria='" + categoria + "'}";
    }
}

class EmpresaBase {
    private String nombre;
    private String ruc;
    private String direccion;
    private DepartamentoBase[] departamentos;

    public EmpresaBase() {
    }

    public EmpresaBase(String nombre, String ruc, String direccion, int numDeptos) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = new DepartamentoBase[numDeptos];
    }

    public void agregarDepartamento(DepartamentoBase d, int index) {
        if (index >= 0 && index < departamentos.length) {
            departamentos[index] = d;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa{nombre='").append(nombre).append("', ruc='").append(ruc)
          .append("', direccion='").append(direccion).append("', departamentos=[\n");
        for (DepartamentoBase d : departamentos) {
            if (d != null) {
                sb.append("  ").append(d.toString()).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}