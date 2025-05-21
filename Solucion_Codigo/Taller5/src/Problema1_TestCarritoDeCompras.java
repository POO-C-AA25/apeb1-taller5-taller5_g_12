public class Problema1_TestCarritoDeCompras {
    public static void main(String[] args) {

        ProductoBase[] tienda = {
            new ProductoBase("Laptop", 1000.0, 5),
            new ProductoBase("Smartphone", 500.0, 10)
        };

        CarritoDeComprasBase carrito = new CarritoDeComprasBase();

        System.out.println("Prueba 1: Compra de 2 Laptops");
        carrito.agregarProducto("Laptop", 2, tienda);
        System.out.println("Total: " + carrito.calcularTotal());
        System.out.println(carrito.realizarPago(2500.0, 1000.0, 0.10)); // Descuento del 10%
        System.out.println(carrito.mostrarDetalleCompra());
        System.out.println("Estado del carrito: " + carrito.toString());
        System.out.println("Stock actualizado: " + tienda[0].toString());

        System.out.println("\nPrueba 2: Compra de 3 Smartphones");
        CarritoDeComprasBase carrito2 = new CarritoDeComprasBase();
        carrito2.agregarProducto("Smartphone", 3, tienda);
        System.out.println("Total: " + carrito2.calcularTotal());
        System.out.println(carrito2.realizarPago(1500.0, 1000.0, 0.10));
        System.out.println(carrito2.mostrarDetalleCompra());
        System.out.println("Estado del carrito: " + carrito2.toString());
        System.out.println("Stock actualizado: " + tienda[1].toString());
    }
}

class ProductoBase {
    private String nombre;
    private double precio;
    private int cantidad;

    public ProductoBase() {
    }
    
    public ProductoBase(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{nombre='" + nombre + "', precio=" + precio + ", cantidad=" + cantidad + "}";
    }
}

class CarritoDeComprasBase {
    private ProductoBase producto;
    private int cantidadComprar;
    private double total;
        
    public CarritoDeComprasBase() {
        this.producto = null;
        this.cantidadComprar = 0;
        this.total = 0.0;
    }

    public void agregarProducto(String nombre, int cantidad, ProductoBase[] tienda) {
        for (ProductoBase p : tienda) {
            if (p.getNombre().equals(nombre)) {
                if (p.getCantidad() >= cantidad) {
                    this.producto = p;
                    this.cantidadComprar = cantidad;
                }
                return;
            }
        }
    }

    public double calcularTotal() {
        if (producto != null) {
            total = producto.getPrecio() * cantidadComprar;
            return total;
        }
        return 0.0;
    }

    public String realizarPago(double montoPagado, double montoPromocional, double descuento) {
        if (producto == null) {
            return "No hay producto en el carrito.";
        }
        double totalCalculado = calcularTotal();
        if (montoPagado >= totalCalculado) {
            if (montoPagado > montoPromocional) {
                total -= total * descuento;
                return "Descuento aplicado. Nuevo total: " + total + ". Pago realizado. Gracias por su compra.";
            }
            producto.setCantidad(producto.getCantidad() - cantidadComprar);
            return "Pago realizado. Gracias por su compra.";
        } else {
            return "Monto insuficiente. Faltan: " + (totalCalculado - montoPagado);
        }
    }

    public String mostrarDetalleCompra() {
        if (producto != null) {
            return "Detalle de la compra: " + producto.getNombre() + ", Cantidad: " + cantidadComprar;
        }
        return "No hay producto en el carrito.";
    }

    @Override
    public String toString() {
        if (producto != null) {
            return "Carrito{producto=" + producto.getNombre() + ", cantidadComprar=" + cantidadComprar + ", total=" + total + "}";
        }
        return "Carrito vacío";
    }
}