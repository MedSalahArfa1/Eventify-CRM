import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Event } from 'app/models/event.model'; // Adjust the path according to your folder structure

@Component({
  selector: 'app-event-modal',
  templateUrl: './event-modal.component.html',
  styleUrls: ['./event-modal.component.css']
})
export class EventModalComponent {
  @Input() event: Event;
  @Output() save = new EventEmitter<Event | FormData>();
  @Output() cancel = new EventEmitter<void>();

  selectedFile: File | null = null;

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.readAsDataURL(file);
    }
  }

  onSave() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('image', this.selectedFile);
      formData.append('event', new Blob([JSON.stringify(this.event)], { type: 'application/json' }));

      this.save.emit(formData);
    } else {
      this.save.emit(this.event);
    }
  }

  onCancel() {
    this.cancel.emit();
  }
}
