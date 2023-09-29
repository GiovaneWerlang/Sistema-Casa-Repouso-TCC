import { Pipe, PipeTransform } from '@angular/core';
import { LabelValue } from '../labelvalue/labelvalue';

@Pipe({
  name: 'Enum'
})
export class EnumPipe implements PipeTransform {

  transform(value: any, lista: LabelValue[]): any {
    return lista.find(i => i.value === value)?.label;
  }

}
