import { HttpInterceptorFn } from '@angular/common/http';
import {AuthService} from "../services/auth.service";
import {inject} from "@angular/core";


export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {
  let authService  : AuthService = inject(AuthService);
  let newRequest = req.clone({
    headers: req.headers.set('Authorization','Bearer '+authService.accessToken)
  });
  return next(newRequest);
};
