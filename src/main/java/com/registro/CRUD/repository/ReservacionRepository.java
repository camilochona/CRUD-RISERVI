/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.registro.CRUD.repository;

import com.registro.CRUD.model.HorarioDisponible;
import com.registro.CRUD.model.Reservacion;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author juanc
 */
public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {

    List<Reservacion> findByFecha(String fecha);

    List<Reservacion> findByHorarioAndFecha(HorarioDisponible horario, String fecha);

    // Verificar si un horario está ocupado en una fecha específica
    boolean existsByFechaAndIdHorario(String fecha, Long horario);
}
