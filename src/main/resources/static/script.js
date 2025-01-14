const apiUrl = 'http://localhost:8080/reservaciones';

// Crear una nueva reservación
document.getElementById('reservacionForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const telefono = document.getElementById('telefono').value;
    const email = document.getElementById('email').value;
    const fecha = document.getElementById('fecha').value;
    const hora = document.getElementById('hora').value;

    const reservacion = {
        cliente: { nombre, telefono, email },
        horario: { fecha, horaInicio: hora, horaFin: hora },
        fecha: new Date(fecha),
        estado: 'Reservado'
    };

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(reservacion)
    });

    if (response.ok) {
        alert('Reservación creada exitosamente');
        document.getElementById('reservacionForm').reset();
    } else {
        alert('Error al crear la reservación');
    }
});

// Ver las reservaciones de una fecha
async function verReservaciones() {
    let fecha = document.getElementById('verFecha').value;
    fecha += "T00:00:00.000Z";
    console.log(fecha);
    
    const response = await fetch(`${apiUrl}/${fecha}`);
    const reservaciones = await response.json();

    const lista = document.getElementById('reservacionesList');
    lista.innerHTML = '';

    reservaciones.forEach((reservacion) => {
        const li = document.createElement('li');
        li.textContent = `${reservacion.cliente.nombre} - ${reservacion.horario.fecha} - ${reservacion.horario.horaInicio} (${reservacion.estado})`;
        lista.appendChild(li);
    });
}

// Actualizar una reservación
async function actualizarReservacion() {
    const id = document.getElementById('idReservacion').value;
    const estado = document.getElementById('estado').value;

    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ estado })
    });

    if (response.ok) {
        alert('Reservación actualizada');
    } else {
        alert('Error al actualizar la reservación');
    }
}

// Eliminar una reservación
async function eliminarReservacion() {
    const id = document.getElementById('idReservacion').value;

    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'DELETE',
    });

    if (response.ok) {
        alert('Reservación eliminada');
    } else {
        alert('Error al eliminar la reservación');
    }
}