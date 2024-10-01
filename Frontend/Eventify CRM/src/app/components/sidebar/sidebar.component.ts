import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/services/auth.service';
import { Router } from '@angular/router';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/dashboard', title: 'Dashboard', icon: 'design_app', class: '' },
  { path: '/events', title: 'Events', icon: 'objects_spaceship', class: '' },
  { path: '/tasks', title: 'Tasks', icon: 'design_bullet-list-67', class: '' },
  { path: '/vendors', title: 'Vendors', icon: 'shopping_shop', class: '' },
  { path: '/feedbacks', title: 'Feedbacks', icon: 'ui-2_chat-round', class: '' },
  { path: '', title: 'LOGOUT', icon: 'arrows-1_share-66', class: 'active active-pro' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(
    public authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

  isMobileMenu() {
    return window.innerWidth <= 991;
  }

  // Call the AuthService logout method and navigate to login page
  logout() {
    this.authService.logout();
    this.router.navigate(['/index']);
  }
}
