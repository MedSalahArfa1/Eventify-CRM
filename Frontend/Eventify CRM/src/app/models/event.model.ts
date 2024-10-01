
export interface Event {
  eventId: number | null;
  eventName: string;
  eventDescription: string;
  totalTickets: number;
  startDateTime: Date;
  endDateTime: Date;
  status: string;
  eventImage?: EventImage;
  imagePreview?: string;
}

export interface EventImage { 
  id: number | null; 
  name: string; 
  picByte: Uint8Array; 
}