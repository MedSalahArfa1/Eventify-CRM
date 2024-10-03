import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FeedbackService } from 'app/services/feedback.service';
import { AuthService } from 'app/services/auth.service';

@Component({
  selector: 'app-feedback-popup',
  templateUrl: './feedback-popup.component.html',
  styleUrls: ['./feedback-popup.component.css']
})
export class FeedbackPopupComponent {
  @Input() show: boolean = false;
  @Input() eventId: number | null = null;
  @Output() close: EventEmitter<void> = new EventEmitter();
  @Output() feedbackAdded: EventEmitter<void> = new EventEmitter();

  feedbackData: { rating: number; comments: string; event: { eventId: number }; leftBy: any } = {
    rating: 5, // Default rating
    comments: '',
    event: { eventId: 0 }, // Initial placeholder
    leftBy: 0 // Placeholder for the user ID
  };

  constructor(private feedbackService: FeedbackService, private authService: AuthService) {}

  onSubmit() {
    if (this.eventId) {
      this.feedbackData.event.eventId = this.eventId; // Set the correct event ID
      this.feedbackData.leftBy = this.authService.getUserId(); // Get the ID of the user leaving feedback

      this.feedbackService.addFeedback(this.feedbackData).subscribe(() => {
          this.feedbackAdded.emit(); // Emit event to notify parent component
          this.closePopup(); // Close the popup
      }, error => {
          console.error('Error adding feedback:', error);
      });
    }
  }

  closePopup() {
    this.show = false;
    this.close.emit();
  }
}
