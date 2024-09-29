// src/app/models/event.model.ts
export interface Event {
  eventId: number | null;
  eventName: string;
  eventDescription: string;
  totalTickets: number;
  startDateTime: Date;
  endDateTime: Date;
  status: string;
  eventImage?: EventImage;
}

export interface EventImage { 
  id: number | null; 
  name: string; 
  picByte: Uint8Array; 
}