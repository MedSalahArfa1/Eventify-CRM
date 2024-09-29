import { Component, OnInit, ElementRef, HostListener } from '@angular/core';
import { Location } from '@angular/common';
import { AuthService } from 'app/services/auth.service';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  private toggleButton: any;
  private sidebarVisible: boolean;
  showNavbar: boolean = true;

  constructor(
    public location: Location, 
    private element: ElementRef, 
    public authService: AuthService,
    private router: Router
  ) {
    this.sidebarVisible = false;
  }

  ngOnInit() {
    const navbar: HTMLElement = this.element.nativeElement;
    this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
    
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.checkRoute(this.router.url);
    });

    // Initial check
    this.checkRoute(this.router.url);
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    this.updateNavbarColor();
  }

  updateNavbarColor() {
    const navbar = this.element.nativeElement.querySelector('.navbar') as HTMLElement;
    if (window.pageYOffset > 50) {
      navbar.classList.add('navbar-orange');
    } else {
      navbar.classList.remove('navbar-orange');
    }
  }

  checkRoute(url: string) {
    // Add routes here where you want to hide the navbar
    const hiddenRoutes = ['/dashboard', '/events', '/tasks', 'vendor'];
    this.showNavbar = !hiddenRoutes.includes(url);
  }

  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName('html')[0];
    setTimeout(function () {
      toggleButton.classList.add('toggled');
    }, 500);
    html.classList.add('nav-open');
    this.sidebarVisible = true;
  }

  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
  }

  sidebarToggle() {
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }

  isDocumentation() {
    const titlee = this.location.prepareExternalUrl(this.location.path());
    return titlee === '/documentation';
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
