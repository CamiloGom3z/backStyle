package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class GaleriaImagenAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertGaleriaImagenAllPropertiesEquals(GaleriaImagen expected, GaleriaImagen actual) {
        assertGaleriaImagenAutoGeneratedPropertiesEquals(expected, actual);
        assertGaleriaImagenAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertGaleriaImagenAllUpdatablePropertiesEquals(GaleriaImagen expected, GaleriaImagen actual) {
        assertGaleriaImagenUpdatableFieldsEquals(expected, actual);
        assertGaleriaImagenUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertGaleriaImagenAutoGeneratedPropertiesEquals(GaleriaImagen expected, GaleriaImagen actual) {
        assertThat(expected)
            .as("Verify GaleriaImagen auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertGaleriaImagenUpdatableFieldsEquals(GaleriaImagen expected, GaleriaImagen actual) {
        assertThat(expected)
            .as("Verify GaleriaImagen relevant properties")
            .satisfies(e -> assertThat(e.getNombre()).as("check nombre").isEqualTo(actual.getNombre()))
            .satisfies(e -> assertThat(e.getDescripcion()).as("check descripcion").isEqualTo(actual.getDescripcion()))
            .satisfies(e -> assertThat(e.getUrlImagen()).as("check urlImagen").isEqualTo(actual.getUrlImagen()))
            .satisfies(e -> assertThat(e.getEstablecimientoId()).as("check establecimientoId").isEqualTo(actual.getEstablecimientoId()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertGaleriaImagenUpdatableRelationshipsEquals(GaleriaImagen expected, GaleriaImagen actual) {
        // empty method
    }
}
