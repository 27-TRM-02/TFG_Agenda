import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TareasService } from '../../tareas.service';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';

@Component({
  selector: 'app-new-tarea',
  templateUrl: './new-tarea.component.html',
  styleUrls: ['./new-tarea.component.scss'],
})
export class NewTareaComponent implements OnInit {
  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    // Only highligh dates inside the month view.
    if (view === 'month') {
      const date = cellDate.getDate();

      // Highlight the 1st and 20th day of each month.
      return date === 1 || date === 20 ? 'example-custom-date-class' : '';
    }

    return '';
  };

  constructor(private tareasService: TareasService) {}

  ngOnInit(): void {}
}
