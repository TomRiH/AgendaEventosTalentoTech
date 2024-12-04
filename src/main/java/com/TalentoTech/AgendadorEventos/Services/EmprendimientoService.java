package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.*;
import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.CategoriaRepository;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprendimientoService {
    //Inyeccion del Repositorio - Llama a la interfaz
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Insertar
    public Emprendimiento guardarEmprendimiento(EmprendimientoDto emprendimientoDto){
        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(emprendimientoDto.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        // Buscar la categoria existente
        Categoria categoria = categoriaRepository.findById(emprendimientoDto.getId_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("categoria no encontrada"));

        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setCodigo(emprendimientoDto.getCodigo());
        emprendimiento.setNombre(emprendimientoDto.getNombre());
        emprendimiento.setMunicipio(municipio);
        emprendimiento.setCategoria(categoria);
        return emprendimientoRepository.save(emprendimiento);
    }

    //Consultar x id
    public Optional<Emprendimiento> buscarById(Integer id) {
        return emprendimientoRepository.findById(id);
    }

    //Consultar Todos
    public List<Emprendimiento> consultarTodos(){
        return emprendimientoRepository.findAll();
    }

    //Editar
    public Emprendimiento editarEmprendimiento(Integer id, EmprendimientoDto editarEmprendimiento){

        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(editarEmprendimiento.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        // Buscar la categoria existente
        Categoria categoria = categoriaRepository.findById(editarEmprendimiento.getId_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("categoria no encontrada"));

        return emprendimientoRepository.findById(id).map(emprendimiento -> {
            emprendimiento.setCodigo(editarEmprendimiento.getCodigo());
            emprendimiento.setNombre(editarEmprendimiento.getNombre());
            emprendimiento.setMunicipio(municipio);
            emprendimiento.setCategoria(categoria);
            return emprendimientoRepository.save(emprendimiento);
        }).orElseThrow(() -> new RuntimeException("Emprendimiento no encontrado"));
    }

    //Borrar por id
    public void borrarEmprendimiento(Integer id) {emprendimientoRepository.deleteById(id);}


    public EmprendimientoDto obtenerEmprendimientoConRelaciones(Integer id) {
        List<EmprendimientoDetalleDto> resultados = emprendimientoRepository.findByIdWithEventos(id);

        if (resultados.isEmpty()) {
            throw new RuntimeException("El emprendimiento con ID " + id + " no existe");
        }

        // Tomar los datos básicos del primer resultado (los campos principales son iguales en todas las filas)
        EmprendimientoDetalleDto base = resultados.get(0);
        EmprendimientoDto dto = new EmprendimientoDto();
        dto.setId(base.getId());
        dto.setCodigo(base.getCodigo());
        dto.setNombre(base.getNombre());
        dto.setId_municipio(base.getIdMunicipio());
        dto.setNombre_municipio(base.getNombreMunicipio());
        dto.setId_categoria(base.getIdCategoria());
        dto.setNombre_categoria(base.getNombreCategoria());

        // Agrupar eventos relacionados
        List<EventoEmprendimientoDto> eventosRelacionados = resultados.stream()
                .filter(r -> r.getEventoId() != null) // Excluir filas sin eventos
                .map(resultado -> {
                    EventoEmprendimientoDto eventoDto = new EventoEmprendimientoDto();
                    eventoDto.setId_evento(resultado.getEventoId());
                    eventoDto.setNombre_evento(resultado.getEventoNombre());
                    return eventoDto;
                })
                .distinct()
                .collect(Collectors.toList());
        dto.setEventosRelacionados(eventosRelacionados);

        // Agrupar usuarios relacionados
        List<UsuarioRelacionadoDto> usuariosRelacionados = resultados.stream()
                .filter(r -> r.getUsuarioId() != null) // Excluir filas sin usuarios
                .map(resultado -> {
                    UsuarioRelacionadoDto usuarioDto = new UsuarioRelacionadoDto();
                    usuarioDto.setId_usuario(resultado.getUsuarioId());
                    usuarioDto.setNombre_usuario(resultado.getUsuarioNombre());
                    return usuarioDto;
                })
                .distinct()
                .collect(Collectors.toList());
        dto.setUsuariosRelacionados(usuariosRelacionados);

        return dto;
    }

    public List<EmprendimientoDto> obtenerTodosLosEmprendimientos() {
        List<EmprendimientoEventoUsuarioDto> resultados = emprendimientoRepository.findAllWithEventosAndUsuarios();

        // Agrupar datos por Emprendimiento
        return resultados.stream().collect(Collectors.groupingBy(
                resultado -> new EmprendimientoDto(
                        resultado.getId(),
                        resultado.getCodigo(),
                        resultado.getNombre(),
                        resultado.getId_municipio(),
                        resultado.getNombre_municipio(),
                        resultado.getId_municipio(),
                        resultado.getNombre_categoria()
                ),
                Collectors.toList()
        )).entrySet().stream().map(entry -> {
            EmprendimientoDto dto = entry.getKey();

            // Mapear eventos
            List<EventoEmprendimientoDto> eventosRelacionados = entry.getValue().stream()
                    .map(resultado -> {
                        EventoEmprendimientoDto eventoDto = new EventoEmprendimientoDto();
                        eventoDto.setId_evento(resultado.getEventoId());
                        eventoDto.setNombre_evento(resultado.getEventoNombre());
                        return eventoDto;
                    })
                    .distinct() // Evitar duplicados en eventos
                    .collect(Collectors.toList());
            dto.setEventosRelacionados(eventosRelacionados);

            // Mapear usuarios
            List<UsuarioRelacionadoDto> usuariosRelacionados = entry.getValue().stream()
                    .map(resultado -> {
                        UsuarioRelacionadoDto usuarioDto = new UsuarioRelacionadoDto();
                        usuarioDto.setId_usuario(resultado.getUsuarioId());
                        usuarioDto.setNombre_usuario(resultado.getUsuarioNombre());
                        return usuarioDto;
                    })
                    .distinct() // Evitar duplicados en usuarios
                    .collect(Collectors.toList());
            dto.setUsuariosRelacionados(usuariosRelacionados);

            return dto;
        }).collect(Collectors.toList());
    }

}
