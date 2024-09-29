import { Component, OnInit } from '@angular/core';
import { EventService } from 'app/services/event.service';
import { Event, EventImage } from 'app/models/event.model';

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

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.getEvents();
  }

  getEvents() {
    this.eventService.getEvents().subscribe((data: Event[]) => {
      this.events = data;
    });
  }

  openAddEventCard() {
    this.currentEvent = {
      eventId: null,
      eventName: '',
      eventDescription: '',
      totalTickets: 0,
      startDateTime: new Date(),
      endDateTime: new Date(),
      status: '',
      eventImage: null
    };
    this.isAddingEvent = true;
  }

  openEditEventCard(event: Event) {
    this.currentEvent = { ...event };
    this.isAddingEvent = false;
  }

  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  saveEvent() {
    if (this.selectedImage) {
      this.uploadImageAndSaveEvent();
    } else {
      this.saveEventData(this.currentEvent);
    }
  }

  uploadImageAndSaveEvent() {
    if (this.currentEvent.eventId) {
      this.eventService.uploadEventImage(this.selectedImage, this.currentEvent.eventId).subscribe(() => {
        this.saveEventData(this.currentEvent);
      });
    } else {
      this.eventService.createEvent(this.currentEvent).subscribe(newEvent => {
        this.eventService.uploadEventImage(this.selectedImage, newEvent.eventId).subscribe(() => {
          this.events.push(newEvent);
          this.currentEvent = null;
        });
      });
    }
  }

  saveEventData(eventOrFormData: Event | FormData) {
    if (eventOrFormData instanceof FormData) {
      this.eventService.uploadEventWithImage(eventOrFormData).subscribe(() => {
        this.getEvents();
        this.currentEvent = null;
      });
    } else {
      if (eventOrFormData.eventId) {
        this.eventService.updateEvent(eventOrFormData).subscribe(() => {
          this.getEvents();
          this.currentEvent = null;
        });
      } else {
        this.eventService.createEvent(eventOrFormData).subscribe(() => {
          this.getEvents();
          this.currentEvent = null;
        });
      }
    }
  }

  deleteEvent(eventId: number) {
    if (confirm("Are you sure you want to delete this event?")) {
      this.eventService.deleteEvent(eventId).subscribe(() => {
        this.events = this.events.filter(event => event.eventId !== eventId);
      });
    }
  }

  resetForm(): void {
    this.currentEvent = null;
    this.isAddingEvent = false;
    this.selectedImage = null;
  }
}
