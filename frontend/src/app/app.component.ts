import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { AuthenticationService } from './authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private authenticationService: AuthenticationService
  ) {}
  ngOnInit(): void {}

  ngAfterViewInit() {
    if (this.sidenav) {
      this.breakpointObserver
        .observe(['(max-width: 800px)'])
        .subscribe((res) => {
          if (res.matches) {
            this.sidenav.mode = 'over';
            this.sidenav.close();
          } else {
            this.sidenav.mode = 'side';
          }
        });
    }
  }

  public userIsAuthenticated(): boolean {
    return this.authenticationService.userIsAuthenticated();
  }
}
