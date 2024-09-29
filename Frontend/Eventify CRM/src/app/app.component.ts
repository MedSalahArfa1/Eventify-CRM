import { Component, OnInit, Inject, Renderer2, ElementRef, ViewChild } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { DOCUMENT } from '@angular/common';
import { Location } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { filter, Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  private _router: Subscription;
  @ViewChild(NavbarComponent) navbar: NavbarComponent;
  showNavbar: boolean = true;

  constructor(
    private renderer: Renderer2,
    private router: Router,
    @Inject(DOCUMENT) private document: any,
    private element: ElementRef,
    public location: Location
  ) {}

  ngOnInit() {
    var navbar: HTMLElement = this.element.nativeElement.children[0].children[0];
    this._router = this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      if (window.outerWidth > 991) {
        window.document.children[0].scrollTop = 0;
      } else {
        window.document.activeElement.scrollTop = 0;
      }
      this.navbar.sidebarClose();

      // Check the current route to determine if the navbar should be shown
      this.showNavbar = this.location.path() !== '/dashboard';

      this.renderer.listen('window', 'scroll', (event) => {
        const number = window.scrollY;
        var _location = this.location.path();
        _location = _location.split('/')[2];

        if (number > 150 || window.pageYOffset > 150) {
          navbar.classList.remove('navbar-transparent');
        } else if (_location !== 'login' && this.location.path() !== '/nucleoicons') {
          // remove logic
          navbar.classList.add('navbar-transparent');
        }
      });
    });
  }
}
