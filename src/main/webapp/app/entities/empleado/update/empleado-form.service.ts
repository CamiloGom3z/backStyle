import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IEmpleado, NewEmpleado } from '../empleado.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmpleado for edit and NewEmpleadoFormGroupInput for create.
 */
type EmpleadoFormGroupInput = IEmpleado | PartialWithRequiredKeyOf<NewEmpleado>;

type EmpleadoFormDefaults = Pick<NewEmpleado, 'id'>;

type EmpleadoFormGroupContent = {
  id: FormControl<IEmpleado['id'] | NewEmpleado['id']>;
  nombre: FormControl<IEmpleado['nombre']>;
  apellido: FormControl<IEmpleado['apellido']>;
  cargo: FormControl<IEmpleado['cargo']>;
  salario: FormControl<IEmpleado['salario']>;
  urlmg: FormControl<IEmpleado['urlmg']>;
  estado: FormControl<IEmpleado['estado']>;
  establecimientoId: FormControl<IEmpleado['establecimientoId']>;
};

export type EmpleadoFormGroup = FormGroup<EmpleadoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmpleadoFormService {
  createEmpleadoFormGroup(empleado: EmpleadoFormGroupInput = { id: null }): EmpleadoFormGroup {
    const empleadoRawValue = {
      ...this.getFormDefaults(),
      ...empleado,
    };
    return new FormGroup<EmpleadoFormGroupContent>({
      id: new FormControl(
        { value: empleadoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nombre: new FormControl(empleadoRawValue.nombre),
      apellido: new FormControl(empleadoRawValue.apellido),
      cargo: new FormControl(empleadoRawValue.cargo),
      salario: new FormControl(empleadoRawValue.salario),
      urlmg: new FormControl(empleadoRawValue.urlmg),
      estado: new FormControl(empleadoRawValue.estado),
      establecimientoId: new FormControl(empleadoRawValue.establecimientoId),
    });
  }

  getEmpleado(form: EmpleadoFormGroup): IEmpleado | NewEmpleado {
    return form.getRawValue() as IEmpleado | NewEmpleado;
  }

  resetForm(form: EmpleadoFormGroup, empleado: EmpleadoFormGroupInput): void {
    const empleadoRawValue = { ...this.getFormDefaults(), ...empleado };
    form.reset(
      {
        ...empleadoRawValue,
        id: { value: empleadoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EmpleadoFormDefaults {
    return {
      id: null,
    };
  }
}
