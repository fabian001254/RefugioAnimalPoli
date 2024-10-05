package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ListaCuidadores;
import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.UsuarioDetailDTO;
import com.refugio.refugioanimal.dto.mappers.AnimalMapper;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.dto.usuario.ListaAnimales;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.excepciones.AnimalesNoEncontradoExeption;
import com.refugio.refugioanimal.excepciones.CuidadorNoEncontradoException;
import com.refugio.refugioanimal.excepciones.UsuariosNoEncontradoExeption;
import com.refugio.refugioanimal.model.Animal;
import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.model.Imagen;
import com.refugio.refugioanimal.repository.AnimalRepository;
import com.refugio.refugioanimal.repository.CuidadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    AnimalMapper animalMapper = new AnimalMapper();
    UsuarioMappers usuarioMapper = new UsuarioMappers();

    UsuarioMappers usuarioMappers = new UsuarioMappers();

    public List<UsuarioDetailDTO> asignarCuidador(Long idAnimal, ListaDeCuidadores listaDeCuidadores) {
        List<Long> cuidadoresIds = listaDeCuidadores.getCuidadores();
        List<Cuidador> cuidadorList = cuidadorRepository.findAllById(cuidadoresIds);
        List<UsuarioDetailDTO> usuariosRepetidos = new ArrayList<>();

        if (cuidadorList.size() != cuidadoresIds.size()) {
            List<Long> foundIds = cuidadorList.stream().map(Cuidador::getId).toList();
            List<Long> missingIds = cuidadoresIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new UsuariosNoEncontradoExeption(missingIds);
        }

        Optional<Animal> animalOpt = animalRepository.findById(idAnimal);
        if (animalOpt.isPresent()) {
            Animal animal = animalOpt.get();

            cuidadorList.forEach(cuidador -> {
                if (!animal.getCuidadores().contains(cuidador)) {
                    animal.getCuidadores().add(cuidador);
                    cuidador.getAnimalesACargo().add(animal);
                } else {
                    usuariosRepetidos.add(usuarioMappers.usuarioToUsuarioDetailDTO(cuidador));
                }
            });

            animalRepository.save(animal);
            cuidadorRepository.saveAll(cuidadorList);

        } else {
            throw new AnimalesNoEncontradoExeption(List.of(idAnimal));
        }

        return usuariosRepetidos;
    }

    public List<AnimalDTO> asignarAnimales(Long idCuidador, ListaDeAnimales listaAnimales) {
        List<Long> animalesIds = listaAnimales.getAnimales();
        List<Animal> animalList = animalRepository.findAllById(animalesIds);
        List<AnimalDTO> animalesRepetidos = new ArrayList<>();

        if (animalList.size() != animalesIds.size()) {
            List<Long> foundIds = animalList.stream().map(Animal::getId).toList();
            List<Long> missingIds = animalesIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new AnimalesNoEncontradoExeption(missingIds);
        }

        Optional<Cuidador> cuidadorOpt = cuidadorRepository.findById(idCuidador);
        if (cuidadorOpt.isPresent()) {
            Cuidador cuidador = cuidadorOpt.get();

            animalList.forEach(animal -> {
                if (!animal.getCuidadores().contains(cuidador)) {
                    animal.getCuidadores().add(cuidador);
                    // Si la relación es bidireccional, agrega el animal al cuidador
                    cuidador.getAnimalesACargo().add(animal);
                } else {
                    animalesRepetidos.add(animalMapper.animalToAnimalDTO(animal));
                }
            });

            // Guarda los animales modificados
            animalRepository.saveAll(animalList);
            // Guarda el cuidador si se modificó su lista de animales
            cuidadorRepository.save(cuidador);

        } else {
            throw new CuidadorNoEncontradoException(idCuidador);
        }

        return animalesRepetidos;
    }


    public void crearAnimal(AnimalDTO animalDTO) {
        Animal animal = animalMapper.animalDTOToAnimal(animalDTO);
        List<Imagen> imagenesAleatorias = generarImagenesAleatorias(animal);
        animal.setFotos(imagenesAleatorias);
        animalRepository.save(animal);
    }

    private List<Imagen> generarImagenesAleatorias(Animal animal) {
        List<Imagen> imagenes = new ArrayList<>();
        Random random = new Random();
        int cantidadImagenes = random.nextInt(5) + 1; // Genera entre 1 y 5 imágenes aleatorias

        for (int i = 0; i < cantidadImagenes; i++) {
            try {
                URL url = new URL("https://random.dog/woof.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();

                // Parsear la respuesta JSON para obtener la URL de la imagen
                String jsonString = content.toString();
                String imageUrl = new JSONObject(jsonString).getString("url");

                // Crear la imagen y asociarla al animal
                Imagen imagen = new Imagen();
                imagen.setUrl(imageUrl);
                imagen.setDescripcion("Imagen aleatoria de perro " + (i + 1));
                imagen.setAnimal(animal);

                imagenes.add(imagen);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return imagenes;
    }

    public ListaCuidadores obtenerListaDeCuidadores(Long id) {
        return animalRepository.findById(id)
                .map(cuidador -> {
                    ListaCuidadores listaDeCuidadores = new ListaCuidadores();
                    listaDeCuidadores.setListaCuidadores(
                            cuidador.getCuidadores().stream().map(usuarioMapper::cuidadorToUsuarioDTO).toList()
                    );
                    return listaDeCuidadores;
                })
                .orElseThrow(() -> new EntityNotFoundException("Cuidador con ID " + id + " no encontrado"));
    }

}
