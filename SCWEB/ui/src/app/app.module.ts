import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import {APP_BASE_HREF} from '@angular/common';

// used to create fake backend

import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AuthGuard } from './_guards/index';
import { AuthenticationService, UserService } from './_services/index';
import { LoginComponent } from './login/index';
import { HomeComponent } from './home/index';



import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";
import { StoreFrontComponent } from "./store-front/store-front.component";
import { ProductsDataService } from "./_services/products.service";
import { ShoppingCartService } from "./_services/shopping-cart.service";
import { LocalStorageServie, StorageService } from "./_services/storage.service";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        ShoppingCartComponent,
        StoreFrontComponent
       
    ],
    providers: [
        AuthGuard,
        AuthenticationService,
        UserService,
        ProductsDataService,
        ShoppingCartService,
        
        LocalStorageServie,
        { provide: StorageService, useClass: LocalStorageServie },
        {
          deps: [StorageService, ProductsDataService],
          provide: ShoppingCartService,
          useClass: ShoppingCartService
        },
        
        
        {provide: APP_BASE_HREF, useValue : '/' },

        MockBackend,
        BaseRequestOptions
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
