package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProductoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Producto getProductoSample1() {
        return new Producto()
            .id(1L)
            .nombre("nombre1")
            .descripcion("descripcion1")
            .cantidad(1)
            .urlImg("urlImg1")
            .categoriaProductoId(1L)
            .establecimientoId(1L);
    }

    public static Producto getProductoSample2() {
        return new Producto()
            .id(2L)
            .nombre("nombre2")
            .descripcion("descripcion2")
            .cantidad(2)
            .urlImg("urlImg2")
            .categoriaProductoId(2L)
            .establecimientoId(2L);
    }

    public static Producto getProductoRandomSampleGenerator() {
        return new Producto()
            .id(longCount.incrementAndGet())
            .nombre(UUID.randomUUID().toString())
            .descripcion(UUID.randomUUID().toString())
            .cantidad(intCount.incrementAndGet())
            .urlImg(UUID.randomUUID().toString())
            .categoriaProductoId(longCount.incrementAndGet())
            .establecimientoId(longCount.incrementAndGet());
    }
}
