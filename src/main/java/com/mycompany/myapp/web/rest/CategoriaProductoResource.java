package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CategoriaProductoRepository;
import com.mycompany.myapp.service.CategoriaProductoService;
import com.mycompany.myapp.service.dto.CategoriaProductoDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.CategoriaProducto}.
 */
@RestController
@RequestMapping("/api/categoria-productos")
public class CategoriaProductoResource {

    private static final Logger LOG = LoggerFactory.getLogger(CategoriaProductoResource.class);

    private static final String ENTITY_NAME = "categoriaProducto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoriaProductoService categoriaProductoService;

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProductoResource(
        CategoriaProductoService categoriaProductoService,
        CategoriaProductoRepository categoriaProductoRepository
    ) {
        this.categoriaProductoService = categoriaProductoService;
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    /**
     * {@code POST  /categoria-productos} : Create a new categoriaProducto.
     *
     * @param categoriaProductoDTO the categoriaProductoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoriaProductoDTO, or with status {@code 400 (Bad Request)} if the categoriaProducto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CategoriaProductoDTO> createCategoriaProducto(@RequestBody CategoriaProductoDTO categoriaProductoDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save CategoriaProducto : {}", categoriaProductoDTO);
        if (categoriaProductoDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoriaProducto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        categoriaProductoDTO = categoriaProductoService.save(categoriaProductoDTO);
        return ResponseEntity.created(new URI("/api/categoria-productos/" + categoriaProductoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, categoriaProductoDTO.getId().toString()))
            .body(categoriaProductoDTO);
    }

    /**
     * {@code PUT  /categoria-productos/:id} : Updates an existing categoriaProducto.
     *
     * @param id the id of the categoriaProductoDTO to save.
     * @param categoriaProductoDTO the categoriaProductoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaProductoDTO,
     * or with status {@code 400 (Bad Request)} if the categoriaProductoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoriaProductoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> updateCategoriaProducto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoriaProductoDTO categoriaProductoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CategoriaProducto : {}, {}", id, categoriaProductoDTO);
        if (categoriaProductoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoriaProductoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoriaProductoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        categoriaProductoDTO = categoriaProductoService.update(categoriaProductoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaProductoDTO.getId().toString()))
            .body(categoriaProductoDTO);
    }

    /**
     * {@code PATCH  /categoria-productos/:id} : Partial updates given fields of an existing categoriaProducto, field will ignore if it is null
     *
     * @param id the id of the categoriaProductoDTO to save.
     * @param categoriaProductoDTO the categoriaProductoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaProductoDTO,
     * or with status {@code 400 (Bad Request)} if the categoriaProductoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categoriaProductoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoriaProductoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoriaProductoDTO> partialUpdateCategoriaProducto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoriaProductoDTO categoriaProductoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CategoriaProducto partially : {}, {}", id, categoriaProductoDTO);
        if (categoriaProductoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoriaProductoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoriaProductoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoriaProductoDTO> result = categoriaProductoService.partialUpdate(categoriaProductoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaProductoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /categoria-productos} : get all the categoriaProductos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoriaProductos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CategoriaProductoDTO>> getAllCategoriaProductos(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of CategoriaProductos");
        Page<CategoriaProductoDTO> page = categoriaProductoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categoria-productos/:id} : get the "id" categoriaProducto.
     *
     * @param id the id of the categoriaProductoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoriaProductoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> getCategoriaProducto(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CategoriaProducto : {}", id);
        Optional<CategoriaProductoDTO> categoriaProductoDTO = categoriaProductoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoriaProductoDTO);
    }

    /**
     * {@code DELETE  /categoria-productos/:id} : delete the "id" categoriaProducto.
     *
     * @param id the id of the categoriaProductoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaProducto(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CategoriaProducto : {}", id);
        categoriaProductoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
