import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { NouisliderModule } from 'ng2-nouislider';
import { JwBootstrapSwitchNg2Module } from 'jw-bootstrap-switch-ng2';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { ComponentsComponent } from './components.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { EventModalComponent } from './event-modal/event-modal.component';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
        NouisliderModule,
        RouterModule,
        JwBootstrapSwitchNg2Module
      ],
    declarations: [
        ComponentsComponent,
        SidebarComponent,
        NavbarComponent,
        EventModalComponent,
    ],
    exports:[ 
        ComponentsComponent,
        SidebarComponent,
        NavbarComponent,
        EventModalComponent,
    ]
})
export class ComponentsModule { }
