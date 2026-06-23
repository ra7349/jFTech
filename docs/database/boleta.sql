-- Tablas para registrar boletas/facturas con sus servicios realizados y repuestos vendidos.
CREATE TABLE IF NOT EXISTS boleta (
    id_boleta SERIAL PRIMARY KEY,
    numero VARCHAR(30) NOT NULL UNIQUE,
    tipo_comprobante VARCHAR(20) NOT NULL,
    id_cliente INTEGER NOT NULL,
    dni_ruc VARCHAR(20),
    metodo_pago VARCHAR(40) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal NUMERIC(10, 2) NOT NULL DEFAULT 0,
    igv NUMERIC(10, 2) NOT NULL DEFAULT 0,
    total NUMERIC(10, 2) NOT NULL DEFAULT 0,
    estado VARCHAR(20) NOT NULL DEFAULT 'Pagado',

    CONSTRAINT fk_boleta_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE IF NOT EXISTS boleta_detalle (
    id_boleta_detalle SERIAL PRIMARY KEY,
    id_boleta INTEGER NOT NULL,
    tipo_item VARCHAR(20) NOT NULL,
    descripcion TEXT NOT NULL,
    cantidad INTEGER NOT NULL DEFAULT 1,
    precio_unitario NUMERIC(10, 2) NOT NULL DEFAULT 0,
    importe NUMERIC(10, 2) NOT NULL DEFAULT 0,
    id_cliente_servicio INTEGER,
    id_repuesto INTEGER,

    CONSTRAINT fk_boleta_detalle_boleta
        FOREIGN KEY (id_boleta) REFERENCES boleta(id_boleta) ON DELETE CASCADE,
    CONSTRAINT fk_boleta_detalle_cliente_servicio
        FOREIGN KEY (id_cliente_servicio) REFERENCES cliente_servicio(id_cliente_servicio),
    CONSTRAINT fk_boleta_detalle_repuesto
        FOREIGN KEY (id_repuesto) REFERENCES repuesto(id_repuesto)
);

CREATE INDEX IF NOT EXISTS idx_boleta_cliente ON boleta(id_cliente);
CREATE INDEX IF NOT EXISTS idx_boleta_fecha ON boleta(fecha);
CREATE INDEX IF NOT EXISTS idx_boleta_detalle_boleta ON boleta_detalle(id_boleta);
