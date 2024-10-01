import { Component, OnInit } from '@angular/core';
import { EventService } from 'app/services/event.service';
import { Event } from 'app/models/event.model';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  events: Event[] = [];
  isAddingEvent: boolean = true;
  currentEvent: Event = null;
  selectedImage: File | null = null;
  isImageUploaded: boolean = false;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(private eventService: EventService) {}

  ngOnInit() {
    this.getEvents();
  }

  getEvents() {
    this.eventService.getEvents().subscribe((data: Event[]) => {
      this.events = data.map(event => {
        // Convert picByte to base64 for image preview if it exists
        if (event.eventImage) {
          event.imagePreview = 'data:image/jpeg;base64,' + this.convertUint8ToBase64(event.eventImage.picByte);
        }
        return event;
      });
    });
  }

  openAddEventCard() {
    this.resetForm();
    this.isAddingEvent = true;
    this.currentEvent = {
      eventId: null,
      eventName: '',
      eventDescription: '',
      totalTickets: 0,
      startDateTime: new Date(),
      endDateTime: new Date(),
      status: '',
      eventImage: null,
      imagePreview: null // Initialize imagePreview
    };
  }

  openEditEventCard(event: Event) {
    this.resetForm();
    this.currentEvent = { ...event };
    this.isAddingEvent = false;
    this.isImageUploaded = !!event.eventImage;

    // Set the imagePreview using the existing picByte
    if (event.eventImage) {
      this.currentEvent.imagePreview = 'data:image/jpeg;base64,' + this.convertUint8ToBase64(event.eventImage.picByte);
    }
  }

  onImageSelected(event: any) {
    const file = event.target.files[0];
    this.validateImage(file);
  }

  validateImage(file: File) {
    if (file) {
      if (file.size > 1048576) { // Check if the file size exceeds 1 MB
        this.showAlert('The selected image exceeds the maximum allowed size of 1 MB.');
        this.resetImageSelection();
      } else {
        const allowedMimeTypes = ['image/jpeg', 'image/png'];
        if (!allowedMimeTypes.includes(file.type)) {
          this.showAlert('Only JPG, JPEG, and PNG images are allowed.');
          this.resetImageSelection();
          return;
        } else {
          this.selectedImage = file;
          this.previewImage(file);
        }
      }
    } else {
      this.resetImageSelection();
    }
  }

  previewImage(file: File) {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
      this.isImageUploaded = true;
    };
    reader.readAsDataURL(file);
  }

  showAlert(message: string) {
    alert(message);
  }

  resetImageSelection() {
    this.selectedImage = null;
    this.imagePreview = null;
    this.isImageUploaded = false;
  }

  saveEvent() {
    if (this.isAddingEvent) {
      this.createEventWithImage();
    } else {
      this.updateEventWithImage();
    }
  }

  createEventWithImage() {
    this.eventService.createEvent(this.currentEvent).subscribe((newEvent) => {
      if (this.selectedImage) {
        this.eventService.uploadEventImage(this.selectedImage, newEvent.eventId).subscribe({
          next: () => {
            this.events.push(newEvent);
            this.resetForm();
          },
          error: () => this.showAlert('Failed to upload image.')
        });
      } else {
        this.events.push(newEvent);
        this.resetForm();
      }
    }, () => this.showAlert('Failed to create event.'));
  }

  updateEventWithImage() {
    if (this.currentEvent.eventId) {
      if (this.selectedImage) {
        this.eventService.uploadEventImage(this.selectedImage, this.currentEvent.eventId).subscribe({
          next: () => this.saveEventData(this.currentEvent),
          error: () => this.showAlert('Failed to upload image.')
        });
      } else {
        this.saveEventData(this.currentEvent);
      }
    }
  }

  saveEventData(event: Event) {
    this.eventService.updateEvent(event).subscribe({
      next: () => {
        this.getEvents();
        this.resetForm();
      },
      error: () => this.showAlert('Failed to update event.')
    });
  }

  deleteEvent(eventId: number) {
    if (confirm("Are you sure you want to delete this event?")) {
      this.eventService.deleteEvent(eventId).subscribe(() => {
        this.events = this.events.filter((event) => event.eventId !== eventId);
      }, () => this.showAlert('Failed to delete event.'));
    }
  }

  resetForm() {
    this.currentEvent = null;
    this.isAddingEvent = true;
    this.resetImageSelection();
  }

  convertUint8ToBase64(uint8Array: Uint8Array): string {
    const byteArray = new Uint8Array(uint8Array);
    const binaryString = Array.from(byteArray)
      .map((byte) => String.fromCharCode(byte))
      .join('');
    return btoa(binaryString);
  }
}
