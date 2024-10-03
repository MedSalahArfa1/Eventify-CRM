import { Component, OnInit } from '@angular/core';
import { Vendor } from 'app/models/vendor.model';
import { Event } from 'app/models/event.model';
import { EventService } from 'app/services/event.service';
import { FeedbackService } from 'app/services/feedback.service';
import { VendorService } from 'app/services/vendor.service';
import { Feedback } from 'app/models/feedback.model';
import { AuthService } from 'app/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  totalEvents: number = 0;
  totalVendors: number = 0;
  totalFeedbacks: number = 0;
  username: string;

  constructor(
    private eventService: EventService,
    private vendorService: VendorService,
    private feedbackService: FeedbackService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getUserDetails();
    this.loadTotals();
  }

  loadTotals() {
    // Fetch all events and calculate total
    this.eventService.getEvents().subscribe((events: Event[]) => {
      this.totalEvents = events.length; // Count of total events
    });

    // Fetch all vendors and calculate total
    this.vendorService.getAllVendors().subscribe((vendors: Vendor[]) => {
      this.totalVendors = vendors.length; // Count of total vendors
    });

    // Fetch all feedbacks and calculate total
    this.feedbackService.getAllFeedbacks().subscribe((feedbacks: Feedback[]) => {
      this.totalFeedbacks = feedbacks.length; // Count of total feedbacks
    });
  }

  getUserDetails() {
    this.authService.getUserDetails().subscribe((data) => {
      this.username = data.username;
    });
  }
}
