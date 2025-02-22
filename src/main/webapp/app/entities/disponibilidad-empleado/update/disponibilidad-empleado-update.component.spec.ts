import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { DisponibilidadEmpleadoService } from '../service/disponibilidad-empleado.service';
import { IDisponibilidadEmpleado } from '../disponibilidad-empleado.model';
import { DisponibilidadEmpleadoFormService } from './disponibilidad-empleado-form.service';

import { DisponibilidadEmpleadoUpdateComponent } from './disponibilidad-empleado-update.component';

describe('DisponibilidadEmpleado Management Update Component', () => {
  let comp: DisponibilidadEmpleadoUpdateComponent;
  let fixture: ComponentFixture<DisponibilidadEmpleadoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let disponibilidadEmpleadoFormService: DisponibilidadEmpleadoFormService;
  let disponibilidadEmpleadoService: DisponibilidadEmpleadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [DisponibilidadEmpleadoUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DisponibilidadEmpleadoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DisponibilidadEmpleadoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    disponibilidadEmpleadoFormService = TestBed.inject(DisponibilidadEmpleadoFormService);
    disponibilidadEmpleadoService = TestBed.inject(DisponibilidadEmpleadoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const disponibilidadEmpleado: IDisponibilidadEmpleado = { id: 456 };

      activatedRoute.data = of({ disponibilidadEmpleado });
      comp.ngOnInit();

      expect(comp.disponibilidadEmpleado).toEqual(disponibilidadEmpleado);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisponibilidadEmpleado>>();
      const disponibilidadEmpleado = { id: 123 };
      jest.spyOn(disponibilidadEmpleadoFormService, 'getDisponibilidadEmpleado').mockReturnValue(disponibilidadEmpleado);
      jest.spyOn(disponibilidadEmpleadoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disponibilidadEmpleado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disponibilidadEmpleado }));
      saveSubject.complete();

      // THEN
      expect(disponibilidadEmpleadoFormService.getDisponibilidadEmpleado).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(disponibilidadEmpleadoService.update).toHaveBeenCalledWith(expect.objectContaining(disponibilidadEmpleado));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisponibilidadEmpleado>>();
      const disponibilidadEmpleado = { id: 123 };
      jest.spyOn(disponibilidadEmpleadoFormService, 'getDisponibilidadEmpleado').mockReturnValue({ id: null });
      jest.spyOn(disponibilidadEmpleadoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disponibilidadEmpleado: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disponibilidadEmpleado }));
      saveSubject.complete();

      // THEN
      expect(disponibilidadEmpleadoFormService.getDisponibilidadEmpleado).toHaveBeenCalled();
      expect(disponibilidadEmpleadoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisponibilidadEmpleado>>();
      const disponibilidadEmpleado = { id: 123 };
      jest.spyOn(disponibilidadEmpleadoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disponibilidadEmpleado });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(disponibilidadEmpleadoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
