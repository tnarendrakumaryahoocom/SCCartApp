﻿import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

import { AuthenticationService } from './index';
import { User } from '../_models/index';

@Injectable()
export class UserService {
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService) {
    }

    getUsers(): Observable<User[]> {
        
        let headers = new Headers({ 'jw_token':  this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });

        
        return this.http.get('/api/users', options)
            .map((response: Response) => response.json());
    }
}