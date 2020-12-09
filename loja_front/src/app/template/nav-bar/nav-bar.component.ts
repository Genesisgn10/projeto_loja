import { Router } from '@angular/router';
import { AuthService } from './../../seguranca/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  usuario: string = '';
  
  constructor(public service: AuthService) { 
    this.usuario = service.jwtPayload ? service.jwtPayload.user_name : '';    
  }

  ngOnInit(): void {
  }

  logout() {
    this.service.logout();      
  }
}
