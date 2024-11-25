package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    //inyeccion del Repositorio - llama a la interfaz
    @Autowired
    private CategoriaRepository categoriaRepository;

    //insertar
    public Categoria guardarCategoria(Categoria categoria){
        return  categoriaRepository.save(categoria);
    }

    //Consultar x id
    public Optional<Categoria> buscarById(Integer id) {
        return categoriaRepository.findById(id);
    }

    //consultar Todos
    public List<Categoria> consultarTodos(){
        return  categoriaRepository.findAll();
    }

    public Categoria editarCategory(Integer id, Categoria categoriaEditar) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaEditar.getNombre());
            categoria.setDescripcion(categoriaEditar.getDescripcion());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    // Borrar por id
    public void borrarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
