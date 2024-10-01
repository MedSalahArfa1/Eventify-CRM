import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProfileDTO } from 'app/models/user.model';
import { AuthService } from 'app/services/auth.service';
import * as Rellax from 'rellax';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  profileForm: FormGroup;
  user: ProfileDTO;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    // Initialize the profile form in the constructor
    this.profileForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      phone: [''],
      birthDate: [''],
    });
  }

  ngOnInit() {
    this.getUserDetails(); // Fetch user data on component load
    new Rellax('.rellax-header'); // Initialize Rellax for the header

    // Add classes to body and navbar for styling
    document.body.classList.add('profile-page');
    document.getElementsByTagName('nav')[0].classList.add('navbar-transparent');
  }

  ngOnDestroy() {
    // Clean up by removing classes when the component is destroyed
    document.body.classList.remove('profile-page');
    document.getElementsByTagName('nav')[0].classList.remove('navbar-transparent');
  }

  // Fetch the user's details from the service
  getUserDetails() {
    this.authService.getUserDetails().subscribe((data: ProfileDTO) => {
      this.user = data;
      this.profileForm.patchValue(data); // Populate the form with user data
    });
  }
}
