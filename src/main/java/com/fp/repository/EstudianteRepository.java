package com.fp.repository;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteRepository() {
        Estudiante juan = new Estudiante();
        juan.setId(1);
        juan.setNombre("Juan");
        juan.setApellidos("Pérez");
        juan.setEdad(20);
        this.estudiantes.add(juan);

        Estudiante maria = new Estudiante();
        maria.setId(2);
        maria.setNombre("María");
        maria.setApellidos("García");
        maria.setEdad(21);
        this.estudiantes.add(maria);

        Estudiante pedro = new Estudiante();
        pedro.setId(3);
        pedro.setNombre("Pedro");
        pedro.setApellidos("Rodríguez");
        pedro.setEdad(22);
        this.estudiantes.add(pedro);

        Estudiante ana = new Estudiante();
        ana.setId(4);
        ana.setNombre("Ana");
        ana.setApellidos("Sánchez");
        ana.setEdad(23);
        this.estudiantes.add(ana);

        Estudiante carlos = new Estudiante();
        carlos.setId(5);
        carlos.setNombre("Carlos");
        carlos.setApellidos("López");
        carlos.setEdad(24);
        this.estudiantes.add(carlos);
    }

    public Estudiante findById(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }
}
