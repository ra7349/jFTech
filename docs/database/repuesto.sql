-- Tabla para gestionar el catálogo de repuestos desde RepuestosView.
CREATE TABLE IF NOT EXISTS repuesto (
    id_repuesto SERIAL PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(120) NOT NULL,
    marca VARCHAR(80),
    categoria VARCHAR(80) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_compra NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (precio_compra >= 0),
    precio_venta NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (precio_venta >= 0),
    estado VARCHAR(20) NOT NULL DEFAULT 'Activo'
);

CREATE INDEX IF NOT EXISTS idx_repuesto_codigo ON repuesto(codigo);
CREATE INDEX IF NOT EXISTS idx_repuesto_estado ON repuesto(estado);
