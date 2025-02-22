package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DisponibilidadEmpleadoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDisponibilidadEmpleadoAllPropertiesEquals(DisponibilidadEmpleado expected, DisponibilidadEmpleado actual) {
        assertDisponibilidadEmpleadoAutoGeneratedPropertiesEquals(expected, actual);
        assertDisponibilidadEmpleadoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDisponibilidadEmpleadoAllUpdatablePropertiesEquals(
        DisponibilidadEmpleado expected,
        DisponibilidadEmpleado actual
    ) {
        assertDisponibilidadEmpleadoUpdatableFieldsEquals(expected, actual);
        assertDisponibilidadEmpleadoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDisponibilidadEmpleadoAutoGeneratedPropertiesEquals(
        DisponibilidadEmpleado expected,
        DisponibilidadEmpleado actual
    ) {
        assertThat(expected)
            .as("Verify DisponibilidadEmpleado auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDisponibilidadEmpleadoUpdatableFieldsEquals(DisponibilidadEmpleado expected, DisponibilidadEmpleado actual) {
        assertThat(expected)
            .as("Verify DisponibilidadEmpleado relevant properties")
            .satisfies(e -> assertThat(e.getDiaSemana()).as("check diaSemana").isEqualTo(actual.getDiaSemana()))
            .satisfies(e -> assertThat(e.getFechaInicio()).as("check fechaInicio").isEqualTo(actual.getFechaInicio()))
            .satisfies(e -> assertThat(e.getFechaFin()).as("check fechaFin").isEqualTo(actual.getFechaFin()))
            .satisfies(e -> assertThat(e.getEmpleadoId()).as("check empleadoId").isEqualTo(actual.getEmpleadoId()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDisponibilidadEmpleadoUpdatableRelationshipsEquals(
        DisponibilidadEmpleado expected,
        DisponibilidadEmpleado actual
    ) {
        // empty method
    }
}
