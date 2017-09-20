import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { Product } from "app/_models/product.model";
import "rxjs/add/operator/map";
import { Observable } from "rxjs/Observable";
import { CachcingServiceBase } from "./caching.service";

let count = 0;

@Injectable()
export class ProductsDataService extends CachcingServiceBase {
  private products: Observable<Product[]>;

  public constructor(private http: Http) {
    super();
  }

  public all(): Observable<Product[]> {
    return this.cache<Product[]>(() => this.products,
                                 (val: Observable<Product[]>) => this.products = val,
                                 () => this.http
                                 .get("/api/products")
                                           .map((response) => response.json().productList
                                                                      .map((item) => {
                                                                        console.log(item)
                                                                        let model = new Product();
                                                                        model.updateFrom(item);
                                                                        console.log(item)
                                                                        return model;
                                                                      })));

  }
}
