import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { RegisterDTO, Role } from 'app/models/user.model';
import { AuthService } from 'app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../login/login.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  focus: boolean;
  focus1: boolean;
  focus2: boolean;
  focus3: boolean;
  focus4: boolean;
  focus5: boolean;
  focus6: boolean;
  focus7: boolean;
  focus8: boolean;
  focus9: boolean;
  focus10: boolean;
  focus11: boolean;
  focus12: boolean;

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      birthDate: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      address: this.fb.group({
        street: [''],
        city: [''],
        state: [''],
        zipCode: [''],
        country: ['']
      }),
      roles: this.fb.array([
        this.fb.control(false), // Event Manager
        this.fb.control(false)  // Participant
      ]) // Initialize as FormArray
    });
  }

  get roles(): FormArray {
    return this.registerForm.get('roles') as FormArray;
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const formValues = this.registerForm.value;
      const payload: RegisterDTO = {
        username: formValues.username,
        firstName: formValues.firstName,
        lastName: formValues.lastName,
        birthDate: new Date(formValues.birthDate), // Convert to Date
        password: formValues.password,
        email: formValues.email,
        phone: formValues.phone,
        address: formValues.address,
        roles: this.getSelectedRoles() // Convert roles to the required format
      };
      console.log('Payload:', payload); // Log the payload
      this.authService.register(payload).subscribe(
        response => {
          console.log('Registration successful', response);
          alert('Registration successful!');
        },
        error => {
          console.log('Registration failed', error);
          alert('Registration failed!');
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }

  getSelectedRoles(): Role[] {
    const selectedRoles: Role[] = [];
    const rolesData = [
      { roleId: 1, roleName: 'Event Manager' },
      { roleId: 2, roleName: 'Participant' }
    ];

    this.roles.controls.forEach((control, index) => {
      if (control.value) {
        selectedRoles.push(rolesData[index]);
      }
    });
    return selectedRoles;
  }

  getRoleName(index: number): string {
    const roleNames = ['Event Manager', 'Participant'];
    return roleNames[index];
  }

  setFocus(field: string, focus: boolean): void {
    this.focus[field] = focus;
  }
}
