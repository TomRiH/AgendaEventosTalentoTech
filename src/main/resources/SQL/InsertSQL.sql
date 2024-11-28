USE `proyecto_eventos`;

-- Insert data into `rol`
INSERT INTO proyecto_eventos.rol (`nombre`) VALUES
('Administrador'),
('Organizador'),
('Usuario'),
('Colaborador'),
('Visitante');

-- Insert data into `permiso`
INSERT INTO proyecto_eventos.permiso (`nombre`, `descripcion`) VALUES
('Crear evento', 'Permite crear nuevos eventos'),
('Modificar evento', 'Permite modificar eventos existentes'),
('Eliminar evento', 'Permite eliminar eventos'),
('Ver usuarios', 'Permite ver información de usuarios'),
('Gestionar permisos', 'Permite gestionar permisos de roles');

-- Insert data into `rol_permiso`
INSERT INTO proyecto_eventos.rol_permiso (`id_rol`, `id_permiso`) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2),
(3, 4),
(4, 1), (4, 4);

-- Insert data into `departamento`
INSERT INTO proyecto_eventos.departamento (`codigo`, `nombre`) VALUES
('DEP-001', 'Antioquia'),
('DEP-002', 'Cundinamarca'),
('DEP-003', 'Valle del Cauca'),
('DEP-004', 'Santander'),
('DEP-005', 'Bolívar');

-- Insert data into `municipio`
INSERT INTO proyecto_eventos.municipio (`codigo`, `nombre`, `id_departamento`) VALUES
('MUN-001', 'Medellín', 1),
('MUN-002', 'Bogotá', 2),
('MUN-003', 'Cali', 3),
('MUN-004', 'Bucaramanga', 4),
('MUN-005', 'Cartagena', 5);

-- Insert data into `usuario`
INSERT INTO proyecto_eventos.usuario (`cedula`, `nombre`, `apellido`, `telefono`, `email`, `contraseña`, `id_municipio`, `id_rol`) VALUES
('1234567890', 'Juan', 'Pérez', '3001234567', 'juan.perez@example.com', 'password123', 1, 1),
('2345678901', 'María', 'Gómez', '3012345678', 'maria.gomez@example.com', 'password123', 2, 2),
('3456789012', 'Carlos', 'López', '3023456789', 'carlos.lopez@example.com', 'password123', 3, 3),
('4567890123', 'Ana', 'Ramírez', '3034567890', 'ana.ramirez@example.com', 'password123', 4, 4),
('5678901234', 'Luis', 'Martínez', '3045678901', 'luis.martinez@example.com', 'password123', 5, 5);

-- Insert data into `categoria`
INSERT INTO proyecto_eventos.categoria (`nombre`, `descripcion`) VALUES
('Tecnología', 'Eventos relacionados con tecnología'),
('Educación', 'Conferencias educativas y talleres'),
('Salud', 'Eventos relacionados con bienestar y salud'),
('Arte', 'Exposiciones y actividades artísticas'),
('Deportes', 'Competencias y actividades deportivas');

-- Insert data into `emprendimiento`
INSERT INTO proyecto_eventos.emprendimiento (`codigo`, `nombre`, `id_municipio`, `id_categoria`) VALUES
('EMP-001', 'Startup Medellín', 1, 1),
('EMP-002', 'Academia Bogotá', 2, 2),
('EMP-003', 'Cali Fitness', 3, 3),
('EMP-004', 'Arte Bucaramanga', 4, 4),
('EMP-005', 'Deportes Cartagena', 5, 5);

-- Insert data into `evento`
INSERT INTO proyecto_eventos.evento (`nombre`, `fecha`, `id_municipio`, `direccion`, `descripcion`) VALUES
('Conferencia Tech', '2024-11-10 09:00:00', 1, 'Auditorio Medellín', 'Evento tecnológico en Medellín'),
('Taller de Educación', '2024-11-15 14:00:00', 2, 'Centro Cultural Bogotá', 'Taller educativo'),
('Maratón Fitness', '2024-12-01 07:00:00', 3, 'Parque Cali', 'Competencia de fitness'),
('Exposición de Arte', '2024-12-05 10:00:00', 4, 'Galería Bucaramanga', 'Exposición de obras artísticas'),
('Torneo de Fútbol', '2024-12-20 15:00:00', 5, 'Estadio Cartagena', 'Competencia de fútbol local');

-- Insert data into `usuario_emprendimiento`
INSERT INTO proyecto_eventos.usuario_emprendimiento (`id_usuario`, `id_emprendimiento`) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- Insert data into `evento_emprendimiento`
INSERT INTO proyecto_eventos.evento_emprendimiento (`id_evento`, `id_emprendimiento`) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);
