import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'design_app', class: '' },
    { path: '/payments', title: 'Payments',  icon:'shopping_credit-card', class: '' },
    { path: '/events', title: 'Events',  icon:'objects_spaceship', class: '' },
    { path: '/tasks', title: 'Tasks',  icon:'design_bullet-list-67', class: '' },
    { path: '/vendor', title: 'Vendor Management',  icon:'shopping_shop', class: '' },
    { path: '/logout', title: 'LOGOUT',  icon:'arrows-1_share-66', class: 'active active-pro' }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  };
}
