﻿import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    public token: string;
     
    constructor(private http: Http) {
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    login(username: string, password: string): Observable<boolean> { 
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post('/login', JSON.stringify({ name: username, password: password }),{
            headers: headers
          })
            .map((response: Response) => {
                console.log(response);
               
                let token = response.json() && response.json().token;
                
                if (token) {
                    
                    this.token = token;

                    
                    localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

                    
                    return true;
                } else {
                    
                    return false;
                }
            });
    }

    logout(): void {
        
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}