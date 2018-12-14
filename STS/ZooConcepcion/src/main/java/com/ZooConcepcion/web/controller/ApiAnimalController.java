package com.ZooConcepcion.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZooConcepcion.web.DAO.AnimalDao;
import com.ZooConcepcion.web.DAO.SectorDao;
import com.ZooConcepcion.web.DAO.TipoDao;
import com.ZooConcepcion.web.entity.Animal;
import com.ZooConcepcion.web.entity.Sector;
import com.ZooConcepcion.web.entity.Tipo;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiAnimalController {
	
	@Autowired
	private AnimalDao aDao;
	
	@Autowired
	private TipoDao tDao;
	
	@Autowired
	private SectorDao sDao;
	
	
	@GetMapping("/animales")
	public Iterable<Animal> listar()
	{
		return this.aDao.crud().findAll();
	}
	
	
	@PostMapping("/animales")
	public ResponseEntity<Animal> guardar(@RequestBody Animal animal)
	{
		try
		{
			Animal animalCreado = aDao.crud().save(animal);
			return new ResponseEntity<Animal>(animalCreado,HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/tipos")
	public ResponseEntity<Tipo> guardarTipo(@RequestBody Tipo tipo)
	{
		try
		{
			Tipo tipoCreado = tDao.crud().save(tipo);
			return new ResponseEntity<Tipo>(tipoCreado,HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/tipo")
	public Iterable<Tipo> listarTipos()
	{
		return this.tDao.crud().findAll();
	}
	
	@GetMapping("sector")
	public Iterable<Sector> listarSector()
	{
		return this.sDao.crud().findAll();
	}
	
	@CrossOrigin
	@GetMapping("/animal/{id}")
	public ResponseEntity<Animal> buscarAnimal(@PathVariable("id") int id)
	{
		Animal animal = null;
				
		try
		{
			animal = aDao.crud().findById(id).get();
			return new ResponseEntity<Animal>(animal,HttpStatus.OK);
					
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@GetMapping("/tipo/{id}")
	public ResponseEntity<Tipo> buscarTipo(@PathVariable("id") int id)
	{
		Tipo tipo = null;
				
		try
		{
			tipo = tDao.crud().findById(id).get();
			return new ResponseEntity<Tipo>(tipo,HttpStatus.OK);
					
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@PutMapping("/animales/{id}")
	public ResponseEntity<Animal> modificar(@RequestBody Animal animal,
			@PathVariable("id") int id)
	{
		Animal animalBuscado = null;
		
		try
		{
			animalBuscado = aDao.crud().findById(id).get();
			animalBuscado.setFechaDefuncion(animal.getFechaDefuncion());
			animalBuscado.setFechaIngreso(animal.getFechaIngreso());
			animalBuscado.setFechaNacimiento(animal.getFechaNacimiento());
			animalBuscado.setGenero(animal.getGenero());
			animalBuscado.setNombre(animal.getNombre());
			animalBuscado.setPeso(animal.getPeso());
			animalBuscado.setSector(animal.getSector());
			animalBuscado.setTipo(animal.getTipo());
			
			
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		try
		{
			Animal animalModificado = aDao.crud().save(animalBuscado);
			return new ResponseEntity<Animal>(animalModificado,
					HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/tipos/{id}")
	public ResponseEntity<Tipo> modificarTipo(@RequestBody Tipo tipo,
			@PathVariable("id") int id)
	{
		Tipo tipoModificado = null;
		
		try
		{
			tipoModificado = tDao.crud().findById(id).get();
			tipoModificado.setNombre(tipo.getNombre());
			tipoModificado.setDescripcion(tipo.getDescripcion());
			
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		try
		{
			Tipo tipoModificado2 = tDao.crud().save(tipoModificado);
			return new ResponseEntity<Tipo>(tipoModificado2,
					HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/animales/{id}")
	public ResponseEntity<Map<String,String>> eliminar(@PathVariable("id") int id)
	{
		try
		{
			aDao.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/tipos/{id}")
	public ResponseEntity<Map<String,String>> eliminarTipo(@PathVariable("id") int id)
	{
		try
		{
			tDao.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	

}
