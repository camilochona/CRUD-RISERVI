package com.registro.CRUD.controller;

import ch.qos.logback.core.CoreConstants;
import com.registro.CRUD.model.Cliente;
import com.registro.CRUD.model.HorarioDisponible;
import com.registro.CRUD.model.Reservacion;
import com.registro.CRUD.repository.ClienteRepository;
import com.registro.CRUD.repository.HorarioDisponibleRepository;
import com.registro.CRUD.repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservaciones")
public class ReservacionController {

    @Autowired
    private ReservacionRepository reservacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HorarioDisponibleRepository horarioDisponibleRepository;

    // Crear una reservación
    @PostMapping
    public ResponseEntity<Reservacion> crearReservacion(@RequestBody Reservacion reservacion) {

        HorarioDisponible horarioDisponible = new HorarioDisponible();
        horarioDisponible.setFecha(reservacion.getHorario().getFecha());
        horarioDisponible.setHoraFin(reservacion.getHorario().getHoraFin());
        horarioDisponible.setHoraInicio(reservacion.getHorario().getHoraInicio());
        horarioDisponible = horarioDisponibleRepository.save(horarioDisponible);

        Cliente cliente = new Cliente();
        cliente.setNombre(reservacion.getCliente().getNombre());
        cliente.setEmail(reservacion.getCliente().getEmail());
        cliente.setTelefono(reservacion.getCliente().getTelefono());
        cliente = clienteRepository.save(cliente);

        // Verificar si el horario ya está reservado
        if (reservacionRepository.existsByFechaAndIdHorario(reservacion.getFecha(), reservacion.getHorario().getIdHorario())) {
            return ResponseEntity.status(400).body(null);
        }
        
        Reservacion nuevaReservacion = new Reservacion();
        reservacion.setHorario(horarioDisponible);
        reservacion.setCliente(cliente);

        reservacionRepository.save(reservacion);
        return ResponseEntity.ok(nuevaReservacion);
    }

    // Leer las reservaciones por fecha
    @GetMapping("/{fecha}")
    public List<Reservacion> obtenerReservaciones(@PathVariable String fecha) {
        return reservacionRepository.findByFecha(fecha);
    }

    // Actualizar una reservación
    @PutMapping("/{id}")
    public ResponseEntity<Reservacion> actualizarReservacion(@PathVariable Long id, @RequestBody Reservacion reservacion) {
        Optional<Reservacion> reservacionExistente = reservacionRepository.findById(id);
        if (reservacionExistente.isPresent()) {
            Reservacion reservacionActualizar = reservacionExistente.get();
            reservacionActualizar.setEstado(reservacion.getEstado());
            reservacionRepository.save(reservacionActualizar);
            return ResponseEntity.ok(reservacionActualizar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una reservación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservacion(@PathVariable Long id) {
        Optional<Reservacion> reservacionExistente = reservacionRepository.findById(id);
        if (reservacionExistente.isPresent()) {
            reservacionRepository.delete(reservacionExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
