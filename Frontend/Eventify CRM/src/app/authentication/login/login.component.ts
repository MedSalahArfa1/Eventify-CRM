import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  focus: boolean = false;
  focus1: boolean = false;
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {

    const loginData = { username: this.username, password: this.password };

    this.authService.login(loginData).subscribe(
      response => {

        const userData = response.user;
        localStorage.setItem('userAuth', JSON.stringify(userData));

        const accessToken = response.accessToken;
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('roles', JSON.stringify(response.roles));

        console.log('Login successful:', response);
        alert('Login successful!');
        window.location.reload();
      },
      error => {
        console.error('Login failed:', error);
        alert('Login failed! Please check your credentials.');
      }
    );
  }

  ngOnInit() {
    document.body.classList.add('login-page');
    document.querySelector('nav')?.classList.add('navbar-transparent');
  }

  ngOnDestroy() {
    document.body.classList.remove('login-page');
    document.querySelector('nav')?.classList.remove('navbar-transparent');
  }
}
