export interface Feedback {
    feedbackId?: number; // Optional for creation
    rating: number;
    comments: string;
    dateTime?: Date; // Optional for creation, will be set by backend
    eventId: number; // Assuming you want to link feedback to an event
    leftBy: number; // Assuming this represents the user who left the feedback
  }
  